package com.example.lktasktracker.ui.fragments.done

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.lifecycle.viewmodel.MutableCreationExtras
import androidx.recyclerview.widget.GridLayoutManager
import com.example.lktasktracker.databinding.DoneFragmentBinding
import com.example.lktasktracker.ui.recycler.ShortTaskAdapter
import kotlinx.coroutines.launch

class DoneFragment : Fragment() {
    private var binding: DoneFragmentBinding? = null
    private val viewModel: DoneViewModel by activityViewModels(
        factoryProducer = { DoneViewModel.Factory },
        extrasProducer =
        {
            MutableCreationExtras().apply {
                set(DoneViewModel.APPLICATION_KEY, requireActivity().application)
            }
        })

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = DoneFragmentBinding.inflate(inflater, container, false)
        val root: View = binding!!.root

        val adapter = ShortTaskAdapter(mutableListOf())
        binding!!.doneRecycler.adapter = adapter
        binding!!.doneRecycler.layoutManager = GridLayoutManager(requireContext(), 3)

        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.doneTasks.collect {
                    adapter.updateItems(it)
                }
            }
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}