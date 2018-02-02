package baltamon.mx.myappprovider

import android.content.Context
import android.database.Cursor
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CursorAdapter
import android.widget.TextView

/**
 * Created by Baltazar Rodriguez on 30/01/2018.
 */
class FriendsCursorAdapter(context: Context, cursor: Cursor?, flags: Int):
        CursorAdapter(context, cursor, flags) {

    override fun newView(p0: Context?, p1: Cursor?, p2: ViewGroup?): View =
            LayoutInflater.from(p0).inflate(R.layout.friend_item_layout, p2, false)

    override fun bindView(p0: View?, p1: Context, p2: Cursor?) {
        val helper = DBOpenHelper(p1)
        val friendName = p2?.getString(p2.getColumnIndex(helper.FRIEND_NAME))
        val friendPhone = p2?.getString(p2.getColumnIndex(helper.FRIEND_PHONE))
        val nameTextView = p0?.findViewById<TextView>(R.id.tv_name)
        val phoneTextView = p0?.findViewById<TextView>(R.id.tv_phone)

        nameTextView?.text = friendName
        phoneTextView?.text = friendPhone
    }
}