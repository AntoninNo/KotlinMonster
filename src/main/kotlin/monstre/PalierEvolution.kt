package monstre
import EspeceMonstre
import IndividuMonstre

/**
 * Représente un palier d'évolution pour une espèce de monstre.
 *
 * Chaque palier définit un niveau requis à partir duquel un individu peut évoluer
 * vers une nouvelle espèce de monstre.
 *
 * Cette classe est utilisée pour déterminer si un individu remplit les conditions
 * nécessaires pour évoluer lors d'un changement de niveau.
 *
 * @property id Identifiant unique du palier d'évolution.
 * @property niveauRequis Le niveau minimal que doit atteindre un individu pour pouvoir évoluer.
 * @property evolution L'espèce vers laquelle le monstre évolue une fois le niveau requis atteint.
 */
class PalierEvolution(
    var id: Int,
    var niveauRequis: Int,
    var evolution:EspeceMonstre
) {
/**
 * Vérifie si un monstre peut évoluer selon ce palier.
 *
 * @param monstre L'individu dont on veut vérifier la possibilité d'évolution.
 * @return `true` si le niveau du monstre correspond au niveau requis du palier, sinon `false`.
 */
    fun peutEvoluer(monstre:IndividuMonstre): Boolean{
        if(monstre.niveau == niveauRequis){
            return true
        }
        return false
    }
}