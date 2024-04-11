package com.example.databases

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteDatabase.CursorFactory
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

class DataBaseHelper(context: Context, name: String, factory: CursorFactory?, version: Int) : SQLiteOpenHelper(context, name, factory, version)
{
    override fun onCreate(db: SQLiteDatabase?)
    {
        var sql = "create table LaptopTb (laptopId integer primary Key autoincrement,name text,modelName text,processorName txet,Price integer)"
        db?.execSQL(sql)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int)
    {

    }

    fun insertData(name : String,modelname : String,processorName : String,price : Int)
    {
        var db = writableDatabase

        var c = ContentValues()
        c.put("name",name)
        c.put("modelName",modelname)
        c.put("processorName",processorName)
        c.put("price",price)

        Log.e("TAG", "insertData: "+name+modelname+processorName+price)
        db.insert("LaptopTb",null,c)
    }

    fun displayRecord(): ArrayList<Model>
    {
        var list: ArrayList<Model> = ArrayList()
        var db=readableDatabase
        var sql="select * from LaptopTb"
        var cursor : Cursor =db.rawQuery(sql,null)

        if(cursor.moveToFirst())
        {
            do
            {
                var id=  cursor.getInt(0)
                var name=  cursor.getString(1)
                var modelName=  cursor.getString(2)
                var processorName=  cursor.getString(3)
                var price=  cursor.getInt(4)

                Log.e("TAG", "displayRecord: "+ id +" "+name+" "+modelName+" "+processorName+" "+price)

                var model=Model(id,name,modelName,processorName,price)
                list.add(model)

            }while (cursor.moveToNext())
        }
        return list
    }

    fun deleteRecord(id : Int)
    {
        var db = writableDatabase
        var sql ="delete from LaptopTb where laptopId = '"+ id +"' "
        db.execSQL(sql)
    }

    fun updateRecord(id : Int,name : String,modelName : String,processorName: String,price : Int)
    {
        var db = writableDatabase
        var sql ="update LaptopTb set name='"+name+"',modelName='"+modelName+"',processorName = '"+processorName+"',price='"+price+"' where laptopId='"+id+"'"

        db.execSQL(sql)
    }
}