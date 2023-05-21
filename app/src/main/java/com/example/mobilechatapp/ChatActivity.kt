package com.example.mobilechatapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.example.mobilechatapp.adapter.MessageAdapter
import com.example.mobilechatapp.classes.Message
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class ChatActivity : AppCompatActivity() {

	private lateinit var messageRecyclerView: RecyclerView
	private lateinit var messageBox: EditText
	private lateinit var sendButton: ImageView
	private lateinit var messageAdapter: MessageAdapter
	private lateinit var messageList: ArrayList<Message>

	private var receiverRoom: String? = null
	private var senderRoom: String? = null
	private lateinit var senderUid: String

	private lateinit var db: DatabaseReference

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_chat)

		val name = intent.getStringExtra("name")
		val receiverUid = intent.getStringExtra("uid")
		this.senderUid = FirebaseAuth.getInstance().currentUser?.uid!!

		senderRoom = receiverUid + senderUid
		receiverRoom = senderUid + receiverUid

		supportActionBar?.title = name

		messageRecyclerView = findViewById(R.id.chat_recycler_view)
		messageBox = findViewById(R.id.chat_edit_text)
		sendButton = findViewById(R.id.send_image_view)
		messageList = ArrayList()
		messageAdapter = MessageAdapter(this, messageList)
		messageRecyclerView.layoutManager = LinearLayoutManager(this)
		messageRecyclerView.adapter = messageAdapter

		db = FirebaseDatabase.getInstance().reference

		db.child("chats").child(senderRoom!!).child("messages").addValueEventListener (object: ValueEventListener {
			override fun onDataChange(snapshot: DataSnapshot) {
				messageList.clear()

				for (postSnapshot in snapshot.children) {
					val message = postSnapshot.getValue(Message::class.java)
					messageList.add(message!!)
				}

				messageAdapter.notifyDataSetChanged()
			}

			override fun onCancelled(error: DatabaseError) {

			}

		})
	}

	fun sendMessage(view: View) {
		val message = messageBox.text.toString()
		val messageObj = Message(message, senderUid)
		db.child("chats").child(senderRoom!!).child("messages").push().setValue(messageObj)
			.addOnSuccessListener {
				db.child("chats").child(receiverRoom!!).child("messages").push().setValue(messageObj)
			}

		messageBox.setText("")
	}
}