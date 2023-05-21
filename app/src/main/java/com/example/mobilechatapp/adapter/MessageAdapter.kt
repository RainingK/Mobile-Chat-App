package com.example.mobilechatapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mobilechatapp.R
import com.example.mobilechatapp.classes.Message
import com.google.firebase.auth.FirebaseAuth

class MessageAdapter(val context: Context, val messageList: ArrayList<Message>): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

	val ITEM_RECEIVE = 1
	val ITEM_SENT = 2

	class SentViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
		val sentMessage = itemView.findViewById<TextView>(R.id.sent_message_edit_text)
	}

	class ReceivedViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
		val receivedMessage = itemView.findViewById<TextView>(R.id.received_message_edit_text)
	}

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
		if (viewType == ITEM_SENT) {
			val view: View = LayoutInflater.from(context).inflate(R.layout.message_receive, parent, false)
			return ReceivedViewHolder(view)
		} else {
			val view: View = LayoutInflater.from(context).inflate(R.layout.message_sent, parent, false)
			return SentViewHolder(view)
		}
	}

	override fun getItemCount(): Int {
		return messageList.size
	}

	override fun getItemViewType(position: Int): Int {
		val currentMessage = messageList[position]

		if (FirebaseAuth.getInstance().currentUser?.uid.equals(currentMessage.getSenderID())) {
			return ITEM_SENT
		} else {
			return ITEM_RECEIVE
		}
	}

	override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
		if (holder.javaClass == SentViewHolder::class.java) {
			val viewHolder = holder as SentViewHolder

			viewHolder.sentMessage.text = messageList[position].getMessage()
		} else  {
			val viewHolder = holder as ReceivedViewHolder

			viewHolder.receivedMessage.text = messageList[position].getMessage()
		}
	}
}