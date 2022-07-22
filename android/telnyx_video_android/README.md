# Create a Room and view all the Rooms created 

![Telnyx](https://github.com/team-telnyx/devrel/blob/main/assets/img/logo-dark.png?raw=true)

The Android project lets you create a Telnyx Video Room and view all the created Rooms using Kotlin.

## Prerequisites

- [Android Studio]()
- Telnyx Developer Account (https://developers.telnyx.com/)

## Steps 

#### Project Download
Download the project and open it in Android Studio

#### Add API_KEY
Replace  the `API_KEY` value with your own API Key acquired from Telnyx Portal in `ApiInterface.kt` file

```
import ...

interface ApiInterface {

//replace API_KEY with yours from Telnyx Portal
@Headers("Content-Type: application/json","Accept: application/json", "Authorization: Bearer API_KEY")
@POST("rooms")
suspend fun createRoom(@Body requestBody: RequestBody) : Response<CreateRoom>

@Headers("Content-Type: application/json","Accept: application/json", "Authorization: Bearer API_KEY")
@GET("rooms")
suspend fun listOfRooms() :Response<ListRooms>

}
```

#### Run the project!
Use the emulator or your own Android device to run this project



