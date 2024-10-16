package com.example.lktasktracker.ui.fragments.todo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.lktasktracker.databinding.TodoFragmentBinding

class ToDoFragment : Fragment() {
    private var binding: TodoFragmentBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = TodoFragmentBinding.inflate(inflater, container, false)
        val root: View = binding!!.root

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}