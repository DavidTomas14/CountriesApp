package com.example.countriesapp.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.countriesapp.GlideApp
import com.example.countriesapp.model.Database.Country
import com.example.countriesapp.R
import com.example.countriesapp.databinding.ViewCountryBinding

class CountriesAdapter( var items: List<Country>, val onClick:(Country) -> Unit): RecyclerView.Adapter<CountriesAdapter.ViewHolder>() {

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = ViewCountryBinding.bind(view)

        fun bind(country: Country) = with(binding){
            countryName.text = country.name


            GlideApp.with(binding.root)
                .load(country.flagPath)
                .centerCrop()
                .override(400, 400)
                .into(countryFlag)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.view_country, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.bind(item)
        holder.itemView.setOnClickListener { onClick(item) }
    }

    override fun getItemCount(): Int = items.size
}