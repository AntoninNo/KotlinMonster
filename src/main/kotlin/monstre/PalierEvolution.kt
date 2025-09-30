package monstre
import EspeceMonstre
import IndividuMonstre


class PalierEvolution(
    var id: Int,
    var niveauRequis: Int,
    var evolution:EspeceMonstre
) {
    fun peutEvoluer(monstre:IndividuMonstre): Boolean{
        if(monstre.niveau == niveauRequis){
            return true
        }
        return false
    }
}