package com.smartsatu.android.live.example

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.smartsatu.android.live.example.databinding.SecondFragmentBinding


class SecondFragment : Fragment() {

    private val sharedViewModel by lazy {
        ViewModelProviders.of(requireActivity()).get(SharedViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val binding = DataBindingUtil.inflate<SecondFragmentBinding>(inflater, R.layout.second_fragment, container, false).apply {
            lifecycleOwner = viewLifecycleOwner
            viewModel = sharedViewModel
        }
        return binding.root
    }
}
