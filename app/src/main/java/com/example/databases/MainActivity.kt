package com.example.databases

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity()
{
    lateinit var btnInsert : Button
    lateinit var btnSubmit : Button

    lateinit var edttxtLaptop : TextView
    lateinit var edttxtmModelName : TextView
    lateinit var edttxtProcessorName : TextView
    lateinit var edttxtLaptopPrice : TextView
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
//        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_main)

        initview()
    }

    private fun initview()
    {
        var dataBaseHelper = DataBaseHelper(this,"Laptop.db",null,1)

        btnInsert = findViewById(R.id.btnInsert)
        btnSubmit = findViewById(R.id.btnSubmit)

        edttxtLaptop = findViewById(R.id.edttxtLaptop)
        edttxtmModelName = findViewById(R.id.edttxtmModelName)
        edttxtProcessorName = findViewById(R.id.edttxtProcessorName)
        edttxtLaptopPrice = findViewById(R.id.edttxtLaptopPrice)

        btnInsert.setOnClickListener {
            val name = edttxtLaptop.text.toString()
            val modelName = edttxtmModelName.text.toString()
            val ProcessorName = edttxtProcessorName.text.toString()
            val LaptopPrice = edttxtLaptopPrice.text.toString().toInt()

            dataBaseHelper.insertData(name,modelName,ProcessorName,LaptopPrice)

        }

        btnSubmit.setOnClickListener {
            var i = Intent(this@MainActivity,DataDisplayActivity::class.java)
            startActivity(i)
        }
    }
}