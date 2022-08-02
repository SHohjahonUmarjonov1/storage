package com.example.datastorage

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.isVisible
import com.bumptech.glide.Glide
import com.example.datastorage.databinding.ActivityLoginBinding


class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityLoginBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        val storage = Preferences(this)
        val user = storage.getUser() ?: kotlin.run {
            finish()
            logout()
            return
        }
        binding.FirstName.setText(user.firstName)
        binding.LastName.setText(user.lastName)
        binding.Email.setText(user.email)

        Glide.with(this).load(user.avatar).circleCrop().into(binding.image)

        binding.edit.setOnClickListener {
            setEdit(true)
        }
        binding.save.setOnClickListener {
            setEdit(false)
            val newUser = User(
                firstName = binding.FirstName.text?.toString() ?: return@setOnClickListener,
                lastName = binding.LastName.text?.toString() ?: return@setOnClickListener,
                email = binding.Email.text?.toString() ?: return@setOnClickListener,
                avatar = user.avatar
            )
            storage.setUser(newUser)
        }

        binding.out.setOnClickListener {
            storage.setUser(null)
            logout()
            finish()
        }
    }

    private fun setEdit(enabled: Boolean): Unit = with(binding) {
        listOf(FirstName, LastName, Email).forEach {
            it.isEnabled = enabled
        }
        edit.isVisible = enabled.not()
        save.isVisible = enabled
    }

    private fun logout() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}