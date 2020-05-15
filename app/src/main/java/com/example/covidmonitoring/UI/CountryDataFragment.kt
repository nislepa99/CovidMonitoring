package com.example.covidmonitoring.UI

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.covidmonitoring.R
import com.example.covidmonitoring.data.CovidRepository
import kotlinx.android.synthetic.main.fragment_country.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CountryDataFragment: Fragment(), CoroutineScope {

    override val coroutineContext = Dispatchers.Main

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_country, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val repo = CovidRepository()

        launch {
            val countryCases = repo.getRussiaLiveData().await()

            loaderCountryData.visibility = View.GONE
            countryData.visibility = View.VISIBLE

            countryCases?.let {
                country.text = it.country
                cases.text = getString(R.string.total_confirmed_template).format(it.cases)
            }
        }
    }
}