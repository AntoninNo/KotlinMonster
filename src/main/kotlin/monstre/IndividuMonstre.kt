import monstre.PalierEvolution
import kotlin.math.pow
import kotlin.math.round
import kotlin.random.Random

/**
 * Représente un individu de type monstre dans le contexte du jeu.
 *
 * Chaque individu possède des caractéristiques propres, dérivées de son espèce,
 * et peut être affilié ou non à un entraîneur. Ses statistiques varient légèrement
 * par rapport aux valeurs de base de son espèce grâce à une variation aléatoire à la création.
 * Il possède également un niveau, une expérience, un potentiel d'évolution et des
 * statistiques telles que l'attaque, la défense, la vitesse, etc.
 *
 * Lorsqu'il gagne de l'expérience, il peut monter de niveau automatiquement,
 * ce qui est géré via la propriété `exp`.
 *
 * @property id L'identifiant unique de l'individu.
 * @property nom Le nom donné à l'individu.
 * @property espece L'espèce à laquelle appartient l'individu, définissant ses statistiques de base.
 * @property entraineur L'entraîneur auquel l'individu est rattaché, s'il y en a un.
 * @property niveau Le niveau actuel de l'individu.
 * @property attaque La valeur d'attaque de l'individu, basée sur l'espèce avec une variation aléatoire.
 * @property defense La valeur de défense de l'individu, avec variation aléatoire.
 * @property vitesse La vitesse de l’individu, avec variation aléatoire.
 * @property attaqueSpe L’attaque spéciale de l’individu, avec variation aléatoire.
 * @property defenseSpe La défense spéciale de l’individu, avec variation aléatoire.
 * @property pvMax Les points de vie maximum de l’individu, avec variation aléatoire.
 * @property potentiel Le potentiel d’évolution de l’individu, une valeur entre 0.5 et 2.0.
 * @property exp L'expérience accumulée par l’individu ; déclenche une montée de niveau lorsqu’un palier est atteint.
 */
class IndividuMonstre(
    var id:Int,
    var nom: String,
    var espece: EspeceMonstre,
    var entraineur: Entraineur? = null,
    expInit: Double
    ) {
    var niveau: Int = 1
    var attaque:Int = this.espece.baseAttaque + if (Random.nextBoolean()) 2 else -2
    var defense:Int = this.espece.baseDefense + if (Random.nextBoolean()) 2 else -2
    var vitesse: Int = this.espece.baseVitesse + if (Random.nextBoolean()) 2 else -2
    var attaqueSpe: Int = this.espece.baseAttaqueSpe + if (Random.nextBoolean()) 2 else -2
    var defenseSpe : Int = this.espece.baseDefenseSpe + if (Random.nextBoolean()) 2 else -2
    var pvMax: Int = this.espece.basePv + if (Random.nextBoolean()) 5 else -5
    var potentiel: Double = Random.nextDouble(0.5,2.000001)
    var exp: Double = 0.0
        get() = field
        set(value) {
            field = value
            var estNiveau1 = false
            if (niveau == 1) {
                estNiveau1 = true
            }
            do {
                levelUp()
                if (estNiveau1 == false) {
                    println("Le monstre $nom est maintenant niveau $niveau")
                    break

                }
            } while (field >= palierExp(niveau))
        }


    /**
     *  @property pv  Points de vie actuels.
     * Ne peut pas être inférieur à 0 ni supérieur à [pvMax].
     */
    var pv: Int = pvMax
        get() = field
        set(nouveauPv) {
            field=nouveauPv
            if(nouveauPv > pvMax){
                field = pvMax
            } else if(nouveauPv<0)
                field = 0
        }
    init {
        this.exp = expInit // applique le setter et déclenche un éventuel level-up
    }
    /**
     * Calcule l'expérience totale nécessaire pour atteindre un niveau donné.
     *
     * @param niveau Niveau cible.
     * @return Expérience cumulée nécessaire pour atteindre ce niveau.
     */
    fun palierExp(niveauCible: Int = this.niveau): Double{
        return (100*(niveauCible-1).toDouble().pow(2.0))
    }

    /**
     * Incrémente le niveau quand appeler
     * Vérifie si un palier d'évolution est atteint
     * Augmente les stats de l'invididu avec un caractère aléatoire
     */
    fun levelUp():Unit{
        niveau ++

        if(espece.palierEvolution != null){
            if(espece.palierEvolution!!.peutEvoluer(this)){
                evoluer()
            }
        }

        attaque += round((espece.modAttaque * potentiel)).toInt() + (Random.nextInt(-2,3))
        defense += round((espece.modDefense * potentiel)).toInt() + (Random.nextInt(-2,3))
        vitesse += round((espece.modVitesse * potentiel)).toInt() + (Random.nextInt(-2,3))
        attaqueSpe += round((espece.modAttaqueSpe * potentiel)).toInt() + (Random.nextInt(-2,3))
        defenseSpe += round((espece.modDefenseSpe * potentiel)).toInt() + (Random.nextInt(-2,3))
        pvMax += round((espece.modPv * potentiel)).toInt() + (Random.nextInt(-5,6))
    }
    /**
     * Attaque un autre [IndividuMonstre] et inflige des dégâts.
     *
     * Les dégâts sont calculés de manière très simple pour le moment :
     * `dégâts = attaque - (défense / 2)` (minimum 1 dégât).
     *
     * @param cible Monstre cible de l'attaque.
     */

    fun attaquer(cible: IndividuMonstre){
        var degatBrut = this.attaque
        var degatTotal = degatBrut-(this.defense/2)
        if(degatTotal<1){
            degatTotal = 1
        }
        var pvAvant = cible.pv
        cible.pv -= degatTotal
        var pvApres = cible.pv
        println("${this.nom} inflige ${pvAvant-pvApres} dégâts à ${cible.nom}")
    }
    /**
     * Demande au joueur de renommer le monstre.
     * Si l'utilisateur entre un texte vide, le nom n'est pas modifié.
     */
    fun renommer(){
       println("Renommer ${this.nom}")
       var nouveauNom =  readln()
        if(nouveauNom.isNotEmpty()){
            this.nom = nouveauNom
        }
    }

    /**
     * Afficher le détail d'un individu
     */
    fun afficherDetail(){
        println(espece.afficheArt())
        println("Nom du monstre : $nom")
        println("Point de vie : $pv")
        println("Attaque : $attaque")
        println("Défense : $defense")
        println("AttaqueSpé : $attaqueSpe")
        println("DéfenseSpé : $defenseSpe")
        println("Vitesse : $vitesse")
    }

    /**
     * Change l'espèce de l'individu pour son évolution
     */
    fun evoluer(){
        this.espece = this.espece.palierEvolution!!.evolution
    }
}