package com.example.readercomposeapp.screens.login

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.readercomposeapp.model.MUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ReaderLoginViewModel @Inject constructor() : ViewModel() {
    private val _loading = MutableLiveData(false)
    val loading = _loading
    private val auth = Firebase.auth

    fun loginWithEmailAndPassword(email: String, password: String, goHome: (result: Boolean) -> Unit) =
        viewModelScope.launch {
            try {
                auth.signInWithEmailAndPassword(email, password).addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        goHome(true)
                    } else {
                        goHome(false)
                    }
                }
            } catch (ex: Exception) {
                Log.d("FB", "Login " + ex.message)
            }
        }

    fun createUserWithEmailAndPassword(email: String, password: String, goHome: (result: Boolean) -> Unit) =
        viewModelScope.launch {
            try {
                _loading.value = true
                auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        val userName = auth.currentUser?.email?.split("@")?.get(0)
                        createUser(userName)
                        goHome(true)
                    } else {
                        goHome(false)
                    }
                    _loading.value = false
                }
            } catch (ex: Exception) {
                Log.d("FB", "Login " + ex.message)
            }
        }

    private fun createUser(userName: String?) {
        val userId = auth.currentUser?.uid
        val user = MUser(
            id = null,
            userId = userId.toString(),
            name = userName.toString(),
            avatarUrl = "",
            quote = "Life is good",
            profession = "Android developer"
        ).createMap()
        FirebaseFirestore.getInstance().collection("users").add(user)
    }
}