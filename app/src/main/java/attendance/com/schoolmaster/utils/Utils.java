package attendance.com.schoolmaster.utils;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.widget.ArrayAdapter;

import java.util.List;

import attendance.com.schoolmaster.R;
import attendance.com.schoolmaster.interfaces.DialogClickInterface;
import attendance.com.schoolmaster.interfaces.PopupListCallback;

public final class Utils {
    
    public static void showSpinnerPopup(final Context mContext, final String title,
                                        final List<String> mList,
                                        final PopupListCallback callback) {
        final AlertDialog.Builder builderSingle = new AlertDialog.Builder(mContext);
        builderSingle.setTitle(title);

        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(mContext,
                android.R.layout.select_dialog_item,
                mList);
        builderSingle.setNegativeButton(R.string.btn_cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        builderSingle.setAdapter(arrayAdapter, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                final String strName = arrayAdapter.getItem(which);
                if (callback != null) {
                    callback.getSelectedItemValue(strName);
                    callback.getSelectedPosition(which);
                }
                dialog.dismiss();
            }
        });
        builderSingle.show();
    }


    public static void showErrorDialog(final Context context,
                                       final String message, final DialogClickInterface listener) {
        if (context != null) {
            final AlertDialog dialog = new AlertDialog.Builder(context, R.style.CustomDialogTheme).create();
            dialog.setMessage(message);
            dialog.setButton(DialogInterface.BUTTON_POSITIVE, context.getResources().getString(R.string.btn_ok), new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    if (listener != null) {
                        listener.onDialogNeutralClick();
                    }
                    dialog.dismiss();
                }
            });
            dialog.setCanceledOnTouchOutside(false);
            dialog.setCancelable(false);
            dialog.show();
        }
    }
}
