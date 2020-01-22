package com.example.rollspellkotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rollspellkotlin.Adapter.InventoryListAdapter
import com.example.rollspellkotlin.Models.ArmorType
import kotlinx.android.synthetic.main.activity_inventory.*
import com.example.rollspellkotlin.Myapp.Companion.player

class InventoryActivity : AppCompatActivity() {

    lateinit var selectedEquipments: ArmorType

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inventory)
        inventory_list_recycler_view.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        inventory_list_recycler_view.adapter = InventoryListAdapter(player.items, super.getResources())
        initEquipments()
        inventory_helmet_button.setOnClickListener {
            inventory_helmet_button.setBackgroundResource(R.drawable.card_border_selected)
            selectedEquipments = ArmorType.helmet
        }
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
}
