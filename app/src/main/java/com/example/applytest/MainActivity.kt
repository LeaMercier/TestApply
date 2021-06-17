package com.example.applytest

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import com.example.applytest.BDD.Aliment
import com.example.applytest.BDD.AlimentDao
import com.example.applytest.List.AlimentListAdapter

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val button = findViewById<Button>(R.id.ajout_suppresion_aliment_main).setOnClickListener{
            ajouterAliment()
        }
    }

    /**
     * Permet de lancer l'activité où l'on peut ajouter des aliments.
     * Se charge aussi de "fermer" l'activitée principale
     */
    fun ajouterAliment(){
        startActivity(Intent(this, ActivityAliment::class.java))
        //finish()
    }


    /**
     * Fonction permettant de voir la database sous forme de liste
     */
    fun showlistAliments(view: View)
    {
        val listAliments: ListView = findViewById<ListView>(R.id.liste_alliments)
        var alimentDao : AlimentDao = AlimentDao(this)
        // On appelle la fonction nous permettant de récupérer tout les éléments de la base
        val alis : List<Aliment> = alimentDao.getAllElements()
        val aliName = Array<String>(alis.size){"null"}
        val aliFamille = Array<String>(alis.size){"null"}
        var index = 0
        for (food in alis)
        {
            aliName[index] = food.nom
            aliFamille[index] = food.famille
            index++
        }
        val ala : AlimentListAdapter = AlimentListAdapter(this, aliName, aliFamille)
        listAliments.adapter = ala
    }
}