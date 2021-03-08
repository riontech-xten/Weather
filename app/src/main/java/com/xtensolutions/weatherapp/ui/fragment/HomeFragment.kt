package com.xtensolutions.weatherapp.ui.fragment

import android.location.Location
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.xtensolutions.weatherapp.R
import com.xtensolutions.weatherapp.core.DataResult
import com.xtensolutions.weatherapp.data.model.WeatherData
import com.xtensolutions.weatherapp.databinding.FragmentHomeBinding
import com.xtensolutions.weatherapp.extension.isTablet
import com.xtensolutions.weatherapp.room.model.BookmarkCity
import com.xtensolutions.weatherapp.ui.adapter.BookmarkCityAdapter
import com.xtensolutions.weatherapp.ui.viewholder.BaseViewHolder
import com.xtensolutions.weatherapp.ui.viewmodel.CityViewModel
import com.xtensolutions.weatherapp.ui.viewmodel.WeatherViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : LocationFragment(), BaseViewHolder.ViewHolderClickListener {

    private val TAG: String? = HomeFragment::class.simpleName
    private val viewModel: WeatherViewModel by viewModels()
    private val cityViewModel: CityViewModel by viewModels()
    private lateinit var binding: FragmentHomeBinding
    private lateinit var adapter: BookmarkCityAdapter

    override fun onLocationUpdate(location: Location) {
        viewModel.getTodayWeather(location.latitude, location.longitude)
            .observe(viewLifecycleOwner, Observer {
                if (it is DataResult.Success<WeatherData>) {
                    bindCurrentLocationWeatherData(it.data!!)
                }
            })
    }

    private fun bindCurrentLocationWeatherData(data: WeatherData) {
        binding.current = data
        binding.txtWaitingForLocation.visibility = View.GONE
        binding.currentContainer.tag =
            BookmarkCity(data.name, data.coord.lat, data.coord.lon)
        binding.currentContainer.setOnClickListener {
            onViewHolderViewClicked(it, 0)
        }

        if (requireContext().isTablet()) {
            onViewHolderViewClicked(binding.currentContainer, 0)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initBookmarkListView()
    }

    private fun initBookmarkListView() {
        adapter = BookmarkCityAdapter(requireContext(), ArrayList())
        adapter.viewHolderClickListener = this
        binding.bookmarkedList.layoutManager = LinearLayoutManager(requireContext())
        binding.bookmarkedList.adapter = adapter
        bindBookmarkedCities()
        setAddBookmarkListener()
    }

    private fun bindBookmarkedCities() {
        cityViewModel.getBookmarkedList().observe(viewLifecycleOwner, Observer {
            Log.d(TAG, "bindBookmarkedCities: ")
            binding.btnBookmark.visibility = if (it.isEmpty()) View.VISIBLE else View.GONE
            adapter.cities = it.toMutableList()
            adapter.notifyDataSetChanged()
        })
    }

    private fun setAddBookmarkListener() {
        binding.btnBookmarkMore.setOnClickListener {
            if (requireContext().isTablet()) {
                requireActivity().findNavController(R.id.content_nav_host_fragment)
                    .navigate(R.id.fragment_bookmark)
            } else {
                findNavController().navigate(HomeFragmentDirections.homeToBookmark())
            }
        }

        binding.btnSearch.setOnClickListener {
            if (requireContext().isTablet()) {
                requireActivity().findNavController(R.id.content_nav_host_fragment)
                    .navigate(R.id.fragment_search_city)
            } else {
                findNavController().navigate(HomeFragmentDirections.homeToSearch())
            }
        }
    }

    override fun onViewHolderViewClicked(view: View?, position: Int) {
        val city: BookmarkCity = view!!.tag as BookmarkCity
        if (view!!.id == R.id.rowContainer || view!!.id == R.id.currentContainer) {
            if (requireContext().isTablet()) {
                requireActivity().findNavController(R.id.content_nav_host_fragment)
                    .navigate(R.id.fragment_city_weather, Bundle().apply {
                        putSerializable("city", city)
                    })
            } else {
                findNavController().navigate(HomeFragmentDirections.homeToCityWeather(city))
            }
        } else if (view!!.id == R.id.btnRemove) {
            cityViewModel.removeBookmark(city.name)
        }
    }
}