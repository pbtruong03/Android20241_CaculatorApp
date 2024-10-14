package com.pbtruong03.calculator

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.util.Locale


class MainActivity : AppCompatActivity(){
    private lateinit var display: TextView
    private lateinit var resultDisplay: TextView

    private var currentInput: String = ""
    private var currentOperator: String = ""


    private var operand1: Double? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_constraint)
//
        display = findViewById(R.id.display)
        resultDisplay = findViewById(R.id.resultDisplay)

        // Set click listeners for each button
        findViewById<Button>(R.id.btn_0).setOnClickListener { appendToInput("0") }
        findViewById<Button>(R.id.btn_1).setOnClickListener { appendToInput("1") }
        findViewById<Button>(R.id.btn_2).setOnClickListener { appendToInput("2") }
        findViewById<Button>(R.id.btn_3).setOnClickListener { appendToInput("3") }
        findViewById<Button>(R.id.btn_4).setOnClickListener { appendToInput("4") }
        findViewById<Button>(R.id.btn_5).setOnClickListener { appendToInput("5") }
        findViewById<Button>(R.id.btn_6).setOnClickListener { appendToInput("6") }
        findViewById<Button>(R.id.btn_7).setOnClickListener { appendToInput("7") }
        findViewById<Button>(R.id.btn_8).setOnClickListener { appendToInput("8") }
        findViewById<Button>(R.id.btn_9).setOnClickListener { appendToInput("9") }

        findViewById<Button>(R.id.btn_decimal).setOnClickListener { appendToInput(".") }

        findViewById<Button>(R.id.btn_add).setOnClickListener { setOperator("+") }
        findViewById<Button>(R.id.btn_subtract).setOnClickListener { setOperator("-") }
        findViewById<Button>(R.id.btn_multiply).setOnClickListener { setOperator("*") }
        findViewById<Button>(R.id.btn_divide).setOnClickListener { setOperator("/") }

        findViewById<Button>(R.id.btn_equals).setOnClickListener { calculateResult() }
        findViewById<Button>(R.id.btn_c).setOnClickListener { clearAll() }
        findViewById<Button>(R.id.btn_ce).setOnClickListener { clearCurrent() }
        findViewById<Button>(R.id.btn_bs).setOnClickListener { backspace() }
        findViewById<Button>(R.id.btn_plus_minus).setOnClickListener { togglePlusMinus() }
    }

    private fun appendToInput(value: String) {
        if (value == "." && currentInput.contains(".")) return
        currentInput += value
        display.text = currentInput
    }

    private fun setOperator(operator: String) {
        if (currentInput.isNotEmpty()) {
            operand1 = currentInput.toDoubleOrNull()
            currentInput = ""
            currentOperator = operator
            display.text = currentOperator
        }
    }

    private fun calculateResult() {
        if (operand1 != null && currentInput.isNotEmpty()) {
            val operand2 = currentInput.toDoubleOrNull()
            val result = when (currentOperator) {
                "+" -> operand1!! + operand2!!
                "-" -> operand1!! - operand2!!
                "*" -> operand1!! * operand2!!
                "/" -> operand1!! / operand2!!
                else -> 0.0
            }
            resultDisplay.text = String.format(Locale.getDefault(), "%f", result)
            currentInput = result.toString()
            currentOperator = ""
            operand1 = null
        }
    }

    private fun clearAll() {
        currentInput = ""
        currentOperator = ""
        operand1 = null
        display.text = ""
        resultDisplay.text = "0"
    }

    private fun clearCurrent() {
        currentInput = ""
        display.text = "0"
    }

    private fun backspace() {
        if (currentInput.isNotEmpty()) {
            currentInput = currentInput.dropLast(1)
            display.text = currentInput
        }
    }

    private fun togglePlusMinus() {
        if (currentInput.isNotEmpty()) {
            currentInput = if (currentInput.startsWith("-")) {
                currentInput.substring(1)
            } else {
                "-$currentInput"
            }
            display.text = currentInput
        }
    }
}
