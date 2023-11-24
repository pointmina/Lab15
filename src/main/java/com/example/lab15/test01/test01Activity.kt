package com.example.lab15.test01

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.lab15.R
import com.example.lab15.databinding.ActivityTest01Binding

class test01Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityTest01Binding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.button1.setOnClickListener{
            val intent = Intent(this, MyService::class.java)
            startService(intent)
        }

        binding.button2.setOnClickListener{
            val intent = Intent(this, MyService::class.java)
            stopService(intent)
        }



    }
}