package monde
import CombatDresseur
import Entraineur
import joueur

class Arene(
    var id: Int,
    var nom: String,
    var dresseurs: MutableList<Entraineur> = mutableListOf(),
    var champion:Entraineur
) {
    fun challenger(){
        var listeDresseurs: MutableList<Entraineur> = mutableListOf()
        for(entraineur in dresseurs){
            listeDresseurs.add(entraineur)
        }
        listeDresseurs.add(champion)
        while(listeDresseurs.isNotEmpty()){
            var adversaire = listeDresseurs[0]
            var combat = CombatDresseur(1,joueur,adversaire)
            combat.lanceCombat()
            if(combat.avoirPerdu()){
                println("${joueur.nom} à été vaincu par ${adversaire.nom}")
            } else if(combat.avoirGagner()){
                println("${joueur.nom} à vaincu par ${adversaire.nom}")
                listeDresseurs.removeAt(0)
            }
        }
        println("${joueur.nom} a gagné tous ses combats dans l'${this.nom}")
        //joueur.sacAItems.add(badge)
        println("${joueur.nom} remporte le ${badge.nom}")
    }
}