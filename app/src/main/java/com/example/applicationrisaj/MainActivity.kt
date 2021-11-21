package com.example.applicationrisaj

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.applicationrisaj.databinding.ActivityMainBinding
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    @SuppressLint("CommitPrefEdits")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setClickListener(binding)

       userNameParsing()

    }

    fun setClickListener(binding: ActivityMainBinding) {
        binding.editProfileButton.setOnClickListener {
            val intent = Intent(this@MainActivity, AuthActivity::class.java)
            val userName = getSharedPreferences(Constants.appPreferencesName, MODE_PRIVATE)
            val userPassword = getSharedPreferences(Constants.appPreferencesPassword, MODE_PRIVATE)
            userName.edit().remove(Constants.appPreferencesName).apply()
            userPassword.edit().remove(Constants.appPreferencesPassword).apply()
            startActivity(intent)
        }
    }

    fun userNameParsing() {
        binding.usernameTextView.text =
            intent.extras!!.getString("name")!!.substringBefore("@").split(".").joinToString(" ") {
                it.replaceFirstChar { firstChar ->
                    firstChar.uppercase()
                }
            }.trimEnd()
    }

}