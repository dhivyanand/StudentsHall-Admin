package com.example.system.stuentzhall_admin;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class hall extends AppCompatActivity {

    Button add;
    EditText hall;
    ListView list;
    String session;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hall);

        add = (Button)findViewById(R.id.add);
        hall = (EditText)findViewById(R.id.hall);
        list = (ListView)findViewById(R.id.hall_list);

        session = getIntent().getStringExtra("session");

    }
}
