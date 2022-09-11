package com.example.batteryhelper

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.CheckedTextView
import android.widget.EditText
import androidx.core.widget.addTextChangedListener
import kotlin.math.abs
import kotlin.math.round

class MainActivity : AppCompatActivity() {
    private lateinit var bat1Voltage : EditText
    private lateinit var bat2Voltage : EditText
    private lateinit var bat3Voltage : EditText
    private lateinit var bat4Voltage : EditText
    private lateinit var bat1label : CheckedTextView
    private lateinit var bat2label : CheckedTextView
    private lateinit var bat3label : CheckedTextView
    private lateinit var bat4label : CheckedTextView
    private lateinit var chargeBtn : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // ensure that all input is in format 0.0
        bat1Voltage = findViewById(R.id.bat1Voltage)
        bat2Voltage = findViewById(R.id.bat2Voltage)
        bat3Voltage = findViewById(R.id.bat3Voltage)
        bat4Voltage = findViewById(R.id.bat4Voltage)
        bat1label = findViewById(R.id.bat1label)
        bat2label = findViewById(R.id.bat2label)
        bat3label = findViewById(R.id.bat3label)
        bat4label = findViewById(R.id.bat4label)

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
                checkBatteries(bat1, bat2, bat3, bat4)
            }

            checkBatteries(bat1, bat2, bat3, bat4)
        }

        bat1Voltage.addTextChangedListener{
            setCheckmark(bat1label, false)
            setCheckmark(bat2label, false)
            setCheckmark(bat3label, false)
            setCheckmark(bat4label, false)
        }
        bat2Voltage.addTextChangedListener{
            setCheckmark(bat1label, false)
            setCheckmark(bat2label, false)
            setCheckmark(bat3label, false)
            setCheckmark(bat4label, false)
        }
        bat3Voltage.addTextChangedListener{
            setCheckmark(bat1label, false)
            setCheckmark(bat2label, false)
            setCheckmark(bat3label, false)
            setCheckmark(bat4label, false)
        }
        bat4Voltage.addTextChangedListener{
            setCheckmark(bat1label, false)
            setCheckmark(bat2label, false)
            setCheckmark(bat3label, false)
            setCheckmark(bat4label, false)
        }

    }
    private fun checkBatteries (d1: Double, d2: Double, d3: Double, d4: Double) {
        if (d1 >= 3 && (round2Decimal(d1 - d2) <= 0.1 && round2Decimal(d1 - d3) <= 0.1 && round2Decimal(d1 - d4) <= 0.1) ) {
            setCheckmark(bat1label, true)
        } else {
            setCheckmark(bat1label, false)
        }
        if (d2 >= 3 && (round2Decimal(d2 - d1) <= 0.1 && round2Decimal(d2 - d3) <= 0.1 && round2Decimal(d2 - d4) <= 0.1) ) {
            setCheckmark(bat2label, true)
        } else {
            setCheckmark(bat2label, false)
        }
        if (d3 >= 3 && (round2Decimal(d3 - d1) <= 0.1 && round2Decimal(d3 - d2) <= 0.1 && round2Decimal(d3 - d4) <= 0.1) ) {
            setCheckmark(bat3label, true)
        } else {
            setCheckmark(bat3label, false)
        }
        if (d4 >= 3 && (round2Decimal(d4 - d1) <= 0.1 && round2Decimal(d4 - d2) <= 0.1 && round2Decimal(d4 - d3) <= 0.1) ) {
            setCheckmark(bat4label, true)
        } else {
            setCheckmark(bat4label, false)
        }
    }
    private fun round2Decimal (d: Double): Double {
        return round(abs(d)* 100) / 100
    }
    private fun setCheckmark (item: CheckedTextView, state: Boolean): Boolean {
        item.setChecked(state)
        if (state) {
            item.setCheckMarkDrawable(R.drawable.checked)
        } else {
            item.setCheckMarkDrawable(R.drawable.error)
        }
        return state
    }


}