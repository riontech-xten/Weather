package com.xtensolutions.weatherapp.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.xtensolutions.weatherapp.R
import com.xtensolutions.weatherapp.core.DataResult
import com.xtensolutions.weatherapp.databinding.FragmentSearchCitiesBinding
import com.xtensolutions.weatherapp.databinding.FragmentSettingBinding
import com.xtensolutions.weatherapp.extension.isTablet
import com.xtensolutions.weatherapp.room.model.BookmarkCity
import com.xtensolutions.weatherapp.ui.adapter.CityAdapter
import com.xtensolutions.weatherapp.ui.viewholder.BaseViewHolder
import com.xtensolutions.weatherapp.ui.viewmodel.CityViewModel
import com.xtensolutions.weatherapp.utils.Constants
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchCityFragment : Fragment(), BaseViewHolder.ViewHolderClickListener,
                           SearchView.OnQueryTextListener {

    private val cityViewModel: CityViewModel by viewModels()
    private lateinit var binding: FragmentSearchCitiesBinding
    private lateinit var adapter: CityAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSearchCitiesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initListView()
        loadCities()
        setupSearchBar()
        setAddToBookmarkListener()
    }

    private fun setupSearchBar() {
        binding.citySearchView.setOnQueryTextListener(this)
    }

    private fun setAddToBookmarkListener() {
        binding.btnBookmark.setOnClickListener {
            val items = adapter.getSelectedItems()
            addToBookmarkList(items as ArrayList<BookmarkCity>)
        }
    }

    private fun addToBookmarkList(items: ArrayList<BookmarkCity>) {
        cityViewModel.bookmarkCities(items!!.toMutableList()).observe(viewLifecycleOwner, {
            if (it == true) {
                showToast(R.string.added_to_bookmark)
                findNavController().popBackStack()
            }
        })
    }

    private fun showToast(message: Int) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_LONG).show()
    }

    private fun loadCities() {
        cityViewModel.getCities().observe(viewLifecycleOwner, {
            binding.txtCitiesLoading.visibility = View.GONE
            when (it) {
                is DataResult.Success -> bindCitiesData(it.data!!)
                is DataResult.Fail -> showFailMsg(it.message)
                else -> binding.txtCitiesLoading.visibility = View.VISIBLE
            }
        })
    }

    private fun initListView() {
        var layoutManager = LinearLayoutManager(requireContext())
        if (requireContext().isTablet())
            layoutManager = GridLayoutManager(requireContext(), 3)
        adapter = CityAdapter(requireContext(), ArrayList())
        adapter.viewHolderClickListener = this
        binding.cityList.layoutManager = layoutManager
        binding.cityList.adapter = adapter
    }

    private fun bindCitiesData(list: List<BookmarkCity>) {
        adapter.updateList(list.toMutableList())
    }

    private fun showFailMsg(message: String) {
        Snackbar.make(binding.root, message, Snackbar.LENGTH_LONG).show()
    }

    override fun onViewHolderViewClicked(view: View?, position: Int) {
        if (requireView().id == R.id.rowSearchContainer) {
            val city: BookmarkCity = requireView().tag as BookmarkCity
            adapter.toggleSelection(position)
        }
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        return false
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        adapter.filter.filter(newText)
        return true
    }
}
