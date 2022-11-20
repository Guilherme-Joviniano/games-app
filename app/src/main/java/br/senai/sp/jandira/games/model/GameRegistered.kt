package br.senai.sp.jandira.games.model
import android.graphics.drawable.Drawable
import java.util.concurrent.atomic.AtomicLong

class GameRegistered {
    private var counter = AtomicLong();
    var id: Long = counter.getAndIncrement()
    var gameOwner = ""
    var gameName = ""
    var description = ""
    var img: Drawable? = null

    constructor (name: String, owner: String, description: String, img: Drawable?) {
        this.gameOwner = owner
        this.gameName = name
        this.description = description
        this.img = img
    }
}