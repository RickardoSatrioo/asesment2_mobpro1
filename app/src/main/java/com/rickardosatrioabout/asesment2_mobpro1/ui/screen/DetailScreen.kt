package com.rickardosatrioabout.asesment2_mobpro1.ui.screen

import android.content.res.Configuration
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.outlined.Check
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.rickardosatrioabout.asesment2_mobpro1.R
import com.rickardosatrioabout.asesment2_mobpro1.ui.theme.Asesment2_mobpro1Theme
import com.rickardosatrioabout.asesment2_mobpro1.util.ViewModelFactory

const val KEY_ID_CATATAN = "idCatatan"

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(navController: NavHostController, id: Long? = null) {
    val context = LocalContext.current
    val factory = ViewModelFactory(context)
    val viewModel: DetailViewModel = viewModel(factory = factory)

    var namaUkm by remember { mutableStateOf("") }
    var namaKetua by remember { mutableStateOf("") }
    var kontakUkm by remember { mutableStateOf("") }
    var deskripsi by remember { mutableStateOf("") }

    LaunchedEffect(id) {
        id?.let { nonNullId ->
            viewModel.getUkm(nonNullId)?.let { ukm ->
                namaUkm = ukm.namaukm
                namaKetua = ukm.namaketua
                kontakUkm = ukm.kontak
                deskripsi = ukm.deskripsi
            }
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = stringResource(R.string.kembali),
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }
                },
                title = {
                    if (id == null)
                        Text(text = stringResource(id = R.string.tambah_UKM))
                    else
                        Text(text = stringResource(id = R.string.edit_UKM))
                },
                colors = TopAppBarDefaults.mediumTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                actions = {
                    IconButton(onClick = {
                        if (namaUkm.isBlank() || namaKetua.isBlank() || kontakUkm.isBlank() || deskripsi.isBlank()) {
                            Toast.makeText(context, R.string.invalid, Toast.LENGTH_LONG).show()
                            return@IconButton
                        }

                        if (id == null) {
                            viewModel.insert(namaUkm, namaKetua, kontakUkm, deskripsi)
                        } else {
                            viewModel.update(id, namaUkm, namaKetua, kontakUkm, deskripsi)
                        }
                        navController.popBackStack()
                    }) {
                        Icon(
                            imageVector = Icons.Outlined.Check,
                            contentDescription = stringResource(R.string.simpan),
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }
                    if (id !=null){
                        DeleteActions {
                            viewModel.delete(id)
                            navController.popBackStack()
                        }
                    }
                }
            )
        },
    ) { padding ->
        FormCatatan(
            namaUkm = namaUkm,
            onNamaUkmChange = { namaUkm = it },
            namaKetua = namaKetua,
            onNamaKetuaChange = { namaKetua = it },
            kontakUkm = kontakUkm,
            onKontakUkmChange = { kontakUkm = it },
            deskripsi = deskripsi,
            onDeskripsiChange = { deskripsi = it },
            modifier = Modifier.padding(padding)
        )
    }
}

@Composable
fun FormCatatan(
    namaUkm: String, onNamaUkmChange: (String) -> Unit,
    namaKetua: String, onNamaKetuaChange: (String) -> Unit,
    kontakUkm: String, onKontakUkmChange: (String) -> Unit,
    deskripsi: String, onDeskripsiChange: (String) -> Unit,
    modifier: Modifier
) {
    Column(
        modifier = modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        OutlinedTextField(
            value = namaUkm,
            onValueChange = { onNamaUkmChange(it) },
            label = { Text(text = stringResource(R.string.nama_UKM)) },
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                capitalization = KeyboardCapitalization.Words,
                imeAction = ImeAction.Next
            ),
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = namaKetua,
            onValueChange = { onNamaKetuaChange(it) },
            label = { Text(text = stringResource(R.string.nama_ketua)) },
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                capitalization = KeyboardCapitalization.Words,
                imeAction = ImeAction.Next
            ),
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = kontakUkm,
            onValueChange = { onKontakUkmChange(it) },
            label = { Text(text = stringResource(R.string.kontak_UKM)) },
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                capitalization = KeyboardCapitalization.None,
                imeAction = ImeAction.Next
            ),
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = deskripsi,
            onValueChange = { onDeskripsiChange(it) },
            label = { Text(text = stringResource(R.string.deskripsi)) },
            keyboardOptions = KeyboardOptions(
                capitalization = KeyboardCapitalization.Sentences,
            ),
            modifier = Modifier.fillMaxSize()
        )
    }
}

@Composable
fun DeleteActions(delete: () -> Unit){
    var expanded by remember { mutableStateOf(false) }

    IconButton(onClick = { expanded = true}) {
        Icon(
            imageVector = Icons.Filled.MoreVert,
            contentDescription = stringResource(R.string.lainnya),
            tint = MaterialTheme.colorScheme.primary
        )
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false}
        ) {
            DropdownMenuItem(
                text = {
                    Text(text = stringResource(id = R.string.hapus))
                },
                onClick = {
                    expanded = false
                    delete()
                }
            )
        }
    }
}

@Preview(showBackground = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun DetailScreenPreview() {
    Asesment2_mobpro1Theme {
        DetailScreen(rememberNavController())
    }
}
