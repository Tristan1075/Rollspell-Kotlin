package com.example.rollspellkotlin.Models


import java.io.Serializable

class Weapon(damage: Double, name: String, price: Int): Serializable,Items() {

    var name:String = name
    var damage:Double = damage
    var price:Int = price
}