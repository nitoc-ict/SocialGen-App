package com.nitok_ict.socialgen.socialgen_app

import android.os.Bundle
import android.util.Log
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.nitok_ict.socialgen.socialgen_app.model.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navView: BottomNavigationView = findViewById(R.id.nav_view)

        val navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(setOf(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications))
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        val communicationListner = object : ServerCommunicationInterface{
            override fun onSuccess() {
                Log.d("Debug", "Accept")
            }

            override fun onFailure() {
                Log.d("Debug", "Failure")
            }

            override fun onTry() {
                Log.d("Debug", "Trying...")
            }
        }
        val communication = ServerCommunicationModel()
        communication.getRanking(communicationListner)
        communication.getUserTotalScore(communicationListner)
        communication.postEntryUserData(EntryUserData("hoge", "Okinawa"), communicationListner)
        communication.postUserResultData(UserResultData("181305", "12345"), communicationListner)
        //Log.d("Debug", "MainActivity:$test")
    }
}