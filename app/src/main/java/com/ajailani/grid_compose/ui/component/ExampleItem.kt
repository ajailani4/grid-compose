package com.ajailani.grid_compose.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ExampleItem(
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
        Text(
            modifier = Modifier.padding(start = 10.dp),
            text = description
        )
    }
}