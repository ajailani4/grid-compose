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
fun rememberHorizontalGridMeasurePolicy(
    gridScope: GridScope,
    rows: GridCellType,
    verticalGap: Dp = 0.dp,
    horizontalGap: Dp = 0.dp
) = remember(gridScope, rows, verticalGap, horizontalGap) {
    MeasurePolicy { measurables, constraints ->
        val verticalGapPx = verticalGap.roundToPx()
        val horizontalGapPx = horizontalGap.roundToPx()

        val rowCount = when (rows) {
            is GridCellType.Fixed -> rows.count
            is GridCellType.Adaptive -> constraints.maxHeight / rows.minSize.roundToPx()
        }.coerceAtLeast(1)

        val totalColumn = ceil(gridScope.itemCount.toDouble() / rowCount.toDouble()).toInt()
        val totalHorizontalGapSize = horizontalGapPx * (rowCount - 1)
        val totalVerticalGapSize = (verticalGapPx * (totalColumn - 1))
        val placeables = measurables.map {
            it.measure(
                Constraints.fixedHeight((constraints.maxHeight - totalVerticalGapSize) / rowCount)
            )
        }
        val maxColumnHeights = measureMaxColumnHeights(
            placeables = placeables,
            rowCount = rowCount
        )

        layout(
            width = measureLayoutWidth(
                maxColumnHeights = maxColumnHeights,
                totalHorizontalGapSize = totalHorizontalGapSize
            ),
            height = constraints.maxHeight
        ) {
            var xPosition = 0
            var yPosition = 0
            var column = 0

            placeables.forEachIndexed { index, placeable ->
                placeable.placeRelative(xPosition, yPosition)

                if ((index + 1) % rowCount == 0) {
                    yPosition = 0
                    xPosition += maxColumnHeights[column] + horizontalGapPx
                    column = column.inc()
                } else yPosition += placeable.height + verticalGapPx
            }
        }
    }
}

private fun measureMaxColumnHeights(
    placeables: List<Placeable>,
    rowCount: Int
): List<Int> {
    val result = mutableListOf<Int>()
    var maxWidthInColumn = 0

    placeables.forEachIndexed { index, placeable ->
        if (placeable.width > maxWidthInColumn) maxWidthInColumn = placeable.width

        if ((index + 1) % rowCount == 0 || index == placeables.lastIndex) {
            result.add(maxWidthInColumn)
            maxWidthInColumn = 0
        }
    }

    return result.toList()
}

private fun measureLayoutWidth(
    maxColumnHeights: List<Int>,
    totalHorizontalGapSize: Int
) = if (totalHorizontalGapSize >= 0) maxColumnHeights.sum() + totalHorizontalGapSize else 0