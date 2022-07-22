package com.example.video3

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.*
import com.example.video3.api.ApiInterface
import com.example.video3.api.ApiUtilities
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject

class MainActivity : AppCompatActivity() {

    lateinit var editTextName : EditText
    lateinit var editTextRoomName : EditText
    lateinit var editparticipants : EditText
//    lateinit var editWebhookUrl : EditText
//    lateinit var editWebhookUrlFailOver : EditText
//    lateinit var editWebhookTimeOut : EditText
    lateinit var buttonCreateRoom : Button
    var radioGroupRecording: RadioGroup? = null
    lateinit var radioButton: RadioButton
    var recordingText = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val roomApi = ApiUtilities.getInstance().create(ApiInterface::class.java)

        editTextName            =   findViewById(R.id.etName)
        editTextRoomName        =   findViewById(R.id.etRoomName)
        editparticipants        =   findViewById(R.id.etParticipants)
//        editWebhookUrl          =   findViewById(R.id.etWebhookUrl)
//        editWebhookUrlFailOver  =   findViewById(R.id.etWebhookFailOverUrl)
//        editWebhookTimeOut      =   findViewById(R.id.etWebhookTimeOut)
        radioGroupRecording     =   findViewById<RadioGroup>(R.id.rgRecording)
        buttonCreateRoom        =   findViewById(R.id.btCreateRoom)

        buttonCreateRoom.setOnClickListener {
            val textYourName        =   editTextName.text
            val textRoomName        =   editTextRoomName.text
            val textParticipant     =   editparticipants.text
//            val textWebhookrUrl     =   editWebhookUrl.text
//            val textWebhookFailover =   editWebhookUrlFailOver.text
//            val textWebhookTimeOut  =   editWebhookTimeOut.text
            val selectedOption: Int = radioGroupRecording!!.checkedRadioButtonId

            radioButton = findViewById(selectedOption)
            if (radioButton.text.toString() == "Enable Recording"){
                recordingText = "true"
            }else{
                recordingText = "false"
            }

            // Create JSON using JSONObject
            val jsonObject = JSONObject()
            jsonObject.put("unique_name", textRoomName)
            jsonObject.put("max_participants", textParticipant)
            jsonObject.put("enable_recording", recordingText)

            // Convert JSONObject to String
            val jsonObjectString = jsonObject.toString()

            // Create RequestBody ( We're not using any converter, like GsonConverter, MoshiConverter e.t.c, that's why we use RequestBody )
            val requestBody = jsonObjectString.toRequestBody("application/json".toMediaTypeOrNull())

            GlobalScope.launch {
                val roomResult = roomApi.createRoom(requestBody)

                if(roomResult.body() != null){
                    Log.d("Telnyx", "DONE: ${roomResult.body()}")
//                    Open a new Activity
                    val intent = Intent(applicationContext, RoomListActivity::class.java).also {
                        it.putExtra("USER_NAME", textYourName.toString())
                        startActivity(it)
                    }

                }else{
//                    Toast.makeText(this@MainActivity, "Enter correct values", Toast.LENGTH_LONG).show()
                    Log.d("Telnyx", "FAIL")
                }
            }
        }
    }
}