package com.example.databases

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.databases.Adapter.LaptopAdaper

class DataDisplayActivity : AppCompatActivity() {
    lateinit var myAdapater : LaptopAdaper
    lateinit var databaseHelper : DataBaseHelper
    var list : ArrayList<Model> = ArrayList()
    lateinit var recyclerview : RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_data_display)


        initview()
    }

    private fun initview() {
        databaseHelper = DataBaseHelper(this, "Laptop.db", null, 1)

        recyclerview = findViewById(R.id.recyclerview)
        myAdapater = LaptopAdaper(list , object : Myinterface
        {

            override fun edit(id: Int, name: String, modelName: String, ProcessorName: String, price: Int) {
                var i = Intent(this@DataDisplayActivity,UpdateActivity::class.java)
                i.putExtra("id",id)
                i.putExtra("name",name)
                i.putExtra("modelName",modelName)
                i.putExtra("processorName",ProcessorName)
                i.putExtra("price",price)
                startActivity(i)
            }

            override fun delete(id: Int)
            {
                databaseHelper.deleteRecord(id)
                var list = databaseHelper.displayRecord()

                val builder = AlertDialog.Builder(this@DataDisplayActivity)
                builder.setTitle("Delete")
                builder.setMessage("Are you sure you want to Delete ")
                builder.setPositiveButton("Yes")
                { dialogInterface, i ->
                    Toast.makeText(this@DataDisplayActivity,"yes", Toast.LENGTH_SHORT).show()
                    myAdapater.updateNewData(list)
                }

                builder.setNegativeButton("No")
                { dialogInterface, i ->
                    Toast.makeText(this@DataDisplayActivity,"no", Toast.LENGTH_SHORT).show()
                }

                builder.setCancelable(false)
                builder.show()
            }
        })

        var manager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false)
        recyclerview.layoutManager = manager
        recyclerview.adapter = myAdapater
    }

    override fun onResume() {
        super.onResume()
        var list = databaseHelper.displayRecord()

        myAdapater.updateNewData(list)
    }
}