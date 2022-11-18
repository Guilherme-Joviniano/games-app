package br.senai.sp.jandira.games.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.senai.sp.jandira.games.R
import br.senai.sp.jandira.games.model.GameRegistered

class GameRegisteredAdapter(context: Context) : RecyclerView.Adapter<GameRegisteredAdapter.GameRegisteredHolder>() {

    private var list = listOf<GameRegistered>(); // list of characters

    fun updateList(list: List<GameRegistered>) {
        this.list = list
        notifyDataSetChanged()
    }

    class GameRegisteredHolder(view: View): RecyclerView.ViewHolder(view) {
        private val name = view.findViewById<TextView>(R.id.textViewGameName)
        private val owner = view.findViewById<TextView>(R.id.textViewOwner)
        private val description = view.findViewById<TextView>(R.id.textViewGameDescription)
        private val imgBanner = view.findViewById<ImageView>(R.id.imageViewBanner)

        fun bind(data: GameRegistered) {
            name.text = data.gameName
            owner.text = data.gameOwner
            description.text = data.description
            imgBanner.setImageDrawable(data.img)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GameRegisteredHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.card_games_holder, parent, false)
        return GameRegisteredHolder(view)
    }

    override fun onBindViewHolder(holder: GameRegisteredHolder, position: Int) {
        holder.bind(this.list[position])
    }

    override fun getItemCount(): Int {
        return this.list.size
    }
}