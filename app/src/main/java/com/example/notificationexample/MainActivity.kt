package com.example.notificationexample

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.notificationexample.databinding.ActivityMainBinding
import com.example.notificationexample.ui.Router
import com.google.android.material.bottomnavigation.BottomNavigationView


class MainActivity : AppCompatActivity() {

  private lateinit var binding: ActivityMainBinding
  private lateinit var navController: NavController
  private lateinit var navView: BottomNavigationView

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    binding = ActivityMainBinding.inflate(layoutInflater)
    setContentView(binding.root)

    navView = binding.navView

    navController = findNavController(R.id.nav_host_fragment_activity_main)

    Router.navControler = navController

    navView.setupWithNavController(navController)

    navView.setOnItemSelectedListener { item ->
      navController.navigate(item.itemId)
      true
    }
  }

  override fun onNewIntent(intent: Intent?) {
    super.onNewIntent(intent)
    navController.handleDeepLink(intent)
  }

}