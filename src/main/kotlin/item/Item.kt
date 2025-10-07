/**
 * Représente un objet générique dans le jeu.
 *
 * @property id Identifiant unique de l'objet.
 * @property nom Nom de l'objet.
 * @property description Description textuelle de l'objet.
 *
 * Cette classe peut être étendue pour créer différents types d'objets.
 */
open class Item (
    var id: Int,
    var nom: String,
    var description: String
){

}