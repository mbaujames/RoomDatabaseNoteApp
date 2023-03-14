package com.joelkanyi.roomdatabaselesson.navigation

const val ID = "id"
const val TITLE = "title"
const val CONTENT = "content"

sealed class Screen(val route: String) {
    object Notes : Screen(route = "notes_screen")
    object AddNote : Screen(route = "add_note_screen?title={$TITLE}&content={$CONTENT}&id={$ID}") {
        fun passNoteValues(title: String? = null, content: String? = null, id: Int? = null): String {
            return "add_note_screen?title=$title&content=$content&id=$id"
        }
    }
}
