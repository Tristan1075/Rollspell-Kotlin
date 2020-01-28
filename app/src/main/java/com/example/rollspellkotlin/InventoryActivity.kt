package com.example.rollspellkotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.rollspellkotlin.Adapter.InventoryListAdapter
import com.example.rollspellkotlin.Models.Armor
import com.example.rollspellkotlin.Models.ArmorType
import com.example.rollspellkotlin.Models.Weapon
import kotlinx.android.synthetic.main.activity_inventory.*
import com.example.rollspellkotlin.Myapp.Companion.player

class InventoryActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inventory)
        inventory_list_recycler_view.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        inventory_list_recycler_view.adapter = InventoryListAdapter(player.items, super.getResources())
        initEquipments()
        inventory_list_recycler_view.addOnItemClickListener(object : OnItemClickListener {
        override fun onItemClicked(position: Int, view: View) {
            val item = player.items.get(position)
            when (item) {
                is Weapon -> {

                }
                is Armor -> {
                    when(item.type) {
                        ArmorType.helmet -> {
                            player.equipments.newHelmet(item)
                            inventory_helmet_button.setImageResource(resources.getIdentifier(
                                "${item.type}${item.id}",
                                "drawable",
                                "com.example.rollspellkotlin"
                            ))
                        }
                        ArmorType.gloves -> {
                            player.equipments.newGloves(item)
                            inventory_left_gloves_button.setImageResource(resources.getIdentifier(
                                "${item.type}${item.id}",
                                "drawable",
                                "com.example.rollspellkotlin"
                            ))
                            inventory_right_gloves_button.setImageResource(resources.getIdentifier(
                                "${item.type}${item.id}",
                                "drawable",
                                "com.example.rollspellkotlin"
                            ))
                        }
                        ArmorType.boots -> {
                            player.equipments.newBoots(item)
                            inventory_left_boots_button.setImageResource(resources.getIdentifier(
                                "${item.type}${item.id}",
                                "drawable",
                                "com.example.rollspellkotlin"
                            ))
                            inventory_right_boots_button.setImageResource(resources.getIdentifier(
                                "${item.type}${item.id}",
                                "drawable",
                                "com.example.rollspellkotlin"
                            ))
                        }
                        ArmorType.chestplate -> {
                            player.equipments.newChestplate(item)
                            inventory_chestplate_button.setImageResource(resources.getIdentifier(
                                "${item.type}${item.id}",
                                "drawable",
                                "com.example.rollspellkotlin"
                            ))

                            println(player.equipments.toString())
                        }
                    }

                }
            }
        }
        })
    }



    fun initEquipments () {
        val helmet = super.getResources().getIdentifier(
            "${player.equipments.helmet.type}${player.equipments.helmet.id}",
            "drawable",
            super.getPackageName()
        )
        println(super.getPackageName())
        inventory_helmet_button.setImageResource(helmet)
        val gloves = super.getResources().getIdentifier(
            "${player.equipments.gloves.type}${player.equipments.gloves.id}",
            "drawable",
            super.getPackageName()
        )
        inventory_left_gloves_button.setImageResource(gloves)
        inventory_right_gloves_button.setImageResource(gloves)
        val boots = super.getResources().getIdentifier(
            "${player.equipments.boots.type}${player.equipments.boots.id}",
            "drawable",
            super.getPackageName()
        )
        inventory_left_boots_button.setImageResource(boots)
        inventory_right_boots_button.setImageResource(boots)
        val chestplate = super.getResources().getIdentifier(
            "${player.equipments.chestplate.type}${player.equipments.chestplate.id}",
            "drawable",
            super.getPackageName()
        )
        inventory_chestplate_button.setImageResource(chestplate)
    }

    interface OnItemClickListener {
        fun onItemClicked(position: Int, view: View)
    }

    fun RecyclerView.addOnItemClickListener(onClickListener: OnItemClickListener) {
        this.addOnChildAttachStateChangeListener(object : RecyclerView.OnChildAttachStateChangeListener {
            override fun onChildViewDetachedFromWindow(view: View) {
                view?.setOnClickListener(null)
            }

            override fun onChildViewAttachedToWindow(view: View) {
                view?.setOnClickListener {
                    val holder = getChildViewHolder(view)
                    onClickListener.onItemClicked(holder.adapterPosition, view)
                }
            }
        })
    }
}

