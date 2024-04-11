package com.calculatorinjetpackcompose.widgets

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.ParagraphIntrinsics
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.font.createFontFamilyResolver
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

private const val TEXT_SCALE_REDUCTION_INTERVAL = 0.9f

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AutoSizeTextField(
    modifier: Modifier = Modifier,
    inputValue: MutableState<String>,
    fontSize: TextUnit = 30.sp,
    lineHeight: TextUnit = 37.sp,
    inputValueChanged: (String) -> Unit,
) {

    var text by remember { mutableStateOf(TextFieldValue()) }

    Box() {
        BoxWithConstraints(modifier = modifier.fillMaxWidth()
            .background(
                Color.Transparent
        )) {
            var shrunkFontSize = fontSize
            val calculateIntrinsics = @Composable {
                ParagraphIntrinsics(
                    text = inputValue.value,
                    style = TextStyle(
                        fontSize = shrunkFontSize,
                        fontWeight = FontWeight.SemiBold,
                        lineHeight = lineHeight,
                        textAlign = TextAlign.End
                    ),
                    density = LocalDensity.current,
                    fontFamilyResolver = createFontFamilyResolver(LocalContext.current)
                )
            }

            var intrinsics = calculateIntrinsics()
            with(LocalDensity.current) {
                // TextField and OutlinedText field have default horizontal padding of 16.dp
                val textFieldDefaultHorizontalPadding = 16.dp.toPx()
                val maxInputWidth = maxWidth.toPx() - 2 * textFieldDefaultHorizontalPadding

                while (intrinsics.maxIntrinsicWidth > maxInputWidth) {
                    shrunkFontSize *= TEXT_SCALE_REDUCTION_INTERVAL
                    intrinsics = calculateIntrinsics()
                }
            }

            TextField(
                readOnly = true,
                modifier = Modifier.fillMaxWidth().background(Color.Transparent),
                value = inputValue.value,
                onValueChange = {
                    inputValue.value = it
                    Log.e("TAG", "onValueChange$it")
                    inputValueChanged( inputValue.value)
                                },
                textStyle = TextStyle(
                    fontSize = shrunkFontSize,
                    fontWeight = FontWeight.SemiBold,
                    lineHeight = lineHeight,
                    textAlign = TextAlign.End
                ),
                singleLine = true,
                colors = TextFieldDefaults.textFieldColors(
                    textColor = Color.Gray,
                    disabledTextColor = Color.Transparent,
                    containerColor = Color.White,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent
                )
            )


        }

    }
}
