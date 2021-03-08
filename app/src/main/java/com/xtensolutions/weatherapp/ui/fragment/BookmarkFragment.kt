package com.xtensolutions.weatherapp.ui.fragment

import android.location.Location
import android.location.LocationManager
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.xtensolutions.weatherapp.R
import com.xtensolutions.weatherapp.databinding.DialogPinLocationBinding
import com.xtensolutions.weatherapp.databinding.FragmentBookmarkMapBinding
import com.xtensolutions.weatherapp.room.model.BookmarkCity
import com.xtensolutions.weatherapp.ui.viewmodel.CityViewModel
import dagger.hilt.android.AndroidEntryPoint


/**
 * Created by Vaghela Mithun R. on 18/02/21.
 * vaghela.mithun@gmail.com
 */
@AndroidEntryPoint
class BookmarkFragment : LocationFragment(), OnMapReadyCallback, GoogleMap.OnMarkerDragListener {
    private val TAG: String? = BookmarkFragment::class.java.name
    private lateinit var mMap: GoogleMap

    private val viewModel: CityViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = FragmentBookmarkMapBinding.inflate(inflater, container, false)
        val mapFragment = childFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        return binding.root
    }

    override fun onMapReady(map: GoogleMap?) {
        mMap = map!!
    }

    private fun locationConvertToBookmarkCity(location: Location): BookmarkCity {
        return location.let {
            val name = locationViewModel.getAddress(it)
            return BookmarkCity(name, it.latitude, it.longitude)
        }
    }

    private fun addCurrentMarker(city: BookmarkCity) {
        val latLng = LatLng(city.latitude, city.longitude)
        mMap.addMarker(
            MarkerOptions()
                .icon(BitmapDescriptorFactory.defaultMarker())
                .position(latLng)
                .draggable(true)
        )
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 10f))
    }

    override fun onLocationUpdate(location: Location) {
        addCurrentMarker(locationConvertToBookmarkCity(location))
        mMap.setOnMarkerDragListener(this)
    }

    private fun showInfoDialog(city: BookmarkCity) {
        val bottomDialog = BottomSheetDialog(requireContext())
        val binding = DialogPinLocationBinding.inflate(
            LayoutInflater.from(requireContext()), null, false
        )

        binding.pinLocation = city
        binding.btnBookmark.tag = bottomDialog
        binding.btnBookmark.setOnClickListener {
            val dialog: BottomSheetDialog = it.tag as BottomSheetDialog
            dialog.dismiss()
            viewModel.bookmarkCity(city)
        }

        bottomDialog.setContentView(binding.root)
        bottomDialog.show()
    }

    override fun onMarkerDragStart(marker: Marker?) {

    }

    override fun onMarkerDrag(marker: Marker?) {

    }

    override fun onMarkerDragEnd(marker: Marker?) {
        mMap.clear()
        val location = Location(LocationManager.GPS_PROVIDER).apply {
            longitude = marker!!.position.longitude
            latitude = marker!!.position.latitude
        }

        val city = locationConvertToBookmarkCity(location)
        addCurrentMarker(city)
        showInfoDialog(city)

        Log.d(TAG, "onMarkerDragEnd: ${city.name}")
    }
}