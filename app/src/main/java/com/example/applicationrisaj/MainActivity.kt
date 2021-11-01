package com.example.applicationrisaj

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button

class MainActivity : AppCompatActivity() {

    lateinit var editProfileButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editProfileButton = findViewById(R.id.edit_profile_button)
        editProfileButton.setOnClickListener {
            val intent = Intent(this@MainActivity, AuthActivity::class.java)
            startActivity(intent)
        }
    }

}