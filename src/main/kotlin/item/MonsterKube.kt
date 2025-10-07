import item.Utilisable
import kotlin.random.Random
import kotlin.random.nextInt
/**
 * Représente un objet consommable appelé "Monster Kube" permettant de capturer un monstre sauvage.
 *
 * @property chanceCapture La probabilité de capture de base de cet objet.
 *
 * Cette classe hérite de la classe Item et implémente l'interface Utilisable.
 */
class MonsterKube(
    id: Int,
    nom: String,
    description: String,
    var chanceCapture: Double,
    ): Item(id,nom,description), Utilisable {
    /**
     * Tente de capturer un monstre cible en fonction de sa santé actuelle et de la chance de capture.
     *
     * La chance effective de capture est ajustée en fonction du ratio des PV actuels sur les PV max du monstre,
     * augmentant la probabilité si le monstre a moins de PV.
     *
     * Si le monstre est déjà possédé par un dresseur, la capture est impossible.
     *
     * En cas de réussite, le joueur peut renommer le monstre capturé.
     * Le monstre est ajouté à l'équipe si la limite n'est pas atteinte, sinon il est stocké dans la boîte.
     *
     * @param cible Le monstre à capturer.
     * @return true si la capture a réussi, false sinon.
     */
    override fun utiliser(cible: IndividuMonstre): Boolean {
        println("Vous lancez un Monster Kube !")
        if(cible.entraineur != null){
            println("Le monstre ne peut pas être capturé")
        }
        val ratioVie = cible.pv/cible.pvMax
        var chanceEffective = chanceCapture * (1.5-ratioVie)

        if(chanceEffective<5) chanceEffective = 5.0

        val nbAleatoire = Random.nextInt(0..100)

        if(nbAleatoire < chanceEffective){
            println("Le monstre est capturé !")
            println("Insérer un nouveau nom si vous le souhaitez :")
            var nouveauNom = readln()
            if(nouveauNom.isNotEmpty()) cible.nom = nouveauNom
            if(joueur.equipeMonstre.size >= 6){
                joueur.boiteMonstre.add(cible)
            } else {
                joueur.equipeMonstre.add(cible)
            }
            cible.entraineur = joueur
            return true
        } else {
            println("Presque ! Le Kube n'a pas pu capturer le monstre")
            return false
        }
    }
}
