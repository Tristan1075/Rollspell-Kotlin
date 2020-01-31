package com.example.rollspellkotlin.Adapter

import android.content.res.Resources
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.rollspellkotlin.Models.*
import com.example.rollspellkotlin.Myapp
import com.example.rollspellkotlin.R
import kotlinx.android.synthetic.main.inventory_list_item.view.*
import kotlin.collections.ArrayList

class InventoryListAdapter(
    var lists: ArrayList<Items>,
    var resources: Resources
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.inventory_list_item, parent, false)
        return Item(v)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as Item).initList(lists[position], resources)
    }

    override fun getItemCount(): Int = lists.size


    class Item(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun initList(item: Items, resources: Resources){
            itemView.inventory_item_name_text_view.text = item.name
            when (item) {
                is Weapon -> {
                    itemView.inventory_item_value_text_view.text = item.attack.toString()
                    print("wesh")
                    println("${item}${item.id}")
                    itemView.inventory_item_image_view.setImageResource(resources.getIdentifier(
                        "sword${item.id}",
                        "drawable",
                        "com.example.rollspellkotlin"
                    ))
                }
                is Armor -> {
                    itemView.inventory_item_image_view.setImageResource(resources.getIdentifier(
                        "${item.type}${item.id}",
                        "drawable",
                        "com.example.rollspellkotlin"
                    ))
                    itemView.inventory_item_value_text_view.text = item.defense.toString()
                }
            }
        }
    }
}