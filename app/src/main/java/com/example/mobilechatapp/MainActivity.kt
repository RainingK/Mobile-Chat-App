package com.example.mobilechatapp

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mobilechatapp.adapter.UserAdapter
import com.example.mobilechatapp.classes.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class MainActivity : AppCompatActivity() {
	private lateinit var userRecyclerView: RecyclerView
	private lateinit var userList:  ArrayList<User>
	private lateinit var userAdapter: UserAdapter

	private lateinit var auth: FirebaseAuth
	private lateinit var db: DatabaseReference

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)

		userRecyclerView = findViewById(R.id.recycler_user)
		userList = ArrayList()
		userAdapter = UserAdapter(this, userList)

		auth = FirebaseAuth.getInstance()
		db = FirebaseDatabase.getInstance().reference

		userRecyclerView.layoutManager = LinearLayoutManager(this)
		userRecyclerView.adapter = userAdapter

		db.child("users").addValueEventListener(object : ValueEventListener {
			override fun onDataChange(snapshot: DataSnapshot) {
				userList.clear()

				for (ds in snapshot.children) {
					val currentUser = ds.getValue(User::class.java)

					if (currentUser?.getUid() != auth.currentUser?.uid) {
						userList.add(currentUser!!)
					}
				}

				userAdapter.notifyDataSetChanged()
			}

			override fun onCancelled(error: DatabaseError) {

			}

		})
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