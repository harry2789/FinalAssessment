// LoginViewModel.kt
package com.example.assigment1

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val authService: AuthService
) : ViewModel() {

    fun login(username: String, password: String, onResult: (Response<LoginResponse>?, Throwable?) -> Unit) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = authService.login(LoginRequest(username, password)).execute()
                // Switch to the main thread to update UI
                launch(Dispatchers.Main) {
                    onResult(response, null)
                }
            } catch (e: Exception) {
                // Handle the exception and return it to the UI thread
                launch(Dispatchers.Main) {
                    onResult(null, e)
                }
            }
        }
    }
}
