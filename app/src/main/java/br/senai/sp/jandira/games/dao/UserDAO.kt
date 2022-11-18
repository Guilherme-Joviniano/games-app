package br.senai.sp.jandira.games.dao

import androidx.room.*
import br.senai.sp.jandira.games.model.UserModel

@Dao
interface UserDAO {
    @Query("SELECT * FROM tbl_user ORDER BY user_name ASC")
    fun getAll(): List<UserModel>

    @Query("SELECT * FROM tbl_user WHERE userId = :id")
    fun getContactById(id: Int): UserModel

    @Update
    fun update(user: UserModel): Int

    @Delete
    fun delete(user: UserModel): Int

    @Insert
    fun save(user: UserModel): Long
}