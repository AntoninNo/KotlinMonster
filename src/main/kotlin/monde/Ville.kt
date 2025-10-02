package monde
import Zone
import kotlin.math.exp
import EspeceMonstre

class Ville(id: Int,
            nom: String,
            expZone: Int,
            especeMonstres: MutableList<EspeceMonstre>
            //TODO arene de type Arene
            //TODO lignesMagasin de type MutableList<Ligne Magasin>
            ):Zone(id,nom,expZone,especeMonstres) {

}