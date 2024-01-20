package net.azarquiel.caravan_retrofit_livedata.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import net.azarquiel.caravan_retrofit_livedata.R
import net.azarquiel.caravan_retrofit_livedata.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnLoginSubmit.setOnClickListener{
            val nick = binding.tieLoginNick.text.toString()
            val pass = binding.tieLoginPass.text.toString()
            Log.d("sufiDev", "Login: $nick, $pass")
        }
    }
}