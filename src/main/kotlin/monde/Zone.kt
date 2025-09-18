/**
 * Représente une zone dans le jeu.
 *
 * Une zone contient des informations sur son identité, son nom,
 * l'expérience qu'elle procure, ainsi que la liste des espèces de monstres qu'on peut y rencontrer.
 * Elle peut aussi être liée à une zone précédente et une zone suivante, formant une chaîne de zones.
 *
 * @property id L'identifiant unique de la zone.
 * @property nom Le nom de la zone.
 * @property expZone L'expérience que rapporte la zone.
 * @property especeMonstres La liste mutable des espèces de monstres présentes dans cette zone.
 * @property zoneSuivante La zone suivante reliée à cette zone, si elle existe.
 * @property ZonePrecedente La zone précédente reliée à cette zone, si elle existe.
 */
class Zone(
    val id: Int,
    var nom: String,
    var expZone : Int,
    var especeMonstres: MutableList<EspeceMonstre> = mutableListOf(),
    var zoneSuivante: Zone? = null,
    var ZonePrecedente: Zone? = null
){
    // TODO genereMonstre()
    // TODO rencontreMonstre()
}