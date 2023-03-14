package com.joelkanyi.roomdatabaselesson.screen

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.joelkanyi.roomdatabaselesson.data.NoteDatabase
import com.joelkanyi.roomdatabaselesson.data.NoteEntity

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun AddNoteScreen(
    title: String = "",
    content: String = "",
    id: Int = -1,
    navController: NavController,
) {
    val context = LocalContext.current
    val database = NoteDatabase.getDatabase(context)
    val noteDao = database.noteDao()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Add Note")
                }
            )
        }
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            var noteTitle by remember {
                mutableStateOf(title)
            }

            var noteContent by remember {
                mutableStateOf(content)
            }

            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = noteTitle,
                onValueChange = {
                    noteTitle = it
                },
                placeholder = {
                    Text(text = "Note title...")
                }
            )

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = noteContent,
                onValueChange = {
                    noteContent = it
                },
                placeholder = {
                    Text(text = "Note content...")
                }
            )

            Spacer(modifier = Modifier.height(32.dp))

            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = {
                    val note = NoteEntity(
                        title = noteTitle,
                        content = noteContent,
                        id = if (id != -1) {
                            id
                        } else {
                            null
                        }
                    )

                    if (title != ""){
                        noteDao.updateANote(NoteEntity(
                            id = id,
                            title = noteTitle,
                            content = noteContent
                        ))
                    }else {
                        noteDao.insertNote(note)
                    }

                    navController.popBackStack()
                }
            ) {
                Text(
                    modifier = Modifier.padding(8.dp),
                    text = "Add Note"
                )
            }
        }
    }
}