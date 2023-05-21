package com.example.mobilechatapp.classes

class Message {
	private var message: String? = null
	private var senderID: String? = null

	constructor(message: String, senderID: String) {
		this.message = message
		this.senderID = senderID
	}

	fun getMessage(): String? {
		return message
	}

	fun getSenderID(): String? {
		return senderID
	}
}