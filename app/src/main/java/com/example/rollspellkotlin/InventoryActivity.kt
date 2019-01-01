package com.example.rollspellkotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rollspellkotlin.Adapter.InventoryListAdapter
import kotlinx.android.synthetic.main.activity_inventory.*
import com.example.rollspellkotlin.Myapp.Companion.player

class InventoryActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inventory)
        inventory_list_recycler_view.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        inventory_list_recycler_view.adapter = InventoryListAdapter(player.items)
    }
}
