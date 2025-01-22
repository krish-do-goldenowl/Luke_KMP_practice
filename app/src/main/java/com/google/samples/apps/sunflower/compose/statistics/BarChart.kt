/*
 * Copyright 2025 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.samples.apps.sunflower.compose.statistics

import android.graphics.Paint
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun BarChart(
    modifier: Modifier = Modifier,
    xValues: List<Int>,
    yValues: List<Int>,
    points: List<Float>,
    interval: Int
) {

    Box(modifier.size(300.dp), contentAlignment = Alignment.Center) {

        val textPaint = Paint().apply {
            textSize = 8f
            color = Color.Black.toArgb()
        }
        Canvas(
            modifier = Modifier
                .padding(0.dp)
                .fillMaxSize()
        ) {

            val maxX = xValues.max()
            val xSpacing = size.width.div(maxX)

            val maxY = yValues.max()
            val ySpacing = size.height.div(maxY)

            for (index in 0..maxX step interval) {
                drawContext.canvas.nativeCanvas.drawText(
                    if (index == 0) "" else index.toString(),
                    xSpacing.times(index),
                    size.height,
                    textPaint
                )
            }

            for (index in 0..maxY step interval) {
                drawContext.canvas.nativeCanvas.drawText(
                    if (index == 0) "" else index.toString(),
                    0f,
                    size.height - (ySpacing.times(index)),
                    textPaint
                )
            }
            // if you want to shift your bar towards right horizontally,then add some values in you x coordinate
            points.forEachIndexed { index, value ->
                val x = xSpacing.times(index)
                val y = size.height - (ySpacing.times(value))

                drawLine(
                    color = Color.Blue,
                    start = Offset(x, size.height),
                    end = Offset(x, y),
                    strokeWidth = 2f
                )
            }
        }
    }

}

@Preview(showBackground = true)
@Composable
fun BarChartPreview(modifier: Modifier = Modifier) {
    BarChart(
        xValues = listOf(0, 4, 5, 6, 7, 8, 9, 10, 11, 12).map { it.times(10) },
        yValues = listOf(0, 4, 5, 6, 7, 8, 9, 10, 11, 12).map { it.times(10) },
        interval = 10,
        modifier = Modifier.size(300.dp),
        points = listOf(0f, 60f, 60f, 50f, 10f)
    )

}