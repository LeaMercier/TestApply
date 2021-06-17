package com.example.applytest

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.applytest.BDD.Aliment
import com.example.applytest.BDD.AlimentDao

class ActivityAliment : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_aliment)
        val button = findViewById<Button>(R.id.ajout_aliment).setOnClickListener{
            addAliment()
        }
        val buttonSuppression = findViewById<Button>(R.id.suppression_aliment).setOnClickListener{
            deleteAliment()
        }
    }

    /**
     * Fonction permettant d'ajouter l'aliment rentré par l'utilisateur
     */
    fun addAliment()
    {
        val nom : String = findViewById<EditText>(R.id.nom_aliment).text.toString()
        val famille : String = findViewById<EditText>(R.id.nom_aliment).text.toString()
        var alimentDao : AlimentDao = AlimentDao(this)
        //val test = alimentDao.get(nom)
        if(nom.isNotEmpty() && famille.isNotEmpty()){
            val activityAliment = Aliment(nom, famille)
            alimentDao.insert(activityAliment)
        } else {
            val text = "Veuillez rentrer rentrer les informations demandées dans un premier temps"
            val duration = Toast.LENGTH_SHORT
            val toast = Toast.makeText(applicationContext, text, duration)
            toast.show()
        }
        /**else
            alimentDao.update(activityAliment)
*/
        //Fait aparraître un court pop-up afin d'annoncer le bon ajout
        //ou la bonne mise-à-jour de l'aliment
        val text = "Aliment ajouté"
        val duration = Toast.LENGTH_SHORT
        val toast = Toast.makeText(applicationContext, text, duration)
        toast.show()
    }


    /**
     * Fonction permettant de supprimer l'aliment rentré par l'utilisateur
     */
    fun deleteAliment()
    {
        val nom : String = findViewById<EditText>(R.id.nom_aliment_a_supprimer).text.toString()
        var alimentDao : AlimentDao = AlimentDao(this)
        if(alimentDao.get(nom) == null)
        {
            // Fait aparraître un court pop-up afin d'expliquer que l'aliment n'est pas là
            val text = "Vous essayer de supprimer un aliment non-enregistré"
            val duration = Toast.LENGTH_SHORT
            val toast = Toast.makeText(applicationContext, text, duration)
            toast.show()
        }
        else
        {
            alimentDao.delete(nom)
            // Fait aparraître un court pop-up afin de valider le fait que l'aliment est bien été supprimé
            val text = "L'aliment a bien été supprimé"
            val duration = Toast.LENGTH_SHORT
            val toast = Toast.makeText(applicationContext, text, duration)
            toast.show()
        }
    }

}