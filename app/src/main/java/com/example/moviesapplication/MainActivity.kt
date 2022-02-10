package com.example.moviesapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.moviesapplication.discovery.Discovery_Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    // start a fragment
    private lateinit var currentFragment : Fragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // we need two fragments for the bottom navigation
        // home and dashboard
        // set home fragment as launcher
        supportFragmentManager.beginTransaction().replace(R.id.nav_container, Discovery_Fragment()).commit()
        // now create a menu
        val bottomNav : BottomNavigationView = findViewById(R.id.bottom_navigation)
        bottomNav.setOnNavigationItemSelectedListener(bottomListener)


    }

    val bottomListener = BottomNavigationView.OnNavigationItemSelectedListener {
        // switch between ids of menu
        when(it.itemId){
            R.id.discover -> {
                currentFragment = Discovery_Fragment()
            }
            R.id.favorite -> {
                currentFragment = Favorite_Fragment()
            }
        }
        supportFragmentManager.beginTransaction().replace(R.id.nav_container,currentFragment).commit()
        true
    }

}
