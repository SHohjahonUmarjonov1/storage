package com.example.datastorage

import android.content.Context
import com.google.gson.Gson

class PreferencesStorage(context: Context):Storage {
    private val preferences=context.getSharedPreferences("preferences.file",Context.MODE_PRIVATE)
    override fun setUser(user: User?) {
        if (user==null){
            preferences.edit().clear().apply()
            return
        }
        val json_user=Gson().toJson(user)
        preferences.edit().putString("key",json_user).apply()
    }

    override fun getUser(): User? {
        val user= preferences.getString("key",null)
        return Gson().fromJson(user,User::class.java)
    }

}