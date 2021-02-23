package com.example.android.lazyengineer

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.preference.PreferenceManager

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navController = this.findNavController(R.id.mainNavHostFragment)
        NavigationUI.setupActionBarWithNavController(this,navController)
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = this.findNavController(R.id.mainNavHostFragment)
        return navController.navigateUp()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.menu_logout -> {
                logout(); true
            }
            R.id.menu_feedback -> {
                feedback(); true
            }
            R.id.menu_website -> {
                website(); true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    @SuppressLint("ApplySharedPref")
    private fun logout() {
        val sharedPref = PreferenceManager.getDefaultSharedPreferences(this)
        sharedPref.edit().putString(getString(R.string.logout), getString(R.string.logout)).commit()
        startActivity(Intent(this, LoadingScreen::class.java))
        finish()
    }

    private fun feedback() {
        val intent = Intent(Intent.ACTION_SENDTO).apply {
            type = "text/plain"
            data = Uri.parse("mailto:${getString(R.string.email_link)}")
            putExtra(Intent.EXTRA_SUBJECT, getString(R.string.feedback))
        }
        if (intent.resolveActivity(packageManager) != null) {
            startActivity(intent)
        }
    }

    private fun website() {
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse(getString(R.string.website_link))
        if (intent.resolveActivity(packageManager) != null) {
            startActivity(intent)
        }
    }

}