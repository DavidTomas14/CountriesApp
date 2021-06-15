package com.example.countriesapp.ui.detail

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatImageView
import com.example.countriesapp.R

class CustomView2 @JvmOverloads constructor(
        context : Context, attrs : AttributeSet? = null, defStyleAttr : Int = 0
) : AppCompatImageView(context, attrs, defStyleAttr) {

    private var ratio = 1f

    init {
        val a = context.obtainStyledAttributes(attrs, R.styleable.CustomView2)
        ratio = a.getFloat(R.styleable.CustomView2_ratio, 1f)
        a.recycle()
    }

    override fun onMeasure(widthMeasureSpec : Int, heightMeasureSpec : Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)

        var width = measuredWidth
        var height = measuredWidth

        if(width == 0 && height == 0){
            return
        }

        if (width > 0) {
            height = (width * ratio).toInt()
        }
        if ( height > 0){
            width = (height/ratio).toInt()
        }

        setMeasuredDimension(width, height)
    }
}