package baltamon.mx.myappprovider

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

/**
 * Created by Baltazar Rodriguez on 28/01/2018.
 */
class DBOpenHelper(context: Context) : SQLiteOpenHelper(context, "myappprovider.db", null, 1) {

    override fun onCreate(p0: SQLiteDatabase?) {
        p0?.execSQL(CREATE_TABLE)
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        p0?.execSQL("DROP TABLE IF EXISTS " + TABLE_FRIENDS)
        onCreate(p0)
    }
}