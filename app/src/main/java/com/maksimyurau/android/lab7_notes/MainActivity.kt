package com.maksimyurau.android.lab7_notes

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import com.maksimyurau.android.lab7_notes.routing.NotesRouter
import com.maksimyurau.android.lab7_notes.routing.Screen
import com.maksimyurau.android.lab7_notes.theme.NotesTheme
import com.maksimyurau.android.lab7_notes.ui.components.screens.NotesScreen
import com.maksimyurau.android.lab7_notes.ui.components.screens.TrashScreen
import com.maksimyurau.android.lab7_notes.viewmodel.MainViewModel
import com.maksimyurau.android.lab7_notes.viewmodel.MainViewModelFactory

class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels(factoryProducer = {
        MainViewModelFactory(
            this,
            (application as NotesApplication).dependencyInjector.repository
        )
    })

    @OptIn(ExperimentalMaterialApi::class)
    @SuppressLint("UnusedMaterialScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            NotesTheme {
                MainActivityScreen(viewModel = viewModel) // Здесь
            }
        }
    }
}

@Composable
@ExperimentalMaterialApi
private fun MainActivityScreen(viewModel: MainViewModel) {
    Surface {
        when (NotesRouter.currentScreen) {
            is Screen.Notes -> NotesScreen(viewModel)
            is Screen.SaveNote -> NotesScreen(viewModel)
            is Screen.Trash -> TrashScreen(viewModel)
        }
    }
}