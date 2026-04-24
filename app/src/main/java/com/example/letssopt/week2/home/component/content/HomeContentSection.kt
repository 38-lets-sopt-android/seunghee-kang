package com.example.letssopt.week2.home.component.content

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp

@Composable
fun HomeContentSection(contents: List<Int>) {
    Column {
        LazyRow(
            contentPadding = PaddingValues(horizontal = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(contents) { imageRes ->
                HomeContentItem(imageRes = imageRes)
            }
        }
    }
}