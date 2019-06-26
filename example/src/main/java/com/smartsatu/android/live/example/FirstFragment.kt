package com.smartsatu.android.live.example

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.smartsatu.android.live.example.databinding.FirstFragmentBinding


class FirstFragment : Fragment() {

    private lateinit var binding: FirstFragmentBinding
    private lateinit var viewModel: FirstViewModel
    private val sharedViewModel by lazy {
        ViewModelProviders.of(requireActivity()).get(SharedViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.first_fragment, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(FirstViewModel::class.java)
        binding.viewModel = viewModel
        binding.sharedViewModel = sharedViewModel
        binding.lifecycleOwner = viewLifecycleOwner

        viewModel.listener.observe(viewLifecycleOwner, Observer {
            when (it) {
                is FirstViewModel.Callbacks.OpenSecondFragment -> {
                    findNavController().navigate(R.id.action_firstFragment_to_secondFragment)
                }
            }
        })

        sharedViewModel.listener.observe(viewLifecycleOwner, Observer {
            when (it) {
                is SharedViewModel.Callback.ShowToast -> {
                    Toast.makeText(requireContext(), "Fragment's: ${it.toast}", Toast.LENGTH_SHORT).show()
                }
            }
        })
    }
}
