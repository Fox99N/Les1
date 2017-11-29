package com.example.fox.startactivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

/**
 * Created by fox on 25.11.17.
 */

public class ChildActivity  extends MainActivity{

    private TextView showTextIntent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.child_activity);

        showTextIntent = (TextView)findViewById(R.id.tv_display);

        Intent thatStartThisActivity = getIntent();

        if (thatStartThisActivity.hasExtra(Intent.EXTRA_TEXT)) {

            String textEntered = thatStartThisActivity.getStringExtra(Intent.EXTRA_TEXT);

            showTextIntent.setText(textEntered);
        }
    }
}
