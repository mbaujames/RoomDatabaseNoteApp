package com.joelkanyi.roomdatabaselesson.screen

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.joelkanyi.roomdatabaselesson.data.NoteDatabase
import com.joelkanyi.roomdatabaselesson.navigation.Screen

@OptIn(ExperimentalMaterialApi::class)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun NotesScreen(
    navController: NavController
) {
    val context = LocalContext.current
    val database = NoteDatabase.getDatabase(context)
    val noteDao = database.noteDao()
    val notes = noteDao.getAllNotes().collectAsState(initial = emptyList()).value

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Notes")
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                backgroundColor = Color.Blue,
                onClick = {
                    navController.navigate(Screen.AddNote.route)
                }
            ) {
               Icon(
                   imageVector = Icons.Default.Add,
                   contentDescription = null,
                   tint = Color.White
               )
            }
        }
    ) {
        LazyColumn {
            items(notes) {note ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    shape = RoundedCornerShape(12.dp),
                    elevation = 5.dp,
                    onClick = {
                       navController.navigate(Screen.AddNote.passNoteValues(
                           title = note.title,
                           content = note.content,
                           id = note.id
                       ))
                    }
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(12.dp)
                    ) {
                        Text(text = note.title, fontSize = 18.sp, fontWeight = FontWeight.Bold)
                        Text(text = note.content)
                        IconButton(
                            modifier = Modifier.align(Alignment.End),
                            onClick = {
                                noteDao.deleteANote(note)
                            }
                        ) {
                            Icon(
                                imageVector = Icons.Default.Delete,
                                contentDescription = null,
                                tint = Color.Red
                            )
                        }
                    }
                }
            }
        }
    }
}

// Kotlin Flows/ LiveData











