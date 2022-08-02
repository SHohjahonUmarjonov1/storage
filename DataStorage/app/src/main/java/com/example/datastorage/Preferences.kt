package com.example.datastorage

import android.content.Context

class Preferences(context: Context):Storage {
    private val preferences=context.getSharedPreferences("preferences2", Context.MODE_PRIVATE)
    override fun setUser(user: User?) {
        if (user==null) {
            preferences.edit().clear().apply()
            return
        }
        preferences.edit()
            .putString("firstName",user.firstName)
            .putString("lastName",user.lastName)
            .putString("email",user.email)
            .putString("avatar",user.avatar)
            .apply()
    }

    override fun getUser(): User? {
        return User(
            firstName = preferences.getString("firstName",null)?:return null,
            lastName = preferences.getString("lastName",null)?:return null,
            email = preferences.getString("email",null)?:return null,
            avatar = preferences.getString("avatar",null)?:return null,
        )
    }

}