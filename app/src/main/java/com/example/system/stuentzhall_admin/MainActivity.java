package com.example.system.stuentzhall_admin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button student , exam;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        student = (Button)findViewById(R.id.student);
        exam = (Button)findViewById(R.id.exam);

        student.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent student = new Intent(MainActivity.this,Student.class);
                startActivity(student);

            }
        });

        exam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent exam = new Intent(MainActivity.this,Exam.class);
                startActivity(exam);

            }
        });

    }
}
