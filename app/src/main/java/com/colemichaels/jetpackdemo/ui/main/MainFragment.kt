package com.colemichaels.jetpackdemo.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.colemichaels.jetpackdemo.R
import com.colemichaels.jetpackdemo.databinding.MainFragmentBinding

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var _binding: MainFragmentBinding
    private val binding get() = _binding

    // The "by viewModels()" syntax is the Kotlin way of getting the view model from a view model provider.
    // No parameters are needed to be passed to viewModels() constructor by default a default factory is passed in for saved state handler.
    private val viewModel: MainViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DataBindingUtil.inflate(inflater, R.layout.main_fragment, container, false)
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.myViewModel = viewModel
    }

}