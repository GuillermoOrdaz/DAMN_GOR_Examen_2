package com.example.gor_examen2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.gor_examen2.entities.User

class CustomAdapterUser(private var userList: List<User>
): RecyclerView.Adapter<CustomAdapterUser.ViewHolder>() {
    private lateinit var itemClickListener: OnItemClickListener

    interface OnItemClickListener{
        fun onItemClick(user: User)
        fun onItemLongClick(user: User)
    }

    fun setOnItemClickListener(listener: OnItemClickListener){
        itemClickListener = listener
    }

    inner class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        val tvname: TextView = view.findViewById(R.id.tvName)
        val tvUsername: TextView = view.findViewById(R.id.tvUsername)
        val tvEmail: TextView = view.findViewById(R.id.tvEmail)
        val tvPhone: TextView = view.findViewById(R.id.tvPhone)
        val tvWebsite: TextView = view.findViewById(R.id.tvWebSite)

        init {
            itemView.setOnClickListener {
                itemClickListener.onItemClick(userList[adapterPosition])
            }
            itemView.setOnLongClickListener {
                itemClickListener.onItemLongClick(userList[adapterPosition])
                true
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.custom_item_list_user, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = userList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val user = userList[position]
        holder.tvname.text = user.name
        holder.tvUsername.text = "Username: ${user.username}"
        holder.tvEmail.text = "Email: ${user.email}"
        holder.tvPhone.text = "Phone: ${user.phone}"
        holder.tvWebsite.text = "Website: ${user.website}"
    }

    fun updateUserList(newUsersList: MutableList<User>) {
        userList = newUsersList
        notifyDataSetChanged()
    }

}