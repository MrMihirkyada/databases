package com.example.databases

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class UpdateActivity : AppCompatActivity()
{
    var id : Int = 0

    lateinit var edtName : EditText
    lateinit var edtmodelName : EditText
    lateinit var edtProcessorName : EditText
    lateinit var edtprice : EditText
    lateinit var btnUpdate : Button

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update)

        initview()
    }

    private fun initview() {

        var databaseHelper = DataBaseHelper(this, "Laptop.db", null, 1)

        edtName = findViewById(R.id.edtName)
        edtmodelName = findViewById(R.id.edtmodelName)
        edtProcessorName = findViewById(R.id.edtProcessorName)
        edtprice = findViewById(R.id.edtprice)
        btnUpdate = findViewById(R.id.btnUpdate)

        if(intent != null)
        {
            id = intent.getIntExtra("id",0)

            var name = intent.getStringExtra("name")
            var modelName = intent.getStringExtra("modelName")
            var processorName = intent.getStringExtra("processorName")
            var price = intent.getIntExtra("price",0)

            edtName.setText(name)
            edtmodelName.setText(modelName)
            edtProcessorName.setText(processorName)
            edtprice.setText(price.toString())
        }

        btnUpdate.setOnClickListener {
            var name = edtName.text.toString()
            var modelName = edtmodelName.text.toString()
            var processorName = edtProcessorName.text.toString()
//            var price = edtprice.toString().toInt()

            var price = 0
            try {
                price = edtprice.text.toString().toInt()
            } catch (e: NumberFormatException) {
                // Handle the case where the text is not a valid integer
                Toast.makeText(this, "Please enter a valid price", Toast.LENGTH_SHORT).show()
                return@setOnClickListener  // Exit the listener to prevent further execution
            }

            databaseHelper.updateRecord(id, name, modelName,processorName, price)
            Toast.makeText(this, "record update successfully", Toast.LENGTH_SHORT).show()

            finish()
        }
    }
}