/**
 * Représente un Badge obtenu en battant un champion d'arène.
 *
 * @property champion L'entraîneur (champion) qui a remis ce badge.
 *
 * Hérite de la classe Item, car c'est un objet que le joueur peut posséder.
 */
class Badge(id: Int,
            nom: String,
            description: String,
            var champion: Entraineur
            ): Item(id,nom,description) {


}
