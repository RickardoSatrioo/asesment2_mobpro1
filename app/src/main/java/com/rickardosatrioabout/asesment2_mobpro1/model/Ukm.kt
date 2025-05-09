package com.rickardosatrioabout.asesment2_mobpro1.model

data class Ukm(
    val id: Long,
    val namaukm: String,
    val namaketua: String,
    val kontak: String,
    val deskripsi: String,
    val status: Boolean = true
)
