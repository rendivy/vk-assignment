package com.keyinc.vk_assignment.domain.paginator

interface Paginator<Key, Item> {

    suspend fun loadNextItems()

    fun reset()

}