class Partie (
    var id: Int,
    var joueur:Entraineur,
    var zone: Zone){

    fun choixStarter(){
        val monstre1:IndividuMonstre = IndividuMonstre(1, "springleaf", especeSpringleaf,null,1500.0 )    }
        val monstre2:IndividuMonstre = IndividuMonstre(2, "flamkip", especeFlamkip,null,1500.0 )
        val monstre3:IndividuMonstre = IndividuMonstre(3, "aquamy", especeAquamy,null,1500.0 )
        println("${monstre1.nom} = 1 , ${monstre2.nom} = 2 , ${monstre2.nom} = 3 ")
        var choix = readln()
}