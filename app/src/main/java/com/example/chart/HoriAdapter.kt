package com.example.chart

import android.content.Context
import android.content.res.ColorStateList
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.chart.databinding.LayoutRecordChartItemBinding

class HoriAdapter(
    val context: Context,
    val list: List<RecordEntity>,
    val height: Int,
    val max: Int,
    val min: Int
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ItemVH(LayoutRecordChartItemBinding.inflate(LayoutInflater.from(context)))
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is ItemVH) {
            list[position]?.let {
                holder.binding.level.layoutParams.height =( (it.sys - it.dia)  * height / (max - min)).dp2px(context)
                holder.binding.level.translationY = (((max + min)/2f - (it.sys + it.dia)/2f )* height /(max - min).toFloat()).dp2px(context)
                Log.e("record","height = ${height} position = ${position} sys = ${it.sys} dia = ${it.dia} trans = ${holder.binding.level.translationY } height = ${holder.binding.level.layoutParams.height}")
                var color = R.color.result_0

                when (it.level) {
                    0 -> {
                        color = R.color.result_0
                    }

                    1 -> {
                        color = R.color.result_1
                    }

                    2 -> {
                        color = R.color.result_2
                    }

                    3 -> {
                        color = R.color.result_3
                    }

                    4 -> {
                        color = R.color.result_4
                    }

                    5 -> {
                        color = R.color.result_5
                    }
                }
                ColorStateList.valueOf(ContextCompat.getColor(context, color)).let {
                    holder.binding.level.imageTintList = it
                }



            }


        }
    }

    inner class ItemVH(bind: LayoutRecordChartItemBinding) : RecyclerView.ViewHolder(bind.root) {
        val binding = bind
    }
}