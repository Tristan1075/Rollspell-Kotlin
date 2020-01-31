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
        this.getWindow().setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        );
        setContentView(R.layout.activity_character)
        activity_character_race_details_text_view.text =
            resources.getString(R.string.activity_character_dwarf_description)

        var selector = 1

        activity_character_race_one_button.setOnClickListener {
            activity_character_race_details_text_view.text =
                resources.getString(R.string.activity_character_dwarf_description)
            activity_character_race_image_view.setImageDrawable(activity_character_race_one_button.background)
            selector = 1
        }

        activity_character_race_two_button.setOnClickListener {
            activity_character_race_details_text_view.text =
                resources.getString(R.string.activity_character_human_description)
            activity_character_race_image_view.setImageDrawable(activity_character_race_two_button.background)
            selector = 2
        }

        activity_character_race_three_button.setOnClickListener {
            activity_character_race_details_text_view.text =
                resources.getString(R.string.activity_character_elf_description)
            activity_character_race_image_view.setImageDrawable(activity_character_race_three_button.background)
            selector = 3
        }

        activity_character_race_four_button.setOnClickListener {
            activity_character_race_details_text_view.text =
                resources.getString(R.string.activity_character_demon_description)
            activity_character_race_image_view.setImageDrawable(activity_character_race_four_button.background)
            selector = 4
        }

        activity_character_start_button.setOnClickListener {
            when (selector) {
                // DWARF
                1 -> {
                    Myapp.createPlayer(
                        activity_character_pseudo_edit_text.text.toString(),
                        race = "dwarf",
                        picture = "player_avatar",
                        spell = addSpell(
                            Spell("Coup de chopine",0, 20,"Hic !"),
                            Spell("Coup fracassant",35, 0,"Frappe tel un forgeron nain")
                        )
                    )
                }
                //HUMAN
                2 -> {
                    Myapp.createPlayer(
                        activity_character_pseudo_edit_text.text.toString(),
                        race = "human",
                        picture = "human",
                        spell =
                        addSpell(
                            Spell("charge rapide",28, 0, "Lance une charge puissante vers l'adversaire"),
                            Spell("Bénédiction", 0,30, "Les dieux envoient leurs lumières")
                        )
                    )
                }
                //ELF
                3 -> {
                    Myapp.createPlayer(
                        activity_character_pseudo_edit_text.text.toString(),
                        race = "elf",
                        picture = "elf",
                        spell =
                        addSpell(
                            Spell("magie des elfes",0,40,"Les racines de la forêt répondent à l'appel"),
                            Spell("soif de sang", 5,20,"La magie des elfes soignent toutes les blessures")
                        )
                    )
                }
                //DEMON
                4 -> {
                    Myapp.createPlayer(
                        activity_character_pseudo_edit_text.text.toString(),
                        race = "demon",
                        picture = "demon",
                        spell =
                        addSpell(
                            Spell("Envoutement", 35, 0, "Son âme raisonne..."),
                            Spell("Buveuse d'âme", 50, 0, "Tant de puissance...")
                        )
                    )
                }
            }

            val intent = Intent(this, GamingBoardActivity::class.java)
            startActivity(intent)
        }
    }

    fun addSpell(firstSpell: Spell, secondSpell: Spell): ArrayList<Spell> {
        val spellList = ArrayList<Spell>()
        spellList.add(firstSpell)
        spellList.add(secondSpell)
        return spellList
    }
}
