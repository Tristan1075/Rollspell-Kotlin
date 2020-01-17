package com.example.rollspellkotlin.Models

import java.io.Serializable

class Armor(name:String,defense:Double,price:Int): Serializable,Items(){

    var name:String = name
    var defense:Double = defense
    var price:Int = price
}