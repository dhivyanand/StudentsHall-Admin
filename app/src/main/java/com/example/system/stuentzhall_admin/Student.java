package com.example.system.stuentzhall_admin;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;
import java.io.IOException;

import static android.R.drawable.ic_menu_upload_you_tube;

public class Student extends AppCompatActivity {

    Button add , barcode;
    Bitmap img;
    EditText name , reg;
    ImageView barcode_img;
    ListView list;

    public void clearViews(){
        name.setText(null);
        reg.setText(null);
        barcode_img.setImageResource(ic_menu_upload_you_tube);
        img = null;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);

        name = (EditText)findViewById(R.id.name);
        reg = (EditText)findViewById(R.id.reg);

        add = (Button)findViewById(R.id.add);
        barcode = (Button)findViewById(R.id.barcode);

        barcode_img = (ImageView)findViewById(R.id.barcode_img);

        list = (ListView)findViewById(R.id.list);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(!name.getText().toString().isEmpty() && !reg.getText().toString().isEmpty() && (img != null)) {
                    AppFunctions.add_student(Student.this, name.getText().toString(), reg.getText().toString(), img);
                    clearViews();
                    AppFunctions.populateList(Student.this,list);
                }
                else
                    Toast.makeText(Student.this, "Please fill the fields", Toast.LENGTH_SHORT).show();

            }
        });

        barcode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), 1);

            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1 && resultCode == RESULT_OK && data != null && data.getData() != null) {

            Uri uri = data.getData();

            try {
                img = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                barcode_img.setImageBitmap(img);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
}
