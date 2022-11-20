package br.senai.sp.jandira.games

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import br.senai.sp.jandira.games.databinding.ActivityLoginBinding
import br.senai.sp.jandira.games.model.ConsoleModel
import br.senai.sp.jandira.games.model.UserModel
import br.senai.sp.jandira.games.repository.ConsoleRepository
import br.senai.sp.jandira.games.repository.UserRepository

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private var user: UserModel? = null

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

    private fun authenticate(): Boolean {
        user = UserRepository(this).getUserByEmail(binding.EmailAddressField.text.toString())

        if (user === null) {
            Toast.makeText(this, "Email nao encontrado, faca um cadastro!", Toast.LENGTH_SHORT).show()
            return false
        }
        if (binding.PasswordField.text.toString() != user?.password) {
            Toast.makeText(this, "Senha incorreta", Toast.LENGTH_SHORT).show()
            return false
        }
        return true
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

        binding.LoginButton.setOnClickListener {
            if(!validateForm()) {
                Toast.makeText(this, "Algum Campo nao foi corretamente preenchido", Toast.LENGTH_SHORT).show()
            }
            if(!authenticate()) {
                Toast.makeText(this, "Falha no login", Toast.LENGTH_SHORT).show()
            }
            else {
                val openHomeUserHomeActivity = Intent(this, UserHomeActivity::class.java)

                openHomeUserHomeActivity.putExtra("user_id", user?.userId)

                startActivity(openHomeUserHomeActivity)
            }
        }
    }
}