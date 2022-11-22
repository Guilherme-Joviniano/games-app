package br.senai.sp.jandira.games.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.senai.sp.jandira.games.R
import br.senai.sp.jandira.games.helpers.getBitmapFromByteArray
import br.senai.sp.jandira.games.model.GameModel

class GameRegisteredAdapter(context: Context) : RecyclerView.Adapter<GameRegisteredAdapter.GameRegisteredHolder>() {

    private var list = listOf<GameModel>(); // list of characters

    fun updateList(list: List<GameModel>) {
        this.list = list
        notifyDataSetChanged()
    }

    class GameRegisteredHolder(view: View): RecyclerView.ViewHolder(view) {
        private val name = view.findViewById<TextView>(R.id.textViewGameName)
        private val owner = view.findViewById<TextView>(R.id.textViewOwner)
        private val description = view.findViewById<TextView>(R.id.textViewGameDescription)
        private val imgBanner = view.findViewById<ImageView>(R.id.imageViewBanner)

        fun bind(data: GameModel) {
            name.text = data.title
            owner.text = data.studio
            description.text = data.description
            imgBanner.setImageBitmap(getBitmapFromByteArray(data.game_picture))
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