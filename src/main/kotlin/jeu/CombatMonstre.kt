package jeu
import IndividuMonstre
import item.Utilisable
import joueur

/**
 * Gère un combat entre le monstre du joueur et un monstre sauvage.
 *
 * Ce combat prend en compte la vitesse pour déterminer l'ordre des actions,
 * les attaques, l'utilisation d'objets, le changement de monstre, et la gestion
 * des conditions de victoire ou de défaite.
 *
 * @property monstreJoueur Le monstre actuellement utilisé par le joueur.
 * @property monstreSauvage Le monstre sauvage affronté.
 * @property round Compteur du round actuel du combat.
 */
class CombatMonstre(
    var monstreJoueur: IndividuMonstre?,
    var monstreSauvage: IndividuMonstre
    ) {
    var round : Int = 1
    /**
     * Vérifie si le joueur a perdu le combat.
     *
     * Condition de défaite :
     * - Aucun monstre de l'équipe du joueur n'a de PV > 0.
     *
     * @return `true` si le joueur a perdu, sinon `false`.
     */
    fun gameOver(): Boolean {
        var compteur = 0
        joueur.equipeMonstre.forEach { monstre ->
            if(monstre.pv <= 0){
                compteur++
            }
        }
        if(compteur == joueur.equipeMonstre.size) {
            return true
        }else {
            return false
        }
    }
    /**
     * Vérifie si le joueur a gagné le combat.
     *
     * Condition : le monstre sauvage est KO ou capturé.
     * En cas de victoire, le monstre du joueur gagne de l'expérience.
     *
     * @return true si le joueur a gagné, sinon false.
     */
    fun joueurGagne(): Boolean{
        if(monstreSauvage.pv <= 0){
            println("${joueur.nom} a gagné !")
            var gainExp = monstreSauvage.exp*0.20
            monstreJoueur.exp += gainExp
            println("${monstreJoueur.nom} gagne $gainExp exp")
            return true
        }else{
            if(monstreSauvage.entraineur == joueur){
                println("${monstreSauvage.nom} à été capturé !")
                return true
            }else{
                return false
            }
        }
    }
    /**
     * Action du monstre sauvage (adversaire) : attaque si toujours en vie.
     */
    fun actionAdversaire(){
        if(monstreSauvage.pv > 0){
            monstreSauvage.attaquer(monstreJoueur)
        }
    }
    /**
     * Action du joueur pendant son tour.
     *
     * Propose trois choix : attaquer, utiliser un objet, changer de monstre.
     *
     * @return false si le combat doit s'arrêter (par exemple capture réussie ou défaite), true sinon.
     */
    fun actionJoueur(): Boolean{
        if(gameOver() == true){
            return false
        }
        println("Menu d'action (1 - > Attaquer le monstre , 2 -> Utiliser un objet , 3 -> Changer de monstre)")
        var choixAction = readln().toInt()
        if(choixAction == 1){
            monstreJoueur.attaquer(monstreSauvage)
            return true
        } else if(choixAction == 2){
            println("Sac :")
            for (i in joueur.sacAItems.indices) {
                println("$i : ${joueur.sacAItems[i].nom}")
            }
            var indexChoix: Int = readln().toInt()
            var objetChoisi = joueur.sacAItems[indexChoix]
            if(objetChoisi is Utilisable){
                var captureReussie = objetChoisi.utiliser(monstreSauvage)
                if(captureReussie == true){
                    return false
                }
            } else{
                println("Objet non utilisable")
            }
        } else if(choixAction == 3){
            println("Équipe :")
            for (i in joueur.equipeMonstre.indices) {
                println("$i : ${joueur.equipeMonstre[i].nom}")
            }
            var indexChoix = readln().toInt()
            var choixMonstre = joueur.equipeMonstre[indexChoix]
            if(choixMonstre.pv <= 0){
                println("Impossible ! Ce monstre est KO")
            } else{
                println("${choixMonstre.nom} remplace ${monstreJoueur.nom}")
            }
        }
    return true
    }
    /**
     * Affiche l'état actuel du combat : niveaux, PV, et art ASCII des monstres.
     */
    fun afficheCombat(){
        println("========= Début Round : $round =========")
        println("Niveau : ${monstreSauvage.niveau}")
        println("PV : ${monstreSauvage.pv} / ${monstreSauvage.pvMax}")
        println(monstreSauvage.espece.afficheArt())
        println(monstreJoueur.espece.afficheArt(false))
        println("Niveau : ${monstreJoueur.niveau}")
        println("PV : ${monstreJoueur.pv} / ${monstreJoueur.pvMax}")
    }
    /**
     * Gère un tour complet du combat en fonction de la vitesse des monstres.
     *
     * Le monstre le plus rapide agit en premier.
     */
    fun jouer(){
        val joueurPlusRapide: Boolean = (monstreJoueur.vitesse>=monstreSauvage.vitesse)
        afficheCombat()
        if(joueurPlusRapide){
            var continuer = this.actionJoueur()
            if(continuer == false){
                return
            }
            else{
                this.actionAdversaire()
                return
            }
        } else {
            this.actionAdversaire()
            if(this.gameOver()==false){
                val continuer = this.actionJoueur()
            } else {
                return
            }
        }
    }
    /**
     * Lance le combat et gère les rounds jusqu'à la victoire ou la défaite.
     *
     * Affiche un message de fin si le joueur perd et restaure les PV
     * de tous ses monstres.
     */
    fun lancerCombat() {
        while (!gameOver() && !joueurGagne()) {
            this.jouer()
            println("======== Fin du Round : $round ========")
            round++
        }
        if (gameOver()) {
            joueur.equipeMonstre.forEach { it.pv = it.pvMax }
            println("Game Over !")
        }
    }



}