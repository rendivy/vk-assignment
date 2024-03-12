package com.keyinc.vk_assignment.presentation.viewmodel


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.keyinc.vk_assignment.data.pagination.PaginatorImpl
import com.keyinc.vk_assignment.di.IoDispatcher
import com.keyinc.vk_assignment.domain.usecase.GetProductListUseCase
import com.keyinc.vk_assignment.presentation.ui.state.ProductPaginationState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException
import javax.inject.Inject


@HiltViewModel
class ProductViewModel @Inject constructor(
    private val getProductListUseCase: GetProductListUseCase,
    @IoDispatcher private val dispatcherIO: CoroutineDispatcher
) : ViewModel() {


    private val _productPaginationState = MutableStateFlow(ProductPaginationState())
    val productPaginationState = _productPaginationState.asStateFlow()

    private val coroutineExceptionHandler = CoroutineExceptionHandler { _, exception ->
        _productPaginationState.value = when (exception) {
            is HttpException -> ProductPaginationState(errorMessage = exception.message)
            else -> ProductPaginationState(errorMessage = exception.message)
        }
    }

    init {
        loadNextProducts()
    }


    private val paginator = PaginatorImpl(
        initialKey = _productPaginationState.value.currentPage,
        onLoadUpdated = {
            _productPaginationState.value = _productPaginationState.value.copy(isLoading = it)
        },
        onRequest = { nextPage ->
            getProductListUseCase.invoke(nextPage, 20)
        },
        getNextKey = {
            _productPaginationState.value.currentPage + 20
        },
        onError = {
            _productPaginationState.value =
                _productPaginationState.value.copy(errorMessage = it?.message)
        },
        onSuccess = { newItems, newPage ->
            _productPaginationState.value = _productPaginationState.value.copy(
                items = _productPaginationState.value.items + newItems,
                currentPage = newPage,
                isEndReached = newItems.isEmpty()
            )
        }
    )


    fun loadNextProducts() {
        viewModelScope.launch(dispatcherIO + coroutineExceptionHandler) {
            _productPaginationState.value.errorMessage = null
            paginator.loadNextItems()
        }
    }

}