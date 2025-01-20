package com.ajailani.grid_compose.ui.preview

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ajailani.grid_compose.component.VerticalGrid
import com.ajailani.grid_compose.util.GridCellType

@Composable
fun XItem(
    id: String,
    name: String,
    description: String
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Card {
            Column(modifier = Modifier.padding(10.dp)) {
                Text(text = "ID: $id")
                Spacer(modifier = Modifier.height(10.dp))
                Text(text = "Name: $name")
            }
        }
        Spacer(modifier = Modifier.height(10.dp))
        Text(text = description)
    }
}

data class XItemData(
    val id: String,
    val name: String,
    val description: String
)

@Preview(showBackground = true)
@Composable
private fun VerticalGridPreview() {
    val xItems = listOf(
        XItemData("1", "Satu", "test\ntest\ntest"),
        XItemData("2", "Dua", "test test test"),
        XItemData("3", "Tiga", "test test test"),
        XItemData("4", "Satu", "test\ntest\ntest"),
        XItemData("5", "Dua", "test\ntest\ntest\ntest"),
//        XItemData("6", "Dua", "test\ntest\ntest\ntest")
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
//            .verticalScroll(rememberScrollState())
    ) {
        Text(text = "Grid fixed")
        Spacer(modifier = Modifier.height(10.dp))
        VerticalGrid(
            columns = GridCellType.Fixed(3),
            verticalGap = 10.dp,
            horizontalGap = 5.dp
        ) {
            items(items = xItems) {
                XItem(it.id, it.name, it.description)
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
            items(items = xItems) {
                XItem(it.id, it.name, it.description)
            }
        }
    }
}