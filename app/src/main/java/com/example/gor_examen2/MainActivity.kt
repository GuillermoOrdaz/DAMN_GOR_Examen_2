package com.example.gor_examen2

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.gor_examen2.entities.User
import com.example.gor_examen2.repositories.RepositoryComment
import com.example.gor_examen2.repositories.RepositoryPost
import com.example.gor_examen2.repositories.RepositoryUser
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val btnRecycler = findViewById<Button>(R.id.btnRecycler)
        val btnCreditos = findViewById<Button>(R.id.btnCreditos)
        val btnSalir = findViewById<Button>(R.id.btnSalir)

        btnRecycler.setOnClickListener {
            val intentRecycler = Intent(this, RecyclerViewUsuariosActivity::class.java)
            startActivity(intentRecycler)
        }

        btnCreditos.setOnClickListener {
            val intentCreditos = Intent(this, CreditosActivity::class.java)
            startActivity(intentCreditos)
        }

        btnSalir.setOnClickListener {
            finishAffinity()
        }
    }
}