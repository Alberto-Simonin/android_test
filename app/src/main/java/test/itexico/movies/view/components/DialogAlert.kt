package test.itexico.movies.view.components

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.os.Build

object DialogAlert {

    fun show(context: Context, title: String, message: String, positiveListener: DialogInterface.OnClickListener) {
        val builder: AlertDialog.Builder
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            builder = AlertDialog.Builder(context, android.R.style.Theme_Material_Dialog_Alert)
        } else {
            builder = AlertDialog.Builder(context)
        }
        builder.setTitle(title)
                .setMessage(message)
                .setPositiveButton(android.R.string.yes, positiveListener)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show()
    }
}
