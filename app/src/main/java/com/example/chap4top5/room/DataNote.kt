package com.example.chap4top5.room

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable


@Entity
data class DataNote(
    @PrimaryKey(autoGenerate = true)
    var id : Int,
    var title : String,
    var content : String
): Serializable
