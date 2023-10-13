package com.example.petspace;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

public class About extends Activity {

    private ImageView mIconback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        mIconback = findViewById(R.id.IconBack);

        mIconback.setOnClickListener(view -> {
            onBackPressed();
        });
    }
}