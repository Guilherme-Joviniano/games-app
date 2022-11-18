package br.senai.sp.jandira.games.daos

import android.content.Context
import br.senai.sp.jandira.games.R
import br.senai.sp.jandira.games.models.GameRegistered

class GameRegisteredDAO {
    companion object {
        fun list(context: Context): List<GameRegistered> {
            return listOf<GameRegistered>(
                GameRegistered(
                    "Street Fighter",
                    "Capcom",
                    "Street Fighter, popularmente abreviado para SF, é uma popular série de jogos de luta.",
                    context.getDrawable(R.drawable.riuky)
                ),
                GameRegistered(
                    "Street Fighter",
                    "Capcom",
                    "Street Fighter, popularmente abreviado para SF, é uma popular série de jogos de luta.",
                    context.getDrawable(R.drawable.riuky)
                ),
                GameRegistered(
                    "Street Fighter",
                    "Capcom",
                    "Street Fighter, popularmente abreviado para SF, é uma popular série de jogos de luta.",
                    context.getDrawable(R.drawable.riuky)
                )
            )
        }
    }
}