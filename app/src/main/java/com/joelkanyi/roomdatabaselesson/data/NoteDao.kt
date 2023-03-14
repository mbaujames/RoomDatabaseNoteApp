package com.joelkanyi.roomdatabaselesson.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDao {
    @Insert
    fun insertNote(noteEntity: NoteEntity)

    @Query("SELECT * FROM notes_table ORDER BY id DESC")
    fun getAllNotes(): Flow<List<NoteEntity>>

    @Delete
    fun deleteANote(noteEntity: NoteEntity)

    @Update
    fun updateANote(noteEntity: NoteEntity)
}