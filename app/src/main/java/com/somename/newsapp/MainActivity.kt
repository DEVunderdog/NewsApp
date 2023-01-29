package com.somename.newsapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.somename.newsapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var binding:ActivityMainBinding = ActivityMainBinding.inflate(this.layoutInflater)
        setContentView(binding.root)

        if(supportActionBar != null){
            supportActionBar!!.hide()
        }

        val btnAhead = binding.moveAhead

        btnAhead.setOnClickListener {
            val intent = Intent(this, NewsFeed::class.java)
            startActivity(intent)
            finish()
        }


    }
}