package com.example.mobilechatapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mobilechatapp.R
import com.example.mobilechatapp.classes.User

class UserAdapter(val context: Context, val userList: ArrayList<User>): RecyclerView.Adapter<UserAdapter.ViewHolder>() {

	class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
		val name: TextView = view.findViewById(R.id.text_view_name)
	}

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
		val view: View = LayoutInflater.from(context).inflate(R.layout.user_layout, parent, false)
		return ViewHolder(view)
	}

	override fun getItemCount(): Int {
		return userList.size
	}

	override fun onBindViewHolder(holder: ViewHolder, position: Int) {
		val currentUser = userList[position]
		holder.name.text = currentUser.getName()
	}

}