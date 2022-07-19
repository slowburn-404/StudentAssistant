package com.boris.studentassistant

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Toast
import com.boris.studentassistant.adapters.MessageAdapter
import com.boris.studentassistant.models.Message
import com.boris.studentassistant.databinding.ActivityChatBinding
import com.google.api.gax.core.FixedCredentialsProvider
import com.google.auth.oauth2.GoogleCredentials
import com.google.auth.oauth2.ServiceAccountCredentials
import com.google.cloud.dialogflow.v2.*
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers.Default
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.*
import kotlin.collections.ArrayList


class ChatActivity : AppCompatActivity() {
        private lateinit var binding: ActivityChatBinding

        private var messageList: ArrayList<Message> = ArrayList()

        //firebase
        private lateinit var sAuth: FirebaseAuth
        //dialogflow
        private var sessionsClient: SessionsClient? = null
        private var sessionName: SessionName? = null
        private var uuid = UUID.randomUUID().toString()
        private val TAG = "chatactivity"

        @SuppressLint("NotifyDataSetChanged")

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            binding = ActivityChatBinding.inflate(layoutInflater)
            setContentView(binding.root)

            supportActionBar?.show()

            //initialize firebase
            sAuth = FirebaseAuth.getInstance()

            //setting adapter to recyclerview
            val messageAdapter = MessageAdapter(this, messageList)
            binding.RecyclerViewChat.adapter = messageAdapter

            //update list and call dialogflow
            binding.floatingActionButtonSendMessage.setOnClickListener {
                val message = binding.textInputEditTextTypeMessage.text.toString()

                if(message.isNotEmpty()){
                    addMessageToList(message, false)
                    sendMessageToBot(message)
                }
                else{
                    Toast.makeText(this, "You must enter a message", Toast.LENGTH_SHORT).show()
                }
            }
            //setup bot config
            initializeBot()
    }
    @SuppressLint("NotifyDataSetChanged")
    private fun addMessageToList(message: String, isReceived: Boolean){
        messageList.add(Message(message, isReceived))
        binding.textInputEditTextTypeMessage.setText("")
        val messageAdapter = MessageAdapter(this, messageList)
        messageAdapter.notifyDataSetChanged()
        binding.RecyclerViewChat.layoutManager?.scrollToPosition(messageList.size - 1)
    }

    private fun initializeBot(){
        try{
            val stream = this.resources.openRawResource(R.raw.credentials)
            val credentials: GoogleCredentials = GoogleCredentials.fromStream(stream).createScoped("https://www.googleapis.com/auth/cloud-platform")
            val projectID: String = (credentials as ServiceAccountCredentials).projectId
            val settingsBuilder: SessionsSettings.Builder = SessionsSettings.newBuilder()
            val sessionsSettings: SessionsSettings = settingsBuilder.setCredentialsProvider( FixedCredentialsProvider.create(credentials)).build()

            sessionsClient = SessionsClient.create(sessionsSettings)
            sessionName = SessionName.of(projectID, uuid)
            Log.d(TAG, "projectID : $projectID")



        }catch (e: Exception){
            Log.d(TAG, "initializeBot:" + e.message)
        }
    }
    @OptIn(DelicateCoroutinesApi::class)
    private fun sendMessageToBot(message: String){
        val input = QueryInput.newBuilder().setText(TextInput.newBuilder().setText(message).setLanguageCode("en-US")).build()
        GlobalScope.launch{
            sendMessageInBG(input)
        }
    }
    private suspend fun sendMessageInBG(queryInput: QueryInput){
        withContext(Default){
            try{
                val detectIntentRequest = DetectIntentRequest.newBuilder().setSession(sessionName.toString()).setQueryInput(queryInput).build()
                val result = sessionsClient?.detectIntent(detectIntentRequest)
                if(result != null){
                    runOnUiThread{
                        updateUI(result)
                    }
                }
            }catch(e: Exception){
                Log.d(TAG, "doInBackground: " + e.message)
                e.printStackTrace()
            }
        }
    }
    private fun updateUI(response: DetectIntentResponse){
        val botReply: String = response.queryResult.fulfillmentText
        if(botReply.isNotEmpty()){
            addMessageToList(botReply, true)
        } else{
            Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.chat_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId) {
         R.id.log_out -> {
            //log out user
             sAuth.signOut()
             val intent = Intent(this, LogInActivity::class.java)
             finish()
             startActivity(intent)
             true
         }
            R.id.exit_app -> {
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }


}