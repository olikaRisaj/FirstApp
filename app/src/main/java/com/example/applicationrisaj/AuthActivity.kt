package com.example.applicationrisaj

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.InputDevice
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class AuthActivity : AppCompatActivity() {

    lateinit var emailTextInput: TextInputEditText
    lateinit var passwordTextInput: TextInputEditText
    lateinit var registerButton: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)

        emailTextInput = findViewById(R.id.email_edit_text)
        passwordTextInput = findViewById(R.id.password_edit_text)

        emailTextInput.addTextChangedListener() {
            if(validateEmail()) {
                emailTextInput.setBackgroundColor(Color.CYAN)
            } else {
                emailTextInput.setBackgroundColor(Color.RED)
            }
        }

        passwordTextInput.addTextChangedListener() {
            if(validatePassword()) {
                passwordTextInput.setBackgroundColor(Color.CYAN)
            } else {
                passwordTextInput.setBackgroundColor(Color.RED)
            }
        }

        registerButton = findViewById(R.id.register_button)
        registerButton.setOnClickListener {
            val intent = Intent(this@AuthActivity, MainActivity::class.java)
            startActivity(intent)
        }
    }

    private fun validateEmail(): Boolean {
        return if(emailTextInput.text.toString().trim().isEmpty()) {
            emailTextInput.error = "Field can\'t be empty"
            false
        } else if(!android.util.Patterns.EMAIL_ADDRESS.matcher(emailTextInput.text.toString().trim()).matches()) {
            emailTextInput.error = "Email address is not valid"
            false
        } else {
            true
        }
    }

    private fun validatePassword(): Boolean {
        return if(passwordTextInput.text.toString().trim().isEmpty()) {
            passwordTextInput.error = "Field can\'t be empty"
            false
        } else if(passwordTextInput.text.toString().length < 6) {
            passwordTextInput.error = "Password can't be less than 6 digit"
            false
        } else {
            true
        }
    }


}