package net.azarquiel.caravan_retrofit_livedata.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import net.azarquiel.caravan_retrofit_livedata.R
import net.azarquiel.caravan_retrofit_livedata.databinding.ActivityLoginBinding
import net.azarquiel.caravan_retrofit_livedata.viewmodel.MainViewModel

class LoginActivity : AppCompatActivity() {
    lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnLoginSubmit.setOnClickListener{
            val nick = binding.tieLoginNick.text.toString()
            val pass = binding.tieLoginPass.text.toString()
            login(nick, pass)
        }
    }

    private fun login(nick: String, pass: String) {
        var viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        viewModel.getDataUser(nick, pass).observe(this, Observer { it ->
                if (it != null) {
                    Log.d("sufiDev", "User logged in: ${it.nick}")
                    //toast
                } else {
                    Toast.makeText(this, "Usuario o contraseña incorrectos", Toast.LENGTH_LONG).show()
                }
        })
    }
}