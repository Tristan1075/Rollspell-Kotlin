package com.example.rollspellkotlin.Models

import java.io.Serializable

class Player(name:String,weapon:Weapon,life:Double,armor:Armor,gold:Int) : Serializable{

    var name :String = name
    var weapon:Weapon = weapon
    var life:Double = life
    var armor:Armor = armor
    var gold:Int = gold

}