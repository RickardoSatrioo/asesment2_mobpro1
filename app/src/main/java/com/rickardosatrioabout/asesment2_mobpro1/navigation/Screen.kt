package com.rickardosatrioabout.asesment2_mobpro1.navigation

sealed class Screen(val route: String) {
    data object Home : Screen("mainScreen")
    data object FormBaru : Screen("form_baru/{id}") {
        fun withId(id: Long): String {
            return "form_baru/$id"
        }
    }
    data object Recycle : Screen("recycle")
}
