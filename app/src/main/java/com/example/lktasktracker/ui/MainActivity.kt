package com.example.lktasktracker.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import com.example.lktasktracker.R
import com.example.lktasktracker.databinding.ActivityMainBinding
import com.example.lktasktracker.ui.fragments.done.DoneFragment
import com.example.lktasktracker.ui.fragments.todo.ToDoFragment

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val toDoFragment = ToDoFragment()
    private val doneFragment = DoneFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navigation = binding.bottomNavigation
        navigation.setOnItemSelectedListener{
            when (it.itemId) {
                R.id.todo_page -> {
                    replaceFragment(toDoFragment)
                    true
                }
                R.id.done_page -> {
                    replaceFragment(doneFragment)
                    true
                }
                else -> false
            }
        }

        navigation.selectedItemId = R.id.todo_page
    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.commit {
            replace(binding.fragmentContainer.id, fragment)
            setReorderingAllowed(true)
        }
    }
}