package baltamon.mx.myappprovider

import android.net.Uri

/**
 * Created by Baltazar Rodriguez on 04/02/2018.
 */
//TABLE AND COLUMNS
val TABLE_FRIENDS = "friends"
val FRIEND_ID = "_id"
val FRIEND_NAME = "friendName"
val FRIEND_PHONE = "friendPhone"
val FRIEND_CREATED_ON = "friendCreationOn"

val ALL_COLUMNS = arrayOf(FRIEND_ID, FRIEND_NAME, FRIEND_PHONE, FRIEND_CREATED_ON)

//CREATE TABLE
val CREATE_TABLE = "CREATE TABLE " + TABLE_FRIENDS + " (" +
        FRIEND_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
        FRIEND_NAME + " TEXT, " +
        FRIEND_PHONE + " TEXT, " +
        FRIEND_CREATED_ON + " TEXT default CURRENT_TIMESTAMP" +
        ")"

val AUTHORITY = "baltamon.mx.myappprovider" // You can find this in the Manifest
val BASE_PATH = "friends" // Table name
val CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/" + BASE_PATH)