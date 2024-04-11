package com.calculatorinjetpackcompose.utils

public fun evaluateExpression(expression: String): Double {
    // Split the expression into operands and operators
    val parts = expression.split(Regex("(?=[*+/\\-])|(?<=[*+/\\-])"))

    // Initialize the result to the first operand
    var result = parts[0].toDouble()

    // Iterate over the rest of the parts
    for (i in 1 until parts.size step 2) {
        val operator = parts[i]
        val operand = parts[i + 1].toDouble()
        when (operator) {
            "+" -> result += operand
            "-" -> result -= operand
            "*" -> result *= operand
            "/" -> result /= operand
        }
    }

    return result
}

fun containsMultipleOperators(inputString: String): Boolean {
    var operatorCount = 0
    for (char in inputString) {
        if (char == '+' || char == '-' || char == '*' || char == '/') {
            operatorCount++
            if (operatorCount > 1) {
                return true
            }
        }
    }
    return false
}

fun pressedOperators(inputString: String): String {

    val elements = inputString.split(Regex("(?=[*+/\\-])|(?<=[*+/\\-])")).filter { it.isNotEmpty() }

    // Iterate through the elements in reverse order to find the last operator
    for (i in elements.size - 1 downTo 0) {
        val element = elements[i]
        if (element.length == 1 && "+-*/".contains(element)) {
            return element.single().toString()
        }
    }
    return ""
}



fun formatNumberString(numberString: String): String {
    val number = numberString.toDouble()
    return if (number % 1 == 0.0) {
        number.toInt().toString()
    } else {
        numberString
    }
}
 fun solveExpression(expression: String): Double {
    // Split the expression into operands and operators
    val parts = expression.split(Regex("(?=[*+/\\-])|(?<=[*+/\\-])"))

    // Separate lists for operands and operators
    val operands = mutableListOf<Double>()
    val operators = mutableListOf<String>()

    // Populate the lists
    for (part in parts) {
        if(part.isNotEmpty())
        if (part.matches(Regex("[*+/\\-]"))) {
            operators.add(part)
        } else {
            operands.add(part.toDouble())
        }
    }

    // Perform calculations according to BODMAS rule
    var result = operands[0]
    var index = 0
    for (i in 1 until operands.size) {
        when (operators[index]) {
            "*" -> result *= operands[i]
            "/" -> result /= operands[i]
            "+" -> result += operands[i]
            "-" -> result -= operands[i]
        }
        index++
    }

    return result
}
