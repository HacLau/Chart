package com.example.chart

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.chart.databinding.ActivityChartBinding
import kotlin.math.max


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
        binding.hsv.adapter = HoriAdapter(this, chartList,116, maxNumber,minNumber)

        binding.let {
            it.numMax.text = "$maxNumber"
            it.numTop.text = "${minNumber + (maxNumber - minNumber) / 4 * 3}"
            it.numCenter.text = "${minNumber + (maxNumber - minNumber) / 4 * 2}"
            it.numBottom.text = "${minNumber + (maxNumber - minNumber) / 4 * 1}"
            it.numMin.text = "$minNumber"
        }

    }

}