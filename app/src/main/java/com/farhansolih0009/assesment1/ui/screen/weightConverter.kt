package com.farhansolih0009.assesment1.ui.screen

import android.content.Context
import android.content.Intent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.farhansolih0009.assesment1.R
import com.farhansolih0009.assesment1.navigation.Screen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WeightConverterScreen(navController: NavController) {
    val context = LocalContext.current
    val weightUnits = listOf("Gram", "Kilogram", "Miligram")
    var inputValue by remember { mutableStateOf("") }
    var fromUnit by remember { mutableStateOf(weightUnits[0]) }
    var toUnit by remember { mutableStateOf(weightUnits[1]) }
    var result by remember { mutableStateOf("") }
    var inputError by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(stringResource(R.string.weight_converter)) },
                navigationIcon = {
                    IconButton(onClick = { navController.navigate(Screen.Home.route) }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                },
                actions = {
                    IconButton(onClick = { navController.navigate("about") }) {
                        Icon(Icons.Default.Info, contentDescription = "About")
                    }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            DropdownWeight(weightUnits, fromUnit, stringResource(R.string.from_unit)) { fromUnit = it }

            OutlinedTextField(
                value = inputValue,
                onValueChange = { inputValue = it.filter { c -> c.isDigit() || c == '.' } },
                label = { Text(stringResource(R.string.enter_value)) },
                isError = inputError,
                trailingIcon = { if (inputError) Icon(Icons.Default.Warning, contentDescription = null) },
                supportingText = { if (inputError) Text(stringResource(R.string.invalid_input_warning)) },
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number, imeAction = ImeAction.Done),
                modifier = Modifier.fillMaxWidth()
            )

            DropdownWeight(weightUnits, toUnit, stringResource(R.string.to_unit)) { toUnit = it }

            Button(onClick = {
                inputError = inputValue.isBlank()
                if (!inputError) {
                    result = convertWeight(inputValue.toDouble(), fromUnit, toUnit).toString()
                }
            }) {
                Text(stringResource(R.string.caonvert))
            }

            if (result.isNotEmpty()) {
                OutlinedTextField(
                    value = result,
                    onValueChange = {},
                    readOnly = true,
                    label = { Text(stringResource(R.string.result_label)) },
                    modifier = Modifier.fillMaxWidth()
                )

                Button(
                    onClick = {
                        shareData(
                            context = context,
                            message = context.getString(
                                R.string.share_weight_template,
                                inputValue,
                                fromUnit,
                                result,
                                toUnit
                            )
                        )
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(stringResource(R.string.share_button))
                }
            }
        }
    }
}

fun convertWeight(value: Double, from: String, to: String): Double {
    val inKg = when (from) {
        "Gram" -> value / 1000
        "Kilogram" -> value
        "Miligram" -> value / 1_000_000
        else -> value
    }
    return when (to) {
        "Gram" -> inKg * 1000
        "Kilogram" -> inKg
        "Miligram" -> inKg * 1_000_000
        else -> inKg
    }
}

private fun shareData(context: Context, message: String) {
    val shareIntent = Intent(Intent.ACTION_SEND).apply {
        type = "text/plain"
        putExtra(Intent.EXTRA_TEXT, message)
    }
    if (shareIntent.resolveActivity(context.packageManager) != null) {
        context.startActivity(shareIntent)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DropdownWeight(
    items: List<String>,
    selectedItem: String,
    label: String,
    onItemSelected: (String) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = !expanded }
    ) {
        OutlinedTextField(
            value = selectedItem,
            onValueChange = {},
            label = { Text(label) },
            readOnly = true,
            modifier = Modifier
                .menuAnchor()
                .fillMaxWidth()
        )
        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            items.forEach { item ->
                DropdownMenuItem(
                    text = { Text(item) },
                    onClick = {
                        onItemSelected(item)
                        expanded = false
                    }
                )
            }
        }
    }
}
