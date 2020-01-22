package com.example.rollspellkotlin

import android.app.Application
import com.example.rollspellkotlin.Models.*

class Myapp : Application() {
    companion object {
        lateinit var player :Player
        val weapon = Weapon("Bâton de l'aventurier",20 , 10)
        val equipments = Equipments(
            Armor("Chapeau de l'aventurier", 20, 10, ArmorType.HELMET),
            Armor("Gants de l'aventurier", 20, 10, ArmorType.GLOVES),
            Armor("Bottes de l'aventurier", 20, 10, ArmorType.BOOTS),
            Armor("Plastron de l'aventurier", 20, 10, ArmorType.CHESTPLATE)
        )
        fun createPlayer(
            name: String,
            spell: ArrayList<Spell>,
            life: Double = 120.0,
            gold: Int = 0,
            items: ArrayList<Items> = ArrayList(Companion.weapon, Companion.equipments),
            weapon: Weapon = Companion.weapon,
            equipments: Equipments = Companion.equipments
            ){player = Player(name, spell, life,  gold, items, weapon, equipments)
        }

        private fun ArrayList(weapon: Weapon, equipments: Equipments): ArrayList<Items> {
            val arrayList = ArrayList<Items>()
            arrayList.add(weapon)
            arrayList.add(equipments.helmet)
            arrayList.add(equipments.gloves)
            arrayList.add(equipments.boots)
            arrayList.add(equipments.chestplate)
            return arrayList
        }
    }
}
