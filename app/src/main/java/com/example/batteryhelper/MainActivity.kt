package com.example.batteryhelper

import android.os.Bundle
import android.util.Log
import android.widget.*
import android.widget.SeekBar.OnSeekBarChangeListener
import androidx.appcompat.app.AppCompatActivity
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
    private lateinit var cellCountBar : SeekBar
    private lateinit var ampRateValue : TextView
    private lateinit var estTimeValue : TextView
    private lateinit var cellCountValue : TextView
    private lateinit var batCapValue : EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        bat1Voltage = findViewById(R.id.bat1Voltage)
        bat2Voltage = findViewById(R.id.bat2Voltage)
        bat3Voltage = findViewById(R.id.bat3Voltage)
        bat4Voltage = findViewById(R.id.bat4Voltage)
        bat1label = findViewById(R.id.bat1label)
        bat2label = findViewById(R.id.bat2label)
        bat3label = findViewById(R.id.bat3label)
        bat4label = findViewById(R.id.bat4label)
        cellCountBar = findViewById(R.id.cellCountBar)
        ampRateValue = findViewById(R.id.ampRateValue)
        estTimeValue = findViewById(R.id.estTimeValue)
        chargeBtn = findViewById(R.id.chargeBtn)
        cellCountValue = findViewById(R.id.cellCountValue)
        batCapValue = findViewById(R.id.batCapValue)

        cellCountBar.setOnSeekBarChangeListener(object : OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                cellCountValue.text = progress.toString()
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {}

            override fun onStopTrackingTouch(seekBar: SeekBar) {}
        })

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
            updateAmpRate(bat1, bat2, bat3, bat4)
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
    private fun updateAmpRate (d1: Double, d2: Double, d3: Double, d4: Double) {
        var numOfBat = 0
        if (d1 >= 3) numOfBat++
        if (d2 >= 3) numOfBat++
        if (d3 >= 3) numOfBat++
        if (d4 >= 3) numOfBat++
        var amps = batCapValue.text.toString().toDouble() * numOfBat
        ampRateValue.text = (round2Decimal(amps) / 1000).toString()
        var est = amps / (amps * 0.10)
        estTimeValue.text = "$est m"
    }
    private fun checkBatteries (d1: Double, d2: Double, d3: Double, d4: Double) {
        var bound: Double = cellCountValue.text.toString().toDouble() * 0.1
        if (d1 >= 3 && (round2Decimal(d1 - d2) <= bound && round2Decimal(d1 - d3) <= bound && round2Decimal(d1 - d4) <= bound) ) {
            setCheckmark(bat1label, true)
        } else {
            setCheckmark(bat1label, false)
        }
        if (d2 >= 3 && (round2Decimal(d2 - d1) <= bound && round2Decimal(d2 - d3) <= bound && round2Decimal(d2 - d4) <= bound) ) {
            setCheckmark(bat2label, true)
        } else {
            setCheckmark(bat2label, false)
        }
        if (d3 >= 3 && (round2Decimal(d3 - d1) <= bound && round2Decimal(d3 - d2) <= bound && round2Decimal(d3 - d4) <= bound) ) {
            setCheckmark(bat3label, true)
        } else {
            setCheckmark(bat3label, false)
        }
        if (d4 >= 3 && (round2Decimal(d4 - d1) <= bound && round2Decimal(d4 - d2) <= bound && round2Decimal(d4 - d3) <= bound) ) {
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