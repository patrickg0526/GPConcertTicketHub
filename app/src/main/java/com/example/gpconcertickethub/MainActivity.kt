package com.example.gpconcertickethub

import android.os.Bundle
import android.widget.AdapterView
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
//Patrick Gonzalez
//SDC 340 - Concert Ticket Hub Performance Assessment
//07/07/2026
class MainActivity : AppCompatActivity() {

    private val prices = doubleArrayOf(49.99, 45.99, 42.99)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //add the icon to the Action Bar
        val actionBar = supportActionBar
        actionBar?.setIcon(R.mipmap.ic_launcher_foreground)
        actionBar?.setDisplayUseLogoEnabled(true)
        actionBar?.setDisplayShowHomeEnabled(true)

        //get the UI controls
        val selEvent = findViewById<Spinner>(R.id.selEvent)
        val numTickets = findViewById<EditText>(R.id.numTickets)
        val btnCalculate = findViewById<Button>(R.id.btnCalculate)
        val txtTotal = findViewById<TextView>(R.id.txtTotal)

        btnCalculate.setOnClickListener {
            val ticketText = numTickets.text.toString()
            if (ticketText.isEmpty()) {
                Toast.makeText(this, "Please enter a number of tickets", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val ticketCount = ticketText.toIntOrNull()
            if (ticketCount == null || ticketCount <= 0) {
                Toast.makeText(this, "Please enter a valid number of tickets", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val position = selEvent.selectedItemPosition
            val price = prices[position]
            val total = price * ticketCount
            val txtValue = String.format("%.2f", total)
            txtTotal.text = "Total Cost: $$txtValue"
        }

        selEvent.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: android.view.View?, position: Int, id: Long) {
                txtTotal.text = "Total Cost: $0.00"
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }
    }
}
