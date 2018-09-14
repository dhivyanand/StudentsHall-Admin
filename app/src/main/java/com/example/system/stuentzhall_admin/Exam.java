package com.example.system.stuentzhall_admin;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

public class Exam extends AppCompatActivity {

    EditText date;
    Button add;
    Calendar myCalendar;
    Spinner session;
    ListView list_date;

    private void populate_spinner(){

        ArrayList<String> array = new ArrayList<String>();
        array.add("FN");
        array.add("AN");

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, array);

        session.setAdapter(dataAdapter);

    }

    private void updateLabel() {
        String myFormat = "MM/dd/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.ENGLISH);

        date.setText(sdf.format(myCalendar.getTime()));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam);

        add = (Button)findViewById(R.id.add);
        date = (EditText)findViewById(R.id.date);
        session = (Spinner)findViewById(R.id.session);
        list_date = (ListView)findViewById(R.id.list_date);
        myCalendar = Calendar.getInstance();

        populate_spinner();

        AppFunctions.server_sync_exam(Exam.this,list_date);

        final DatePickerDialog.OnDateSetListener myDateListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker arg0, int arg1, int arg2, int arg3) {

                myCalendar.set(Calendar.YEAR, arg1);
                myCalendar.set(Calendar.MONTH, arg2);
                myCalendar.set(Calendar.DAY_OF_MONTH, arg3);
                updateLabel();

            }
        };

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(!date.getText().toString().equals(null)) {
                    AppFunctions.addDate(Exam.this, date.getText().toString()+" "+session.getSelectedItem().toString());
                    date.setText(null);
                    finish();
                    startActivity(getIntent());
                }

            }
        });

        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(Exam.this, myDateListener, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        list_date.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                startActivity(new Intent(Exam.this,hall.class).putExtra("session",adapterView.getItemAtPosition(i).toString()));

            }
        });

    }
}
