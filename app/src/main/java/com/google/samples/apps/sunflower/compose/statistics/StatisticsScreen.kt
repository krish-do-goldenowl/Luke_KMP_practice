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

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import com.aay.compose.baseComponents.model.LegendPosition
import com.aay.compose.lineChart.LineChart
import com.aay.compose.lineChart.model.LineParameters
import com.aay.compose.lineChart.model.LineType
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.google.samples.apps.sunflower.R
import com.google.samples.apps.sunflower.compose.homebank.TransactionItem
import com.google.samples.apps.sunflower.compose.homebank.transactions
import com.google.samples.apps.sunflower.compose.utils.BankTopAppBar
import com.google.samples.apps.sunflower.ui.md_theme_light_primary
import kotlin.math.PI
import kotlin.math.sin

val listMonth = listOf(
    "Jan",
    "Feb",
    "Mar",
    "Apr",
    "May",
    "Jun",
    "Jul",
    "Aug",
    "Sep",
    "Oct",
    "Nov",
    "Dec",
)

@Composable
fun StatisticsScreen() {
    val listState = rememberLazyListState()
    var currentIndex by remember {
        mutableIntStateOf(0)
    }

    Scaffold(
        topBar = {
            BankTopAppBar(
                title = "Statistics",
                iconSource = R.drawable.notification_icon,
                hasBackIcon = false,
            )
        }
    ) { contentPadding ->
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(20.dp, contentPadding.calculateTopPadding(), 20.dp, 0.dp)
                .fillMaxSize(),
        ) {
            Spacer(modifier = Modifier.height(32.dp))
            Text(
                text = "Current Balance",
                fontSize = 18.sp,
                lineHeight = 18.sp,
                textAlign = TextAlign.Center,
                color = Color(0xFFA2A2A7)
            )
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = "$8,545.00",
                fontSize = 26.sp,
                lineHeight = 26.sp,
                textAlign = TextAlign.Center,
                color = Color(0xFFFFFFFF)
            )
            Box(
                contentAlignment = Alignment.Center
            ) {
//                LineChartCard(
//                    lineData = LineData(lineDataSet)
//                )
                LineChart(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(end = 20.dp)
                        .height(250.dp),
                    linesParameters = listOf(
                        LineParameters(
                            label = "",
                            data = listOf(1.0, 3.5, 1.5, 5.0, 3.0, 7.0),
                            lineColor = md_theme_light_primary,
                            lineType = LineType.CURVED_LINE,
                            lineShadow = true,
                        ),
                    ),
                    legendPosition = LegendPosition.DISAPPEAR,
                    isGrid = false,
                    gridColor = Color.Transparent,
                    showXAxis = false,
                    showYAxis = false,
                    xAxisData = listOf("0", "1", "2", "3", "4", "5"),
                    animateChart = true,
                    showGridWithSpacer = false,
//                    yAxisStyle = TextStyle(
//                        fontSize = 14.sp,
//                        color = Color.Gray,
//                    ),
//                    xAxisStyle = TextStyle(
//                        fontSize = 14.sp,
//                        color = Color.Gray,
//                        fontWeight = FontWeight.W400
//                    ),
//                    yAxisRange = 14,
                    oneLineChart = true,
//                    gridOrientation = GridOrientation.VERTICAL
                )
            }
            Spacer(modifier = Modifier.height(14.dp))
            Row {
                for (index in 0..5) {
                    val date = listMonth[index]
                    Button(
                        onClick = {
                            currentIndex = index
                        },
                        contentPadding = PaddingValues(0.dp),
                        shape = RoundedCornerShape(8.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = if (index == currentIndex) md_theme_light_primary else Color.Transparent,
                        ),
                        modifier = Modifier
                            .padding(horizontal = 5.dp)
                            .weight(1F)
                    ) {
                        Text(
                            text = date,
                            color = if (index == currentIndex) Color(0xFFFFFFFF) else Color(0xFFA2A2A7),
                            fontSize = 15.sp,
                            lineHeight = 15.sp,
                            modifier = Modifier
                                .padding(10.dp, 6.dp)
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.height(29.dp))
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "Transaction",
                    fontSize = 18.sp,
                    lineHeight = 18.sp,
                    fontWeight = FontWeight(500),
                    color = Color(0xFFFFFFFF),
                )
                TextButton(
                    onClick = { /*TODO*/ },
                    contentPadding = PaddingValues(0.dp)
                ) {
                    Text(
                        text = "See All",
                        fontSize = 14.sp,
                        lineHeight = 14.sp,
                        fontWeight = FontWeight(500),
                    )
                }
            }
            LazyColumn(
                state = listState,
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(0.dp)
            ) {
                items(transactions.size){ index ->
                    val item = transactions[index]
                    TransactionItem(item)
                }
            }
        }
    }
}

val listData = (0..100).map {
    sin((it * 2 * PI * 20).toFloat())
}

@Composable
fun LineChartComponent(modifier: Modifier = Modifier, lineData: LineData) {
    // (x,y) -> Entry -> List<Entry> -> LineDataSet -> LineData -> LineChart
    AndroidView(
        modifier = modifier,
        factory = { context ->
            LineChart(context)
                .setupLineChart()
                .apply {
                    data = lineData
                }
        },
        update = { view ->  /* Add animation here*/ }
    )
}

fun LineChart.setupLineChart(): LineChart = this.apply {
    setTouchEnabled(false)
    isDragEnabled = false
    setScaleEnabled(false)
    setPinchZoom(false)
    description.isEnabled = false

    // set up x-axis
    xAxis.apply {
        position = XAxis.XAxisPosition.BOTTOM
        // axisMinimum = -10f
        // axisMaximum = 10f
    }

    // set up y-axis
    axisLeft.apply {
        // axisMinimum = -5f
        // axisMaximum = 5f
        setDrawGridLines(false)
    }

    axisRight.isEnabled = false
}

@Composable
fun LineChartCard(modifier: Modifier = Modifier, lineData: LineData) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .aspectRatio(2f) // (width:height) 2:1
            .padding(16.dp)
    ) {
        LineChartComponent(
            modifier = Modifier.fillMaxSize(),
            lineData = lineData
        )
    }
}

val lineDataSet = listData.createDataSetWithColor(
    datasetColor = android.graphics.Color.BLUE,
    label = "Oxygen"
)

fun List<Float>.createDataSetWithColor(
    datasetColor: Int = android.graphics.Color.GREEN,
    label: String = "No Label"
): LineDataSet {
    // List<Float> -> List<Entry>
    val entries = this.mapIndexed { index, value ->
        Entry(index.toFloat(), value)
    }
    // List<Entry> -> LineDataSet
    return LineDataSet(entries, label).apply {
        color = datasetColor
        setDrawFilled(false)
        setDrawCircles(false)
        mode = LineDataSet.Mode.CUBIC_BEZIER
    }
}
