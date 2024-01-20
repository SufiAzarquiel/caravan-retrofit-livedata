package net.azarquiel.caravan_retrofit_livedata.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import net.azarquiel.caravan_retrofit_livedata.R
import net.azarquiel.caravan_retrofit_livedata.databinding.ActivityRegisterBinding
import net.azarquiel.caravan_retrofit_livedata.model.Usuario
import net.azarquiel.caravan_retrofit_livedata.viewmodel.MainViewModel

class RegisterActivity : AppCompatActivity() {
    lateinit var binding: ActivityRegisterBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnRegisterSubmit.setOnClickListener{
            val nick = binding.tieRegisterNick.text.toString()
            val pass = binding.tieRegisterPass.text.toString()
            addUser(nick, pass)
        }
    }

    private fun addUser(nick: String, pass: String) {
        val viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        val user = Usuario(nick, pass)
        viewModel.addUser(user).observe(this, Observer {
            it?.let {
                Log.d("sufiDev", "User added: ${it.nick}")
                val intent = Intent(this, MainActivity::class.java)
                intent.putExtra("user", it)
                startActivity(intent)
            }
        })
    }
}