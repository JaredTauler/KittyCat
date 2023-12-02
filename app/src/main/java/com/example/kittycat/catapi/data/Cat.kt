package com.example.kittycat.catapi.data
data class Cat(
    val _id: String,
    val tags: List<String>,
    val createdAt: String
) {
    fun imgurl(): String {
        return  "https://cataas.com/cat/" + _id
    }
}
