package com.ajailani.grid_compose.ui.preview

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
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
        ExampleItemData("1", "One", "Lorem ipsum dolor sit amet"),
        ExampleItemData("2", "Two", "Lorem ipsum dolor sit amet, consectetur adipiscing elit"),
        ExampleItemData("3", "Three", "Lorem ipsum dolor sit amet"),
        ExampleItemData("4", "Four", "Lorem ipsum dolor sit amet, consectetur"),
        ExampleItemData("5", "Five", "Lorem ipsum dolor sit amet"),
        ExampleItemData("6", "Six", "Lorem ipsum dolor sit amet"),
        ExampleItemData("7", "Seven", "Lorem ipsum dolor sit amet")
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        Text(
            text = "Grid fixed",
            style = MaterialTheme.typography.titleLarge
        )
        Spacer(modifier = Modifier.height(10.dp))
        VerticalGrid(
            columns = GridCellType.Fixed(3),
            verticalGap = 20.dp,
            horizontalGap = 10.dp
        ) {
            items(items = exampleItems) {
                ExampleItem(it.id, it.name, it.description)
            }
        }
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text = "Grid adaptive",
            style = MaterialTheme.typography.titleLarge
        )
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