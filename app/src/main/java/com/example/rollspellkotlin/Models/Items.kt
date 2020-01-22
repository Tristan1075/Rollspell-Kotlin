package com.example.rollspellkotlin.Models

sealed class Items(open val id: Int, open val name: String, open val price: Int)

data class Armor(
    override val id: Int,
    override val name: String,
    override val price: Int,
    var defense: Int = 0,
    val type: ArmorType
): Items(id, name, price)

class Equipments(var helmet: Armor, var gloves: Armor, var boots: Armor, var chestplate: Armor) {
    fun getArmor(): Int {
        return helmet.defense + gloves.defense + boots.defense + chestplate.defense
    }
}

enum class ArmorType(type: String) {
    helmet("helmet"),
    gloves("gloves"),
    boots("boots"),
    chestplate("chestplate")
}

data class Weapon(
    override val id: Int,
    override val name: String,
    override val price: Int,
    var attack: Int = 0
): Items(id, name, price)
