package com.example.batteryhelper

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.Button
import android.widget.EditText
import kotlin.math.abs
import kotlin.math.round

class MainActivity : AppCompatActivity() {
    private lateinit var bat1Voltage : EditText
    private lateinit var bat2Voltage : EditText
    private lateinit var bat3Voltage : EditText
    private lateinit var bat4Voltage : EditText
    private lateinit var chargeBtn : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // ensure that all input is in format 0.0
        bat1Voltage = findViewById(R.id.bat1Voltage)
        bat2Voltage = findViewById(R.id.bat2Voltage)
        bat3Voltage = findViewById(R.id.bat3Voltage)
        bat4Voltage = findViewById(R.id.bat4Voltage)
        chargeBtn = findViewById(R.id.chargeBtn)

        chargeBtn.setOnClickListener {
            var bat1 = 0.0
            var bat2 = 0.0
            var bat3 = 0.0
            var bat4 = 0.0
            try {
                bat1 = bat1Voltage.text.toString().toDouble()
                bat2 = bat2Voltage.text.toString().toDouble()
                bat3 = bat3Voltage.text.toString().toDouble()
                bat4 = bat4Voltage.text.toString().toDouble()
            } catch (e: NumberFormatException) {
                Log.i("battery3", "input is not a decimal number")
            }
            if (round2Decimal(bat1 - bat2) > 0.1 || round2Decimal(bat1 - bat3) > 0.1 || round2Decimal(bat1 - bat4) > 0.1) {
                // add cell count input
                Log.i("battery3", "Not able to charge current configuration. Voltage too far apart from each other.")
            } else if (round2Decimal(bat2 - bat3) > 0.1 || round2Decimal(bat2 - bat4) > 0.1 || round2Decimal(bat3 - bat4) > 0.1) {
                Log.i("battery3", "Not able to charge current configuration. Voltage too far apart from each other.")
            } else {
                Log.i("battery3", "Able to charge!")
            }
        }

    }
    private fun round2Decimal (d: Double): Double {
        return round(abs(d)* 100) / 100
    }


}