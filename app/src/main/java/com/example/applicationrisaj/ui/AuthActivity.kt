package com.example.applicationrisaj.ui

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import com.example.applicationrisaj.utils.Constants
import com.example.applicationrisaj.R
import com.example.applicationrisaj.databinding.ActivityAuthBinding
import com.example.applicationrisaj.utils.ext.putString

class AuthActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAuthBinding

    private lateinit var mySettings: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAuthBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.emailEditText.addTextChangedListener {
            validateEmail()
        }

        binding.passwordEditText.addTextChangedListener {
            validatePassword()
        }


        mySettings = getSharedPreferences(Constants.appPreferencesName, Context.MODE_PRIVATE)

        binding.registerButton.setOnClickListener {
            val intent = Intent(this@AuthActivity, MainActivity::class.java)
            intent.putExtras(Bundle().also {
                it.putString("name", binding.emailEditText.text.toString())
                it.putString("password", binding.passwordEditText.text.toString())
            })
            startActivity(intent)
            overridePendingTransition(R.anim.diagonal, R.anim.alpha)

            if(binding.rememberMeCheckBox.isChecked) {
                val userNameLogin = binding.emailEditText.text.toString()
                val passwordLogin = binding.passwordEditText.text.toString()
                mySettings.putString(Constants.appPreferencesEmailKey, userNameLogin)
                mySettings.putString(Constants.appPreferencesPasswordKey, passwordLogin)
            }
        }

        if(!mySettings.getString(Constants.appPreferencesEmailKey, "").isNullOrEmpty()) {
            binding.emailEditText.setText(mySettings.getString(Constants.appPreferencesEmailKey, ""))
            binding.passwordEditText.setText(mySettings.getString(Constants.appPreferencesPasswordKey, ""))
            val intent = Intent(this@AuthActivity, MainActivity::class.java)
            intent.putExtra("name", binding.emailEditText.text.toString())
//            startActivity(intent)
        }
    }

    private fun validateEmail(): Boolean {
        return if (binding.emailEditText.text.toString().trim().isEmpty()) {
            binding.emailEditText.error = "Field can\'t be empty"
            false
        } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(
                binding.emailEditText.text.toString().trim()
            ).matches()
        ) {
            binding.emailEditText.error = "Email address is not valid"
            false
        } else {
            true
        }
    }

    private fun validatePassword(): Boolean {
        return when {
            binding.passwordEditText.text.toString().trim().isEmpty() -> {
                binding.passwordEditText.error = "Field can\'t be empty"
                false
            }
            binding.passwordEditText.text.toString().length < 6 -> {
                binding.passwordEditText.error = "Password can't be less than 6 digits"
                false
            }
            else -> {
                true
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putString("e-mail", binding.emailEditText.text.toString())
        outState.putString("passw", binding.passwordEditText.text.toString())
        super.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        binding.emailEditText.setText(savedInstanceState.getString("e-mail"))
        binding.passwordEditText.setText(savedInstanceState.getString("passw"))
    }

}