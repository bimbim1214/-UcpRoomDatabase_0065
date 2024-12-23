package com.example.ucp2_ujicoba.ui.navigation

import HomeScreen
import InsertSprView

import UpdateBrgView
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.ucp2_ujicoba.ui.view.barang.DetailBrgView
import com.example.ucp2_ujicoba.ui.view.barang.HomeBrgView
import com.example.ucp2_ujicoba.ui.view.barang.InsertBrgView
import com.example.ucp2_ujicoba.ui.view.suplier.DetailSprView
import com.example.ucp2_ujicoba.ui.view.suplier.HomeSprView


@Composable
fun PengelolaHalaman(
    navController: NavHostController = rememberNavController(),
    modifier: Modifier = Modifier,
){
    NavHost(navController = navController, startDestination = DestinasiHome.route){
        composable(
            route = DestinasiHome.route
        ) {
            HomeScreen(
                onBarangClick = {navController.navigate(DestinasiInsertBrg.route) },
                onBarangListClick = {navController.navigate(DestinasiHomeBrg.route)},
                onSuplierClick = {navController.navigate(DestinasiSuplierInsert.route)},
                onSuplierListClick = {navController.navigate(DestinasiSuplierHome.route) }
            )
        }
        composable(
            route = DestinasiHomeBrg.route
        ){
            HomeBrgView(
                onDetailClick = { id ->
                    navController.navigate("${DestinasiDetailBrg.route}/$id")
                    println("PengelolaHalaman: id = $id")
                },
                onAddBrg = {
                    navController.navigate(DestinasiInsertBrg.route)
                },
                modifier = modifier,
                onBack = {navController.popBackStack()}
            )
        }
        composable(
            route = DestinasiInsertBrg.route
        ) {
            InsertBrgView(
                onBack = {navController.popBackStack()},
                onNavigate = { navController.navigate(DestinasiHomeBrg.route)},
                modifier = modifier,
            )
        }
        composable(
            DestinasiDetailBrg.routeWithArg,
            arguments = listOf(
                navArgument(DestinasiDetailBrg.ID) {
                    type = NavType.StringType
                }
            )
        ) {
            val id = it.arguments?.getString(DestinasiDetailBrg.ID)
            id?.let { id ->
                DetailBrgView(
                    onBack = {navController.popBackStack()},
                    onEditClick = {navController.navigate("${DestinasiUpdateBrg.route}/$it")},
                    modifier = modifier,
                    onDeleteClick = {navController.popBackStack()}
                )
            }
        }
        composable(
            DestinasiUpdateBrg.routeWithArg,
            arguments = listOf(
                navArgument(DestinasiUpdateBrg.ID) {
                    type = NavType.StringType
                }
            )
        ) {
            UpdateBrgView(
                onBack = {navController.popBackStack()},
                onNavigate = {navController.popBackStack()},
                modifier = modifier,
            )
        }

        composable(
            route = DestinasiSuplierInsert.route
        ) {
            InsertSprView(
                onBack = { navController.popBackStack() },
                onNavigate = { navController.navigate(DestinasiSuplierHome.route) },
                modifier = modifier
            )
        }

        composable(
            route = DestinasiSuplierHome.route
        ) {
            HomeSprView(
                onDetailClick = { id ->
                    navController.navigate("${DestinasiSuplierDetail.route}/$id")
                    println("PengelolaHalaman: id = $id")
                },
                onAddSpr = {
                    navController.navigate(DestinasiSuplierInsert.route)
                },
                modifier = modifier,
                onBack = {navController.popBackStack()}

            )
        }

        composable(
            route = DestinasiSuplierDetail.routeWithArg,
            arguments = listOf(
                navArgument(DestinasiSuplierDetail.ID) {
                    type = NavType.StringType
                }
            )
        ) {
            val id = it.arguments?.getString(DestinasiSuplierDetail.ID)
            id?.let { suplierId ->
                DetailSprView(
                    onBack = { navController.popBackStack() },
                    modifier = modifier
                )
            }
        }
    }
}