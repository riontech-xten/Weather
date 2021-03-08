package com.xtensolutions.weatherapp.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.xtensolutions.weatherapp.R
import com.xtensolutions.weatherapp.databinding.FragmentSettingBinding
import com.xtensolutions.weatherapp.ui.viewmodel.CityViewModel
import com.xtensolutions.weatherapp.ui.viewmodel.WeatherViewModel
import com.xtensolutions.weatherapp.utils.Constants
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SettingFragment : Fragment() {

    private val cityViewModel: CityViewModel by viewModels()
    private val weatherViewModel: WeatherViewModel by viewModels()
    private lateinit var binding: FragmentSettingBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSettingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnTempUnits.text = weatherViewModel.getUnitsCode()
        binding.btnResetBookmark.setOnClickListener { resetBookmark() }
        binding.btnTempUnits.setOnClickListener { p0 -> showUnitsOptionsMenu(p0) }
    }

    private fun showUnitsOptionsMenu(view: View?) {
        val popupMenu = PopupMenu(requireContext(), view)
        popupMenu.inflate(R.menu.units_menu)
        popupMenu.setOnMenuItemClickListener { menuItem ->
            binding.btnTempUnits.text = menuItem.title
            when (menuItem!!.itemId) {
                R.id.units_celsius -> weatherViewModel.saveUnits(Constants.METRIC)
                R.id.units_fahrenheit -> weatherViewModel.saveUnits(Constants.IMPERIAL)
            }
            popupMenu.dismiss()
            true
        }
        popupMenu.show()
    }

    private fun resetBookmark() {
        val dialog = AlertDialog.Builder(requireContext())
            .setTitle(R.string.bookmarked)
            .setCancelable(false)
            .setMessage(R.string.reset_bookmarked_confirm_msg)
            .setPositiveButton(R.string.reset) { dialog, p1 ->
                dialog!!.dismiss()
                cityViewModel.resetBookmark()
            }.setNegativeButton(R.string.cancel) { dialog, p1 -> dialog!!.dismiss() }.create()

        dialog.setCanceledOnTouchOutside(false)
        dialog.show()
    }
}