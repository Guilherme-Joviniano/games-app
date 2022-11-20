package br.senai.sp.jandira.games.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import br.senai.sp.jandira.games.dao.ConsoleDAO
import br.senai.sp.jandira.games.dao.UserDAO
import br.senai.sp.jandira.games.model.ConsoleModel
import br.senai.sp.jandira.games.model.UserModel

@Database(entities = [UserModel::class, ConsoleModel::class], version = 1)
abstract class AppDB: RoomDatabase() {
    abstract fun userDAO(): UserDAO
    abstract fun consoleDAO(): ConsoleDAO

    companion object {
        private lateinit var instance: AppDB
        fun getDatabase(context: Context): AppDB {
            if (!::instance.isInitialized) {
                instance = Room.databaseBuilder(context, AppDB::class.java, "db_app_game").allowMainThreadQueries().build()
            }
            return instance
        }
    }
}