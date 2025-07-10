package org.example.gameopedia

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.material.NavigationRail
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.Navigation
import androidx.navigation.Navigator
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.game.ui.game.GameScreen
import com.example.game.ui.gameDetails.GameDetailsScreen
import com.example.search.ui.SearchScreen
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

import gameopedia.composeapp.generated.resources.Res
import gameopedia.composeapp.generated.resources.compose_multiplatform
import org.example.gameopedia.navigation.GameNavGraph

@Composable
@Preview
fun App() {
    MaterialTheme {
        val navController = rememberNavController()
        /*NavHost(navController, startDestination = GameNavGraph.Dest.Game.route){
            listOf(GameNavGraph)
                .forEach {
                    it.build(
                        modifier =  Modifier.fillMaxSize(),
                        navHostController = navController,
                        navGraphBuilder = this
                    )
                }
        }*/
        NavHost(navController, startDestination = "/game") {
            composable("/game") {
                GameScreen(onFavoriteClick = {}, onSearchClick = {
                    navController.navigate("/search")
                }, onItemClick = { id ->
                    navController.navigate("/gameDetails/$id")
                })
            }
            composable("/gameDetails/{id}") {
                val id = it.arguments?.getString("id")?.toIntOrNull() ?: 0
                GameDetailsScreen(id = id, onBackCLick = {
                    navController.popBackStack()
                })
            }
            composable("/search") {
                SearchScreen(onClick = {id->
                    navController.navigate("/gameDetails/$id")
                })
            }
        }
    }
}