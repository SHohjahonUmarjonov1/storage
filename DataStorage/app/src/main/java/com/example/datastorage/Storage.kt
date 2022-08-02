package com.example.datastorage

interface Storage {
    fun setUser(user: User?)
    fun getUser():User?
}