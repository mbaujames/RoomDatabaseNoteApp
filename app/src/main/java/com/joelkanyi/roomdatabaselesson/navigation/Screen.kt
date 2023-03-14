package com.joelkanyi.roomdatabaselesson.navigation

const val ID = "id"
const val TITLE = "title"
const val CONTENT = "content"

sealed class Screen(val route: String) {
    object Notes : Screen(route = "notes_screen")
    object AddNote : Screen(route = "add_note_screen/{$TITLE}/{$CONTENT}/{$ID}") {
        fun passNoteValues(title: String, content: String, id: Int?): String {
            return "add_note_screen/$title/$content/$id"
        }
    }
}
