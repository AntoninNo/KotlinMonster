import jeu.CombatMonstre
import kotlin.random.Random
/**
 * Représente une session de combat entre deux entraineurs (joueur et adversaire).
 *
 * Cette classe gère l'état du combat, vérifie si un des entraineurs a gagné ou perdu,
 * lance le combat entre monstres choisis, et distribue les récompenses ou soigne les équipes en fonction du résultat.
 *
 * @property id Identifiant unique du combat.
 * @property joueur L'entraineur joueur participant au combat.
 * @property entraineurAdversaire L'entraineur adverse participant au combat.
 */
class CombatDresseur(
    var id: Int,
    var joueur :Entraineur,
    var entraineurAdversaire: Entraineur
) {

    /**
     * Vérifie si le joueur a gagné le combat.
     *
     * Cette méthode compte combien de monstres de l'adversaire ont leurs points de vie (pv) à 0 ou moins.
     * Si tous les monstres adverses sont KO, le joueur est déclaré vainqueur.
     *
     * @return true si tous les monstres adverses sont KO, false sinon.
     */
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
    /**
     * Vérifie si le joueur a perdu le combat.
     *
     * Cette méthode compte combien de monstres du joueur ont leurs points de vie (pv) à 0 ou moins.
     * Si tous les monstres du joueur sont KO, le joueur est déclaré perdant.
     *
     * @return true si tous les monstres du joueur sont KO, false sinon.
     */
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
    /**
     * Lance un tour de combat entre le joueur et l'entraineur adverse.
     *
     * - Affiche le début du combat.
     * - Si le combat n'est pas encore terminé (ni gagné ni perdu), chaque entraineur choisit un monstre.
     * - Lance un combat entre les monstres sélectionnés.
     * - Si le joueur gagne, il reçoit une récompense en argent (moitié de l'argent de l'adversaire).
     * - Si le joueur perd, les deux équipes sont soignées.
     */
    fun lanceCombat(){
        println("Combat entre ${joueur.nom} et ${entraineurAdversaire.nom} commence !")
        if(!avoirGagner() && !avoirPerdu()){
            var monstreJoueur = joueur.choisirMonstre()
            var monstreAdverse = entraineurAdversaire.equipeMonstre[Random.nextInt(0,entraineurAdversaire.equipeMonstre.size-1)]
            println("${joueur.nom} envoie ${monstreJoueur}")
            println("${entraineurAdversaire.nom} envoie ${monstreAdverse}")
            var combat = CombatMonstre(monstreJoueur,monstreAdverse)
            combat.lancerCombat()
        }
        if(avoirGagner()){
            var recompense = entraineurAdversaire.argents/2
            println("Fécilitations ! Vous avez gagné le combat et ramasser ${recompense} $")
            joueur.argents += recompense
        }else {
            println("Vous avez perdu ...")
            joueur.soignerEquipe()
            entraineurAdversaire.soignerEquipe()
        }
    }
}