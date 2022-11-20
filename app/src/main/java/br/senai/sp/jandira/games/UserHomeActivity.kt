package br.senai.sp.jandira.games

import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import br.senai.sp.jandira.games.adapters.GameRegisteredAdapter
import br.senai.sp.jandira.games.dao.GameRegisteredDAO
import br.senai.sp.jandira.games.databinding.ActivityUserHomeBinding
import br.senai.sp.jandira.games.model.GameRegistered
import br.senai.sp.jandira.games.repository.UserRepository

class UserHomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityUserHomeBinding
    private lateinit var adapterGameRegisterd: GameRegisteredAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Change the title of the actionBar
        supportActionBar?.title = getString(R.string.actionBar_profile_title)

        // Change the background color of the actionBar
        supportActionBar?.setBackgroundDrawable(
            ColorDrawable(
                ContextCompat.getColor(this, R.color.dark_blue) // ?????
            )
        )

        binding.rvGameRegistered.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        this.adapterGameRegisterd = GameRegisteredAdapter(this)

        bindGameRegistered(GameRegisteredDAO.list(this))
        binding.rvGameRegistered.adapter = this.adapterGameRegisterd

        val userId = intent.getIntExtra("user_id", -1)

        val user = UserRepository(this).getUserById(userId)

        binding.userName.text = user.user_name
        binding.userEmail.text = user.email
        binding.userLevel.text = user.level.toString()
    }

    private fun bindGameRegistered(data: List<GameRegistered>) {
        this.adapterGameRegisterd.updateList(data)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_user_home, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        Toast.makeText(this, "add game", Toast.LENGTH_SHORT).show()
        return true
    }
}