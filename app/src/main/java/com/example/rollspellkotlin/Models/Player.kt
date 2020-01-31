package com.example.rollspellkotlin.Models

data class Player(
    var name: String,
    var spell: ArrayList<Spell>,
    var life: Int,
    var gold: Int,
    var items: ArrayList<Items>,
    var equipments: Equipments,
    var race:String,
    var picture: String

)
