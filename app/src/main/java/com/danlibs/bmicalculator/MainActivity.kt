package com.danlibs.bmicalculator

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.Gravity
import android.widget.Button
import android.widget.LinearLayout
import android.widget.PopupWindow
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.danlibs.bmicalculator.databinding.ActivityMainBinding
import kotlin.math.pow

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setListeners()
    }

    private fun setListeners() {
        binding.btnCalculate.setOnClickListener {
            openPopUpWindow()
        }
    }

    private fun calculateBmi(heightText: String, weightText: String): Float? {
        val height = heightText.toFloatOrNull()
        val weight = weightText.toFloatOrNull()
        if (height != null && weight != null) {
            return weight / (height.pow(2))
        }
        return 0f
    }

    @SuppressLint("InflateParams")
    private fun openPopUpWindow() {
        val popup = PopupWindow(this)
        val view = layoutInflater.inflate(R.layout.activity_result, null)
        popup.width = LinearLayout.LayoutParams.MATCH_PARENT
        popup.contentView = view
        val bmi = calculateBmi(binding.etHeight.text.toString(), binding.etWeight.text.toString())
        view.findViewById<TextView>(R.id.bmi_result).text = getString(R.string.result).format(bmi)
        popup.showAtLocation(binding.txtAppName, Gravity.CENTER, 0, 0)
        binding.btnCalculate.isClickable = false
        view.findViewById<Button>(R.id.btn_close).setOnClickListener {
            popup.dismiss()
            binding.btnCalculate.isClickable = true
        }
    }
}