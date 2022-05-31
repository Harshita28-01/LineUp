package com.example.todolist

import android.graphics.Paint.STRIKE_THRU_TEXT_FLAG
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.item_todo.view.*

class TodoAdapter(private val todos:MutableList<Todo>,val listName:String) :RecyclerView.Adapter<TodoAdapter.TodoViewHolder>(){
    private val database = Firebase.database
    private val myRef = database.getReference(listName)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        return TodoViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_todo, parent, false)
        )
    }

    fun addTodo(todo:Todo){
//        todos.add(todo)
        //insert item at the end
//        notifyItemInserted(todos.size-1)
        myRef.child(todo.title.toString()).setValue(todo)
    }

    fun deleteDoneTodos(todoList:MutableList<Todo>){
        for(item in todoList){
            if(item.isChecked){
                myRef.child(item.title.toString()).removeValue()
            }
        }
        //refreshes whole list
        notifyDataSetChanged()
    }

    private fun toggleStrikeThrough(tvTodoTitle: TextView, isChecked:Boolean){
        if(isChecked){
            tvTodoTitle.paintFlags=tvTodoTitle.paintFlags or STRIKE_THRU_TEXT_FLAG
        }else{
            tvTodoTitle.paintFlags=tvTodoTitle.paintFlags and STRIKE_THRU_TEXT_FLAG.inv()
        }
    }
    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        val currentTodo=todos[position]
        holder.itemView.apply{
            tvTodoTitle.text=currentTodo.title
            cbDone.isChecked=currentTodo.isChecked
            toggleStrikeThrough(tvTodoTitle,currentTodo.isChecked)

            cbDone.setOnCheckedChangeListener { _, isChecked ->
                toggleStrikeThrough(tvTodoTitle,isChecked)
                currentTodo.isChecked=!currentTodo.isChecked
                myRef.child(tvTodoTitle.text.toString()).child("checked").setValue(isChecked)
            }
        }
    }

    override fun getItemCount(): Int {
        return todos.size;
    }

    class TodoViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

    }
}

