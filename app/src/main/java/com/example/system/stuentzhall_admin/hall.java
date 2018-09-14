package com.example.system.stuentzhall_admin;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class hall extends AppCompatActivity {

    String session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hall);

        session = getIntent().getStringExtra("session");

    }
}
