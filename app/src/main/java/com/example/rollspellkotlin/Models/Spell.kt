package com.example.rollspellkotlin.Models

import java.io.Serializable

class Spell(name:String,damage:Double, heal:Double,description:String) : Serializable {

    var name:String = name
    var damage:Double = damage
    var description:String = description
    var heal:Double = heal

}