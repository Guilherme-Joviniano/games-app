package br.senai.sp.jandira.games.model

import android.graphics.Bitmap
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.Year

@Entity(tableName = "tbl_console")
data class ConsoleModel (
    val console_name: String,
    val manufacturer: String,
    val description: String,
    val console_picture: ByteArray,
    @ColumnInfo(name = "launched_year") var launchedYear:Int? = null
) {
    @PrimaryKey(autoGenerate = true) var consoleId: Int = 0;
}