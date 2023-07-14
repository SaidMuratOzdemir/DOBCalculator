package com.udemy.dobcalculator

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button = findViewById<Button>(R.id.buttonDatePicker)
        button.setOnClickListener {
            clickDatePicker()
        }
    }

    private fun clickDatePicker() {
        val calendarObj = Calendar.getInstance()
        val year = calendarObj.get(Calendar.YEAR)
        val month = calendarObj.get(Calendar.MONTH)
        val day = calendarObj.get(Calendar.DAY_OF_MONTH)

        val dpd = DatePickerDialog(
            this,
            { _, selectedYear, selectedMonth, selectedDay ->
                val dateInString = "${selectedDay}.${selectedMonth + 1}.$selectedYear"
                val date = findViewById<TextView>(R.id.selectedDate)
                date.text = dateInString

                val sdf = SimpleDateFormat("dd.MM.yyyy", Locale.ENGLISH)
                val theDate = sdf.parse(dateInString)

                // use let to check if theDate is null
                theDate?.let {
                    val selectedDateInMinutes = theDate.time / 60000
                    val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))
                    currentDate?.let {
                        val currentDateInMinutes = currentDate.time / 60000
                        val differenceInMinutes = currentDateInMinutes - selectedDateInMinutes

                        val minute = findViewById<TextView>(R.id.ageInMinutes)
                        minute?.text = differenceInMinutes.toString()
                    }
                }
            },
            year,
            month,
            day
        )
        dpd.datePicker.maxDate = System.currentTimeMillis()
        dpd.show()
    }
}