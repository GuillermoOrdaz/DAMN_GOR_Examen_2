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
import com.example.gor_examen2.entities.Post
import com.example.gor_examen2.entities.User
import com.example.gor_examen2.repositories.RepositoryPost
import com.example.gor_examen2.repositories.RepositoryUser
import com.example.gor_examen2.services.PostService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class RecyclerViewPostsActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: CustomAdapterPost
    private var listadoPosts = mutableListOf<Post>()
    private var postRepository = RepositoryPost()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_recycler_view_posts)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        recyclerView = findViewById(R.id.rvPosts) // Asegúrate que este ID coincida con tu layout XML
        adapter = CustomAdapterPost(listadoPosts) // Pasar razaDao al adaptador
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        val userId = intent.getLongExtra("userId", -1) // Obtén el ID del usuario
        if (userId != -1L) {
            obtenerPosts(userId) // Llama al método con el ID del usuario
        } else {
            Toast.makeText(this, "No se pudo obtener el ID del usuario.", Toast.LENGTH_SHORT).show()
        }

        adapter.setOnItemClickListener(object : CustomAdapterPost.OnItemClickListener {
            //val intentPost = Intent(this, RecyclerViewPostsActivity::class.java)
            override fun onItemClick(post: Post) {
                val intentComm = Intent(this@RecyclerViewPostsActivity, RecyclerViewCommentsActivity::class.java)
                intentComm.putExtra("postId", post.id) // Pasar datos al siguiente RecyclerView
                startActivity(intentComm)
            }

            override fun onItemLongClick(post: Post) {
                //mostrarDialogoEliminar(heroe)
            }
        })

    }

    private fun obtenerPosts(userId: Long){
        //Mandar a segundo plano
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val posts = postRepository.getAllPosts(userId)
                //Mandar a primer plano = UI = Vistas
                withContext(Dispatchers.Main){
                    //Cargar el listado en el Listview, Recyclerview, Textview
                    //Toast.makeText(this@MainActivity, "Listado Heroes: "+heroes.msg, Toast.LENGTH_LONG).show()
                    adapter.updatePostsList(posts.toMutableList())
                }
            }catch (e:Exception){
                if (e is retrofit2.HttpException && e.code() == 404) {
                    // Manejar el 404 como una lista vacía
                    withContext(Dispatchers.Main) {
                        Toast.makeText(this@RecyclerViewPostsActivity,"Sin post aún", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    // Manejar otras excepciones como errores
                    withContext(Dispatchers.Main) {
                        Toast.makeText(
                            this@RecyclerViewPostsActivity,
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