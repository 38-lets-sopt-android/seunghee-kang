package com.example.letssopt.week2.home.component.party

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.letssopt.week2.home.PartyModel
import kotlinx.collections.immutable.ImmutableList

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