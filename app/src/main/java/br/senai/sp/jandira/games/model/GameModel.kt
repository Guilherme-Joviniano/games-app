package br.senai.sp.jandira.games.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tbl_game")
data class GameModel (
    val title: String,
    val description: String,
    val studio: String,
    val gameLaunchedYear: String,
    val completed: Boolean
        ) {
    @PrimaryKey(autoGenerate = true) var gameId: Int = 0;
}