package com.boris.studentassistant.adapters

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.boris.studentassistant.R
import com.boris.studentassistant.models.Message
import com.boris.studentassistant.adapters.MessageAdapter.MessageViewHolder
//import io.grpc.netty.shaded.io.netty.util.Recycler

class MessageAdapter(
    private var activity: Activity,
    private var messageList: List<Message>
): RecyclerView.Adapter<MessageViewHolder>() {
    class MessageViewHolder(
        itemView: View
    ): RecyclerView.ViewHolder(itemView){
        val receiveMessage: TextView = itemView.findViewById(R.id.textReceiveMessage)
        val sendMessage: TextView = itemView.findViewById(R.id.textSendMessage)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessageViewHolder {
        val view = LayoutInflater.from(activity).inflate(R.layout.messages, parent, false)
        return MessageViewHolder(view)
    }

    override fun onBindViewHolder(holder: MessageViewHolder, position: Int) {
        val message: String = messageList[position].message
        val isReceived: Boolean = messageList[position].isReceived

        if(isReceived){
            holder.receiveMessage.visibility = View.VISIBLE
            holder.sendMessage.visibility = View.GONE
            holder.receiveMessage.text = message
        } else{
            holder.sendMessage.visibility = View.VISIBLE
            holder.receiveMessage.visibility = View.GONE
            holder.sendMessage.text = message
        }
    }

    override fun getItemCount(): Int {
        return messageList.count()
    }

}