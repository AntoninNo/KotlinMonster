import java.io.File

/**
 * Représente une espèce de monstre dans le contexte du jeu.
 *
 * Décrit les caractéristiques de base d’un type de monstre (comme un « modèle » ou une espèce).
 * Contient ses statistiques de base, ses multiplicateurs de croissance, son nom, son type et éventuellement son art ASCII.
 *
 * @property id L'identifiant unique de l'espèce
 * @property nom Le nom de l'espèce
 * @property type Le type de l'espèce
 * @property baseAttaque L'attaque de base de l'espèce
 * @property baseDefense La défense de base de l'espèce
 * @property baseVitesse La vitesse de base de l'espèce
 * @property baseAttaqueSpe L'attaque spéciale de base de l'espèce
 * @property baseDefenseSpe La défense spéciale de base de l'espèce
 * @property basePv Les points de base de l'espèce
 * @property modAttaque La valeur de modification de l'attaque de l'espèce à chaque passage de niveau
 * @property modDefense La valeur de modification de la défense de l'espèce à chaque passage de niveau
 * @property modVitesse La valeur de modification de la vitesse de l'espèce à chaque passage de niveau
 * @property modAttaqueSpe La valeur de modification de l'attaque spéciale de l'espèce à chaque passage de niveau
 * @property modDefenseSpe valeur de modification de la défense spéciale de l'espèce à chaque passage de niveau
 * @property modPv La valeur de modification des points de vie de l'espèce à chaque passage de niveau
 * @property description La description de l'espèce
 * @property particularites Les particularités de l'espèce
 * @property caractères Les caractères possibles de l'espèce
 */
class EspeceMonstre(
    var id : Int,
    var nom: String,
    var type: String,
    val baseAttaque: Int,
    val baseDefense: Int,
    val baseVitesse: Int,
    val baseAttaqueSpe: Int,
    val baseDefenseSpe: Int,
    val basePv: Int,
    val modAttaque: Double,
    val modDefense: Double,
    val modVitesse: Double,
    val modAttaqueSpe: Double,
    val modDefenseSpe: Double,
    val modPv: Double,
    val description: String = "",
    val particularites: String = "",
    val caractères: String = "",
    ){
    /**
     * Affiche la représentation artistique ASCII du monstre.
     *
     * @param deFace Détermine si l'art affiché est de face (true) ou de dos (false).
     *               La valeur par défaut est true.
     * @return Une chaîne de caractères contenant l'art ASCII du monstre avec les codes couleur ANSI.
     *         L'art est lu à partir d'un fichier texte dans le dossier resources/art.
     */
    fun afficheArt(deFace: Boolean=true): String{
        val nomFichier = if(deFace) "front" else "back";
        val art=
            File("src/main/resources/art/${this.nom.lowercase()}/$nomFichier.txt").readText()
        val safeArt = art.replace("/", "∕")
        return safeArt.replace("\\u001B", "\u001B")
    }
}