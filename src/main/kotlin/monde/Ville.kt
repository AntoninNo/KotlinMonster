package monde
import Zone
import kotlin.math.exp
import EspeceMonstre
/**
 * Représente une ville dans le monde du jeu.
 *
 * Une ville est un type particulier de zone, avec des fonctionnalités supplémentaires
 * comme la présence d'une arène pour les combats et d'un magasin pour acheter des objets.
 * Elle hérite des caractéristiques de base d'une zone (identifiant, nom, gain d'expérience,
 * et liste des espèces de monstres rencontrables), tout en ajoutant ses propres particularités.
 *
 * @property id L'identifiant unique de la ville.
 * @property nom Le nom de la ville.
 * @property expZone L'expérience moyenne que les monstres de cette ville rapportent.
 * @property especeMonstres La liste des espèces de monstres pouvant être rencontrées dans cette ville.
 */
class Ville(id: Int,
            nom: String,
            expZone: Int,
            especeMonstres: MutableList<EspeceMonstre>,
            arene: Arene
            //TODO lignesMagasin de type MutableList<Ligne Magasin>
            ):Zone(id,nom,expZone,especeMonstres) {

}