package jeu

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
    fun gameOver(): Boolean{
        var compteur = 0
        joueur.equipeMonstre.forEach { monstre ->
            if(monstre.pv = 0){
                compteur++
            }
            if(compteur == joueur.equipeMonstre.size) return true else return false

        }
    }

}