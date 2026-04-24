package com.example.letssopt.week2.home.component.party

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.letssopt.week2.home.PartyModel

@Composable
fun HomePartySection(parties: List<PartyModel>) {
    Column {
        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            contentPadding = PaddingValues(horizontal = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(parties) { party ->
                HomePartyItem(party = party)
            }
        }
    }
}