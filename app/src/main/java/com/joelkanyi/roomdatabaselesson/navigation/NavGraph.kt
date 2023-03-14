package com.joelkanyi.roomdatabaselesson.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.joelkanyi.roomdatabaselesson.screen.AddNoteScreen
import com.joelkanyi.roomdatabaselesson.screen.NotesScreen

@Composable
fun NavGraph(
    navController: NavHostController,
) {
    NavHost(navController = navController, startDestination = Screen.Notes.route) {
        composable(route = Screen.Notes.route) {
            NotesScreen(navController = navController)
        }

        composable(
            route = Screen.AddNote.route,
            arguments = listOf(
                navArgument(name = TITLE){
                    type = NavType.StringType
                },
                navArgument(name = CONTENT) {
                    type = NavType.StringType
                },
                navArgument(name = ID) {
                    type = NavType.IntType
                }
            )
        ) { navBackStackEntry ->
            AddNoteScreen(
                navController = navController,
                title = navBackStackEntry.arguments?.getString(TITLE) ?: "",
                content = navBackStackEntry.arguments?.getString(CONTENT) ?: "",
                id = navBackStackEntry.arguments?.getInt(ID) ?: -1
            )
        }
    }
}