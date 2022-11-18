package br.senai.sp.jandira.games.dao

import androidx.room.*
import br.senai.sp.jandira.games.model.ConsoleModel

@Dao
interface ConsoleDAO {
    @Query("SELECT * FROM tbl_console ORDER BY console_name ASC")
    fun getAll(): List<ConsoleModel>

    @Query("SELECT * FROM tbl_console WHERE consoleId = :id")
    fun getConsoleById(id: Int): ConsoleModel

    @Query("SELECT * FROM tbl_console WHERE console_name = :name")
    fun getConsoleByName(name: String): ConsoleModel

    @Update
    fun update(console: ConsoleModel): Int

    @Delete
    fun delete(console: ConsoleModel): Int

    @Insert
    fun save(console: ConsoleModel): Long
}