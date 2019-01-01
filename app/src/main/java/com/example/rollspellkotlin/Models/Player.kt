package com.example.rollspellkotlin.Models

data class Player(
    var name:String,
    var weapon:Weapon,
    var life:Double,
    var armor:Armor,
    var gold:Int,
    var spell:ArrayList<Spell>,
    var items:ArrayList<Items>
)
