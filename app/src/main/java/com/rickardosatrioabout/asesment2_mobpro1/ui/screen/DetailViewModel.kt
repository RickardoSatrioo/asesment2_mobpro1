package com.rickardosatrioabout.asesment2_mobpro1.ui.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rickardosatrioabout.asesment2_mobpro1.database.UkmDao
import com.rickardosatrioabout.asesment2_mobpro1.model.Ukm
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DetailViewModel(private val dao: UkmDao) : ViewModel() {

    suspend fun getUkm(id: Long): Ukm? {
        return withContext(Dispatchers.IO) {
            dao.getUkmById(id)
        }
    }

    fun insert(namaUkm: String, ketuaUkm: String, kontak: String, deskripsi: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val ukm = Ukm(
                id = 0,
                namaukm = namaUkm,
                namaketua = ketuaUkm,
                kontak = kontak,
                deskripsi = deskripsi,
                status = true
            )
            dao.insert(ukm)
        }
    }

    fun update(
        id: Long,
        namaUkm: String,
        ketuaUkm: String,
        kontak: String,
        deskripsi: String,
        status: Boolean
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            val existingUkm = dao.getUkmById(id)
            existingUkm?.let {
                val updatedUkm = it.copy(
                    namaukm = namaUkm,
                    namaketua = ketuaUkm,
                    kontak = kontak,
                    deskripsi = deskripsi,
                    status = status
                )
                dao.update(updatedUkm)
            }
        }
    }

    fun delete(id: Long) {
        viewModelScope.launch {
            dao.deleteById(id)
        }
    }

    fun deactivate(id: Long) {
        viewModelScope.launch(Dispatchers.IO) {
            val existingUkm = dao.getUkmById(id)
            existingUkm?.let {
                val updatedUkm = it.copy(status = false)
                dao.update(updatedUkm)
            }
        }
    }

    fun restore(id: Long) {
        viewModelScope.launch(Dispatchers.IO) {
            val existingUkm = dao.getUkmById(id)
            existingUkm?.let {
                val updatedUkm = it.copy(status = true)
                dao.update(updatedUkm)
            }
        }
    }
}
