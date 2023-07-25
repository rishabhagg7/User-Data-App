package com.example.firebase.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.firebase.R
import com.example.firebase.models.User

class UserAdapter: RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    private val userList = ArrayList<User>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.user_item,
            parent, false)
        return UserViewHolder(itemView)
    }

    override fun getItemCount(): Int = userList.size

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val currentUser = userList[position]
        holder.firstName.text = currentUser.firstName
        holder.lastName.text = currentUser.lastName
        holder.age.text = currentUser.age
    }

    fun updateUserList(userList: List<User>){
        this.userList.clear()
        this.userList.addAll(userList)
        notifyDataSetChanged()
    }

    class UserViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val firstName: TextView = itemView.findViewById(R.id.tvfirstName)
        val lastName: TextView = itemView.findViewById(R.id.tvlastName)
        val age: TextView = itemView.findViewById(R.id.tvage)
    }
}