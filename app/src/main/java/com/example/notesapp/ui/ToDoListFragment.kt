package com.example.notesapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.notesapp.MyApplication
import com.example.notesapp.R
import com.example.notesapp.adapter.TodoAdapter
import com.example.notesapp.databinding.FragmentToDoListBinding
import com.example.notesapp.model.Todo
import com.example.notesapp.utils.OnClickListener
import com.example.notesapp.viewmodel.TodoViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ToDoListFragment : Fragment(R.layout.fragment_to_do_list), OnClickListener {

    private var _binding: FragmentToDoListBinding?= null
    private val binding get() = _binding!!
    private val viewModel: TodoViewModel by viewModels()
    private lateinit var todoAdapter: TodoAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentToDoListBinding.inflate(inflater, container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRv()

        binding.fabAddTodo.setOnClickListener {
            view.findNavController().navigate(R.id.action_toDoListFragment_to_addToDoFragment)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun setupRv() {
        todoAdapter = TodoAdapter()
        binding.rvTodoList.apply {
            adapter = todoAdapter
            todoAdapter.setListener(this@ToDoListFragment)
            layoutManager = LinearLayoutManager(activity)
            setHasFixedSize(true)
        }

        viewModel.getAllTodoList.observe(viewLifecycleOwner, { todoList ->
            updateUI(todoList)
            todoAdapter.differ.submitList(todoList)
        })
    }

    private fun updateUI(todoList: List<Todo>) {
        if (todoList.isNotEmpty()) {
            binding.rvTodoList.visibility = View.VISIBLE
            binding.cvTodoNoAdd.visibility = View.GONE
        } else {
            binding.rvTodoList.visibility = View.GONE
            binding.cvTodoNoAdd.visibility = View.VISIBLE
        }
    }

    override fun onClick(todo: Todo, position: Int) {
        viewModel.deleteTodoList(todo)
    }
}