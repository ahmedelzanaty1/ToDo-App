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

class HomeActivity : AppCompatActivity() {
    lateinit var binding : ActivityHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.bottomAppBar.setOnItemSelectedListener {
            when(it.itemId){
                R.id.list_ic -> pushfragment(ListFragment())
                R.id.setting_ic -> pushfragment(SettingFragment())
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
            float.show(supportFragmentManager,"")

        }
    }

}