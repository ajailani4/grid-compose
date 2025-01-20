package com.ajailani.grid_compose.ui.preview

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ajailani.grid_compose.component.HorizontalGrid
import com.ajailani.grid_compose.model.ExampleItemData
import com.ajailani.grid_compose.ui.component.ExampleItem
import com.ajailani.grid_compose.util.GridCellType

@Preview(showBackground = true)
@Composable
private fun HorizontalGridPreview() {
    val exampleItems = listOf(
        ExampleItemData("1", "Satu satu satu satu satu satu", "test\ntest\ntest"),
        ExampleItemData("2", "Dua", "test test test"),
        ExampleItemData("3", "Tiga", "test test test"),
        ExampleItemData("4", "Empat empat empat empat", "test\ntest\ntest"),
        ExampleItemData("5", "Dua", "test\ntest\ntest\ntest"),
        ExampleItemData("6", "Dua", "test\ntest\ntest\ntest")
    )

    Column(modifier = Modifier.fillMaxWidth()) {
        Text(text = "Grid fixed")
        Spacer(modifier = Modifier.height(10.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(400.dp)
                .horizontalScroll(rememberScrollState())
        ) {
            HorizontalGrid(
                rows = GridCellType.Fixed(3),
                verticalGap = 10.dp,
                horizontalGap = 5.dp
            ) {
                items(exampleItems) {
                    ExampleItem(it.id, it.name, it.description)
                }
            }
        }
    }
}