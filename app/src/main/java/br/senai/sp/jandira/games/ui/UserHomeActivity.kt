package br.senai.sp.jandira.games.ui

import android.content.Intent
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import br.senai.sp.jandira.games.R
import br.senai.sp.jandira.games.adapters.GameRegisteredAdapter
import br.senai.sp.jandira.games.databinding.ActivityUserHomeBinding
import br.senai.sp.jandira.games.helpers.getBitmapFromByteArray
import br.senai.sp.jandira.games.model.GameModel
import br.senai.sp.jandira.games.model.UserModel
import br.senai.sp.jandira.games.repository.UserRepository
import java.time.Year

class UserHomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityUserHomeBinding
    private lateinit var adapterGameRegisterd: GameRegisteredAdapter
    private var user: UserModel? = null;
    private lateinit var gameList: List<GameModel>
    private var USER_ID: Int = 0;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if(checkLogin()) {
            val sharedPreferences = getSharedPreferences("credentials", MODE_PRIVATE)
            val email = sharedPreferences.getString("email", "") as String
            user = UserRepository(this).getUserByEmail(email)
        } else {
            USER_ID = intent.getIntExtra("user_id", -1)
            user = UserRepository(this).getUserById(USER_ID)
        }

        // Change the title of the actionBar
        supportActionBar?.title = getString(R.string.actionBar_profile_title)

        // Change the background color of the actionBar
        supportActionBar?.setBackgroundDrawable(
            ColorDrawable(
                ContextCompat.getColor(this, R.color.dark_blue)
            )
        )

        // setup the recyclerView
        setupGameRegisteredRecyclerView()

        val age =
            Year.now().value - ((user?.birthday?.substring(user?.birthday.toString().length - 4))?.toInt()
                ?: Year.now().value)

        binding.userName.text = user?.user_name
        binding.userEmail.text = user?.email
        binding.userLevel.text = user?.level.toString()
        binding.textViewUserAge.text = age.toString()
        binding.userProfilePicture.setImageBitmap(getBitmapFromByteArray(user?.user_picture))

        binding.finishedGamesTextView.text = getFinishedGamesSize().toString()
        binding.playingGamesTextView.text = getUnfinishedGameSize().toString()
    }

    private fun getFinishedGamesSize(): Int {
        var counter = 0;
        gameList.forEach { game ->
            run {
                if (game.completed) counter++
            }
        }
        return counter
    }

    private fun getUnfinishedGameSize(): Int {
        var counter = 0;
        gameList.forEach { game ->
            run {
                if (!game.completed) counter++
            }
        }
        return counter
    }

    private fun setupGameRegisteredRecyclerView() {
        binding.rvGameRegistered.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        this.adapterGameRegisterd = GameRegisteredAdapter(this)
        print(user?.userId)
        gameList = updateGameList(user?.userId)
        bindGameRegistered(gameList)
        binding.rvGameRegistered.adapter = this.adapterGameRegisterd
    }

    private fun bindGameRegistered(data: List<GameModel>) {
        this.adapterGameRegisterd.updateList(data)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_user_home, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val openGameRegisterActivity = Intent(this, GameRegisterActivity::class.java)
        openGameRegisterActivity.putExtra("user_id", user?.userId)
        startActivity(openGameRegisterActivity)
        return true
    }

    private fun updateGameList(id: Int?): List<GameModel> {
        return UserRepository(this).getUserWithGames(id)[0].games
    }
    private fun updateUserInfo() {
        binding.finishedGamesTextView.text = getFinishedGamesSize().toString()
        binding.playingGamesTextView.text = getUnfinishedGameSize().toString()
    }

    override fun onResume() {
        super.onResume()
        // updating the user game list
        gameList = updateGameList(user?.userId)

        // updating the Rv
        bindGameRegistered(gameList)

        // updating the user info details
        updateUserInfo()

    }

    private fun checkLogin(): Boolean {
        val sharedPreferences = getSharedPreferences("credentials", MODE_PRIVATE)
        val email = sharedPreferences.getString("email", "") as String

        return email.isNotEmpty()
    }
}