package com.example.rollspellkotlin.Models

sealed class Items(open val id: Int, open val name: String, open val price: Int)

 data class Armor(
    override val id: Int,
    override val name: String,
    override val price: Int,
    var defense: Int = 0,
    val type: ArmorType
): Items(id, name, price)

class Equipments(var helmet: Armor, var gloves: Armor, var boots: Armor, var chestplate: Armor,var weapon:Weapon) {

    fun newHelmet(helmet: Armor){
        this.helmet = helmet
    }

    fun newGloves(gloves: Armor){
        this.gloves = gloves
    }

    fun newBoots(boots: Armor){
        this.boots = boots
    }

    fun newChestplate(chestplate: Armor){
        this.chestplate = chestplate
    }

    fun newWeapon(weapon: Weapon){
        this.weapon = weapon
    }
    fun getArmor(): Int {
        return helmet.defense + gloves.defense + boots.defense + chestplate.defense
    }
    fun getDamage(): Int {
        return weapon.attack
    }

    override fun toString(): String {
        return "$helmet, " +
                "$gloves, " +
                "$boots, " +
                "$chestplate"
    }
}

enum class ArmorType() {
    helmet(),
    gloves(),
    boots(),
    chestplate()
}

data class Weapon(
    override val id: Int,
    override val name: String,
    override val price: Int,
    var attack: Int = 0
): Items(id, name, price)
