package br.senai.sp.jandira.games.model

import android.graphics.Bitmap
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tbl_game")
data class GameModel (
    var game_picture: ByteArray?,
    val title: String,
    val description: String,
    val studio: String,
    val gameLaunchedYear: String,
    val completed: Boolean,
    val userCreatorId: Int
        ) {
    @PrimaryKey(autoGenerate = true) var gameId: Int = 0;
}