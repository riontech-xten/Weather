package com.xtensolutions.weatherapp.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.xtensolutions.weatherapp.databinding.FragmentHelpBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class HelpFragment : Fragment() {

    lateinit var binding: FragmentHelpBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHelpBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.helpWebView.loadUrl("file:///android_asset/help")
    }
}