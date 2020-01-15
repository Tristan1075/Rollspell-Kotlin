package com.example.rollspellkotlin

import android.app.Application
import com.example.rollspellkotlin.Models.Armor
import com.example.rollspellkotlin.Models.Player
import com.example.rollspellkotlin.Models.Spell
import com.example.rollspellkotlin.Models.Weapon

class Myapp : Application() {
    companion object {

        lateinit var player :Player


        fun createPlayer(name:String,weapon:Weapon,life:Double,armor:Armor,gold:Int,spell:ArrayList<Spell>){
             player =
                Player(name, weapon, life, armor, gold, spell)
        }


    }
}
