package com.example.customimagelist.demo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.savedstate.SavedStateRegistryOwner
import com.example.customimagelist.R
import com.example.customimagelist.databinding.FragmentDemoBinding

class DemoFragment : Fragment() {

    private var _binding: FragmentDemoBinding? = null
    private val binding get() = _binding!!

    private val viewModel: DemoViewModel by viewModels { DemoViewModelFactory(owner = this) }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        setUpBinding(container = container)
        return binding.root
    }

    override fun onDestroyView() {
        tearDownBinding()
        super.onDestroyView()
    }

    private fun setUpBinding(container: ViewGroup?) {
        _binding = DataBindingUtil.inflate(layoutInflater, R.layout.fragment_demo, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel
    }

    private fun tearDownBinding() {
        binding.assignmentView.adapter = null
        _binding = null
    }

    companion object {

        fun newInstance(): DemoFragment = DemoFragment()

    }

}

private class DemoViewModelFactory(
    owner: SavedStateRegistryOwner,
    defaultArgs: Bundle? = null
) : AbstractSavedStateViewModelFactory(owner, defaultArgs) {

    override fun <T : ViewModel> create(key: String, modelClass: Class<T>, handle: SavedStateHandle): T {
        @Suppress("UNCHECKED_CAST")
        return DemoViewModel(handle) as T
    }

}
