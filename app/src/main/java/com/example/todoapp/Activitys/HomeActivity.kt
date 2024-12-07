package com.example.todoapp.Activitys

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.example.todoapp.FloatingActionButton.AddTask
import com.example.todoapp.Fragments.ListFragment
import com.example.todoapp.Fragments.SettingFragment
import com.example.todoapp.R
import com.example.todoapp.databinding.ActivityHomeBinding
import com.example.todoapp.pack_interface.AddedTask

class HomeActivity : AppCompatActivity() {
    lateinit var binding : ActivityHomeBinding
    lateinit var listfragment: ListFragment
    lateinit var settingfragment: SettingFragment
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        listfragment = ListFragment()
        settingfragment = SettingFragment()
        binding.bottomAppBar.setOnItemSelectedListener {
            when(it.itemId){
                R.id.list_ic -> pushfragment(listfragment)
                R.id.setting_ic -> pushfragment(settingfragment)
            }
            return@setOnItemSelectedListener true
        }
        binding.bottomAppBar.selectedItemId = R.id.list_ic
        floatingbutton()


    }
    fun pushfragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().replace(binding.fragments.id, fragment).commit()
    }
    fun floatingbutton(){
        binding.fab.setOnClickListener {
            val float = AddTask()
            float.addedtask = object : AddedTask{

                override fun addtask() {
                    if(listfragment.isVisible)
                    listfragment.getalltask()
                }
            }
            float.show(supportFragmentManager,"")

        }
    }

}