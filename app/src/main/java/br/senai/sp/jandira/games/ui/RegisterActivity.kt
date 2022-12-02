package br.senai.sp.jandira.games.ui

import android.content.Intent
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.core.content.ContextCompat
import br.senai.sp.jandira.games.databinding.ActivityRegisterBinding
import br.senai.sp.jandira.games.model.UserModel
import br.senai.sp.jandira.games.model.enums.Level
import br.senai.sp.jandira.games.repository.ConsoleRepository
import br.senai.sp.jandira.games.repository.UserRepository

import android.graphics.Bitmap

import android.view.View
import br.senai.sp.jandira.games.R
import br.senai.sp.jandira.games.helpers.getBitmapFromUri
import br.senai.sp.jandira.games.helpers.getByteArrayFromBitmap


class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    private val IMAGE_REQUEST_CODE = 100
    private lateinit var pictureBitmap: Bitmap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title = getString(R.string.actionBar_register_title)
        supportActionBar?.setBackgroundDrawable(
            ColorDrawable(
                ContextCompat.getColor(this, R.color.secondary_blue)
            )
        )

        supportActionBar?.elevation = 0F

        binding.userPictureImageView.setOnClickListener {
            pickImageGallery()
        }
        binding.choosedImageImageView.visibility = View.INVISIBLE


        adapterSpinner()
        setupSlider()
    }

    private fun validateFormData(): Boolean {
        var validated = true

        val emailField = binding.EmailRegisterField; val passwordField = binding.passwordRegisterField; val nameField = binding.nameField;
        val cityField = binding.cityField; val birthdayField = binding.birthdayField;

        val inputs = listOf(emailField, passwordField, nameField, cityField, birthdayField)

        inputs.forEach { editText -> if(editText.text.isEmpty()) {
            editText.error = "preencha o campo"
            validated = false
        } }

        if (binding.genderRadioGroup.checkedRadioButtonId.toString().isEmpty()) {
            Toast.makeText(this, "Preencha o campo de genero", Toast.LENGTH_SHORT).show()
            return false
        }

        if (binding.choosedImageImageView.visibility === View.INVISIBLE) {
            Toast.makeText(this, "Escolha uma foto de perfil!", Toast.LENGTH_SHORT).show()
            return false
        }


        if (UserRepository(this).getUserByEmail(emailField.text.toString()) != null) {
            Toast.makeText(this, "Email já cadastrado", Toast.LENGTH_SHORT).show()
            return false
        }

        return validated
    }

    private fun getFormData(): UserModel {
        var email = binding.EmailRegisterField.text.toString()
        var password = binding.passwordRegisterField.text.toString()
        var name = binding.nameField.text.toString()
        var city = binding.cityField.toString()
        var birthday = binding.birthdayField.text.toString()
        var level = getSliderText(binding.slider.value.toInt())
        var genre = binding.genderRadioGroup.checkedRadioButtonId.toString()
        var console = ConsoleRepository(this).getConsoleByName(getSpinnerValue())
        var picture = getByteArrayFromBitmap(this.pictureBitmap)

        return UserModel(user_name = name, email = email, password = password, city = city, birthday = birthday, level = level, console = console, gender = genre[0], user_picture = picture)
    }

    private fun getSpinnerValue(): String {
        return binding.spinner.selectedItem.toString()
    }

    private fun adapterSpinner() {
        val listOfConsoles = ConsoleRepository(this).getAll().map { e -> e.console_name }
        binding.spinner.adapter = ArrayAdapter(this, R.layout.spinner_item, listOfConsoles)
    }

    private fun setupSlider() {
        binding.slider.addOnChangeListener { slider, value, fromUser ->
            binding.sliderRefText.text = getSliderText(binding.slider.value.toInt()).toString()
        }
    }

    private fun getSliderText(pos: Int): Level {
        if (pos <= 20) return Level.AMADOR
        if (pos in 21..40) return Level.CASUAL
        if (pos in 41..60) return Level.ENTUSIASTA
        if (pos in 61..80) return Level.TRYHARD
        return Level.PROFISSIONAL
    }

    private fun pickImageGallery() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, IMAGE_REQUEST_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == IMAGE_REQUEST_CODE && resultCode == RESULT_OK) {
            pictureBitmap = getBitmapFromUri(data?.data, this)

            binding.userPictureImageView.visibility = View.INVISIBLE
            binding.choosedImageImageView.visibility = View.VISIBLE

            binding.choosedImageImageView.setImageBitmap(pictureBitmap)

        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_register_user, menu)
        return true
    }

    // add click listener in the menu
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (validateFormData()) {
            val user = getFormData();
            Toast.makeText(this, "$user", Toast.LENGTH_SHORT).show()
            UserRepository(this).save(user)
            finish()
            return true
        }
        return false
    }
}
