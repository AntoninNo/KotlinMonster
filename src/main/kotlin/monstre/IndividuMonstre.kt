import kotlin.math.pow
import kotlin.math.round
import kotlin.random.Random

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
    fun levelUp():Unit{
        niveau ++

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

}