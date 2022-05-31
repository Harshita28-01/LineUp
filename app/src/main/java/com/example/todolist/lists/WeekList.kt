package com.example.todolist.lists

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.todolist.Todo
import com.example.todolist.TodoAdapter
import com.example.todolist.databinding.FragmentTodayListBinding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase

class WeekList : Fragment() {
    private var _binding: FragmentTodayListBinding?=null
    private val binding get() = _binding!!
    private lateinit var recyclerView: RecyclerView
    private lateinit var todoAdapter: TodoAdapter
    private val database = Firebase.database
    private val myRef = database.getReference("Weeklist")
    var toDolist: MutableList<Todo> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding= FragmentTodayListBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        recyclerView=binding.rvToDoItems
        binding.rvToDoItems.layoutManager= LinearLayoutManager(context)
        getData()
        todoAdapter= TodoAdapter(toDolist,"Weeklist")
        binding.rvToDoItems.adapter=todoAdapter

        binding.btnAddTodo.setOnClickListener{
            val todoTitle=binding.etToDoTitle.text.toString()
            if(todoTitle.isNotEmpty()){
                val todo= Todo(todoTitle)
                todoAdapter.addTodo(todo)
                binding.etToDoTitle.text.clear()
            }
        }

        binding.btnDeleteDoneTodo.setOnClickListener {
            todoAdapter.deleteDoneTodos(toDolist)
        }

    }

    private fun getData() {
        myRef.addValueEventListener(object: ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                println(snapshot)
                if(snapshot.value !=null) {
                    toDolist.clear()
                    for (itemSnapshot in snapshot.children) {
                        val item: Todo? = itemSnapshot.getValue<Todo>()
                        toDolist.add(item!!)
                    }
                }else{
                    toDolist.clear()
                }
                todoAdapter.notifyDataSetChanged();
            }
            override fun onCancelled(error: DatabaseError) {
                throw error.toException();
            }

        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding=null
    }
}