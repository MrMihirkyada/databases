package com.example.databases.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.databases.Model
import com.example.databases.Myinterface
import com.example.databases.R

class LaptopAdaper(var list: ArrayList<Model>, var myinterface: Myinterface) : RecyclerView.Adapter<LaptopAdaper.MyViewHolder>()
{
    class MyViewHolder(view : View) : RecyclerView.ViewHolder(view)
    {
        var txtId : TextView = view.findViewById(R.id.txtId)
        var txtname : TextView = view.findViewById(R.id.txtname)
        var txtmodelname : TextView = view.findViewById(R.id.txtmodelname)
        var txtprocessorName : TextView = view.findViewById(R.id.txtprocessorName)
        var txtmodelprice : TextView = view.findViewById(R.id.txtmodelprice)
        var imgEdit : ImageView = view.findViewById(R.id.imgEdit)
        var imgDelete : ImageView = view.findViewById(R.id.imgDelete)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder
    {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.laptop_item,parent,false)
        var holder = MyViewHolder(view)
        return holder
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int)
    {
        holder.txtId.text = list.get(position).id.toString()
        holder.txtname.text = list.get(position).name
        holder.txtmodelname.text = list.get(position).modelName
        holder.txtprocessorName.text = list.get(position).processorName
        holder.txtmodelprice.text = list.get(position).price.toString()

        holder.imgEdit.setOnClickListener{
            myinterface.edit(list[position].id, list[position].name, list[position].modelName,list[position].processorName ,list[position].price)
        }

        holder.imgDelete.setOnClickListener {

            myinterface.delete(list[position].id)

        }
    }

    override fun getItemCount(): Int
    {
        return list.size
    }

    fun updateNewData(list: ArrayList<Model>)
    {
        this.list.clear()
        this.list = list
        notifyDataSetChanged()
    }
}