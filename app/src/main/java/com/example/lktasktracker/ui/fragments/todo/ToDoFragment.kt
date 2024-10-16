package com.example.lktasktracker.ui.fragments.todo

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
import com.example.lktasktracker.databinding.TodoFragmentBinding
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class ToDoFragment : Fragment() {
    private var binding: TodoFragmentBinding? = null
    private val viewModel: ToDoViewModel by activityViewModels(
        factoryProducer = { ToDoViewModel.Factory },
        extrasProducer = {
            MutableCreationExtras().apply {
                set(ToDoViewModel.APPLICATION_KEY, requireActivity().application)
            }
        })

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = TodoFragmentBinding.inflate(inflater, container, false)
        val root: View = binding!!.root

        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.counter.collect {
                    binding!!.counter.text = "ToDo $it"
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