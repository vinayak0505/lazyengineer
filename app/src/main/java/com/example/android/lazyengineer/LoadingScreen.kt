package com.example.android.lazyengineer

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.preference.PreferenceManager
import com.example.android.lazyengineer.Login.Login

class LoadingScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_loading_sceen)

        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this /* Activity context */)
        val name = sharedPreferences.getString(getString(R.string.logout), getString(R.string.logout))
        if (name == "logout")
            startActivity(Intent(this, Login::class.java))
        else startActivity(Intent(this, MainActivity::class.java))
        finish()
    }


}