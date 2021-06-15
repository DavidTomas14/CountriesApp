 package com.example.countriesapp.ui.main

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.countriesapp.R
import com.example.countriesapp.databinding.ViewCountryBinding
import com.example.countriesapp.ui.common.basicDiffUtil
import com.example.countriesapp.ui.common.inflate
import com.example.countriesapp.ui.common.loadUrl
import com.example.domain.Country

class CountriesAdapter(val onClick:(Country) -> Unit): RecyclerView.Adapter<CountriesAdapter.ViewHolder>() {

    var items: List<Country> by basicDiffUtil(
            emptyList(),
            areItemsTheSame = {old, new -> old.id == new.id}
    )

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = ViewCountryBinding.bind(view)

        fun bind(country: Country) = with(binding){
            countryName.text = country.name

            countryFlag.loadUrl(country.flagPath)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = parent.inflate(R.layout.view_country, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.bind(item)
        holder.itemView.setOnClickListener { onClick(item) }
    }

    override fun getItemCount(): Int = items.size
}