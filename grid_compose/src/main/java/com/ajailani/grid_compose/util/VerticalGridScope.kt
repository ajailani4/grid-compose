package com.ajailani.grid_compose.util

import androidx.compose.runtime.Composable

class VerticalGridScope {
    var itemSize = 0
    lateinit var itemContent: @Composable (index: Int) -> Unit

    fun items(count: Int, content: @Composable (index: Int) -> Unit) {
        itemSize = count
        itemContent = content
    }

    fun <T> items(items: List<T>, content: @Composable (T) -> Unit) {
        items(
            count = items.size,
            content = { content(items[it]) }
        )
    }
}