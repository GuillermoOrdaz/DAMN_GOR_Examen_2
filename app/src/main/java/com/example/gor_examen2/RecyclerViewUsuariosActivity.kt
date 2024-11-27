package com.example.gor_examen2

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.gor_examen2.data.UserDatabase
import com.example.gor_examen2.entities.User
import com.example.gor_examen2.repositories.RepositoryUser
import com.example.gor_examen2.repositories.UserDaoRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class RecyclerViewUsuariosActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: CustomAdapterUser
    private var listadoUsers = mutableListOf<User>()
    private var userRepository = RepositoryUser()
    private var intentPost = Intent(this, RecyclerViewPostsActivity::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_recycler_view_usuarios)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        recyclerView = findViewById(R.id.rvUsuarios) // Asegúrate que este ID coincida con tu layout XML
        adapter = CustomAdapterUser(listadoUsers) // Pasar razaDao al adaptador
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        obtenerUsers()
        adapter.setOnItemClickListener(object : CustomAdapterUser.OnItemClickListener {
            //val intentPost = Intent(this, RecyclerViewPostsActivity::class.java)
            override fun onItemClick(user: User) {
                val intentPost = Intent(this@RecyclerViewUsuariosActivity, RecyclerViewPostsActivity::class.java)
                intentPost.putExtra("userId", user.id) // Pasar datos al siguiente RecyclerView
                startActivity(intentPost)
            }

            override fun onItemLongClick(user: User) {
                CoroutineScope(Dispatchers.IO).launch {
                    val userDao = UserDatabase.getInstancia(this@RecyclerViewUsuariosActivity).UserDao()
                    val userRepository = UserDaoRepository(userDao)

                    try {
                        userRepository.insertUser(user)
                        withContext(Dispatchers.Main) {
                            Toast.makeText(
                                this@RecyclerViewUsuariosActivity,
                                "${user.name} guardado localmente",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    } catch (e: Exception) {
                        withContext(Dispatchers.Main) {
                            Toast.makeText(
                                this@RecyclerViewUsuariosActivity,
                                "Error al guardar usuario",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                }
            }
        })

    }

    private fun isNetworkAvailable(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork = connectivityManager.activeNetworkInfo
        return activeNetwork != null && activeNetwork.isConnected
    }

    private fun obtenerUsers(){
        if (isNetworkAvailable(this)) {
            // Cargar desde la API
            CoroutineScope(Dispatchers.IO).launch {
                try {
                    val users = userRepository.getAllUsuarios() // Llamada a la API
                    withContext(Dispatchers.Main) {
                        adapter.updateUserList(users.toMutableList())
                    }
                } catch (e: Exception) {
                    withContext(Dispatchers.Main) {
                        Toast.makeText(
                            this@RecyclerViewUsuariosActivity,
                            "Error al cargar usuarios desde la API",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
        } else {
            // Cargar desde la base de datos
            CoroutineScope(Dispatchers.IO).launch {
                val userDao = UserDatabase.getInstancia(this@RecyclerViewUsuariosActivity).UserDao()
                val userRepository = UserDaoRepository(userDao)

                try {
                    val users = userRepository.getAllUsers() as? List<User> ?: emptyList()
                    withContext(Dispatchers.Main) {
                        if (users.isNotEmpty()) {
                            adapter.updateUserList(users.toMutableList())
                        } else {
                            Toast.makeText(
                                this@RecyclerViewUsuariosActivity,
                                "No hay usuarios almacenados localmente",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                } catch (e: Exception) {
                    withContext(Dispatchers.Main) {
                        Toast.makeText(
                            this@RecyclerViewUsuariosActivity,
                            "Error al cargar usuarios locales",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
        }
    }

}