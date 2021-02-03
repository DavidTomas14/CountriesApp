package com.example.countriesapp.ui.common

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.annotation.LayoutRes
import com.example.countriesapp.GlideApp


fun ImageView.loadUrl(url:String){
    GlideApp.with(context).load(url).centerCrop().override(400,400).into(this)
}

inline fun <reified T:Activity> Context.startActivity(body: Intent.() -> Unit){
    startActivity(Intent(this, T::class.java).apply(body))
}

fun ViewGroup.inflate(@LayoutRes layoutRes: Int, attachToRoot: Boolean = true) : View =
        LayoutInflater
                .from(this.context)
                .inflate(layoutRes, this, attachToRoot)
