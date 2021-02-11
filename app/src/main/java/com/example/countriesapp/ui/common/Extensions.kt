package com.example.countriesapp.ui.common

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.countriesapp.CountryApp
import kotlin.properties.Delegates


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


inline fun <VH : RecyclerView.ViewHolder, T> RecyclerView.Adapter<VH>.basicDiffUtil(
        initialValue: List<T>,
        crossinline areItemsTheSame: (T, T) -> Boolean = { old, new -> old == new },
        crossinline areContentsTheSame: (T, T) -> Boolean = { old, new -> old == new }
) =
        Delegates.observable(initialValue) { _, old, new ->
            DiffUtil.calculateDiff(object : DiffUtil.Callback() {
                override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
                        areItemsTheSame(old[oldItemPosition], new[newItemPosition])
                override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
                        areContentsTheSame(old[oldItemPosition], new[newItemPosition])
                override fun getOldListSize(): Int = old.size
                override fun getNewListSize(): Int = new.size
            }).dispatchUpdatesTo(this@basicDiffUtil)
        }

val Context.app: CountryApp
    get() = applicationContext as CountryApp