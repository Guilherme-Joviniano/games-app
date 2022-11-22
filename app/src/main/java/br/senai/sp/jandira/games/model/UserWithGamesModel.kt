package br.senai.sp.jandira.games.model

import androidx.room.Embedded
import androidx.room.Relation

data class UserWithGamesModel (
    @Embedded val user: UserModel,
    @Relation(
        parentColumn = "userId",
        entityColumn = "userCreatorId"
    )
    val games: List<GameModel>
)