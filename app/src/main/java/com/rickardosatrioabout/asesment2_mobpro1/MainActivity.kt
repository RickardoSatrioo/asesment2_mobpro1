package com.rickardosatrioabout.asesment2_mobpro1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.rickardosatrioabout.asesment2_mobpro1.navigation.SetupNavGraph
import com.rickardosatrioabout.asesment2_mobpro1.ui.theme.Asesment2_mobpro1Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Asesment2_mobpro1Theme {
                SetupNavGraph()
            }
        }
    }
}