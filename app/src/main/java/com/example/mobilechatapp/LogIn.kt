package com.example.mobilechatapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class LogIn : AppCompatActivity() {
	private lateinit var editTextEmail: EditText
	private lateinit var editTextPassword: EditText
	private lateinit var btnLogIn: Button
	private lateinit var btnSignUp: Button

	private lateinit var auth: FirebaseAuth

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_log_in)

//		Initialize variables
		editTextEmail = findViewById(R.id.edit_text_email)
		editTextPassword = findViewById(R.id.edit_text_password)
		btnLogIn = findViewById(R.id.btn_login)
		btnSignUp = findViewById(R.id.btn_sign_up)

		auth = FirebaseAuth.getInstance()
	}

	fun goToSignUp(view: View) {
		val intent = Intent(this, SignUp::class.java)
		startActivity(intent)
	}

	fun logInUser(view: View) {
		val email: String = editTextEmail.text.toString()
		val password: String = editTextPassword.text.toString()

		var isError = false
		if (email.isEmpty()) {
			editTextEmail.error = "Email cannot be empty"
			isError = true
		}
		if (password.isEmpty()) {
			editTextPassword.error = "Password cannot be empty"
			isError = true
		}

		if (isError) {
			return
		}

		auth.signInWithEmailAndPassword(email, password)
			.addOnCompleteListener(this) { task ->
				if (task.isSuccessful) {
					// Sign in success
					val intent = Intent(this, MainActivity::class.java)
					startActivity(intent)
				} else {
					// If sign in fails, display a message to the user.
					Toast.makeText(this, "User does not exist", Toast.LENGTH_SHORT).show()
				}
			}
	}
}