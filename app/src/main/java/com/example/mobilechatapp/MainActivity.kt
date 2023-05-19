package com.example.mobilechatapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mobilechatapp.adapter.UserAdapter
import com.example.mobilechatapp.classes.User

class MainActivity : AppCompatActivity() {
	private lateinit var userRecyclerView: RecyclerView
	private lateinit var userList:  ArrayList<User>
	private lateinit var userAdapter: UserAdapter

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)

		userRecyclerView = findViewById(R.id.recycler_user)
		userList = ArrayList()
		userAdapter = UserAdapter(this, userList)
	}
}