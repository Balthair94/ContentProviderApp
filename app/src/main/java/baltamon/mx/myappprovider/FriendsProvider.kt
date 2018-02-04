package baltamon.mx.myappprovider

import android.content.ContentProvider
import android.content.ContentUris
import android.content.ContentValues
import android.content.UriMatcher
import android.database.Cursor
import android.database.SQLException
import android.database.sqlite.SQLiteDatabase
import android.net.Uri

/**
 * Created by Baltazar Rodriguez on 29/01/2018.
 */
class FriendsProvider : ContentProvider() {

    val FRIENDS = 1
    val FRIEND_ID = 2

    val uriMatcher = UriMatcher(UriMatcher.NO_MATCH)

    init {
        uriMatcher.addURI(AUTHORITY, BASE_PATH, FRIENDS)
        uriMatcher.addURI(AUTHORITY, BASE_PATH + "/#", FRIEND_ID)
    }

    var database: SQLiteDatabase? = null
    var helper: DBOpenHelper? = null

    override fun onCreate(): Boolean {
        helper = DBOpenHelper(context)
        database = helper?.writableDatabase
        return true
    }

    override fun query(p0: Uri?, p1: Array<out String>?, p2: String?, p3: Array<out String>?, p4: String?): Cursor {
        val cursor: Cursor
        when(uriMatcher.match(p0)){
            FRIENDS -> cursor = database?.query(TABLE_FRIENDS,
                    ALL_COLUMNS, p2, null, null, null,
                    FRIEND_NAME + " ASC") ?: throw IllegalArgumentException("This is an Unknown URI " + p0)
            else -> throw IllegalArgumentException("This is an Unknown URI " + p0)
        }
        return cursor
    }

    override fun insert(p0: Uri?, p1: ContentValues?): Uri {
        val id = database?.insert(TABLE_FRIENDS, null, p1) ?: 0
        if (id > 0){
            val _uri = ContentUris.withAppendedId(CONTENT_URI, id)
            context.contentResolver.notifyChange(_uri, null)
            return _uri
        } else
            throw SQLException("Insertion Failed for URI :" + p0)
    }

    override fun update(p0: Uri?, p1: ContentValues?, p2: String?, p3: Array<out String>?): Int {
        val updCount: Int
        when(uriMatcher.match(p0)){
            FRIENDS -> updCount = database?.update(TABLE_FRIENDS, p1, p2, p3) ?: 0
            else -> throw IllegalArgumentException("This is an Unknown URI " + p0)
        }
        return updCount
    }

    override fun delete(p0: Uri?, p1: String?, p2: Array<out String>?): Int {
        val delCount: Int
        when(uriMatcher.match(p0)){
            FRIENDS -> delCount = database?.delete(TABLE_FRIENDS, p1, p2) ?: 0
            else -> throw IllegalArgumentException("This is an Unknown URI " + p0)
        }
        context.contentResolver.notifyChange(p0, null)
        return delCount
    }

    override fun getType(p0: Uri?): String {
        when(uriMatcher.match(p0)){
            FRIENDS -> return "vnd.android.cursor.dir/friends"
            else -> throw IllegalArgumentException("This is an Unknown URI " + p0)
        }
    }
}