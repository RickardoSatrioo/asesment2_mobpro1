package com.rickardosatrioabout.asesment2_mobpro1.ui.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rickardosatrioabout.asesment2_mobpro1.database.UkmDao
import com.rickardosatrioabout.asesment2_mobpro1.model.Ukm
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DetailViewModel(private val dao: UkmDao) : ViewModel() {

    fun insert(namaUkm: String, ketuaUkm: String, kontak: String, deskripsi: String) {
        val ukm = Ukm(
            id = 0,
            namaukm = namaUkm,
            namaketua = ketuaUkm,
            kontak = kontak,
            deskripsi = deskripsi
        )

        viewModelScope.launch(Dispatchers.IO) {
            dao.insert(ukm)
            val newUkm = dao.getUkmById(ukm.id)
        }
    }

    suspend fun getUkm(id: Long): Ukm? {
        return dao.getUkmById(id)
    }

    fun update(id: Long, namaUkm: String, ketuaUkm: String, kontak: String, deskripsi: String) {
        val ukm = Ukm(
            id = id,
            namaukm = namaUkm,
            namaketua = ketuaUkm,
            kontak = kontak,
            deskripsi = deskripsi
        )

        viewModelScope.launch(Dispatchers.IO) {
            dao.update(ukm)
        }
    }

    fun delete(id: Long) {
        viewModelScope.launch(Dispatchers.IO) {
            dao.deleteById(id)
        }
    }

}