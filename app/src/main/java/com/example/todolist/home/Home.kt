package com.example.todolist.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.example.todolist.databinding.FragmentHomeBinding

class Home : Fragment() {
    private var _binding: FragmentHomeBinding?=null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding= FragmentHomeBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.today.setOnClickListener {
            val action=HomeDirections.actionHomeToTodayList()
            view.findNavController().navigate(action)
        }
        binding.weekly.setOnClickListener {
            val action=HomeDirections.actionHomeToWeekList()
            view.findNavController().navigate(action)
        }
        binding.monthly.setOnClickListener{
            val action=HomeDirections.actionHomeToMonthList()
            view.findNavController().navigate(action)
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding=null
    }
}