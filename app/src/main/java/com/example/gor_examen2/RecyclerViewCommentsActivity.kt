package com.example.gor_examen2

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.gor_examen2.entities.Comments
import com.example.gor_examen2.entities.Post
import com.example.gor_examen2.repositories.RepositoryComment
import com.example.gor_examen2.repositories.RepositoryPost
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class RecyclerViewCommentsActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: CustomAdapterComments
    private var listadoComments = mutableListOf<Comments>()
    private var commentsRepository = RepositoryComment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_recycler_view_comments)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        recyclerView = findViewById(R.id.rvComments) // Asegúrate que este ID coincida con tu layout XML
        adapter = CustomAdapterComments(listadoComments) // Pasar razaDao al adaptador
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        val postId = intent.getLongExtra("postId", -1) // Obtén el ID del usuario
        if (postId != -1L) {
            obtenerPosts(postId) // Llama al método con el ID del usuario
        } else {
            Toast.makeText(this, "No se pudo obtener el ID del post.", Toast.LENGTH_SHORT).show()
        }



    }

    private fun obtenerPosts(postId: Long){
        //Mandar a segundo plano
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val comments = commentsRepository.getAllComments(postId)
                //Mandar a primer plano = UI = Vistas
                withContext(Dispatchers.Main){
                    //Cargar el listado en el Listview, Recyclerview, Textview
                    //Toast.makeText(this@MainActivity, "Listado Heroes: "+heroes.msg, Toast.LENGTH_LONG).show()
                    adapter.updateCommentsList(comments.toMutableList())
                }
            }catch (e:Exception){
                if (e is retrofit2.HttpException && e.code() == 404) {
                    // Manejar el 404 como una lista vacía
                    withContext(Dispatchers.Main) {
                        Toast.makeText(this@RecyclerViewCommentsActivity,"Sin post aún", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    // Manejar otras excepciones como errores
                    withContext(Dispatchers.Main) {
                        Toast.makeText(
                            this@RecyclerViewCommentsActivity,
                            "Sin post",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
                Log.d("Cat", "Error al obtener héroes ${e}")
            }
        }
    }
}