import EspeceMonstre
import kotlin.random.Random

class IndividuMonstre(
    var id:Int,
    var nom: String,
    var espece: EspeceMonstre,
    var entraineur: Entraineur? = null,
    explnit: Double
    ) {
    var niveau: Int = 1
    var attaque:Int = this.espece.baseAttaque + Random.nextInt(-2,2)
    var defense:Int = this.espece.baseDefense + Random.nextInt(-2,2)
    var vitesse: Int = this.espece.baseVitesse + Random.nextInt(-2,2)
    var attaqueSpe: Int = this.espece.baseAttaqueSpe + Random.nextInt(-2,2)
    var defenseSpe : Int = this.espece.baseDefenseSpe + Random.nextInt(-2,2)
    var pvMax: Int = this.espece.basePv + if (Random.nextBoolean()) 5 else -5
    var potentiel: Double = Random.nextDouble(0.5,2.0)
    var exp: Double = 0.0
    var pv: Int = pvMax
}