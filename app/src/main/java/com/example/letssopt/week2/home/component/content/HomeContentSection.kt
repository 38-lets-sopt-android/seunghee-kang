package com.example.letssopt.week2.home.component.content

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.collections.immutable.ImmutableList

@Composable
fun HomeContentSection(
    // ImmutableList로 변경
    contents: ImmutableList<Int>,
    modifier: Modifier = Modifier
) {
    // Column 제거 -> LazyRow 최상위 배치
    LazyRow(
        modifier = modifier,
        contentPadding = PaddingValues(horizontal = 8.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(
            items = contents,
            key = { imageRes -> imageRes }
        ) { imageRes ->
            HomeContentItem(imageRes = imageRes)
        }
    }
}