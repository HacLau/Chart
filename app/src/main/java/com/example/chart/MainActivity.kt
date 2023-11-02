package com.example.chart


import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.components.YAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.formatter.IAxisValueFormatter
import com.github.mikephil.charting.formatter.IValueFormatter
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet


class MainActivity : AppCompatActivity() {
    lateinit var barChart:BarChart
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initBarChart()
    }

    private fun initBarChart() {
        barChart = findViewById(R.id.chart)
        barChart.getDescription().setEnabled(false) // 不显示描述
        barChart.setExtraOffsets(50f, 50f, 50f, 50f) // 设置饼图的偏移量，类似于内边距 ，设置视图窗口大小
        setAxis() // 设置坐标轴
        setLegend() // 设置图例
        setData() // 设置数据
    }

    /**
     * 因为此处的 barData.setBarWidth(0.3f);，也就是说柱子的宽度是0.3f
     * 所以第二个柱子的值要比第一个柱子的值多0.3f，这样才会并列显示两根柱子
     */
    private fun setData() {
        val sets: MutableList<IBarDataSet> = ArrayList()
        // 此处有两个DataSet，所以有两条柱子，BarEntry（）中的x和y分别表示显示的位置和高度
        // x是横坐标，表示位置，y是纵坐标，表示高度
        val barEntries1: MutableList<BarEntry> = ArrayList()
        barEntries1.add(BarEntry(0f, floatArrayOf(20f,320f)))
        barEntries1.add(BarEntry(1f, floatArrayOf(60f,140f)))
        barEntries1.add(BarEntry(2f, floatArrayOf(120f,140f)))
        barEntries1.add(BarEntry(3f, floatArrayOf(45f,90f)))
        barEntries1.add(BarEntry(4f, floatArrayOf(150f,151f)))
        val barDataSet1 = BarDataSet(barEntries1, "蔬菜")
        barDataSet1.valueTextColor = Color.RED // 值的颜色
        barDataSet1.valueTextSize = 15f // 值的大小
        barDataSet1.color = Color.parseColor("#1AE61A") // 柱子的颜色
        // 设置柱子上数据显示的格式
        barDataSet1.valueFormatter =
            IValueFormatter { value, _, _, _ -> value.toString()  }
        sets.add(barDataSet1)
        val barData = BarData(sets)
        barData.barWidth = 0.8f // 设置柱子的宽度
        barChart.setData(barData)
    }

    private fun setLegend() {
        val legend: Legend = barChart.getLegend()
        legend.formSize = 20f // 图例的图形大小
        legend.textSize = 20f // 图例的文字大小
        legend.setDrawInside(true) // 设置图例在图中
        legend.orientation = Legend.LegendOrientation.VERTICAL // 图例的方向为垂直
        legend.horizontalAlignment = Legend.LegendHorizontalAlignment.RIGHT //显示位置，水平右对齐
        legend.verticalAlignment = Legend.LegendVerticalAlignment.TOP // 显示位置，垂直上对齐
        // 设置水平与垂直方向的偏移量
        legend.yOffset = 55f
        legend.xOffset = 30f
    }

    private fun setAxis() {
        // 设置x轴
        val xAxis: XAxis = barChart.getXAxis()
        xAxis.position = XAxis.XAxisPosition.BOTTOM // 设置x轴显示在下方，默认在上方
        xAxis.setDrawGridLines(false) // 将此设置为true，绘制该轴的网格线。
        xAxis.labelCount = 6 // 设置x轴上的标签个数
        xAxis.textSize = 15f // x轴上标签的大小
        val labelName = arrayOf("周一", "周二", "周三", "周四", "周五","周六")
        // 设置x轴显示的值的格式
        xAxis.valueFormatter =
            IAxisValueFormatter { value, axis ->
                if (value.toInt() < labelName.size) {
                    labelName[value.toInt()]
                } else {
                    ""
                }
            }
        xAxis.yOffset = 15f // 设置标签对x轴的偏移量，垂直方向

        // 设置y轴，y轴有两条，分别为左和右
        val yAxis_right: YAxis = barChart.getAxisRight()
        yAxis_right.axisMaximum = 320f // 设置y轴的最大值
        yAxis_right.axisMinimum = 20f // 设置y轴的最小值
        yAxis_right.isEnabled = false // 不显示右边的y轴
    }
}