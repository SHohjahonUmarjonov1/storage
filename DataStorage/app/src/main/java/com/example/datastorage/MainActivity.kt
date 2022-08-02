package com.example.datastorage

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.datastorage.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding= ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        val storage=Preferences(this)
        if (storage.getUser()!=null) {
            homeActivity()
        }
        val login = LoginRequest(
            binding.email.text.toString(),
            binding.password.text.toString()
        )
        binding.login.setOnClickListener {
            Networking.socialApi.login(login).enqueue(object :Callback<User>{
                override fun onResponse(call: Call<User>, response: Response<User>) {
                    storage.setUser(response.body())
                    homeActivity()
                }

                override fun onFailure(call: Call<User>, t: Throwable) {
                    Toast.makeText(this@MainActivity, "Error occurred", Toast.LENGTH_SHORT).show()
                }

            })
        }
    }
    fun homeActivity() {
        finish()
        val inent=Intent(this,LoginActivity::class.java)
        startActivity(inent)
    }
}