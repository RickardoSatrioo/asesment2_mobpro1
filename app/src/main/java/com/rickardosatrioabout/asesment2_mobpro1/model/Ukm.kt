package com.rickardosatrioabout.asesment2_mobpro1.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "ukm")
data class Ukm(
    @PrimaryKey(autoGenerate = true) val id: Long,
    val namaukm: String,
    val namaketua: String,
    val kontak: String,
    val deskripsi: String,
    val status: Boolean = true
)
