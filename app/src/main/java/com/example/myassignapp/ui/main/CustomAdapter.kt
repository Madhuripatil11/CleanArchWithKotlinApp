package com.example.myassignapp.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myassignapp.data.models.api_models.User
import com.example.myassignapp.databinding.UserLayoutBinding

/**
 * Created by Madhuri Patil on 3/15/2024.
 */
class CustomAdapter : RecyclerView.Adapter<CustomAdapter.ViewHolder>() {
    private var userList = ArrayList<User>()
    fun setUserList(userList: List<User>) {
        this.userList = userList as ArrayList<User>
        notifyDataSetChanged()
    }

    class ViewHolder(val binding: UserLayoutBinding) : RecyclerView.ViewHolder(binding.root) {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            UserLayoutBinding.inflate(
                LayoutInflater.from(
                    parent.context
                )
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Glide.with(holder.itemView)
            .load(userList[position].avatar)
            .into(holder.binding.userImage)
        holder.binding.userName.text =
            userList[position].firstName + "" + userList[position].lastName
        holder.binding.email.text=userList[position].email
    }

    override fun getItemCount(): Int {
        return userList.size
    }
}