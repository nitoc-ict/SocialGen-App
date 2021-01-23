package com.nitok_ict.socialgen.socialgen_app

import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.nitok_ict.socialgen.socialgen_app.data.SaveDataModel
import com.nitok_ict.socialgen.socialgen_app.data.ScoreModel
import com.nitok_ict.socialgen.socialgen_app.model.ServerCommunicationInterface
import com.nitok_ict.socialgen.socialgen_app.model.ServerCommunicationModel
import com.nitok_ict.socialgen.socialgen_app.model.UserResultData
import com.unity3d.player.UnityPlayerActivity

class MainActivity : AppCompatActivity() {
    companion object{
        private val PLAY_GAME: Int = 1
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navView: BottomNavigationView = findViewById(R.id.nav_view)

        val navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        navView.setupWithNavController(navController)

        val gameLunchButton: FloatingActionButton = findViewById(R.id.game_lunch_button)
        gameLunchButton.setOnClickListener {
            StartGame()
        }

    }
    private fun StartGame(){
        val intent = Intent(applicationContext, UnityPlayerActivity::class.java)
        startActivity(intent)
    }

    override fun onResume() {
        super.onResume()
        if (ScoreModel().isExists()){
            val listner = object : ServerCommunicationInterface {
                override fun onSuccess() {
                }

                override fun onFailure() {
                }

                override fun onTry() {
                }
            }
            val scoreModel = ScoreModel()
            val score = scoreModel.loadScore()
            ServerCommunicationModel().postUserResultData(UserResultData(SaveDataModel().getUserData().userID, score.toString()), listner)
            scoreModel.removeFile()
        }
    }
}

