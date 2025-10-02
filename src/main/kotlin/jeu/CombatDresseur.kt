import Entraineur
import joueur

class CombatDresseur(
    var id: Int,
    var joueur :Entraineur,
    var entraineurAdversaire: Entraineur
) {
    fun avoirGagner(): Boolean{
        var compteur = 0
        entraineurAdversaire.equipeMonstre.forEach { monstre ->
            if(monstre.pv <= 0){
                compteur++
            }
        }
        if(compteur == entraineurAdversaire.equipeMonstre.size) {
            return true
        }else {
            return false
        }
    }

    fun avoirPerdu(): Boolean{
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

    fun lanceCombat(){
        println("Combat entre ${joueur.nom} et ${entraineurAdversaire.nom} commence !")
        if(!avoirGagner() && !avoirPerdu()){
            var monstreJoueur = joueur.choisirMonstre()
            var monstreAdverse = entraineurAdversaire.equipeMonstre[]
        }
    }
}