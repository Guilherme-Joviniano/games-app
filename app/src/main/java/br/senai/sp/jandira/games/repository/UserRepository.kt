package br.senai.sp.jandira.games.repository

import android.content.Context
import br.senai.sp.jandira.games.database.AppDB
import br.senai.sp.jandira.games.model.UserModel
import br.senai.sp.jandira.games.model.UserWithGamesModel

class UserRepository(context: Context) {
    private val db = AppDB.getDatabase(context).userDAO()
    fun getAll(): List<UserModel> {
        return db.getAll()
    }

    fun getUserByEmail(email: String): UserModel {
        return db.getUserByEmail(email)
    }

    fun getUserWithGames(id:Int): List<UserWithGamesModel> {
        return db.getUserWithGames(id)
    }

    fun getUserById(id: Int): UserModel {
        return db.getUserById(id)
    }
    fun save(user: UserModel): Long {
        return db.save(user)
    }
    fun update(user: UserModel): Int {
        return db.update(user)
    }
    fun delete(user: UserModel): Int {
        return db.delete(user)
    }
}