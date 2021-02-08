package com.example.countriesapp.ui.detail

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Spannable
import android.text.style.ForegroundColorSpan
import android.view.Menu
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.text.bold
import androidx.core.text.buildSpannedString
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.countriesapp.CountryApp
import com.example.countriesapp.GlideApp
import com.example.countriesapp.R
import com.example.countriesapp.databinding.ActivityDetailBinding
import com.example.countriesapp.model.databaseRoom.Country
import com.example.countriesapp.model.server.CountriesRepository
import com.example.countriesapp.ui.common.loadUrl
import java.lang.IllegalStateException


class DetailActivity : AppCompatActivity() {

    companion object{
        const val EXTRA_COUNTRY_ID = "DetailActivity:pais"
    }

    private lateinit var binding: ActivityDetailBinding
    private lateinit var viewModel: DetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)


        viewModel = ViewModelProvider( 
            this,
        DetailViewModelFactory(
                intent.getIntExtra(EXTRA_COUNTRY_ID, -1),
                CountriesRepository(application as CountryApp))
        )[DetailViewModel::class.java]

        viewModel.model.observe(this, Observer(::updateUi))

        binding.fab.setOnClickListener {viewModel.onFavoriteClicked()}


    }
    private fun updateUi(model: DetailViewModel.UiModel){ with(binding) {
        val icon = if(model.country.favourite) R.drawable.me_gusta_relleno else R.drawable.me_gusta
        fab.setImageDrawable(ContextCompat.getDrawable(this@DetailActivity, icon))

        countryDetailInfo.setCountryInfo(model.country)

        with(model.country){
            if(this != null){
                binding.bandera.loadUrl(this.flagPath)
                binding.toolbar.title = this.name
            }





        toolbar.setNavigationOnClickListener {
            Toast.makeText(this@DetailActivity, "Navigation", Toast.LENGTH_SHORT).show()
        }
        toolbar.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.favorite -> {
                    Toast.makeText(this@DetailActivity, "Favorite", Toast.LENGTH_SHORT).show()
                    true
                }
                R.id.search -> {
                    Toast.makeText(this@DetailActivity, "Search", Toast.LENGTH_SHORT).show()
                    true
                }
                R.id.more -> {
                    Toast.makeText(this@DetailActivity, "More", Toast.LENGTH_SHORT).show()
                    true
                }
                else -> false
            }
        }

        val customView = findViewById<CustomView>(R.id.customView)
        customView.setView(
            "https://images-na.ssl-images-amazon.com/images/I/616p3JxvgjL._AC_SX425_.jpg",
            "Españita"
        )



        textaco.text =
            "No dejes que nadie te diga que no puedes hacer algo. Ni siquiera yo. ¿Lo entiendes?" +
                    " Si tienes un sueño, tienes que protegerlo. Cuando no pueden hacer algo, a las personas les gusta decirles " +
                    "a los demás que tampoco podrán hacerlo. Si quieres algo, ve y búscalo. Punto.\n" +
                    "Si quieres conquistar corazones y mentes, debes liderar con tu corazón y con tu cabeza. No creo que sea posible" +
                    " tener una personalidad profesional de lunes a viernes y una personalidad real el resto del tiempo. Todo es profesional" +
                    " y todo es personal al mismo tiempo.\n Hay una discordancia entre lo que la ciencia dice y lo que las empresas hacen. Y lo" +
                    " que me preocupa, mientras estamos junto a los escombros del colapso de la economía, es que muchas organizaciones están tomando " +
                    "sus decisiones y estableciendo sus políticas acerca del talento y el personal en función de presunciones obsoletas sin analizar y fundamentadas" +
                    " más en el folclore que en la ciencia."

    }


        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.top_app_bar, menu)
        return true
    }


}