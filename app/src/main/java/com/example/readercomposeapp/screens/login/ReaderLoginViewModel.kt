package com.example.readercomposeapp.screens.login

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ReaderLoginViewModel @Inject constructor() : ViewModel() {
    private val _loading = MutableLiveData(false)
    val  loading = _loading

    private val auth = Firebase.auth

    fun loginWithEmailAndPassword(email: String, password: String, goHome: () -> Unit) = viewModelScope.launch {
        try {
            auth.signInWithEmailAndPassword(email, password).addOnCompleteListener { task ->
                if(task.isSuccessful) {
                    Log.d("FB", "Log in")
                    goHome()
                } else {
                    Log.d("FB", "Couldn't login " + task.result.toString())
                }
            }
        } catch (ex: Exception) {
            Log.d("FB", "Login " + ex.message)
        }
    }
}