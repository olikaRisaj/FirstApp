package com.example.applicationrisaj.ui

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.applicationrisaj.utils.Constants
import com.example.applicationrisaj.databinding.ActivityMainBinding
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var preferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        preferences = getSharedPreferences(Constants.appPreferencesName, MODE_PRIVATE)

        setClickListener(binding)

       userNameParsing()

    }

    fun setClickListener(binding: ActivityMainBinding) {
        binding.editProfileButton.setOnClickListener {
            val intent = Intent(this@MainActivity, AuthActivity::class.java)
            preferences.edit().remove(Constants.appPreferencesEmailKey).apply()
            preferences.edit().remove(Constants.appPreferencesPasswordKey).apply()
            startActivity(intent)
        }
    }

    fun userNameParsing() {
        binding.usernameTextView.text =
//            intent.extras?.let {
//                it
//            }
            intent.extras!!.getString("name")!!.substringBefore("@").split(".").joinToString(" ") {
                it.replaceFirstChar { firstChar ->
                    firstChar.uppercase()
                }
            }.trimEnd()
    }

}