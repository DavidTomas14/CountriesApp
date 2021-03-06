package com.example.countriesapp.ui.detail

import android.content.Context
import android.graphics.Color
import android.text.Spannable
import android.text.style.ForegroundColorSpan
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.text.bold
import androidx.core.text.buildSpannedString
import com.example.domain.Country

class CountryDetailInfo @JvmOverloads constructor(
        context: Context,
        attrs: AttributeSet? = null,
        defStyleAttr: Int = 0
) : AppCompatTextView(context, attrs, defStyleAttr)  {

    fun setCountryInfo(country: Country) {
        text = buildSpannedString {
            bold { append("Name: ") }
            appendLine(country.name)

            bold{append("Code: ")}
            appendLine(country.code)

            bold { append("NativeName: ") }
            appendLine(country.nativeName)

            bold { append("Capital: ") }
            appendLine(country.capital)

            bold { append("Population: ") }
            appendLine(country.population.toString())

            bold { append("Language: ") }
            appendLine(country.language)

            bold { append("Region: ") }
            appendLine(country.region)

            bold { append("Subregion: ") }
            appendLine(country.subRegion)
            setSpan(ForegroundColorSpan(Color.BLUE),
                    40, 46,
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)

            bold { append("Area: ") }
            appendLine(country.area.toString())
        }
    }


}