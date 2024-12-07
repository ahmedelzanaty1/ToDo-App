
package com.example.todoapp.Fragments

import android.graphics.Color
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import com.example.todoapp.Calender.CalenderViewHolder
import com.example.todoapp.DataBase.TaskDataBase
import com.example.todoapp.adapters.TasksAdapter
import com.example.todoapp.databinding.CalenderViewDayBinding
import com.example.todoapp.databinding.FragmentListBinding
import com.kizitonwose.calendar.core.WeekDay
import com.kizitonwose.calendar.core.atStartOfMonth
import com.kizitonwose.calendar.core.firstDayOfWeekFromLocale
import com.kizitonwose.calendar.view.WeekDayBinder
import java.time.LocalDate
import java.time.YearMonth
import java.time.format.TextStyle
import java.util.Locale

class ListFragment : Fragment() {
    lateinit var binding: FragmentListBinding
    lateinit var adapter: TasksAdapter
    private var previousSelectedView: View? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        calenderview()
        getalltask()

    }

    fun calenderview() {
        val currentDate = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            LocalDate.now()
        } else {
            TODO("VERSION.SDK_INT < O")
        }
        val currentMonth = YearMonth.now()
        val startDate = currentMonth.minusMonths(100).atStartOfMonth()
        val endDate = currentMonth.plusMonths(100).atEndOfMonth()
        val firstDayOfWeek = firstDayOfWeekFromLocale()
        binding.calenderView.setup(startDate, endDate, firstDayOfWeek)
        binding.calenderView.scrollToWeek(currentDate)
        binding.calenderView.dayBinder = object : WeekDayBinder<CalenderViewHolder> {
            override fun bind(container: CalenderViewHolder, data: WeekDay) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

                    container.dayname.text = data.date.dayOfWeek.getDisplayName(TextStyle.SHORT, Locale.getDefault())
                    container.daynumber.text = data.date.dayOfMonth.toString()
                        container.dayname.setOnClickListener {
                        filterTasksByDate(data.date)
                    }
                }
            }
            override fun create(view: View): CalenderViewHolder {
                val binding = CalenderViewDayBinding.bind(view)
                return CalenderViewHolder(binding)
            }
        }
    }

    fun getalltask() {
        adapter = TasksAdapter(TaskDataBase.getDatabase(requireContext()).getdao().getalltasks())
        binding.recycler.adapter = adapter
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun filterTasksByDate(date: LocalDate) {
        val selectedDate = java.sql.Date.valueOf(date.toString())
        val filteredTasks = TaskDataBase.getDatabase(requireContext()).getdao().selectdate(selectedDate)
        adapter.updateData(filteredTasks)
    }
}
