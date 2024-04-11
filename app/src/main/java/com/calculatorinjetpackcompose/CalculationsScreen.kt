package com.calculatorinjetpackcompose

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.calculatorinjetpackcompose.utils.formatNumberString
import com.calculatorinjetpackcompose.utils.pressedOperators
import com.calculatorinjetpackcompose.widgets.AutoSizeTextField

@Composable
fun CalculationsScreen(
    modifier: Modifier,
    onValuesChanged: (calculatorValue: String, resultsValue: String) -> Unit
) {
    val tvResults = remember { mutableStateOf("") }
    val tvCalculator = remember { mutableStateOf("") }

    Column(
        modifier = Modifier.fillMaxHeight(),
        verticalArrangement = Arrangement.Bottom
    ) {

        AutoSizeTextField(inputValue = tvCalculator) {}
        AutoSizeTextField(inputValue = tvResults) {}
        CalculatorKeyboard(
            onKeyPressed = {
                onButtonClick(it, tvCalculator, tvResults)
                onValuesChanged(tvCalculator.value, tvResults.value)
                val enteredString = tvCalculator.value
                val char = pressedOperators(enteredString)
                if (char.isNotEmpty())
                    updateResult(enteredString, char, tvResults)
            },

            onBackspacePressed = {
                if (tvCalculator.value.isNotEmpty()) {
                    // Remove the last character from the input text
                    tvCalculator.value =
                        tvCalculator.value.substring(0, tvCalculator.value.length - 1)
                    // Update the display
                    updateResult(tvCalculator.value.toString(), "", tvResults)
                    onValuesChanged(tvCalculator.value, tvResults.value)
                }

            },
            onClearPressed = {
                tvCalculator.value = ""
                tvResults.value = ""
                onValuesChanged(tvCalculator.value, tvResults.value)
            }
        )
    }

}

fun onButtonClick(
    buttonText: String,
    tvCalculator: MutableState<String>,
    tvResults: MutableState<String>
) {
    when (buttonText) {
        "+", "-", "*", "/" -> {
            tvCalculator.value = buildString {
                append(tvCalculator?.value)
                append(buttonText)
            }
        }

        "=" -> {
            val enteredString = tvCalculator.value.toString()
            val char = pressedOperators(enteredString)
            if (char.isNotEmpty())
                updateResult(enteredString, char, tvResults)
        }

        "AC" -> {
            tvCalculator.value = ""
            tvResults.value = ""
        }

        else -> {
            tvCalculator.value = buildString {
                append(tvCalculator!!.value.toString())
                append(buttonText)
            }
        }
    }
}

private fun updateResult(expression: String, operator: String, tvResults: MutableState<String>) {
    val operators = expression.replace(Regex("[0-9]"), "").toCharArray()
    val numbers = expression.split(Regex("(?=[*+/\\-])|(?<=[*+/\\-])"))
    val operands = numbers.filter { it.toDoubleOrNull() != null }
    var operation = if (operands.isNotEmpty()) operands[0].toDouble() else 0.0
    for (i in 1 until operands.size) {
        if (operands[i].isEmpty()) {
            return
        }
        when (operators[i - 1].toString()) {
            "+" -> operation += operands[i].toIntOrNull() ?: 0
            "-" -> operation -= operands[i].toDouble()
            "*" -> operation *= operands[i].toDouble()
            "/" -> operation /= operands[i].toDouble()
            "÷" -> {
                if (operands[i].toDouble() == 0.0) {
                    throw ArithmeticException("Division by zero is not allowed")
                }
                operation /= operands[i].toDouble()
            }
        }
    }
    tvResults.value = formatNumberString(operation.toString())
}


