package com.ajailani.grid_compose.base

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.ajailani.grid_compose.util.GridCellType
import com.ajailani.grid_compose.util.GridOrientation
import com.ajailani.grid_compose.util.GridScope
import com.ajailani.grid_compose.util.rememberHorizontalGridMeasurePolicy
import com.ajailani.grid_compose.util.rememberVerticalGridMeasurePolicy

@Composable
internal fun Grid(
    modifier: Modifier = Modifier,
    orientation: GridOrientation,
    slots: GridCellType,
    verticalGap: Dp = 0.dp,
    horizontalGap: Dp = 0.dp,
    content: GridScope.() -> Unit
) {
    val gridScope = remember { GridScope() }
    val measurePolicy = if (orientation == GridOrientation.VERTICAL) {
        rememberVerticalGridMeasurePolicy(
            gridScope = gridScope,
            columns = slots,
            verticalGap = verticalGap,
            horizontalGap = horizontalGap
        )
    } else {
        rememberHorizontalGridMeasurePolicy(
            gridScope = gridScope,
            rows = slots,
            verticalGap = verticalGap,
            horizontalGap = horizontalGap
        )
    }

    Layout(
        modifier = modifier,
        content = {
            gridScope.content()

            repeat(gridScope.itemCount) {
                gridScope.itemContent(it)
            }
        },
        measurePolicy = measurePolicy
    )
}