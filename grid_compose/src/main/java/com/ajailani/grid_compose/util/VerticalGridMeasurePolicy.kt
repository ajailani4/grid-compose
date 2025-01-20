package com.ajailani.grid_compose.util

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.layout.MeasurePolicy
import androidx.compose.ui.layout.Placeable
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kotlin.math.ceil

@Composable
fun rememberVerticalGridMeasurePolicy(
    gridScope: GridScope,
    columns: GridCellType,
    verticalGap: Dp = 0.dp,
    horizontalGap: Dp = 0.dp
) = remember(gridScope, columns, verticalGap, horizontalGap) {
    MeasurePolicy { measurables, constraints ->
        val verticalGapPx = verticalGap.roundToPx()
        val horizontalGapPx = horizontalGap.roundToPx()

        val columnCount = when (columns) {
            is GridCellType.Fixed -> columns.count
            is GridCellType.Adaptive -> constraints.maxWidth / columns.minSize.roundToPx()
        }.coerceAtLeast(1)

        val totalRow = ceil(gridScope.itemCount.toDouble() / columnCount.toDouble()).toInt()
        val totalHorizontalGapSize = horizontalGapPx * (columnCount - 1)
        val totalVerticalGapSize = verticalGapPx * (totalRow - 1)
        val placeables = measurables.map {
            it.measure(
                Constraints.fixedWidth((constraints.maxWidth - totalHorizontalGapSize) / columnCount)
            )
        }
        val maxRowHeights = measureMaxRowHeights(
            placeables = placeables,
            columnCount = columnCount
        )

        layout(
            width = constraints.maxWidth,
            height = measureLayoutHeight(
                maxRowHeights = maxRowHeights,
                totalVerticalGapSize = totalVerticalGapSize
            )
        ) {
            var xPosition = 0
            var yPosition = 0
            var row = 0

            placeables.forEachIndexed { index, placeable ->
                placeable.placeRelative(xPosition, yPosition)

                if ((index + 1) % columnCount == 0) {
                    xPosition = 0
                    yPosition += maxRowHeights[row] + verticalGapPx
                    row = row.inc()
                } else xPosition += placeable.width + horizontalGapPx
            }
        }
    }
}

private fun measureMaxRowHeights(
    placeables: List<Placeable>,
    columnCount: Int
): List<Int> {
    val result = mutableListOf<Int>()
    var maxHeightInRow = 0

    placeables.forEachIndexed { index, placeable ->
        if (placeable.height > maxHeightInRow) maxHeightInRow = placeable.height

        if ((index + 1) % columnCount == 0 || index == placeables.lastIndex) {
            result.add(maxHeightInRow)
            maxHeightInRow = 0
        }
    }

    return result.toList()
}

private fun measureLayoutHeight(
    maxRowHeights: List<Int>,
    totalVerticalGapSize: Int
) = if (totalVerticalGapSize >= 0) maxRowHeights.sum() + totalVerticalGapSize else 0