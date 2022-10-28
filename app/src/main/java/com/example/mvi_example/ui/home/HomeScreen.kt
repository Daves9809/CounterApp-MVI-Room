package com.example.mvi_example.ui.home

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Divider
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.mvi_example.ui.home.state.HomeEvents
import com.tomcz.ellipse.common.collectAsState

@Composable
fun HomeScreen(viewModel: HomeViewModel = hiltViewModel()) {
    val processor = viewModel.processor
    //inicjalizacja w celu pobrania danych, na ten moment nie wiem w jaki inny sposob mozna to zrobic
    processor.sendEvent(HomeEvents.StartedApp)
    val count by processor.collectAsState { it.count }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Divider()
        Text(
            text = "Count: $count",
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            modifier = Modifier.padding(10.dp)
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Button(onClick = {
                processor.sendEvent(HomeEvents.IncreaseButtonClick)
            }) {
                Text(text = "Increase")
            }
            Button(onClick = {
                processor.sendEvent(HomeEvents.DecreaseButtonClick)
            }) {
                Text(text = "Decrease")
            }
        }
        OutlinedButton(
            onClick = { processor.sendEvent(HomeEvents.ResetCount) },
            modifier = Modifier.weight(1f, false)
        ) {
            Text(text = "Reset")
        }
    }
}