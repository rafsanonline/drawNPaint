package com.rafsan.drawNpaint

import android.database.DatabaseUtils
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.rafsan.drawnPaint.R
import com.rafsan.drawnPaint.databinding.ActivityModuleOneBinding

class ModuleOneActivity : AppCompatActivity() {
    private var progressValue = MutableLiveData<Int>()
    var progressValueInit = 0

    lateinit var binding: ActivityModuleOneBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_module_one)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_module_one)

        progressValue.observe(this, Observer {
            Log.i("LOTTIE_VALUE",it.toString())
            if (it == 5) {
                binding.lottieAnimationView.progress = "0.0$it".toFloat()
            }
            else {
                binding.lottieAnimationView.progress = "0.$it".toFloat()
            }

        })

    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        var x = event?.x
        var y = event?.y

        when(event?.action) {
            MotionEvent.ACTION_DOWN -> {
                if (progressValueInit<=70) {
                    progressValueInit += 5
                    progressValue.value = progressValueInit
                } else {
                    progressValueInit = 0
                    progressValue.value = progressValueInit
                }

                Log.i("ACTION_DOWN","DOWN $x $y")
            }

            MotionEvent.ACTION_UP -> {

            }

            MotionEvent.ACTION_MOVE -> {

            }

        }
        return true
    }
}

