package br.senai.sp.jandira.games.repository

import android.content.Context
import br.senai.sp.jandira.games.database.AppDB
import br.senai.sp.jandira.games.model.ConsoleModel
import br.senai.sp.jandira.games.model.GameModel
import br.senai.sp.jandira.games.model.UserModel

class GameRepository(context: Context) {
    private val db = AppDB.getDatabase(context).gameDAO()

    fun getAll(): List<GameModel> {
        return db.getAll()
    }
    fun getGameByID(id: Int): GameModel {
        return db.getGameById(id)
    }
    fun getGameByName(name: String): GameModel {
        return db.getGameByName(name)
    }
    fun save(game: GameModel): Long {
        return db.save(game)
    }
    fun update(game: GameModel): Int {
        return db.update(game)
    }
    fun delete(game: GameModel): Int {
        return db.delete(game)
    }
}