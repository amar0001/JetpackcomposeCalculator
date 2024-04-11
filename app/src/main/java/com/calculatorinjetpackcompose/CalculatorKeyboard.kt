package com.calculatorinjetpackcompose

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CalculatorKeyboard(
    onKeyPressed: (String) -> Unit,
    onBackspacePressed: () -> Unit,
    onClearPressed: () -> Unit
) {
    val modifier = Modifier.fillMaxWidth()
    val color = Color.Transparent
    Column(
        modifier = Modifier
            .wrapContentWidth()
            .wrapContentHeight()
    ) {
        Row(modifier = modifier) {
        CalculatorButton(
            text = "AC",
            onClick = { onClearPressed() },
            modifier.weight(1.0f),
            color
        )

           CalculatorButton(
                text = "%",
                onClick = { onKeyPressed("%") },
                modifier.weight(1.0f),
               color
            )
            CalculatorButton(text = "÷", onClick = { onKeyPressed("/") },
                modifier.weight(1.0f),color)

            CalculatorImageButton(onClick = { onBackspacePressed()}, modifier.weight(1.0f))
        }

        // Numbers 7 to 9
        Row(modifier = modifier) {
            for (i in 7..9) {
                CalculatorButton(
                    text = "$i",
                    onClick = { onKeyPressed("$i") },
                    modifier.weight(1.0f)
                )
            }
            CalculatorButton(text = "*", onClick = { onKeyPressed("*") },
                modifier.weight(1.0f), color)

        }
        // Numbers 4 to 6
        Row(modifier = Modifier.fillMaxWidth()) {
            for (i in 4..6) {
                CalculatorButton(
                    text = "$i",
                    onClick = { onKeyPressed("$i") },
                    modifier.weight(1.0f)
                )
            }
            CalculatorButton(text = "-", onClick = { onKeyPressed("-") },
                modifier.weight(1.0f), color)

        }


        Row(modifier = Modifier.fillMaxWidth()) {
            for (i in 1..3) {
                CalculatorButton(
                    text = "$i",
                    onClick = { onKeyPressed("$i") },
                    modifier.weight(1.0f)
                )
            }

            CalculatorButton(text = "+", onClick = { onKeyPressed("+") },
                modifier.weight(1.0f), color)
        }

        Row(modifier = Modifier.fillMaxWidth()) {
            CalculatorButton(text = "0", onClick = { onKeyPressed("0") }, modifier.weight(1.0f))
            CalculatorButton(text = "00", onClick = { onKeyPressed("00") }, modifier.weight(1.0f),
                color)
            CalculatorButton(text = ".", onClick = { onKeyPressed(".") }, modifier.weight(1.0f))


            CalculatorButton(
                text = "=",
                onClick = { onKeyPressed("=") },
                modifier.weight(1.0f),
                color
            )
        }
    }
}

@Composable
fun CalculatorButton(text: String, onClick: () -> Unit, modifier: Modifier) {
    CalculatorButton(text, onClick, modifier, Color.Transparent)
}

@Composable
fun CalculatorButton(text: String, onClick: () -> Unit, modifier: Modifier, color: Color) {
    Box(
        modifier = modifier
            .wrapContentHeight()
            .padding(4.dp)
            .background(color = color)
            .padding(4.dp)
    )
    {
        Button(
            onClick = onClick,
            modifier = modifier,
            colors = ButtonDefaults.buttonColors(Color.Transparent)
        ) {
            Text(
                text = text,
                fontSize = 16.sp,
                color = Color.Black,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                softWrap = false
            )
        }
    }
}


@Composable
fun CalculatorImageButton(onClick: () -> Unit, modifier: Modifier) {
    Box(
        modifier = modifier.clickable{onClick()}
            .wrapContentHeight()
            .padding(4.dp)
            .background(color = Color.Transparent)
            .padding(5.dp)
    )
    {
        Image(

            painter = painterResource(id = R.drawable.backspace),
            contentDescription = "Backspace",
            modifier = Modifier.align(Alignment.Center)

        )
    }
}


