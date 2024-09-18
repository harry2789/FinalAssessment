package com.example.assigment1

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint

import retrofit2.Response

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {

    private lateinit var usernameEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var loginButton: Button

    // Inject the ViewModel using Hilt
    private val loginViewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // Initialize the UI components
        usernameEditText = findViewById(R.id.usernameEditText)
        passwordEditText = findViewById(R.id.passwordEditText)
        loginButton = findViewById(R.id.loginButton)

        // Handle login button click
        loginButton.setOnClickListener {
            val username = usernameEditText.text.toString().trim()
            val password = passwordEditText.text.toString().trim()

            if (username.isNotEmpty() && password.isNotEmpty()) {
                performLogin(username, password)
            } else {
                Toast.makeText(this, "Please enter both username and password", Toast.LENGTH_SHORT).show()
            }
        }
    }

    // Perform login using the ViewModel
    private fun performLogin(username: String, password: String) {
        loginViewModel.login(username, password) { response: Response<LoginResponse>?, throwable: Throwable? ->
            if (throwable != null) {
                // Handle the error case
                Toast.makeText(this, "Login failed: ${throwable.message}", Toast.LENGTH_SHORT).show()
            } else if (response?.isSuccessful == true && response.body() != null) {
                val keypass = response.body()?.keypass
                navigateToDashboard(keypass)
            } else {
                Toast.makeText(this, "Login failed: Invalid credentials", Toast.LENGTH_SHORT).show()
            }
        }
    }

    // Navigate to the Dashboard screen after successful login
    private fun navigateToDashboard(keypass: String?) {
        val intent = Intent(this, DashboardActivity::class.java)
        intent.putExtra("KEYPASS", keypass)
        startActivity(intent)
        finish()
    }
}
