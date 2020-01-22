package com.example.rollspellkotlin.Models

import java.io.Serializable

class Player(name:String,weapon:Weapon,life:Double,armor:Armor,gold:Int,spell:ArrayList<Spell>,items:ArrayList<Items>) : Serializable{

    var name :String = name
    var weapon:Weapon = weapon
    var life:Double = life
    var armor:Armor = armor
    var gold:Int = gold
    var spell:ArrayList<Spell> = spell
    var items:ArrayList<Items> = items

}
