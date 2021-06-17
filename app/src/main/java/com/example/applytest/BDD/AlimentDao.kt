package com.example.applytest.BDD

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteException

class AlimentDao(val context: Context) {

    /**
     * Récupération d'un objet BddHelper permettant
     * de récupérer des connexions à la base de données
     */
    private val dbHelper= BddHelper(context)

    /**
     * Fonction permettant d'enregistrer une personne en base de données
     * Une requête INSERT sera envoyée à la bdd.
     */
    fun insert(aliment:Aliment):Long?
    {
        // On récupère l'objet qui va nous permettre de nous connecter à notre bdd
        //Si elle n'existe pas on la créée
        val db = dbHelper.writableDatabase
        //Il va nous permettre les données sous forme de clef/valeur.
        //La valeur étant celle à insérer dans la colonne de l'enregistrement
        val values = ContentValues().apply {
            put(Contracts.Aliments.COLUMN_NAME_NAME, aliment.nom)
            put(Contracts.Aliments.COLUMN_NAME_FAMILLE, aliment.famille)
        }
        //Il ne nous reste plus qu'à appeler la fonction INSERT pour enregister les données en base
        val result = db?.insert(Contracts.Aliments.TABLE_NAME, null, values)
        db.close()
        return null //result
    }

    /**
     *
     */
    fun getAllElements():List<Aliment>
    {
        var result:List<Aliment> = ArrayList<Aliment>()
        val selectQuery = "SELECT * FROM ${Contracts.Aliments.TABLE_NAME}"
        val db = dbHelper.readableDatabase
        var cursor: Cursor? = null
        try {
            cursor = db.rawQuery(selectQuery, null)
        } catch (e: SQLiteException) {
            db.execSQL(selectQuery)
            return ArrayList()
        }
        var nomAli: String
        var familleAli: String
        if(cursor.moveToFirst()){
            do{
                nomAli = cursor.getInt(cursor.getColumnIndex("nom")).toString()
                familleAli = cursor.getString(cursor.getColumnIndex("famille"))
                val aliment: Aliment = Aliment(nomAli, familleAli)
                result.plus(aliment)
            } while (cursor.moveToNext())
        }
        return result
    }

    /**
     * Fonction permettant de récupérer un objet de type Aliment grâce à son identifiant
     * Une requête SELECT sera envoyée à la bdd
     */
    fun get(nom:String):Aliment?
    {
        // Création de l'objet vide afin de pouvoir enregistrer les infos à retourner
        var resultat:Aliment?=null;
        //Récupération d'un objet représentant une connexion en lecture sur la bdd.
        //Si la base n'existe pas, on la créée.
        val db = dbHelper.readableDatabase

        //On définie ma projection de la requête SELECT
        val projection =
            arrayOf(Contracts.Aliments.COLUMN_NAME_NAME,
                Contracts.Aliments.COLUMN_NAME_FAMILLE)

        //Définition du WHERE de la requête SELECT
        val selection = "${Contracts.Aliments.COLUMN_NAME_NAME} = ?"
        //Le "?" va être remplacé par ce qui nous
        val selectionArgs = arrayOf("$nom")
        //Appel de la fonction query qui exécute le SELECT
        //puis retourne le résultat de la requête sous forme de curseur
        val cursor = db.query(
            // Definition de la table dans laquelle a lieu la recherche (Définition du FROM)
            Contracts.Aliments.TABLE_NAME,
            projection, // Définition du SELECT
            selection, // Définition du WHERE
            selectionArgs, // Valeur pour la clause WHERE
            null, // Pas de GROUP BY dans cette requête
            null, // Pas de HAVING dans cette requête
            null // Pas de tri dans cette requête
        )
        //travail sur le curseur
        with(cursor){
            //fonction moveToNext de l'objet cursor
            if(moveToNext()){
                //Récupération de l'id dans le résultat de la requête
                val itemNom = getString(getColumnIndexOrThrow(Contracts.Aliments.COLUMN_NAME_NAME))
                //Récupération de la famille de l'aliment dans le résutat de la requête
                val itemFamille = getString(getColumnIndexOrThrow(Contracts.Aliments.COLUMN_NAME_FAMILLE))
                //Construction de l'objet de type Aliment qui sera alors retourné
                resultat = Aliment(itemNom, itemFamille)
            }
        }
        //Retourne le résultat
        return resultat
    }

    /**
     * Fonction permettant de mettre à jour la bdd.
     * Une requête UPDATE ser envoyée à la bdd.
     */
    fun update(item:Aliment)
    {
        // On récupère l'objet qui va nous permettre de nous connecter à notre bdd
        //Si elle n'existe pas on la créée
        val db = dbHelper.writableDatabase
        //
        val values = ContentValues().apply {
            put(Contracts.Aliments.COLUMN_NAME_NAME, item.nom)
            put(Contracts.Aliments.COLUMN_NAME_FAMILLE, item.famille)
        }
        //Définition du WHERE de la requête UPDATE
        val selection = "${Contracts.Aliments.COLUMN_NAME_NAME} = ?"
        val selectionArgs = arrayOf("$item.nom")
        //Appel de la fonction query pour l'UPDATE
        //Retourne le nb de lignes mises à jour
        val count = db.update(
            Contracts.Aliments.TABLE_NAME,//Nom de la table à mettre à jour
            values, //Valeurs à mettre à jour
            selection, //Définition du WHERE
            selectionArgs) //Valeurs à mettre dans le WHERE
    }

    /**
     * Fonction permettant de supprimer un enregistrement dans la bdd d'Aliment.
     * Une requête DELETE sera envoyée à la bdd.
     */
    fun delete(nom:String)
    {
        // On récupère l'objet qui va nous permettre de nous connecter à notre bdd
        //Si elle n'existe pas on la créée
        val db = dbHelper.writableDatabase
        //Définition du WHERE de la requête DELETE
        val selection = "${Contracts.Aliments.COLUMN_NAME_NAME} = ?"
        val selectionArgs = arrayOf("$nom")
        //Apple de la fonction query correspondant à la requête DELETE
        db.delete(Contracts.Aliments.TABLE_NAME, selection, selectionArgs)
    }

}
