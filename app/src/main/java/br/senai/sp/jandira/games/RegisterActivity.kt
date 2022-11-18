package br.senai.sp.jandira.games

import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.core.content.ContextCompat
import br.senai.sp.jandira.games.databinding.ActivityRegisterBinding
import br.senai.sp.jandira.games.models.UserModel
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.concurrent.atomic.AtomicLong

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding

    private var counter = AtomicLong();

    private lateinit var user: UserModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title = getString(R.string.actionBar_register_title)
        supportActionBar?.setBackgroundDrawable(ColorDrawable(
            ContextCompat.getColor(this, R.color.secondary_blue)
        ))
        supportActionBar?.elevation = 0F
    }


    private fun getFormData(): UserModel {
        val id = counter.getAndIncrement();
        val email = binding.EmailRegisterField.text.toString()
        val password = binding.passwordRegisterField.text.toString()
        val name = binding.nameField.text.toString()
        val city = binding.cityField.toString()
        val birthdayDate = getBirthDayDate(binding.birthdayField.text.toString())
        val gameLevel = getSliderValue()
        val preferConsole = getSpinnerValue()
        val genre = binding.genreRadioGroup.checkedRadioButtonId.toString();

        return UserModel(id, email, password, name, city, birthdayDate, gameLevel, preferConsole, genre)
    }

    private fun getBirthDayDate(strDate: String): LocalDate {
        val formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy")
        return LocalDate.parse(strDate, formatter)
    }
    private fun getSliderValue(): Int {
        return binding.slider.value.toInt()
    }

    private fun getSpinnerValue(): String {
        return binding.spinner.selectedItem.toString()
    }



    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_register_user, menu)
        return true
    }

    // add click listener in the menu

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        Toast.makeText(this, item.toString(), Toast.LENGTH_SHORT).show()
        user = getFormData();

        return true
    }
}