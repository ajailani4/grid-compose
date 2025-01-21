package com.ajailani.grid_compose.component

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.ajailani.grid_compose.base.Grid
import com.ajailani.grid_compose.util.GridCellType
import com.ajailani.grid_compose.util.GridOrientation
import com.ajailani.grid_compose.util.GridScope

@Composable
fun VerticalGrid(
    modifier: Modifier = Modifier,
    columns: GridCellType,
    verticalGap: Dp = 0.dp,
    horizontalGap: Dp = 0.dp,
    content: GridScope.() -> Unit
) {
    Grid(
        modifier = modifier,
        orientation = GridOrientation.VERTICAL,
        slots = columns,
        verticalGap = verticalGap,
        horizontalGap = horizontalGap,
        content = content
    )
}