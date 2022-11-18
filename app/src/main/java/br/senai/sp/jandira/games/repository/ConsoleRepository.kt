package br.senai.sp.jandira.games.repository

import android.content.Context
import br.senai.sp.jandira.games.database.AppDB
import br.senai.sp.jandira.games.model.ConsoleModel
import br.senai.sp.jandira.games.model.UserModel

class ConsoleRepository(context: Context) {
    private val db = AppDB.getDatabase(context).consoleDAO()

    fun getAll(): List<ConsoleModel> {
        return db.getAll()
    }
    fun getContactById(id: Int): ConsoleModel {
        return db.getContactById(id)
    }
    fun save(console: ConsoleModel): Long {
        return db.save(console)
    }
    fun update(console: ConsoleModel): Int {
        return db.update(console)
    }
    fun delete(console: ConsoleModel): Int {
        return db.delete(console)
    }
}