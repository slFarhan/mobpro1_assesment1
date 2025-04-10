package com.farhansolih0009.assesment1.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.farhansolih0009.assesment1.R
import com.farhansolih0009.assesment1.navigation.Screen
import androidx.compose.ui.layout.ContentScale

@Composable
fun MainScreen(navController: NavController, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.mipmap.logo),
            contentDescription = stringResource(R.string.logo_desc),
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .size(400.dp)
                .padding(bottom = 4.dp)
        )


        Button(
            onClick = { navController.navigate(Screen.WeightConverter.route) },
            modifier = Modifier.fillMaxWidth(0.8f)
        ) {
            Text(stringResource(R.string.weight_converter))
        }

        Spacer(modifier = Modifier.height(16.dp))


        Button(
            onClick = { navController.navigate(Screen.LengthConverter.route) },
            modifier = Modifier.fillMaxWidth(0.8f)
        ) {
            Text(stringResource(R.string.length_converter))
        }
    }
}
