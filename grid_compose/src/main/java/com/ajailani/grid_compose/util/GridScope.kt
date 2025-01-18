package com.ajailani.grid_compose.util

import androidx.compose.runtime.Composable

class GridScope {
    var itemCount = 0
        private set

    lateinit var itemContent: @Composable (index: Int) -> Unit

    fun items(count: Int, content: @Composable (index: Int) -> Unit) {
        itemCount = count
        itemContent = content
    }

    fun <T> items(items: List<T>, content: @Composable (T) -> Unit) {
        items(
            count = items.size,
            content = { content(items[it]) }
        )
    }
}