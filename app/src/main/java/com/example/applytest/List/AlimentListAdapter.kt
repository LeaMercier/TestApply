package com.example.applytest.List

import android.app.Activity
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.example.applytest.R

class AlimentListAdapter(private val context: Activity, private val nom: Array<String>,
                          private val famille: Array<String>)
    : ArrayAdapter<String>(context, R.layout.specific_entity, nom) {

    override fun getView(position: Int, view: View?, parent: ViewGroup): View {
        val inflater = context.layoutInflater
        val rowView = inflater.inflate(R.layout.specific_entity, null, true)

        val nomText = rowView.findViewById(R.id.nom_aliment_liste) as TextView
        val familleText = rowView.findViewById(R.id.famille_aliment_list) as TextView

        nomText.text = "nom de l\'aliment : ${nom[position]}"
        familleText.text = "nom de la famille de l'aliment : ${famille[position]}"
        return rowView
    }
}