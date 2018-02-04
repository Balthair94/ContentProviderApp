package baltamon.mx.myappprovider

import android.app.LoaderManager
import android.content.ContentValues
import android.content.CursorLoader
import android.database.Cursor
import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.toolbar_layout.*

class MainActivity : AppCompatActivity(), LoaderManager.LoaderCallbacks<Cursor>, FriendInterface{

    var cursorAdapter: FriendsCursorAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupToolbar()

        cursorAdapter = FriendsCursorAdapter(this, null, 0)
        lv_friends_list.adapter = cursorAdapter
        restartLoader()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.my_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item?.itemId){
            R.id.add_friend -> showAddFriendDialog()
            R.id.delete_friend -> showToast("Delete")
            R.id.update_friend -> showToast("Update")
        }
        return super.onOptionsItemSelected(item)
    }

    fun showAddFriendDialog(){
        val dialog = AddFriendFragment()
        dialog.show(supportFragmentManager, "fragment_add")
    }

    fun setupToolbar(){
        toolbar.setTitleTextColor(Color.WHITE)
        setSupportActionBar(toolbar)
        supportActionBar?.title = "My Friends"
    }

    fun restartLoader() = loaderManager.restartLoader(0, null, this)


    override fun onLoadFinished(p0: android.content.Loader<Cursor>?, p1: Cursor?) {
        cursorAdapter?.swapCursor(p1)
    }

    override fun onLoaderReset(p0: android.content.Loader<Cursor>?) {
        cursorAdapter?.swapCursor(null)
    }

    override fun onCreateLoader(p0: Int, p1: Bundle?): android.content.Loader<Cursor> =
            CursorLoader(this, CONTENT_URI, null, null, null, null)

    fun showToast(message: String){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun onFriendAdded(friend: Friend) {
        val values = ContentValues()
        values.put(FRIEND_NAME, friend.name)
        values.put(FRIEND_PHONE, friend.phone)
        contentResolver.insert(CONTENT_URI, values)
        restartLoader()
        showToast(friend.name + " added")
    }
}
