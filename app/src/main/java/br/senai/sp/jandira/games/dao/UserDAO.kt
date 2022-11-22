package br.senai.sp.jandira.games.dao

import androidx.room.*
import br.senai.sp.jandira.games.model.UserModel
import br.senai.sp.jandira.games.model.UserWithGamesModel

@Dao
interface UserDAO {
    @Transaction
    @Query("SELECT *  FROM tbl_user WHERE userId = :id")
    fun getUserWithGames(id: Int): List<UserWithGamesModel>

    @Query("SELECT * FROM tbl_user ORDER BY user_name ASC")
    fun getAll(): List<UserModel>

    @Query("SELECT * FROM tbl_user WHERE userId = :id")
    fun getUserById(id: Int): UserModel

    @Query("SELECT * FROM TBL_USER where email = :email")
    fun getUserByEmail(email: String): UserModel

    @Update
    fun update(user: UserModel): Int

    @Delete
    fun delete(user: UserModel): Int

    @Insert
    fun save(user: UserModel): Long
}