package com.example.databases

interface Myinterface
{
    fun edit(id: Int, name: String, modelName: String, ProcessorName: String, price: Int)

    fun delete(id : Int)
}