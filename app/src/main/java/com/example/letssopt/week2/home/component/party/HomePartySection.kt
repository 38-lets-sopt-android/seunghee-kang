package com.example.letssopt.week2.home.component.party

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.letssopt.week2.home.model.PartyModel
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf
import com.example.letssopt.R

@Composable
fun HomePartySection(
    // List 대신 ImmutableList 사용
    parties: ImmutableList<PartyModel>,
    modifier: Modifier = Modifier
) {
    LazyRow(
        modifier = modifier
            .fillMaxWidth()
            .padding(bottom = 16.dp),
        contentPadding = PaddingValues(horizontal = 8.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(
            items = parties,
            key = { party -> party.title }
        ) { party ->
            HomePartyItem(party = party)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomePartySectionPreview() {
    HomePartySection(
        parties = persistentListOf(
            PartyModel("오늘 21:13", "# 왕과 사는 남자", R.drawable.img_party1),
            PartyModel("내일 22:22", "# 파묘", R.drawable.img_party2)
        )
    )
}