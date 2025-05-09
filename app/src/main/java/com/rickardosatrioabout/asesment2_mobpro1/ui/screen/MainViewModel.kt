package com.rickardosatrioabout.asesment2_mobpro1.ui.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rickardosatrioabout.asesment2_mobpro1.database.UkmDao
import com.rickardosatrioabout.asesment2_mobpro1.model.Ukm
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn

class MainViewModel(dao: UkmDao) : ViewModel() {
    val data: StateFlow<List<Ukm>> = dao.getUkm().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(),
        initialValue = emptyList()
    )

    fun getUkm(id: Long): Ukm? {
        return data.value.find { it.id == id }
    }
}

