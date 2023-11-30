package com.example.kittycat.catapi

data class CatPicture(
    val count: Int,
    val lastItemIndex: Int,
    val page: Int,
    val totalCount: Int,
    val totalPages: Int
)
