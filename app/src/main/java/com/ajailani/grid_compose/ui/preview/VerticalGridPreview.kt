package com.ajailani.grid_compose.ui.preview

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ajailani.grid_compose.component.VerticalGrid
import com.ajailani.grid_compose.model.ExampleItemData
import com.ajailani.grid_compose.ui.component.ExampleItem
import com.ajailani.grid_compose.util.GridCellType

@Preview(showBackground = true)
@Composable
private fun VerticalGridPreview() {
    val exampleItems = listOf(
        ExampleItemData("1", "Satu", "test\ntest\ntest"),
        ExampleItemData("2", "Dua", "test test test"),
        ExampleItemData("3", "Tiga", "test test test"),
        ExampleItemData("4", "Empat", "test\ntest\ntest"),
        ExampleItemData("5", "Limat", "test\ntest\ntest\ntest"),
        ExampleItemData("6", "Enam", "test\ntest\ntest\ntest"),
        ExampleItemData("7", "Tujuh", "test\ntest\ntest\ntest")
    )

    Column(modifier = Modifier.fillMaxSize()) {
        Text(text = "Grid fixed")
        Spacer(modifier = Modifier.height(10.dp))
        VerticalGrid(
            columns = GridCellType.Fixed(3),
            verticalGap = 10.dp,
            horizontalGap = 5.dp
        ) {
            items(items = exampleItems) {
                ExampleItem(it.id, it.name, it.description)
            }
        }
        Spacer(modifier = Modifier.height(20.dp))
        Text(text = "Grid adaptive")
        Spacer(modifier = Modifier.height(10.dp))
        VerticalGrid(
            columns = GridCellType.Adaptive(150.dp),
            verticalGap = 10.dp,
            horizontalGap = 5.dp
        ) {
            items(items = exampleItems) {
                ExampleItem(it.id, it.name, it.description)
            }
        }
    }
}