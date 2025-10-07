import monde.Ville
/**
 * Représente une partie de jeu, avec un joueur (dresseur), une zone actuelle, et les différentes actions possibles.
 *
 * La classe permet de gérer :
 * - Le choix du monstre de départ (starter)
 * - L'exploration des zones
 * - Les combats contre des monstres sauvages
 * - La gestion de l’équipe du joueur (consultation, changement d’ordre, soins)
 * - Les transitions entre les zones
 *
 * @property id Identifiant unique de la partie.
 * @property joueur Le dresseur contrôlé par le joueur.
 * @property zone La zone actuelle dans laquelle se trouve le joueur.
 */
class Partie (
    var id: Int,
    var joueur:Entraineur,
    var zone: Zone) {
/**
 * Permet au joueur de choisir son monstre de départ parmi trois options prédéfinies.
 * Le joueur peut voir les détails de chaque monstre et renommer celui qu'il choisit.
 * Le monstre sélectionné est ensuite ajouté à son équipe.
 */
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
/**
 * Permet au joueur de modifier l'ordre de son équipe en échangeant deux positions.
 * Affiche l’équipe actuelle, demande deux positions, et échange les monstres correspondants.
 */
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
/**
 * Affiche les monstres de l’équipe avec leur nom, puis permet :
 * - D’examiner un monstre en détail
 * - De modifier l’ordre de l’équipe
 * - De quitter ce menu
 */
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
/**
 * Boucle principale du jeu : propose différentes actions selon le contexte :
 * - Rencontre d’un monstre sauvage
 * - Consultation de l’équipe
 * - Changement de zone (suivante / précédente)
 * - Accès aux fonctionnalités spécifiques à une ville (soins, arène)
 *
 * Le menu se relance après chaque action sauf si le joueur quitte l’application.
 */
    fun jouer(){
        println("Vous êtes dans ${this.zone.nom}")
        println("Faites :")
        println("1 -> Rencontrer un monstre sauvage, ")
        println("2 -> Examiner l'équipe, ")
        println("3 -> Aller à la zone suivante, ")
        println("4 -> Aller à la zone précédente ")
        if(this.zone is Ville){
            println("5 -> Soigner l'équipe")
            println("6 -> Entrer dans l'arène")
        }
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
            5 -> if(this.zone is Ville){
                joueur.soignerEquipe()
                println("L'hopital soigne votre équipe")
                jouer()
            }
            //TODO 6 -> arene
        }
    }



}