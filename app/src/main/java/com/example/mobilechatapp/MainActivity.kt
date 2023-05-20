package com.example.mobilechatapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mobilechatapp.adapter.UserAdapter
import com.example.mobilechatapp.classes.User
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {
	private lateinit var userRecyclerView: RecyclerView
	private lateinit var userList:  ArrayList<User>
	private lateinit var userAdapter: UserAdapter

	private lateinit var auth: FirebaseAuth

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)

		userRecyclerView = findViewById(R.id.recycler_user)
		userList = ArrayList()
		userAdapter = UserAdapter(this, userList)

		auth = FirebaseAuth.getInstance()
	}

	override fun onCreateOptionsMenu(menu: Menu?): Boolean {
		menuInflater.inflate(R.menu.menu, menu)
		return super.onCreateOptionsMenu(menu)
	}

	override fun onOptionsItemSelected(item: MenuItem): Boolean {
		if (item.itemId == R.id.log_out) {
			auth.signOut()
			val intent = Intent(this, LogIn::class.java)
			finish()
			startActivity(intent)
			return true
		}

		return true
	}
}