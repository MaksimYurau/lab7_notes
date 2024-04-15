package com.maksimyurau.android.lab7_notes

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.maksimyurau.android.lab7_notes.theme.NotesTheme
import com.maksimyurau.android.lab7_notes.ui.components.screens.NotesScreen
import com.maksimyurau.android.lab7_notes.viewmodel.MainViewModel
import com.maksimyurau.android.lab7_notes.viewmodel.MainViewModelFactory

class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels(factoryProducer = {
        MainViewModelFactory(
            this,
            (application as NotesApplication).dependencyInjector.repository
        )
    })

    @SuppressLint("UnusedMaterialScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            NotesTheme {
                NotesScreen(viewModel = viewModel)
            }
        }
    }
}