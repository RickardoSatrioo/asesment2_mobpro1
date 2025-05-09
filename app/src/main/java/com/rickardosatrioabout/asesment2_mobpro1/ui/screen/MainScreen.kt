package com.rickardosatrioabout.asesment2_mobpro1.ui.screen

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.rickardosatrioabout.asesment2_mobpro1.R
import com.rickardosatrioabout.asesment2_mobpro1.model.ukm
import com.rickardosatrioabout.asesment2_mobpro1.ui.theme.Asesment2_mobpro1Theme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(){
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = stringResource(id = R.string.app_name))
                },
                colors = TopAppBarDefaults.mediumTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                )
            )
        }
    ) { innePading ->
        ScreenContent(Modifier.padding(innePading))

    }
}

@Composable
fun ScreenContent(modifier: Modifier = Modifier){
    val viewModel: MainViewModel = viewModel()
    val data = viewModel.data

    LazyColumn (
        modifier.fillMaxSize()
    ){
        items(data){
            UkmListItem(ukm = it)
            HorizontalDivider()
        }
    }
}

@Composable
fun UkmListItem(ukm: ukm) {
    Column (
        modifier = Modifier.fillMaxWidth().padding(16.dp),
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
        MainScreen()
    }
}