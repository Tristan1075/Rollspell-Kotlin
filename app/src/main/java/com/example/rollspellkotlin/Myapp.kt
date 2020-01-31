package com.example.rollspellkotlin

import android.app.Application
import com.example.rollspellkotlin.Models.*

class Myapp : Application() {
    companion object {
        lateinit var player :Player
        val equipments = Equipments(
            Armor(1,"Chapeau de l'aventurier", 20, 10, ArmorType.helmet),
            Armor(1,"Gants de l'aventurier", 20, 10, ArmorType.gloves),
            Armor(1,"Bottes de l'aventurier", 20, 10, ArmorType.boots),
            Armor(1,"Plastron de l'aventurier", 20, 10, ArmorType.chestplate),
            Weapon(1,"Bâton de l'aventurier",20 , 10)

        )
        fun createPlayer(
            name: String,
            spell: ArrayList<Spell>,
            life: Int = 120,
            gold: Int = 0,
            items: ArrayList<Items> = ArrayList( Companion.equipments),

            equipments: Equipments = Companion.equipments,
            race : String,
            picture :String
            ){player = Player(name, spell, life,  gold, items, equipments,race,picture)
        }

        private fun ArrayList( equipments: Equipments): ArrayList<Items> {
            val arrayList = ArrayList<Items>()
            arrayList.add(equipments.weapon)
            arrayList.add(equipments.helmet)
            arrayList.add(equipments.gloves)
            arrayList.add(equipments.boots)
            arrayList.add(equipments.chestplate)
            return arrayList
        }
    }



}
