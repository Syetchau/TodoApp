package com.example.notesapp.ui

import android.os.Bundle
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.example.notesapp.R
import com.example.notesapp.databinding.FragmentAddToDoBinding
import com.example.notesapp.model.Todo
import com.example.notesapp.viewmodel.TodoViewModel
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddToDoFragment : Fragment(R.layout.fragment_add_to_do) {

    private var _binding: FragmentAddToDoBinding?= null
    private val binding get() = _binding!!
    private val viewModel: TodoViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View{
        _binding = FragmentAddToDoBinding.inflate(inflater, container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initEvent()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun initEvent() {
        binding.btnCancel.setOnClickListener {
            view?.findNavController()!!.navigate(R.id.action_addToDoFragment_to_toDoListFragment)
        }

        binding.btnAddTodo.setOnClickListener{ mView ->
            saveTodo(mView)
        }
    }

    private fun saveTodo(mView: View) {
        val todoTitleName = binding.etTodoTitle.text.toString()
        if (todoTitleName.isNotEmpty()) {
            val todo = Todo(0, todoTitleName)
            viewModel.insertTodo(todo)

            Snackbar.make(mView, "Todo added", Snackbar.LENGTH_LONG).show()

            mView.findNavController().navigate(R.id.action_addToDoFragment_to_toDoListFragment)

        } else {
            val toast = Toast.makeText(activity, "Todo Title cannot be empty", Toast.LENGTH_SHORT)
            toast.setGravity(Gravity.CENTER, 0,0)
            toast.show()
        }
    }
}