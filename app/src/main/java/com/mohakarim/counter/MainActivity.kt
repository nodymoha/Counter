package com.mohakarim.counter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.os.SystemClock
import android.view.View
import android.view.WindowManager
import android.widget.Button
import android.widget.EditText
import android.widget.Switch
import android.widget.TextView
import androidx.core.view.isVisible
import kotlin.concurrent.timer

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        val saveBtn = findViewById<Button>(R.id.buttonSave)
        val enteredSeconds = findViewById<EditText>(R.id.editSeconds)
        val seconds_text = findViewById<TextView>(R.id.textTimer)
        val hideSwitch = findViewById<Switch>(R.id.switchHide)
        var defSeconds = 0
        var seconds: Int
        var timer: CountDownTimer
        var timerRun = false

        hideSwitch.setOnClickListener {
            if (hideSwitch.isChecked) {
                saveBtn.isVisible = false
                enteredSeconds.isVisible = false
            }
            else {
                saveBtn.isVisible = true
                enteredSeconds.isVisible = true
            }
        }


        saveBtn.setOnClickListener {
            defSeconds = Integer.parseInt(enteredSeconds.getText().toString())
            seconds = defSeconds
            seconds_text.text = seconds.toString()
        }

        seconds_text.setOnClickListener {
            seconds = defSeconds
            timer = object : CountDownTimer(7200000, 1000) {
                override fun onTick(millisUntilFinished: Long) {
                    seconds = seconds - 1
                    seconds_text.text = seconds.toString()
                }

                override fun onFinish() {
                }
            }

            saveBtn.setOnClickListener {
                timer.cancel()
                timerRun = false
                defSeconds = Integer.parseInt(enteredSeconds.getText().toString())
                seconds = defSeconds
                seconds_text.text = seconds.toString()
            }

            while (!timerRun) {
                timer.cancel()
                timer.start()
                timerRun = true
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        getWindow().clearFlags(android.view.WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
    }
}