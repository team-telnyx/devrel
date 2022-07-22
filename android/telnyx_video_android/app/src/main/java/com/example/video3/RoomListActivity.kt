package com.example.video3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.video3.api.ApiInterface
import com.example.video3.api.ApiUtilities
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class RoomListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_room_list)

        val userName    =   intent.getStringExtra("USER_NAME")
        val textUserName    =   findViewById<TextView>(R.id.tvYourName).apply {
            text    =   "Hi ${userName}"
        }

        // Swipe to refresh
        val swipe = findViewById<SwipeRefreshLayout>(R.id.swipeRefreshLayout)
        // getting the recyclerview by its id
        val recyclerview = findViewById<RecyclerView>(R.id.recyclerview)
        // this creates a vertical layout Manager
        recyclerview.layoutManager = LinearLayoutManager(this)

        // ArrayList of class ItemsViewModel
        val dataRooms = ArrayList<ItemsViewModel>()

        val roomApi = ApiUtilities.getInstance().create(ApiInterface::class.java)

        GlobalScope.launch {
            val listRoomResult =roomApi.listOfRooms()
            if(listRoomResult.body() != null) {
                listRoomResult.body()!!.data.iterator().forEach {
                    Log.d("bro", "Names:${it.unique_name}")
//                    Log.d("Tel", "Participants:${it.max_participants}")
//                    dataRooms.add(ItemsViewModel("Name: ${it.unique_name}","Number: ${it.max_participants}"))
                    dataRooms.add(ItemsViewModel("${it.unique_name}","Max Participants: ${it.max_participants}"))
                    Log.d("Xerxes", "onCreate: $dataRooms")
                }
            }
        }

        swipe.setOnRefreshListener {
            swipe.isRefreshing = false
            // This will pass the ArrayList to our Adapter
            val adapter = CustomAdapter(dataRooms)
            // Setting the Adapter with the recyclerview
            recyclerview.adapter = adapter
            Toast.makeText(this, "Data Refreshed", Toast.LENGTH_SHORT).show()
        }
        // This will pass the ArrayList to our Adapter
        val adapter = CustomAdapter(dataRooms)
        // Setting the Adapter with the recyclerview
        recyclerview.adapter = adapter
//        Log.d("Dino", "$dataRooms")
        textUserName.setText("Hi ${userName}")
    }
}