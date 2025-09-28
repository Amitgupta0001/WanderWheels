package com.example.wanderwheels.ui.screens.explore

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.wanderwheels.ui.components.CaravanCard
import com.example.wanderwheels.viewmodel.CaravanViewModel

@Composable
fun ExploreScreen(
    navController: NavController,
    viewModel: CaravanViewModel = hiltViewModel()
) {
    var selectedTab by remember { mutableStateOf(0) }
    val caravans by viewModel.caravans
    val isLoading by viewModel.isLoading

    Column(modifier = Modifier.fillMaxSize()) {
        // Tab Layout
        TabRow(selectedTabIndex = selectedTab) {
            listOf("Homes", "Experiences", "Services").forEachIndexed { index, title ->
                Tab(
                    selected = selectedTab == index,
                    onClick = { selectedTab = index },
                    text = { Text(text = title) }
                )
            }
        }

        when (selectedTab) {
            0 -> HomesTab(navController, caravans, isLoading)
            1 -> ExperiencesTab()
            2 -> ServicesTab()
        }
    }
}

@Composable
fun HomesTab(
    navController: NavController,
    caravans: List<com.example.wanderwheels.data.models.Caravan>,
    isLoading: Boolean
) {
    if (isLoading) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = androidx.compose.foundation.layout.Arrangement.Center
        ) {
            CircularProgressIndicator()
            Text("Loading caravans...", modifier = Modifier.padding(16.dp))
        }
    } else if (caravans.isEmpty()) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = androidx.compose.foundation.layout.Arrangement.Center
        ) {
            Text("No caravans available", modifier = Modifier.padding(16.dp))
        }
    } else {
        LazyColumn(modifier = Modifier.padding(16.dp)) {
            items(caravans) { caravan ->
                CaravanCard(
                    caravan = caravan,
                    onCardClick = {
                        navController.navigate("details/${caravan.id}")
                    }
                )
            }
        }
    }
}

@Composable
fun ExperiencesTab() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = androidx.compose.foundation.layout.Arrangement.Center
    ) {
        Text("Experiences coming soon!")
    }
}

@Composable
fun ServicesTab() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = androidx.compose.foundation.layout.Arrangement.Center
    ) {
        Text("Services coming soon!")
    }
}