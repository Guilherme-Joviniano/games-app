package br.senai.sp.jandira.games.dao

import androidx.room.*
import br.senai.sp.jandira.games.model.ConsoleModel
import br.senai.sp.jandira.games.model.GameModel

@Dao
interface GameDAO {
    @Query("SELECT * FROM tbl_game ORDER BY title ASC")
    fun getAll(): List<GameModel>

    @Query("SELECT * FROM tbl_game WHERE gameId = :id")
    fun getGameById(id: Int): GameModel

    @Query("SELECT * FROM tbl_game WHERE title = :name")
    fun getGameByName(name: String): GameModel

    @Update
    fun update(game: GameModel): Int

    @Delete
    fun delete(game: GameModel): Int

    @Insert
    fun save(game: GameModel): Long
}