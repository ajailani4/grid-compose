package com.ajailani.grid_compose.util

import androidx.compose.ui.unit.Dp

sealed class GridType {
    data class Fixed(val count: Int) : GridType()
    data class Adaptive(val minSize: Dp) : GridType()
}