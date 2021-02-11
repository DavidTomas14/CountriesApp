package com.example.countriesapp.ui.detail

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.example.countriesapp.R
import com.example.countriesapp.ui.common.GlideApp

class CustomView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    private val imagen :ImageView
    private val btn: Button
    private val textView: TextView

    init {
        val view = LayoutInflater
            .from(context)
            .inflate(R.layout.view_custom, this, true)

        imagen = view.findViewById(R.id.iv)
        btn = view.findViewById(R.id.btn)
        textView = view.findViewById(R.id.tv)
    }

    fun setView( foto: String, nombre: String, nombtn: String = "Pulsa"){
       GlideApp.with(context).load(foto).override(500, 1000).into(imagen)
        btn.text = nombtn
        textView.text = nombre
    }
}