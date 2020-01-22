package com.example.rollspellkotlin.Models

sealed class Items(open val name: String, open val price: Int)

data class Armor(
    override val name: String,
    override val price: Int,
    var defense: Int = 0,
    val type: ArmorType
): Items(name, price)

class Equipments(var helmet: Armor, var gloves: Armor, var boots: Armor, var chestplate: Armor) {
    fun getArmor(): Int {
        return helmet.defense + gloves.defense + boots.defense + chestplate.defense
    }
}

enum class ArmorType(type: String) {
    HELMET("helmet"),
    GLOVES("gloves"),
    BOOTS("boots"),
    CHESTPLATE("chestplate")
}

data class Weapon(
    override val name: String,
    override val price: Int,
    var attack: Int = 0
): Items(name, price)
