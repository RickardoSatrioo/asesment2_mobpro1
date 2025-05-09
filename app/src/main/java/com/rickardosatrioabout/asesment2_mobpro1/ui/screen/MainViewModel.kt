package com.rickardosatrioabout.asesment2_mobpro1.ui.screen

import androidx.lifecycle.ViewModel
import com.rickardosatrioabout.asesment2_mobpro1.model.Ukm

class MainViewModel : ViewModel() {

    val data = listOf(
        Ukm(
            id = 1,
            namaukm = "UKM Teknologi",
            namaketua = "Budi Santoso",
            kontak = "081234567890",
            deskripsi = "Mengembangkan aplikasi dan website untuk berbagai kebutuhan.",
            status = true
        ),
        Ukm(
            id = 2,
            namaukm = "UKM Kuliner",
            namaketua = "Siti Aminah",
            kontak = "082345678901",
            deskripsi = "Menjual makanan sehat dan bergizi.",
            status = true
        ),
        Ukm(
            id = 3,
            namaukm = "UKM Olahraga",
            namaketua = "Andi Pratama",
            kontak = "083456789012",
            deskripsi = "Fasilitas olahraga untuk pelatihan dan turnamen.",
            status = true
        ),
        Ukm(
            id = 4,
            namaukm = "UKM Seni Musik",
            namaketua = "Rina Dewi",
            kontak = "084567890123",
            deskripsi = "Kegiatan seni musik untuk melatih bakat anak muda.",
            status = false
        ),
        Ukm(
            id = 5,
            namaukm = "UKM Kewirausahaan",
            namaketua = "Dewi Lestari",
            kontak = "085678901234",
            deskripsi = "Membantu mahasiswa dalam memulai usaha kecil.",
            status = true
        )
    )

    fun getUkm(id: Long): Ukm? {
        return data.find { it.id == id }
    }
}
