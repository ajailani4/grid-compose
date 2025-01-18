package com.ajailani.grid_compose.util

import androidx.compose.ui.unit.Dp

sealed class GridCellType {
    data class Fixed(val count: Int) : GridCellType()
    data class Adaptive(val minSize: Dp) : GridCellType()
}