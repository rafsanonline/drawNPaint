package com.rafsan.drawNpaint

import android.content.Intent
import android.database.DatabaseUtils
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.rafsan.drawnPaint.R
import com.rafsan.drawnPaint.databinding.ActivityInitBinding
import com.rafsan.drawnPaint.databinding.ActivityModuleTwoBinding

class InitActivity : AppCompatActivity() {
    lateinit var binding: ActivityInitBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_init)

        binding = DataBindingUtil.setContentView(this,R.layout.activity_init)
        binding.module1.setOnClickListener {
            startActivity(Intent(this,ModuleOneActivity::class.java))
        }

        binding.module2.setOnClickListener {
            startActivity(Intent(this,ModuleTwoActivity::class.java))
        }
    }
}