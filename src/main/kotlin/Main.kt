import jeu.CombatMonstre
import monde.Ville
import monstre.PalierEvolution
import java.lang.foreign.Arena
import javax.management.MBeanParameterInfo

var joueur = Entraineur(1,"Sacha",100)
var rival = Entraineur(2,"Regis",200)

val especeSpringleaf = EspeceMonstre(1, "Springleaf", "Graine", 9, 11, 10, 12, 14, 60, 6.5, 9.0, 8.0, 7.0, 10.0, 34.0, "Petit monstre espiègle rond comme une graine, adore le soleil.", "Sa feuille sur la tête indique son humeur.", "Curieux, amical, timide")
val especeFlamkip = EspeceMonstre(4, "Flamkip", "Animal", 12, 8, 13, 16, 7, 50, 10.0, 5.5, 9.5, 9.5, 6.5, 22.0, "Petit animal entouré de flammes, déteste le froid.", "Sa flamme change d’intensité selon son énergie.", "Impulsif, joueur, loyal")
val especeAquamy = EspeceMonstre(7, "Aquamy", "Meteo", 10, 11, 9, 14, 14, 55, 9.0, 10.0, 7.5, 12.0, 12.0, 27.0, "Créature vaporeuse semblable à un nuage, produit des gouttes pures.", "Fait baisser la température en s’endormant.", "Calme, rêveur, mystérieux")
val especeLaoumi = EspeceMonstre(8, "Laoumi", "Animal", 11, 10, 9, 8, 11, 58, 11.0, 8.0, 7.0, 6.0, 11.5, 23.0, "Petit ourson au pelage soyeux, aime se tenir debout.", "Son grognement est mignon mais il protège ses amis.", "Affectueux, protecteur, gourmand")
val especeBugsyface = EspeceMonstre(10, "Bugsyface", "Insecte", 10, 13, 8, 7, 13, 45, 7.0, 11.0, 6.5, 8.0, 11.5, 21.0, "Insecte à carapace luisante, se déplace par bonds et vibre des antennes.", "Sa carapace devient plus dure après chaque mue.", "Travailleur, sociable, infatigable")
val especeGalum = EspeceMonstre(13, "Galum", "Minéral", 12, 15, 6, 8, 12, 55, 9.0, 13.0, 4.0, 6.5, 10.5, 13.0, "Golem ancien de pierre, yeux lumineux en garde.", "Peut rester immobile des heures comme une statue.", "Sérieux, stoïque, fiable")
val especePyrokip = EspeceMonstre(id = 5, nom = "pyrokip", type = "Animal", baseAttaque = 18, baseDefense = 12, baseVitesse = 15, baseAttaqueSpe = 22, baseDefenseSpe = 11, basePv = 70, modAttaque = 12.0, modDefense = 8.0, modVitesse = 11.0, modAttaqueSpe = 12.5, modDefenseSpe = 8.0, modPv = 15.0, description = "Pyrokip, l’évolution de Flamkip. Son feu est devenu intense et ses flammes sont capables de fondre la pierre. Fier et courageux, il protège son dresseur à tout prix.", particularites = "Ses flammes changent de couleur selon son humeur : rouge vif en colère, dorées quand il est calme.", caractères = "Fier, protecteur, explosif.",
    //elements = mutableListOf(feu)
)

val palierEvolutionFlamkip = PalierEvolution(1,7,especePyrokip)


var route1 = Zone(1,"Route 1",600,mutableListOf<EspeceMonstre>(especeLaoumi,especeBugsyface))
var route2 = Zone(2,"Route 2",800,mutableListOf<EspeceMonstre>(especeSpringleaf,especeGalum))
var racailleCity = Ville(3,"Racaille City",1000,mutableListOf<EspeceMonstre>(especeFlamkip))

var kube1 = MonsterKube(1,"Kube","Un petit kube pour capturer un monstre",50.0)

fun main() {
    route1.zoneSuivante = route2
    route2.ZonePrecedente = route1
    route2.zoneSuivante = racailleCity
    racailleCity.ZonePrecedente = route2

    joueur.sacAItems.add(kube1)
    especeFlamkip.palierEvolution = palierEvolutionFlamkip

    val partie = nouvellePartie()
    partie.choixStarter()
    partie.jouer()
}

fun nouvellePartie():Partie{
    println("Bienvenue dans le monde magique des Pokémon! Mon nom est Chen! Les gens souvent m'appellent le Prof Pokémon! Ce monde est peuplé de créatures du nom de Pokémon!")
    println("Rentrez votre nom : ")
    val nomJoueur = readln()
    val PartieJoueur = Partie(1,joueur,route1)
    return PartieJoueur
}