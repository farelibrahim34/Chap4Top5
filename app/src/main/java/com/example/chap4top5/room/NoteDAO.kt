package com.example.chap4top5.room

import androidx.room.*


@Dao
interface NoteDAO {

    @Insert
    fun insertNote(note : DataNote)

    @Query("SELECT * FROM DataNote ORDER BY id DESC ")
    fun getDataNote(): List<DataNote>

    @Delete
    fun deleteNote(note : DataNote) : Int

    @Update
    fun updateNote(note : DataNote)



}