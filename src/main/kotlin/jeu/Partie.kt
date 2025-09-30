class Partie (
    var id: Int,
    var joueur:Entraineur,
    var zone: Zone) {

    fun choixStarter() {
        val monstre1 = IndividuMonstre(4, "springleaf", especeSpringleaf, null, 1500.0)
        val monstre2 = IndividuMonstre(5, "flamkip", especeFlamkip, null, 1500.0)
        val monstre3 = IndividuMonstre(6, "aquamy", especeAquamy, null, 1500.0)

        monstre1.afficherDetail()
        monstre2.afficherDetail()
        monstre3.afficherDetail()

        println("Choisir un monstre (1 -> springleaf, 2 -> flamkip, ou 3 -> aquamy) : ")
        val choix = readln()
        var starter = monstre1
        if (choix == "1") {
            starter = monstre1
        } else if (choix == "2") {
            starter = monstre2
        } else if (choix == "3") {
            starter = monstre3
        } else {
            print("Erreur lors de la saisie du choix.")
        }
        starter.renommer()
        joueur.equipeMonstre.add(starter)
        starter.entraineur = joueur
    }

    fun modifierOrdreEquipe() {
        if (joueur.equipeMonstre.size < 2) {
            println("Erreur : il n'y a pas assez de monstres dans l'équipe.")
        }

        println("Équipe :")
        for (i in joueur.equipeMonstre.indices) {
            println("$i : ${joueur.equipeMonstre[i].nom}")
        }

        println("Saisir la position du premier monstre :")
        val position1 = readln().toInt()
        println("Saisir la position du second monstre :")
        val position2 = readln().toInt()

        val valeur = joueur.equipeMonstre[position1]
        joueur.equipeMonstre[position1] = joueur.equipeMonstre[position2]
        joueur.equipeMonstre[position2] = valeur

        println("Équipe :")
        for (i in joueur.equipeMonstre.indices) {
            println("$i : ${joueur.equipeMonstre[i].nom}")
        }
    }

    fun examineEquipe(){
        println("Équipe :")
        for (i in joueur.equipeMonstre.indices) {
            println("$i : ${joueur.equipeMonstre[i].nom}")
        }
        println("numéro du monstre pour voir son détail ,q -> quitter le menu, m -> modifier l'ordre de l'équipe")
        var choix = readln().toString()
        when(choix){
            "q" -> return
            "m" -> this.modifierOrdreEquipe()
            else -> joueur.equipeMonstre[choix.toInt()].afficherDetail()
        }
    }

    fun jouer(){
        println("Vous êtes dans ${this.zone.nom}")
        println("Faites :" +
                "1 -> Rencontrer un monstre sauvage, " +
                "2 -> Examiner l'équipe, " +
                "3 -> Aller à la zone suivante, " +
                "4 -> Aller à la zone précédente ")
        val choix = readln().toInt()
        when(choix){
            1 -> {
                zone.rencontreMonstre()
                jouer()
            }
            2 -> {
                this.examineEquipe()
                jouer()
            }
            3 -> if(zone.zoneSuivante != null){
                zone = zone.zoneSuivante!!
                jouer()
            }else{
                println("Pas de zone suivante")
                jouer()
            }
            4 -> if(zone.ZonePrecedente!= null){
                zone = zone.ZonePrecedente!!
                jouer()
            }else{
                println("Pas de zone précédente")
                jouer()
            }
        }

    }



}