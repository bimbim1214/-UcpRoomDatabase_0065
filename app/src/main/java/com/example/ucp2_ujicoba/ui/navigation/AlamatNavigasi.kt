package com.example.ucp2_ujicoba.ui.navigation

interface AlamatNavigasi{
    val route: String
}

object DestinasiHome : AlamatNavigasi{
    override val route = "Awal"
}

object DestinasiHomeBrg : AlamatNavigasi{
    override val route = "home"
}

object DestinasiInsertBrg : AlamatNavigasi{
    override val route: String = "insert_brg"
}

object DestinasiDetailBrg : AlamatNavigasi{
    override val route = "detailBrg"
    const val ID = "id"
    val routeWithArg = "$route/{$ID}"
}

object DestinasiUpdateBrg : AlamatNavigasi{
    override val route = "updateBrg"
    const val ID = "id"
    val routeWithArg = "$route/{$ID}"
}

object DestinasiSuplierHome : AlamatNavigasi {
    override val route = "suplier_home"
}

object DestinasiSuplierInsert : AlamatNavigasi {
    override val route = "suplier_insert"
}

object DestinasiSuplierDetail : AlamatNavigasi {
    override val route = "suplier_detail"
    const val ID = "id"
    val routeWithArg = "$route/{$ID}"
}





