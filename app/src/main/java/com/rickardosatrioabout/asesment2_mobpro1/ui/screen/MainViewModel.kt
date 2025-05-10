package com.rickardosatrioabout.asesment2_mobpro1.ui.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rickardosatrioabout.asesment2_mobpro1.database.UkmDao
import com.rickardosatrioabout.asesment2_mobpro1.model.Ukm
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn

class MainViewModel(dao: UkmDao) : ViewModel() {
    val data: Flow<List<Ukm>> = dao.getUkm()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )
}

