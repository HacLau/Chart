package com.example.chart

import android.graphics.Color
import android.graphics.Paint
import android.graphics.Typeface
import android.os.Bundle
import android.util.DisplayMetrics
import android.util.Log
import android.view.View
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.chart.databinding.ActivityChartBinding
import com.example.chart.databinding.LayoutChartBinding
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.components.Description
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.components.YAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.formatter.IAxisValueFormatter
import com.github.mikephil.charting.formatter.IValueFormatter
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet
import com.github.mikephil.charting.utils.Utils
import com.github.mikephil.charting.utils.ViewPortHandler
import java.text.DecimalFormat


class ChartActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityChartBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.hsv.apply {
            addItemDecoration(ItemDecoration(12))
            layoutManager = LinearLayoutManager(this@ChartActivity,LinearLayoutManager.HORIZONTAL,false)
        }
        val chartList = Assets.getRecord(this@ChartActivity)

        var maxNumber: Int = chartList[0].sys
        var minNumber: Int = chartList[0].dia
        chartList.forEach {
            if (maxNumber - it.sys < 0) {
                maxNumber = it.sys
            }
            if (it.dia - minNumber < 0) {
                minNumber = it.dia
            }
        }
        binding.hsv.adapter = HoriAdapter(this, chartList,116,maxNumber,minNumber)

        binding.let {
            it.numMax.text = "$maxNumber"
            it.numTop.text = "${minNumber + (maxNumber - minNumber) / 4 * 3}"
            it.numCenter.text = "${minNumber + (maxNumber - minNumber) / 4 * 2}"
            it.numBottom.text = "${minNumber + (maxNumber - minNumber) / 4 * 1}"
            it.numMin.text = "$minNumber"
        }

    }

}