package com.example.datastorage

import android.content.Context
import com.google.gson.Gson
import java.io.File
import java.lang.Exception

class FileStorage(context: Context) : Storage {
    private val file = File(context.filesDir, "user.json")

    private val gson = Gson()

    init {
        if (file.exists().not()) {
            file.createNewFile()
        }
    }

    override fun setUser(user: User?) {
        file.writeText(gson.toJson(user))
    }

    override fun getUser(): User? {
        return try {
            gson.fromJson(file.readText(), User::class.java)
        } catch (e: Exception) {
            null
        }
    }
}