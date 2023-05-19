package com.example.mobilechatapp.classes

class User {
	private var name: String? = null
	private var email: String? = null
	private var uid: String? = null

	constructor() {}

	constructor(name: String?, email: String?, uid: String?) {
		this.name = name
		this.email = email
		this.uid = uid
	}

	fun getName(): String? {
		return name
	}

	fun getEmail(): String? {
		return email
	}

	fun getUid(): String? {
		return uid
	}
}