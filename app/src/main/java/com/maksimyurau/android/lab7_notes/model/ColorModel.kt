package com.maksimyurau.android.lab7_notes.model

import com.maksimyurau.android.lab7_notes.data.database.model.ColorDbModel

/**
 * Класс модели для одного цвета
 */
data class ColorModel(
  val id: Long,
  val name: String,
  val hex: String
) {

  companion object {

    val DEFAULT = with(ColorDbModel.DEFAULT_COLOR) { ColorModel(id, name, hex) }
  }
}
