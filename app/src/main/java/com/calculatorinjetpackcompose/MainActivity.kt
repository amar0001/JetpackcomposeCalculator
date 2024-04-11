package com.calculatorinjetpackcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.calculatorinjetpackcompose.ui.theme.CalculatorInJetpackComposeTheme
import com.calculatorinjetpackcompose.CalculationsScreen

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            CalculatorInJetpackComposeTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    CalculationsScreen(modifier = Modifier.wrapContentWidth())
                    { _calculatorValue, _resultsValue ->
                        // TODO: do your stuff
                    }
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun MainActivityPreview() {
    CalculatorInJetpackComposeTheme {
        CalculationsScreen(
            Modifier
                .fillMaxWidth()
        )
        { _, _ ->
        }
    }
}


