package com.example.mobilechatapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.mobilechatapp.classes.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class SignUp : AppCompatActivity() {
	private lateinit var editTextName: EditText
	private lateinit var editTextEmail: EditText
	private lateinit var editTextPassword: EditText
	private lateinit var signUpBtn: Button

	private lateinit var auth: FirebaseAuth
	private lateinit var db: DatabaseReference

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_sign_up)

		// Hide the action bar
		supportActionBar?.hide()

//		Initialize the variables
		editTextName = findViewById(R.id.edit_text_name)
		editTextEmail = findViewById(R.id.edit_text_email)
		editTextPassword = findViewById(R.id.edit_text_password)
		signUpBtn = findViewById(R.id.btn_sign_up)

		auth = FirebaseAuth.getInstance()
		db = FirebaseDatabase.getInstance().reference
	}

	fun signUpUser(view: View) {
		val name: String = editTextName.text.toString()
		val email: String = editTextEmail.text.toString()
		val password: String = editTextPassword.text.toString()

		var isError = false
		if (name.isEmpty()) {
			editTextName.error = "Name is required"
			isError = true
		}
		if (email.isEmpty()) {
			editTextEmail.error = "Email is required"
			isError = true
		}
		if (password.isEmpty()) {
			editTextPassword.error = "Password is required"
			isError = true
		}

		if (isError) {
			return
		}

		auth.createUserWithEmailAndPassword(email, password)
			.addOnCompleteListener(this) { task ->
				if (task.isSuccessful) {
					// Add user to database
					addUserToDB(name, email, auth.currentUser?.uid!!)
					// Sign up success
					val intent = Intent(this, MainActivity::class.java)
					finish()
					startActivity(intent)
				} else {
					// If sign up fails, display a message to the user.
					Toast.makeText(this, "Authentication failed.", Toast.LENGTH_SHORT).show()
				}
			}
	}

	private fun addUserToDB(name: String, email: String, uid: String) {
		db.child("users").child(uid).setValue(User(name, email, uid))
			.addOnSuccessListener {
				Toast.makeText(this, "Successfully added user to the database", Toast.LENGTH_SHORT).show()
			}
			.addOnFailureListener { exception ->
				Toast.makeText(this, "Failed to add user to the database: ${exception.message}", Toast.LENGTH_SHORT).show()
			}
	}
}