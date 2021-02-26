package com.example.countriesapp.ui.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.countriesapp.R
import com.example.countriesapp.data.databaseRoom.RoomDataSource
import com.example.countriesapp.data.server.TheCountryServerDataSource
import com.example.countriesapp.databinding.ActivityDetailBinding
import com.example.countriesapp.ui.common.app
import com.example.countriesapp.ui.common.getViewModel
import com.example.countriesapp.ui.common.loadUrl
import com.example.countriesapp.ui.main.MainActivityComponent
import com.example.data1.CountriesRepository
import com.example.usecases1.FindCountryById
import com.example.usecases1.ToggleCountryFavorite


class DetailActivity : AppCompatActivity() {

    companion object{
        const val EXTRA_COUNTRY_ID = "DetailActivity:pais"
    }

    private lateinit var binding: ActivityDetailBinding
    private val viewModel: DetailViewModel by lazy { getViewModel { component.detailViewModel } }
    private lateinit var component : DetailActivityComponent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        component = app.component.plus(DetailActivityModule(intent.getIntExtra(EXTRA_COUNTRY_ID, -1)))

        viewModel.model.observe(this, Observer(::updateUi))

        binding.fab.setOnClickListener {viewModel.onFavoriteClicked()}


    }
    private fun updateUi(model: DetailViewModel.UiModel) {
        with(binding) {
            val icon =
                if (model.country.favourite) R.drawable.me_gusta_relleno else R.drawable.me_gusta
            fab.setImageDrawable(ContextCompat.getDrawable(this@DetailActivity, icon))

            countryDetailInfo.setCountryInfo(model.country)

            with(model.country) {
                if (this != null) {
                    binding.bandera.loadUrl(this.flagPath)
                    binding.toolbar.title = this.name
                }

                toolbar.setNavigationOnClickListener {
                    onBackPressed()
                }

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

}