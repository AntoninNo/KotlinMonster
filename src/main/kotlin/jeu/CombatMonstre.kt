package jeu
import IndividuMonstre
import EspeceMonstre
import item.Utilisable
import joueur


class CombatMonstre(
    var monstreJoueur: IndividuMonstre,
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
    fun actionAdversaire(){
        if(monstreSauvage.pv > 0){
            monstreSauvage.attaquer(monstreJoueur)
        }
    }
    fun actionJoueur(): Boolean{
        if(gameOver() == true){
            return false
        }
        println("Menu d'action (1, 2 ,3)")
        var choixAction = readln().toInt()
        if(choixAction == 1){
            monstreJoueur.attaquer(monstreSauvage)
            return true
        } else if(choixAction == 2){
            println(joueur.sacAItems)
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
            println(joueur.equipeMonstre)
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
    fun afficheCombat(){
        println("========= Début Round : $round =========")
        println("Niveau : ${monstreSauvage.niveau}")
        println("PV : ${monstreSauvage.pv} / ${monstreSauvage.pvMax}")
        println(monstreSauvage.espece.afficheArt())
        println(monstreJoueur.espece.afficheArt(false))
        println("Niveau : ${monstreJoueur.niveau}")
        println("PV : ${monstreJoueur.pv} / ${monstreJoueur.pvMax}")
    }
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