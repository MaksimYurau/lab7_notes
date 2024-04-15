package com.maksimyurau.android.lab7_notes

import android.app.Application
import com.maksimyurau.android.lab7_notes.dependencyinjection.DependencyInjector

/**
 * Класс приложения, отвечающий за инициализацию и выполнение зависимостей.
 */
class NotesApplication : Application() {

  lateinit var dependencyInjector: DependencyInjector

  override fun onCreate() {
    super.onCreate()
    initDependencyInjector()
  }

  private fun initDependencyInjector() {
    dependencyInjector = DependencyInjector(this)
  }
}
