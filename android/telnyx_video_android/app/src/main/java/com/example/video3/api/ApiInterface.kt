package com.example.video3.api

import com.example.video3.model.CreateRoom
import com.example.video3.model.ListRooms
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST

interface ApiInterface {
//replace API_KEY with yours from Telnyx Portal
@Headers("Content-Type: application/json","Accept: application/json", "Authorization: Bearer API_KEY")
@POST("rooms")
suspend fun createRoom(@Body requestBody: RequestBody) : Response<CreateRoom>

@Headers("Content-Type: application/json","Accept: application/json", "Authorization: Bearer API_KEY")
@GET("rooms")
suspend fun listOfRooms() :Response<ListRooms>

}