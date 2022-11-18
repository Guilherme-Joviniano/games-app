package br.senai.sp.jandira.games.models

import java.time.LocalDate

data class UserModel (
    val id: Long,
    val email: String,
    val password: String,
    val name: String,
    val city: String,
    val birthdayDate: LocalDate,
    val gameLevel: Int,
    val preferConsole: String,
    val genre: String )