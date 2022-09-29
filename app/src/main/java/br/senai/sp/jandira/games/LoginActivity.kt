package br.senai.sp.jandira.games

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import br.senai.sp.jandira.games.databinding.ActivityLoginBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    private fun validateForm():Boolean {
        if (binding.PasswordField.text.isEmpty()) {
            binding.PasswordField.error = "teste"
            return false
        }
        if(binding.EmailAddressField.text.isEmpty()) {
            binding.EmailAddressField.error = "teste"
            return false
        }
        return true
    }

    private fun authenticate() {

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar!!.hide()

        binding.CreateAccountTextView.setOnClickListener {
            val openRegisterActivity = Intent(this, RegisterActivity::class.java)
            startActivity(openRegisterActivity)
         }
    }

}