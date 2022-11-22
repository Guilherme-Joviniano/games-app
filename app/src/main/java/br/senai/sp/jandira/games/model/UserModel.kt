package br.senai.sp.jandira.games.model

import android.graphics.Bitmap
import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import br.senai.sp.jandira.games.model.enums.Level
import java.time.LocalDate

@Entity(tableName = "tbl_user")
class UserModel {
    var user_name: String = ""
    var email: String = ""
    var password: String = ""
    var city: String = ""
    @ColumnInfo(name = "birthday")
    var birthday: String? = null
    var level: Level? = null

    @Embedded
    var console: ConsoleModel? = null
    var gender: Char = 'm'
    var user_picture: ByteArray = ByteArray(    0)
    @PrimaryKey(autoGenerate = true)
    var userId: Int = 0
}