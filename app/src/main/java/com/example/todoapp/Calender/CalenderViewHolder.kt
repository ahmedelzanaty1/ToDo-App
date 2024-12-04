package com.example.todoapp.Calender

import android.view.View
import com.example.todoapp.databinding.CalenderViewDayBinding
import com.kizitonwose.calendar.view.ViewContainer

class CalenderViewHolder(val binding: CalenderViewDayBinding) : ViewContainer(binding.root) {
    val dayname = binding.dayName
    val daynumber = binding.dayNumber
}