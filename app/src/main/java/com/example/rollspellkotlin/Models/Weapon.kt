package com.example.rollspellkotlin.Models

import java.io.Serializable

class Weapon(name:String,damage:Double,price:Int): Serializable{

    var name:String = name
    var damage:Double = damage
    var price:Int = price
}