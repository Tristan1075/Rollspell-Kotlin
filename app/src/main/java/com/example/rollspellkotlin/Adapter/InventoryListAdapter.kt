package com.example.rollspellkotlin.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.rollspellkotlin.Models.Armor
import com.example.rollspellkotlin.Models.Items
import com.example.rollspellkotlin.Models.Weapon
import com.example.rollspellkotlin.R
import kotlinx.android.synthetic.main.inventory_list_item.view.*
import kotlin.collections.ArrayList

class InventoryListAdapter(var lists: ArrayList<Items>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.inventory_list_item, parent, false)
        return Item(v)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as Item).initList(lists[position])
    }

    override fun getItemCount(): Int = lists.size


    class Item(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun initList(item: Items) {
            itemView.inventory_item_name_text_view.text = item.name
            when (item) {
                is Weapon -> {
                    itemView.inventory_item_value_text_view.text = item.attack.toString()
                }
                is Armor -> {
                    itemView.inventory_item_value_text_view.text = item.defense.toString()
                }
            }
        }
    }
}