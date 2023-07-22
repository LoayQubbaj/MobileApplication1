package com.example.assignment1

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

data class Item(val name: String, val price: Double)

class MainActivity : AppCompatActivity() {

    private lateinit var itemSpinner: Spinner
    private lateinit var chooseButton: Button
    private lateinit var totalPriceTextView: TextView

    private val items = arrayOf(
        Item("Pizza", 10.0),
        Item("Burger", 8.0),
        Item("Salad", 6.5),
        Item("Taco", 7.0),
        Item("Burrito", 9.5)
    )

    private var totalPrice: Double = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        itemSpinner = findViewById(R.id.spinner)
        chooseButton = findViewById(R.id.btn1)
        totalPriceTextView = findViewById(R.id.txtview2)

        val itemNames = items.map { it.name }
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, itemNames)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        itemSpinner.adapter = adapter

        itemSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(adapterView: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val selectedItem = items[position]
                val selectedItemText = getString(R.string.selected_item_price, selectedItem.name, selectedItem.price)
                totalPriceTextView.text = selectedItemText
            }

            override fun onNothingSelected(adapterView: AdapterView<*>?) {

            }
        }

        chooseButton.setOnClickListener {
            val selectedPosition = itemSpinner.selectedItemPosition
            if (selectedPosition >= 0 && selectedPosition < items.size) {
                val selectedItem = items[selectedPosition]
                totalPrice += selectedItem.price
                val totalPriceText = getString(R.string.total_price, totalPrice)
                totalPriceTextView.text = totalPriceText
            }
        }
    }
}
