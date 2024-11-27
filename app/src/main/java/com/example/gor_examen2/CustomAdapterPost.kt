package com.example.gor_examen2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.gor_examen2.entities.Post
import com.example.gor_examen2.entities.User

class CustomAdapterPost(private var postList: List<Post>
): RecyclerView.Adapter<CustomAdapterPost.ViewHolder>() {
    private lateinit var itemClickListener: OnItemClickListener

    interface OnItemClickListener{
        fun onItemClick(post: Post)
        fun onItemLongClick(post: Post)
    }

    fun setOnItemClickListener(listener: OnItemClickListener){
        itemClickListener = listener
    }

    inner class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        val tvTitle: TextView = view.findViewById(R.id.tvTitulo)
        val tvBody: TextView = view.findViewById(R.id.tvBody)

        init {
            itemView.setOnClickListener {
                itemClickListener.onItemClick(postList[adapterPosition])
            }
            itemView.setOnLongClickListener {
                itemClickListener.onItemLongClick(postList[adapterPosition])
                true
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.custom_item_list_post, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = postList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val post = postList[position]
        holder.tvTitle.text = post.title
        holder.tvBody.text = "Body: ${post.body}"
    }

    fun updatePostsList(newPostsList: MutableList<Post>) {
        postList = newPostsList
        notifyDataSetChanged()
    }

}