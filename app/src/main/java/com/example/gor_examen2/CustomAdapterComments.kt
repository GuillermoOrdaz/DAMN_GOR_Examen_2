package com.example.gor_examen2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.gor_examen2.entities.Comments
import com.example.gor_examen2.entities.Post

class CustomAdapterComments(private var commentList: List<Comments>
): RecyclerView.Adapter<CustomAdapterComments.ViewHolder>() {
    private lateinit var itemClickListener: OnItemClickListener

    interface OnItemClickListener{
        fun onItemClick(comments: Comments)
        fun onItemLongClick(comments: Comments)
    }

    fun setOnItemClickListener(listener: OnItemClickListener){
        itemClickListener = listener
    }

    inner class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        val tvName: TextView = view.findViewById(R.id.tvNameComm)
        val tvEmail: TextView = view.findViewById(R.id.tvEmailComm)
        val tvBody: TextView = view.findViewById(R.id.tvBodyComm)

        init {
            itemView.setOnClickListener {
                itemClickListener.onItemClick(commentList[adapterPosition])
            }
            itemView.setOnClickListener {
                itemClickListener.onItemLongClick(commentList[adapterPosition])
                true
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.custom_item_list_comments, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = commentList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val comment = commentList[position]
        holder.tvName.text = comment.name
        holder.tvEmail.text = "Email: ${comment.email}"
        holder.tvBody.text = "Body: ${comment.body}"
    }

    fun updateCommentsList(newCommentsist: MutableList<Comments>) {
        commentList = newCommentsist
        notifyDataSetChanged()
    }

}