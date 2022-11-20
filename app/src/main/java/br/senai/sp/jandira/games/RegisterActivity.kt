package br.senai.sp.jandira.games

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
import br.senai.sp.jandira.games.model.ConsoleModel
import br.senai.sp.jandira.games.model.UserModel
import br.senai.sp.jandira.games.model.enums.Level
import br.senai.sp.jandira.games.repository.ConsoleRepository
import br.senai.sp.jandira.games.repository.UserRepository
import android.content.ContentResolver

import android.graphics.Bitmap
import android.net.Uri

import android.os.ParcelFileDescriptor
import android.provider.MediaStore
import android.view.View
import androidx.core.view.isInvisible
import br.senai.sp.jandira.games.helpers.getBitmapFromUri
import java.io.ByteArrayOutputStream


class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    private lateinit var user: UserModel
    private val IMAGE_REQUEST_CODE = 100
    private lateinit var pictureBitmap: Bitmap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title = getString(R.string.actionBar_register_title)
        supportActionBar?.setBackgroundDrawable(ColorDrawable(
            ContextCompat.getColor(this, R.color.secondary_blue)
        ))

        supportActionBar?.elevation = 0F

        binding.userPictureImageView.setOnClickListener{
            pickImageGallery()
        }
        binding.choosedImageImageView.visibility = View.INVISIBLE


        adapterSpinner()
        setupSlider()
    }


    private fun getFormData(): UserModel {
        var name = ""
        var genre = ""
        var email = ""
        var password = ""
        var city = ""
        var birthday = ""
        var level: Level = Level.CASUAL
        var console: ConsoleModel = ConsoleModel(console_name = "Playstation 5", description = "Last sony console", launchedYear = 2021, manufacturer = "Sony", console_picture = ByteArray(1))
        var picture: ByteArray = ByteArray(0)

        try {
            email = binding.EmailRegisterField.text.toString()
            password = binding.passwordRegisterField.text.toString()
            name = binding.nameField.text.toString()
            city = binding.cityField.toString()
            birthday = binding.birthdayField.text.toString()
            level = getSliderText(binding.slider.value.toInt())
            genre = binding.genreRadioGroup.checkedRadioButtonId.toString()
            console = ConsoleRepository(this).getContactByName(getSpinnerValue())
            picture = getByteArrayFromBitmap(pictureBitmap)

            return UserModel(name, email, password, city, birthday,
               level, gender = genre[0], console = console, user_picture = picture)

        } catch (exception: Exception) {
            Toast.makeText(this, "${exception}", Toast.LENGTH_SHORT).show()
        }
        return UserModel(name, email, password, city, birthday,
            Level.AMADOR, gender = 'M', console = console, user_picture = picture)
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
        if (pos <= 20) return  Level.AMADOR
        if (pos in 21..40) return Level.CASUAL
        if (pos in 41..60) return  Level.ENTUSIASTA
        if (pos in 61..80) return  Level.TRYHARD
        return  Level.PROFISSIONAL
    }

    private fun pickImageGallery() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, IMAGE_REQUEST_CODE)
    }

    private fun getByteArrayFromBitmap(bitmap: Bitmap):ByteArray {
        val stream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.PNG, 90, stream)
        return stream.toByteArray()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == IMAGE_REQUEST_CODE && resultCode == RESULT_OK) {
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
        user = getFormData();
        Toast.makeText(this, "$user", Toast.LENGTH_SHORT).show()
        UserRepository(this).save(user)
        return true
    }
}
