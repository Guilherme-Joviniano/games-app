package br.senai.sp.jandira.games

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.RadioButton
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.view.isEmpty
import br.senai.sp.jandira.games.databinding.ActivityGameRegisterBinding
import br.senai.sp.jandira.games.helpers.getBitmapFromUri
import br.senai.sp.jandira.games.helpers.getByteArrayFromBitmap
import br.senai.sp.jandira.games.model.GameModel
import br.senai.sp.jandira.games.repository.GameRepository
import java.io.ByteArrayOutputStream

class GameRegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityGameRegisterBinding
    private val IMAGE_REQUEST_CODE = 100
    private var USER_ID = 0;
    private var pictureBitmap: Bitmap? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGameRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title = getString(R.string.actionBar_register_game_title)

        supportActionBar?.setBackgroundDrawable(
            ColorDrawable(
                ContextCompat.getColor(this, R.color.blue_lighter)
            )
        )

        binding.gamePictureImageView.setOnClickListener {
            pickImageGallery()
        }

        // USER ID
        USER_ID = getUserId()
    }


    private fun getUserId(): Int {
        return intent.getIntExtra("user_id", -1)
    }

    private fun validateFormData(): Boolean {
        var validated = true
        val inputs = listOf(
            binding.gameDescEditText,
            binding.gameTitleEditText,
            binding.gameStudioEditText,
            binding.gameReleasedYearEditText
        )

        inputs.forEach { editText ->
            if (editText.text.isEmpty()) {
                editText.error = "Campo Obrigatório!"
                validated = false
            }
        }

        if (binding.finishedRadioGroup.checkedRadioButtonId.toString().isEmpty()) {
            Toast.makeText(this, "Fale para nós se você já zerou o game!", Toast.LENGTH_SHORT)
                .show()
            return false
        }

        return validated
    }

    private fun getFormData(): GameModel {
        val picture = getByteArrayFromBitmap(pictureBitmap)
        val desc = binding.gameDescEditText.text.toString()
        val title = binding.gameTitleEditText.text.toString()
        val gameStudio = binding.gameStudioEditText.text.toString()
        val releasedYear = binding.gameReleasedYearEditText.text.toString()

        var completed: Boolean = false

        val inputRadio = binding.finishedRadioGroup.checkedRadioButtonId
        val value = findViewById<RadioButton>(inputRadio).text.toString()[0]

        if (value == 'c') completed = true

        return GameModel(
            game_picture = picture,
            title = title,
            studio = gameStudio,
            gameLaunchedYear = releasedYear,
            description = desc,
            completed = completed,
            userCreatorId = USER_ID
        )
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_register_game, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (!validateFormData()) {
            return false
        }

        if (pictureBitmap == null) {
            Toast.makeText(this, "É necessario uma foto para o cadastro!", Toast.LENGTH_SHORT)
                .show()
            return false
        }

        GameRepository(this).save(getFormData())

        finish()

        return true
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
            binding.gamePictureImageView.setImageBitmap(pictureBitmap)
        }
    }


}