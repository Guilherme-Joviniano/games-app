package br.senai.sp.jandira.games

import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.core.content.ContextCompat
import br.senai.sp.jandira.games.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title = getString(R.string.actionBar_register_title)
        supportActionBar?.setBackgroundDrawable(ColorDrawable(
            ContextCompat.getColor(this, R.color.secondary_blue)
        ))
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_register_user, menu)
        return true
    }

    // add click listener in the menu

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        Toast.makeText(this, item.toString(), Toast.LENGTH_SHORT).show()

        return true
    }
}