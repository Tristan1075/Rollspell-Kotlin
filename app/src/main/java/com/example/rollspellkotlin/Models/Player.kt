package com.example.rollspellkotlin.Models

data class Player(
    var name: String,
    var spell: ArrayList<Spell>,
    var life: Double,
    var gold: Int,
    var items: ArrayList<Items>,
    var weapon: Weapon,
    var equipments: Equipments
)
