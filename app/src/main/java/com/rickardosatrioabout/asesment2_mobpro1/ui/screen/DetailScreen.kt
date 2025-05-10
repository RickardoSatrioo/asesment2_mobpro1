package com.rickardosatrioabout.asesment2_mobpro1.ui.screen

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.outlined.Check
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.rickardosatrioabout.asesment2_mobpro1.R
import com.rickardosatrioabout.asesment2_mobpro1.util.ViewModelFactory

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
    var showDialog by remember { mutableStateOf(false) }
    var showDeletePermanenDialog by remember { mutableStateOf(false) }
    var ukmStatus by remember { mutableStateOf(true) }

    LaunchedEffect(id) {
        id?.let { nonNullId ->
            viewModel.getUkm(nonNullId)?.let { ukm ->
                namaUkm = ukm.namaukm
                namaKetua = ukm.namaketua
                kontakUkm = ukm.kontak
                deskripsi = ukm.deskripsi
                ukmStatus = ukm.status
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
                    Text(text = if (id == null) stringResource(R.string.tambah_UKM) else stringResource(R.string.edit_UKM))
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
                            viewModel.update(id, namaUkm, namaKetua, kontakUkm, deskripsi, ukmStatus)
                        }
                        navController.popBackStack()
                    }) {
                        Icon(
                            imageVector = Icons.Outlined.Check,
                            contentDescription = stringResource(R.string.simpan),
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }

                    if (id != null) {
                        if (ukmStatus) {
                            DeleteActions(
                                onDelete = { showDialog = true },
                                onDeactivate = {
                                    viewModel.deactivate(id)
                                    navController.popBackStack()
                                }
                            )
                        } else {
                            RestoreActions(
                                onRestore = {
                                    viewModel.restore(id)
                                    navController.popBackStack()
                                },
                                onDeletePermanently = {
                                    showDeletePermanenDialog = true
                                }
                            )
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

        if (id != null && showDialog) {
            DisplayAlertDialog(
                onDismissRequest = { showDialog = false },
                onConfirmation = {
                    showDialog = false
                    viewModel.delete(id)
                    navController.popBackStack()
                }
            )
        }

        if (id != null && showDeletePermanenDialog) {
            DisplayAlertDialog(
                onDismissRequest = { showDeletePermanenDialog = false },
                onConfirmation = {
                    showDeletePermanenDialog = false
                    viewModel.delete(id)
                    navController.popBackStack()
                }
            )
        }
    }
}

@Composable
fun DeleteActions(
    onDelete: () -> Unit,
    onDeactivate: () -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    IconButton(onClick = { expanded = true }) {
        Icon(
            imageVector = Icons.Filled.MoreVert,
            contentDescription = stringResource(R.string.lainnya),
            tint = MaterialTheme.colorScheme.primary
        )
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            DropdownMenuItem(
                text = { Text(text = stringResource(R.string.hapus)) },
                onClick = {
                    expanded = false
                    onDeactivate()
                }
            )
        }
    }
}

@Composable
fun RestoreActions(
    onRestore: () -> Unit,
    onDeletePermanently: () -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    IconButton(onClick = { expanded = true }) {
        Icon(
            imageVector = Icons.Filled.MoreVert,
            contentDescription = stringResource(R.string.lainnya),
            tint = MaterialTheme.colorScheme.primary
        )
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            DropdownMenuItem(
                text = { Text(text = stringResource(R.string.pulihkan)) },
                onClick = {
                    expanded = false
                    onRestore()
                }
            )
            DropdownMenuItem(
                text = { Text(text = stringResource(R.string.hapus_permanen)) },
                onClick = {
                    expanded = false
                    onDeletePermanently()
                }
            )
        }
    }
}

@Composable
fun FormCatatan(
    namaUkm: String,
    onNamaUkmChange: (String) -> Unit,
    namaKetua: String,
    onNamaKetuaChange: (String) -> Unit,
    kontakUkm: String,
    onKontakUkmChange: (String) -> Unit,
    deskripsi: String,
    onDeskripsiChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .padding(16.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        OutlinedTextField(
            value = namaUkm,
            onValueChange = onNamaUkmChange,
            label = { Text("Nama UKM") },
            singleLine = true,
            keyboardOptions = KeyboardOptions.Default.copy(
                capitalization = KeyboardCapitalization.Words,
                imeAction = ImeAction.Next
            ),
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = namaKetua,
            onValueChange = onNamaKetuaChange,
            label = { Text("Nama Ketua") },
            singleLine = true,
            keyboardOptions = KeyboardOptions.Default.copy(
                capitalization = KeyboardCapitalization.Words,
                imeAction = ImeAction.Next
            ),
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = kontakUkm,
            onValueChange = onKontakUkmChange,
            label = { Text("Kontak UKM") },
            singleLine = true,
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Next
            ),
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = deskripsi,
            onValueChange = onDeskripsiChange,
            label = { Text("Deskripsi") },
            keyboardOptions = KeyboardOptions.Default.copy(
                capitalization = KeyboardCapitalization.Sentences,
                imeAction = ImeAction.Done
            ),
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp)
        )
    }
}
