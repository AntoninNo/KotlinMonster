import kotlin.math.pow
import kotlin.random.Random
import kotlin.math.floor

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
    var potentiel: Double = Random.nextDouble(0.5,2.000001arch)
    var exp: Double = 0.0
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
            }
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
            attaque += floor((espece.modAttaque * potentiel)).toInt() + if (Random.nextBoolean()) 2 else -2
            defense += floor((espece.modDefense * potentiel)).toInt() + if (Random.nextBoolean()) 2 else -2
            vitesse += floor((espece.modVitesse * potentiel)).toInt() + if (Random.nextBoolean()) 2 else -2
            attaqueSpe += floor((espece.modAttaqueSpe * potentiel)).toInt() + if (Random.nextBoolean()) 2 else -2
            defenseSpe += floor((espece.modDefenseSpe * potentiel)).toInt() + if (Random.nextBoolean()) 2 else -2
            pvMax += floor((espece.modPv * potentiel)).toInt() + if (Random.nextBoolean()) 5 else -5
    }

}