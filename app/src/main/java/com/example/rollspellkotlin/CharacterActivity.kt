package com.example.rollspellkotlin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import com.example.rollspellkotlin.Models.*
import kotlinx.android.synthetic.main.activity_character.*

class CharacterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_character)
        activity_character_race_details_text_view.text = resources.getString(R.string.activity_character_dwarf_description)

        var selector = 1

        activity_character_race_one_button.setOnClickListener{
            activity_character_race_details_text_view.text = resources.getString(R.string.activity_character_dwarf_description)
            activity_character_race_image_view.setImageDrawable(activity_character_race_one_button.background)
            selector = 1
        }

        activity_character_race_two_button.setOnClickListener{
            activity_character_race_details_text_view.text = resources.getString(R.string.activity_character_human_description)
            activity_character_race_image_view.setImageDrawable(activity_character_race_two_button.background)
            selector = 2
        }

        activity_character_race_three_button.setOnClickListener{
            activity_character_race_details_text_view.text = resources.getString(R.string.activity_character_elf_description)
            activity_character_race_image_view.setImageDrawable(activity_character_race_three_button.background)
            selector = 3
        }

        activity_character_race_four_button.setOnClickListener{
            activity_character_race_details_text_view.text = resources.getString(R.string.activity_character_demon_description)
            activity_character_race_image_view.setImageDrawable(activity_character_race_four_button.background)
            selector = 4
        }

        activity_character_start_button.setOnClickListener {
            var weapon :Weapon
            var armor :Armor
            var firstSpell:Spell
            var secondSpell:Spell
            when(selector) {
                1 -> {

                    weapon = Weapon(14.0,"Mjollnir" , 500)
                    armor = Armor("dwarf chestplate", 18.0, 500)
                    firstSpell = Spell("Coup de chopine", 0.0, 20.0, "Hic !")
                    secondSpell = Spell("Coup fracassant", 35.0, 0.0, "Frappe tel un forgeron nain")
                    Myapp.createPlayer(
                        name = activity_character_pseudo_edit_text.text.toString(),
                        weapon = weapon, armor = armor,
                        life = 120.0, gold = 0,
                        spell = addList(firstSpell, secondSpell),
                        items = addListItems(weapon, armor))
                }//DWARF
                2 -> {
                    weapon = Weapon(11.0,"Excalibur" , 500)
                    armor = Armor("human chestplate", 12.0, 500)
                    firstSpell = Spell("charge rapide", 28.0, 0.0, "Lance une charge puissante vers l'adversaire")
                    secondSpell = Spell("Bénédiction", 0.0, 30.0, "Les dieux envoient leurs lumières")
                        Myapp.createPlayer(
                        name = activity_character_pseudo_edit_text.text.toString(),
                        weapon = weapon, armor = armor,
                        life = 120.0, gold = 0,
                        spell = addList(firstSpell, secondSpell),
                        items = addListItems(weapon, armor))
                }//HUMAN
                3 -> {
                    weapon = Weapon(8.0,"Belthronding" , 500)
                    armor = Armor("elf chestplate", 9.0, 500)
                    firstSpell = Spell("magie des elfes", 0.0, 40.0, "Les racines de la forêt répondent à l'appel")
                    secondSpell = Spell("soif de sang", 5.0, 20.0, "La magie des elfes soignent toutes les blessures")
                    Myapp.createPlayer(
                        name = activity_character_pseudo_edit_text.text.toString(),
                        weapon = weapon, armor = armor,
                        life = 120.0, gold = 0,
                        spell = addList(firstSpell, secondSpell),
                        items = addListItems(weapon, armor))
                }   //ELF
                4 -> {
                    weapon = Weapon(25.0, "Souleater", 500)
                    armor = Armor("demon chestplate", 9.0, 500)
                    firstSpell = Spell("Envoutement", 35.0, 0.0, "Son âme raisonne...")
                    secondSpell = Spell("Buveuse d'âme", 50.0, 0.0, "Tant de puissance...")
                    Myapp.createPlayer(
                        name = activity_character_pseudo_edit_text.text.toString(),
                        weapon = weapon, armor = armor,
                        life = 120.0, gold = 0,
                        spell = addList(firstSpell, secondSpell),
                        items = addListItems(weapon, armor)
                    )
                }//DEMON
            }



            val intent = Intent(this, GamingBoardActivity::class.java)
            startActivity(intent)
        }
    }

    fun addList(firstSpell: Spell, secondSpell: Spell): ArrayList<Spell> {
        val spellList = ArrayList<Spell>()
        spellList.add(firstSpell)
        spellList.add(secondSpell)
        return spellList
    }

    fun addListItems(weapon: Weapon, armor: Armor): ArrayList<Items> {
        val spellList = ArrayList<Items>()
        spellList.add(weapon)
        spellList.add(armor)
        return spellList
    }
}
