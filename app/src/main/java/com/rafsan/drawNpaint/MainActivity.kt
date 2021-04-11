package com.rafsan.drawNpaint
import android.graphics.*
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import com.rafsan.drawnPaint.R
import com.rafsan.drawnPaint.databinding.ActivityMainBinding
import com.rafsan.drawNpaint.utilities.DrawingFlags
class MainActivity : AppCompatActivity(){
    lateinit var binding: ActivityMainBinding
    var canvasWidth = 0
    var canvasHeight = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        manageColorClicks()
       // manageDraw()

        binding.buttonArt.setOnClickListener {
            it.background = ContextCompat.getDrawable(this, R.drawable.ic_brush_pencil_selected)
            binding.red.background = ContextCompat.getDrawable(this, R.drawable.red_back_unselected)
            binding.green.background = ContextCompat.getDrawable(this,
                R.drawable.green_back_unselected
            )
            binding.blue.background = ContextCompat.getDrawable(this,
                R.drawable.blue_back_unselected
            )
            DrawingFlags.DRAW = true
        }

        binding.buttonReset.setOnClickListener {
            binding.myDrawView.clear()
            binding.red.background = ContextCompat.getDrawable(this, R.drawable.red_back_unselected)
            binding.green.background = ContextCompat.getDrawable(this,
                R.drawable.green_back_unselected
            )
            binding.blue.background = ContextCompat.getDrawable(this,
                R.drawable.blue_back_unselected
            )
        }
    }

    private fun manageDrawTwo() {
        DrawingFlags.BITMAP_SELECTED = binding.myDrawView.bitmap
    }

    private fun manageColorClicks() {
        binding.red.setOnClickListener {
            DrawingFlags.DRAW = false
            manageDrawTwo()
            binding.buttonArt.background = ContextCompat.getDrawable(this,
                R.drawable.ic_brush_pencil
            )
            DrawingFlags.COLOR_SELECTED = Color.parseColor("#E91E63")
            it.background = ContextCompat.getDrawable(this, R.drawable.red_back)
            binding.green.background = ContextCompat.getDrawable(
                this,
                R.drawable.green_back_unselected
            )
            binding.blue.background = ContextCompat.getDrawable(
                this,
                R.drawable.blue_back_unselected
            )
        }

        binding.green.setOnClickListener {
            DrawingFlags.DRAW = false
            manageDrawTwo()
            DrawingFlags.COLOR_SELECTED = Color.parseColor("#4CAF50")
            binding.buttonArt.background = ContextCompat.getDrawable(this,
                R.drawable.ic_brush_pencil
            )
            it.background = ContextCompat.getDrawable(this, R.drawable.green_back)
            binding.red.background = ContextCompat.getDrawable(this, R.drawable.red_back_unselected)
            binding.blue.background = ContextCompat.getDrawable(
                this,
                R.drawable.blue_back_unselected
            )
        }

        binding.blue.setOnClickListener {
            DrawingFlags.DRAW = false
            manageDrawTwo()
            DrawingFlags.COLOR_SELECTED = Color.parseColor("#673AB7")
            it.background = ContextCompat.getDrawable(this, R.drawable.blue_back)
            binding.buttonArt.background = ContextCompat.getDrawable(this,
                R.drawable.ic_brush_pencil
            )
            binding.red.background = ContextCompat.getDrawable(this, R.drawable.red_back_unselected)
            binding.green.background = ContextCompat.getDrawable(
                this,
                R.drawable.green_back_unselected
            )
        }
    }

}