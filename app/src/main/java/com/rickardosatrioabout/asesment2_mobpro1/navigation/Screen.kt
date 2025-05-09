package com.rickardosatrioabout.asesment2_mobpro1.navigation

import com.rickardosatrioabout.asesment2_mobpro1.ui.screen.KEY_ID_CATATAN

sealed class Screen ( val route: String){
    data object Home: Screen("mainScreen")
    data object FormBaru : Screen("FormBaru")
    data object FormUbah: Screen("detailScreen/{$KEY_ID_CATATAN}"){
        fun withId(id: Long) = "detailScreen/$id"
    }
}