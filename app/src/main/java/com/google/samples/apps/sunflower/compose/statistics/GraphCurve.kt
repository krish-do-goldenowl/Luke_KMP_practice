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
import android.graphics.PathMeasure
import android.graphics.PointF
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.asAndroidPath
import androidx.compose.ui.graphics.asComposePath
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.fastForEachIndexed
import com.google.samples.apps.sunflower.ui.md_theme_light_primary

@Composable
fun BezierCurve(
    modifier: Modifier = Modifier,
    xValues: List<Int>,
    yValues: List<Int>,
    points: List<Float>,
    interval: Int,
    currentIndex: Int,
) {
    val animationProgress = remember { androidx.compose.animation.core.Animatable(0f) }

    LaunchedEffect(Unit) {
        animationProgress.animateTo(
            targetValue = 1f,
            animationSpec = tween(2000)
        )
    }

    Box(modifier.size(300.dp), contentAlignment = Alignment.Center) {
        Canvas(
            modifier = Modifier
                .padding(12.dp)
                .fillMaxSize()
        ) {

            val textPaint = Paint().apply {
                textSize = 24f
                color = Color.Black.toArgb()
            }

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

            val coordinates = mutableListOf<PointF>()

            points.fastForEachIndexed { index, value ->
                val x = xSpacing.times(xValues.get(index))
                val y = size.height - (ySpacing.times(value))

                coordinates.add(PointF(x, y))
                if (index== currentIndex){
                    drawCircle(
                        Color.White,
                        radius = 20f,
                        center = Offset(x, y)
                    )
                    drawCircle(
                        md_theme_light_primary,
                        radius = 10f,
                        center = Offset(x, y)
                    )
                }
            }

            val controlPoints = calculateControlPoints(coordinates)

            val path = Path().apply {
                reset()
                moveTo(
                    coordinates.first().x,
                    coordinates.first().y
                )
                for (point in 1 until coordinates.size) {
                    val controlPoint = controlPoints.get(point - 1)
                    cubicTo(
                        controlPoint.first.x, controlPoint.first.y,
                        controlPoint.second.x, controlPoint.second.y,
                        coordinates.get(point).x, coordinates.get(point).y
                    )
                }
            }

            val pathMeasure = PathMeasure(path.asAndroidPath(), false)
            val animationPath = android.graphics.Path()

            pathMeasure.getSegment(
                0f,
                pathMeasure.length * animationProgress.value,
                animationPath,
                true
            )

            drawPath(
                Path().apply { addPath(animationPath.asComposePath()) },
                color = md_theme_light_primary,
                style = Stroke(width = 12f)
            )
        }
    }

}

fun calculateControlPoints(points: MutableList<PointF>): List<Pair<PointF, PointF>> {
    val controlPoints = mutableListOf<Pair<PointF, PointF>>()
    for (i in 1 until points.size) {
        val previous = points.get(i - 1)
        val current = points.get(i)
        val next = points.getOrNull(i + 1)

        val c1 = PointF(
            previous.x + (current.x - previous.x).div(3),
            previous.y + (current.y - previous.y).div(3)
        )
        val c2 = next?.let {
            PointF(
                current.x - (next.x - previous.x).div(3f),
                current.y - (next.y - previous.y).div(3)
            )
        } ?: run {
            PointF(
                previous.x - (current.x - previous.x).div(3),
                previous.y - (current.y - previous.y).div(3)
            )
        }
        controlPoints.add(
            Pair(c1, c2)
        )
    }
    return controlPoints
}

@Preview(showBackground = true)
@Composable
fun BezierPreview(modifier: Modifier = Modifier) {
    BezierCurve(
        modifier = Modifier.size(300.dp),
        xValues = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12).map { it.times(10) },
        yValues = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12).map { it.times(10) },
        interval = 10,
        points = listOf(
            0f,
            5.4f,
            2f,
            6f,
            9f,
            4f,
            2f,
            4f,
            8f,
            1f,
            11f,
            3f
        ).map { it.times(10f) },
        currentIndex = 0,
    )
}