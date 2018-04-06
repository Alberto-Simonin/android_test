package test.itexico.movies.view;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Build;

public class DialogAlert  {

    private DialogAlert(){}

    public static void show(final Context context, String title, String message, DialogInterface.OnClickListener positiveListener){
        AlertDialog.Builder builder;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            builder = new AlertDialog.Builder(context, android.R.style.Theme_Material_Dialog_Alert);
        } else {
            builder = new AlertDialog.Builder(context);
        }
        builder.setTitle(title)
                .setMessage(message)
                .setPositiveButton(android.R.string.yes, positiveListener)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }
}
