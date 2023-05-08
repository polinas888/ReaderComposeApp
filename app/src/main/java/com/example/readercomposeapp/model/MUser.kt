package com.example.readercomposeapp.model

data class MUser(
    val id: String?,
    val userId: String,
    val name: String,
    val avatarUrl: String,
    val quote: String,
    val profession: String
) {
    fun createMap(): MutableMap<String, Any> {
          return mutableMapOf(
              "user_id" to userId,
              "name" to name,
              "avatar_url" to avatarUrl,
              "quote" to quote,
              "profession" to profession
          )
    }
}
