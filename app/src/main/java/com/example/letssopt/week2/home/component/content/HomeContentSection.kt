package com.example.letssopt.week2.home.component.content

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.collections.immutable.ImmutableList
import com.example.letssopt.R

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

@Preview(showBackground = true)
@Composable
fun HomeContentSectionPreview() {
    HomeContentSection(
        contents = kotlinx.collections.immutable.persistentListOf(
            R.drawable.img_content1,
            R.drawable.img_content2,
            R.drawable.img_content3,
            R.drawable.img_content1,
            R.drawable.img_content2
        )
    )
}