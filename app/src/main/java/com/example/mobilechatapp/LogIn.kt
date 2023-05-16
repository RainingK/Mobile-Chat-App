package com.example.mobilechatapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText

class LogIn : AppCompatActivity() {
	private lateinit var editTextEmail: EditText
	private lateinit var editTextPassword: EditText
	private lateinit var btnLogIn: Button
	private lateinit var btnSignUp: Button

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_log_in)

//		Initialize variables
		editTextEmail = findViewById(R.id.edit_text_email)
		editTextPassword = findViewById(R.id.edit_text_password)
		btnLogIn = findViewById(R.id.btn_login)
		btnSignUp = findViewById(R.id.btn_sign_up)
	}

	fun goToSignUp(view: View) {
		val intent = Intent(this, SignUp::class.java)
		startActivity(intent)
	}
}