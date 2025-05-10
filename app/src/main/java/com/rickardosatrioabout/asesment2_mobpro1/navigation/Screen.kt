package com.rickardosatrioabout.asesment2_mobpro1.navigation

sealed class Screen(val route: String) {
    object Home : Screen("mainScreen")
    object FormBaru : Screen("form_baru/{id}") {
        fun withId(id: Long): String {
            return "form_baru/$id"
        }
    }
    object Recycle : Screen("recycle")
}
