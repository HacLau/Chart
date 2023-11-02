package com.example.chart

import android.content.Context
import android.util.Log
import org.json.JSONArray
import java.nio.charset.Charset

object Assets {
    fun getRecord(context: Context): MutableList<RecordEntity> {
        val infoJson: String = getJsonFromAssets(context, "record.json")
        Log.e("infoJson",infoJson)
        return mutableListOf<RecordEntity>().apply {
            kotlin.runCatching {
                val jsonArray = JSONArray(infoJson)
                for (i in 0 until jsonArray.length()) {
                    jsonArray.getJSONObject(i).let {
                        add(
                            RecordEntity(
                                it.getInt("level"),
                                it.getString("title"),
                                it.getLong("time"),
                                it.getInt("sys"),
                                it.getInt("dia")
                            )
                        )
                    }
                }
            }
        }
    }

    fun getJsonFromAssets(context: Context, fileName: String): String {
        kotlin.runCatching {
            return context.assets.open(fileName).let {
                val buffer = ByteArray(it.available())
                it.read(buffer)
                it.close()
                String(buffer, Charset.defaultCharset())
            }
        }
        return ""
    }

}