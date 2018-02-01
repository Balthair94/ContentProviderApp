package baltamon.mx.myappprovider

import android.content.Context
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.dialog_fragment_add_friend.*

/**
 * Created by Baltazar Rodriguez on 30/01/2018.
 */
class AddFriendFragment: DialogFragment() {

    var listener: FriendInterface? = null

    override fun onCreateView(inflater: LayoutInflater?,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? =
            inflater?.inflate(R.layout.dialog_fragment_add_friend, container, false)

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        btn_add.setOnClickListener {
            val friend = Friend(et_friend_name.text.toString(), et_friend_phone.text.toString())
            listener?.onFriendAdded(friend)
            dismiss()
        }
        btn_cancel.setOnClickListener { dismiss() }
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        listener = context as FriendInterface
    }
}