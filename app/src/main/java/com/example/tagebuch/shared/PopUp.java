package com.example.tagebuch.shared;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tagebuch.R;

public class PopUp {
    public static void mostrarPopUp(Context context, String mensaje){
        Dialog dialog = new Dialog(context);
        // Changing basic dialog
        dialog.setContentView(R.layout.pop_up);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        // Getting text views from dialog
        TextView alertMessage = (TextView) dialog.findViewById(R.id.pop_up_text);

        // Setting custom messages
        alertMessage.setText(mensaje);

        ImageView imageViewClose = dialog.findViewById(R.id.close_pop_up);

        imageViewClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
//              Toast.makeText(HomeEntryActivity.this, "Dialog close", Toast.LENGTH_SHORT).show();
            }
        });

        dialog.show();
    }
}
