package baltamon.mx.myappprovider

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

/**
 * Created by Baltazar Rodriguez on 28/01/2018.
 */
class DBOpenHelper(context: Context) : SQLiteOpenHelper(context, "myappprovider.db", null, 1) {

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

    override fun onCreate(p0: SQLiteDatabase?) {
        p0?.execSQL(CREATE_TABLE)
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        p0?.execSQL("DROP TABLE IF EXISTS "+ TABLE_FRIENDS)
        onCreate(p0)
    }
}