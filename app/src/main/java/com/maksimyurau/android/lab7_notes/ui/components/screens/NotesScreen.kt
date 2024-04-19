package com.maksimyurau.android.lab7_notes.ui.components.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.FabPosition
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.List
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.tooling.preview.Preview
import com.maksimyurau.android.lab7_notes.model.NoteModel
import com.maksimyurau.android.lab7_notes.routing.Screen
import com.maksimyurau.android.lab7_notes.ui.components.AppDrawer
import com.maksimyurau.android.lab7_notes.ui.components.Note

import com.maksimyurau.android.lab7_notes.viewmodel.MainViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun NotesScreen(viewModel: MainViewModel) {

    val notes: List<NoteModel> by viewModel
        .notesNotInTrash
        .observeAsState(listOf())

    val coroutineScope: CoroutineScope = rememberCoroutineScope()
    val scaffoldState: ScaffoldState = rememberScaffoldState()

    Scaffold(
        topBar = {
        TopAppBar(
            title = {
                Text(
                    text = "Notes",
                    color = MaterialTheme.colors.onPrimary
                )
            },
            navigationIcon = {
                IconButton(onClick = {
                    coroutineScope.launch {
                        scaffoldState.drawerState.open()
                    }
                }
                ) {
                    Icon(
                        imageVector = Icons.Filled.List,
                        contentDescription = "Drawer Button"
                    )
                }
            }
        )
     },
        scaffoldState = scaffoldState, // Здесь — состояние Scaffold
        drawerContent = { // Здесь — Drawer UI
            AppDrawer(
                currentScreen = Screen.Notes, closeDrawerAction = {
                    // Здесь — закрываем Drawer
                    coroutineScope.launch {
                        scaffoldState.drawerState.close()
                    }
                }
            )
        },
        floatingActionButtonPosition = FabPosition.End,
        floatingActionButton = {
            FloatingActionButton(
                onClick = { viewModel.onCreateNewNoteClick() },
                contentColor = MaterialTheme.colors.background,
                content = {
                    Icon(
                        imageVector = Icons.Filled.Add,
                        contentDescription = "Add Note Button"
                    )
                }
            )
        },
        content = {
            if (notes.isNotEmpty()) {
                NotesList(
                    notes = notes, onNoteCheckedChange = {
                        viewModel.onNoteCheckedChange(it)
                    },
                    onNoteClick = { viewModel.onNoteClick(it) }
                )
            }
        }
    )
}

@Composable
private fun NotesList(
    notes: List<NoteModel>,
    onNoteCheckedChange: (NoteModel) -> Unit,
    onNoteClick: (NoteModel) -> Unit
) {
    LazyColumn {
        items(count = notes.size) { noteIndex ->
            val note = notes[noteIndex]
            Note(
                note = note,
                onNoteClick = onNoteClick,
                onNoteCheckedChange = onNoteCheckedChange
            )
        }
    }
}

@Preview
@Composable
private fun NotesListPreview() {
    NotesList(
        notes = listOf(
            NoteModel(1, "Note 1", "Content 1", null),
            NoteModel(2, "Note 2", "Content 2", false),
            NoteModel(3, "Note 3", "Content 3", true)
        ),
        onNoteCheckedChange = {},
        onNoteClick = {}
    )
}