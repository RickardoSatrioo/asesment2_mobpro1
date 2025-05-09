package com.rickardosatrioabout.asesment2_mobpro1.ui.screen

import android.content.res.Configuration
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.rickardosatrioabout.asesment2_mobpro1.R
import com.rickardosatrioabout.asesment2_mobpro1.model.Ukm
import com.rickardosatrioabout.asesment2_mobpro1.navigation.Screen
import com.rickardosatrioabout.asesment2_mobpro1.ui.theme.Asesment2_mobpro1Theme
import com.rickardosatrioabout.asesment2_mobpro1.util.ViewModelFactory

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(navController: NavHostController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = stringResource(id = R.string.app_name))
                },
                colors = TopAppBarDefaults.mediumTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    navController.navigate(Screen.FormBaru.route)
                }
            ) {
                Icon(
                    imageVector = Icons.Filled.Add,
                    contentDescription = stringResource(R.string.tambah_UKM),
                    tint = MaterialTheme.colorScheme.primary
                )
            }
        }
    ) { innePading ->
        ScreenContent(Modifier.padding(innePading), navController)
    }
}

@Composable
fun ScreenContent(modifier: Modifier = Modifier, navController: NavHostController) {
    val context = LocalContext.current
    val factory = ViewModelFactory(context)
    val viewModel: MainViewModel = viewModel(factory = factory)
    val data by viewModel.data.collectAsState()


    if (data.isEmpty()) {
        Column(
            modifier = modifier.fillMaxSize().padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = stringResource(id = R.string.List_kosong))
        }
    } else {
        LazyColumn(modifier = modifier.fillMaxSize()) {
            items(data) { ukm ->
                UkmListItem(ukm = ukm) {
                    navController.navigate(Screen.FormBaru.withId(ukm.id)) // Use ukm.id here
                }
                HorizontalDivider()
            }
        }
    }
}

@Composable
fun UkmListItem(ukm: Ukm, onClick: () -> Unit) {
    Column(
        modifier = Modifier.fillMaxWidth()
            .clickable { onClick() }
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = ukm.namaukm,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            fontWeight = FontWeight.Bold
        )
        Text(text = ukm.namaketua)
        Text(
            text = ukm.kontak,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
        Text(
            text = ukm.deskripsi,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    }
}

@Preview(showBackground = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun MainScreenPreview() {
    Asesment2_mobpro1Theme {
        MainScreen(rememberNavController())
    }
}
