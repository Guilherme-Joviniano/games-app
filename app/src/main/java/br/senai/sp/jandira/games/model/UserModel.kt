package br.senai.sp.jandira.games.model

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import br.senai.sp.jandira.games.model.enums.Level
import java.time.LocalDate

@Entity(tableName = "tbl_user")
data class UserModel (
    val user_name: String,
    val email: String,
    val password: String,
    val city: String,
    @ColumnInfo(name = "birthday") var birthday: String? = null,
    val level: Level,
    @Embedded val console: ConsoleModel?,
    val gender: Char
    ) {
    @PrimaryKey(autoGenerate = true) var userId: Int = 0;
}