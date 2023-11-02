package com.example.chart

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.DashPathEffect
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.View

class DashedLineView:View {
    private lateinit var mPaint: Paint
    private lateinit var mPath: Path
    constructor(context: Context?) : super(context){
        initView()
    }
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs){
        initView()
    }
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ){
        initView()
    }

    constructor(
        context: Context?,
        attrs: AttributeSet?,
        defStyleAttr: Int,
        defStyleRes: Int
    ) : super(context, attrs, defStyleAttr, defStyleRes){
        initView()
    }

    private fun initView() {
        mPaint = Paint().apply {
            color = Color.BLACK
            style = Paint.Style.STROKE
            strokeWidth = 5f
            pathEffect = DashPathEffect(floatArrayOf(10f,10f),0f)
        }
        mPath = Path()
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        mPath.moveTo(0f,height / 2f)
        mPath.lineTo(width.toFloat(),height / 2f)
        canvas?.drawPath(mPath,mPaint)
    }
}