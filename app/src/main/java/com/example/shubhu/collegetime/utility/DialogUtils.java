package com.example.shubhu.collegetime.utility;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.shubhu.practicedemo.R;


/**
 * @author Ranosys Technologies
 * Utility class for Dialog
 */
public class DialogUtils extends AlertDialog.Builder {

    public interface successCallback {
        void onClick();
    }

    public DialogUtils(Context context, int themeResId) {
        super(context, themeResId);
    }

    public static void getConfirmationDialog(Context context, String title, String text) {
        final android.support.v7.app.AlertDialog.Builder builder =
                new android.support.v7.app.AlertDialog.Builder(context);
        View informativeDialogView =
                ((AppCompatActivity) context).getLayoutInflater().inflate(R.layout.dialog_informative, null);
        TextView textTitle = (TextView) informativeDialogView.findViewById(R.id.alert_dialog_title_text_view);
        textTitle.setText(title);
        TextView textContent = (TextView) informativeDialogView.findViewById(R.id.alert_dialog_content_text_view);
        Button positiveButton = (Button) informativeDialogView.findViewById(R.id.positive_button);
        ImageView imageAlert = (ImageView) informativeDialogView.findViewById(R.id.image_alert);
        if (title.equalsIgnoreCase(context.getString(R.string.text_success))) {
            imageAlert.setImageResource(R.drawable.ic_right_alert);
        } else if (title.equalsIgnoreCase(context.getString(R.string.text_alert))) {
            imageAlert.setImageResource(R.drawable.ic_info_alert);
        } else {
            imageAlert.setImageResource(R.drawable.ic_error_alert);
        }
        textContent.setText(text);
        builder.setView(informativeDialogView);
        final android.support.v7.app.AlertDialog alertDialog = builder.create();
        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        builder.setCancelable(false);
        alertDialog.show();
        textContent.setText(text);
        positiveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });
    }

    public static void getConfirmationDialogWithCallback(Context context, String title, String text,
                                                         final successCallback successCallback) {
        final android.support.v7.app.AlertDialog.Builder builder =
                new android.support.v7.app.AlertDialog.Builder(context);
        View informativeDialogView =
                ((AppCompatActivity) context).getLayoutInflater().inflate(R.layout.dialog_informative, null);
        TextView textTitle = (TextView) informativeDialogView.findViewById(R.id.alert_dialog_title_text_view);
        textTitle.setText(title);
        TextView textContent = (TextView) informativeDialogView.findViewById(R.id.alert_dialog_content_text_view);
        Button positiveButton = (Button) informativeDialogView.findViewById(R.id.positive_button);
        textContent.setText(text);
        ImageView imageAlert = (ImageView) informativeDialogView.findViewById(R.id.image_alert);
        if (title.equalsIgnoreCase(context.getString(R.string.text_success))) {
            imageAlert.setImageResource(R.drawable.ic_right_alert);
        } else if (title.equalsIgnoreCase(context.getString(R.string.text_alert))) {
            imageAlert.setImageResource(R.drawable.ic_info_alert);
        } else {
            imageAlert.setImageResource(R.drawable.ic_error_alert);
        }
        builder.setView(informativeDialogView);
        builder.setCancelable(false);
        final android.support.v7.app.AlertDialog alertDialog = builder.create();
        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        positiveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
                successCallback.onClick();
            }
        });
        alertDialog.show();
    }

    public static void getConfrimDialogWithCallbackAndCancel(Context context, String title,
                                                             String text, final successCallback successCallback) {
        final android.support.v7.app.AlertDialog.Builder builder =
                new android.support.v7.app.AlertDialog.Builder(context);
        View informativeDialogView =
                ((AppCompatActivity) context).getLayoutInflater().inflate(R.layout.dialog_confirm_cancel, null);
        TextView textTitle = (TextView) informativeDialogView.findViewById(R.id.alert_dialog_title_text_view);
        textTitle.setText(title);
        TextView textContent = (TextView) informativeDialogView.findViewById(R.id.alert_dialog_content_text_view);
        Button positiveButton = (Button) informativeDialogView.findViewById(R.id.positive_button);
        Button negativeButton = (Button) informativeDialogView.findViewById(R.id.negative_button);
        textContent.setText(text);
        ImageView imageAlert = (ImageView) informativeDialogView.findViewById(R.id.image_alert);
        if (title.equalsIgnoreCase(context.getString(R.string.text_success))) {
            imageAlert.setImageResource(R.drawable.ic_right_alert);
        } else if (title.equalsIgnoreCase(context.getString(R.string.text_alert))) {
            imageAlert.setImageResource(R.drawable.ic_info_alert);
        } else {
            imageAlert.setImageResource(R.drawable.ic_error_alert);
        }
        builder.setView(informativeDialogView);
        builder.setCancelable(false);
        final android.support.v7.app.AlertDialog alertDialog = builder.create();
        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        positiveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
                successCallback.onClick();
            }
        });
        negativeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });

        alertDialog.show();
    }


}
