package com.example.todolist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.example.todolist.databinding.ActivityMainBinding



class MainActivity : AppCompatActivity() {

//    private lateinit var todoAdapter:TodoAdapter
//    val database = Firebase.database
//    val myRef = database.getReference("list")
//    var toDolist: MutableList<Todo> = mutableListOf()

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController

        //Initially list will be empty
//        todoAdapter= TodoAdapter(mutableListOf())


//        rvToDoItems.layoutManager=LinearLayoutManager(this)

//        getData()
//        todoAdapter=TodoAdapter(toDolist)
//        rvToDoItems.adapter=todoAdapter
//
//        btnAddTodo.setOnClickListener{
//            val todoTitle=etToDoTitle.text.toString()
//            if(todoTitle.isNotEmpty()){
//                val todo=Todo(todoTitle)
//                todoAdapter.addTodo(todo)
//                etToDoTitle.text.clear()
//            }
//        }
//
//        btnDeleteDoneTodo.setOnClickListener {
//            todoAdapter.deleteDoneTodos(toDolist)
//        }

    }
    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
//    fun getData(){
//
//        myRef.addValueEventListener(object:ValueEventListener{
//            override fun onDataChange(snapshot: DataSnapshot) {
//                println(snapshot)
//                if(snapshot.value !=null) {
//                    toDolist.clear()
//                    for (itemSnapshot in snapshot.children) {
//                        val item: Todo? = itemSnapshot.getValue<Todo>()
//                        toDolist.add(item!!)
//                    }
//                }else{
//                    toDolist.clear()
//                }
//                todoAdapter.notifyDataSetChanged();
//            }
//            override fun onCancelled(error: DatabaseError) {
//                throw error.toException();
//            }
//
//        })
//
//    }
}