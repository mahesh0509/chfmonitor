package com.example.kevin.chfmonitor;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.parse.ParseUser;

public class PatientHomeActivity extends AppCompatActivity {

    Button updateprofbtn,plogoutbtn,docbtn,dailybtn,sensorbtn,vitalbtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_home);


        ParseUser currentUser = ParseUser.getCurrentUser();

        updateprofbtn= (Button) findViewById(R.id.updateprofID);
        plogoutbtn= (Button) findViewById(R.id.plogoutID);
        docbtn= (Button) findViewById(R.id.docID);
        dailybtn= (Button) findViewById(R.id.dailyID);
        sensorbtn= (Button) findViewById(R.id.sensorID);
        vitalbtn= (Button) findViewById(R.id.vitalID);

        updateprofbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(PatientHomeActivity.this,PatientUpdateActivity.class);
                startActivity(i);
            }
        });

        plogoutbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ParseUser.logOut();
                Intent i=new Intent(PatientHomeActivity.this,MainActivity.class);
                startActivity(i);
                finish();
            }
        });

        docbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(PatientHomeActivity.this,"Under Development",Toast.LENGTH_SHORT).show();
            }
        });

        dailybtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(PatientHomeActivity.this,"Under Development",Toast.LENGTH_SHORT).show();
            }
        });

        sensorbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(PatientHomeActivity.this,"Under Development",Toast.LENGTH_SHORT).show();
            }
        });

        vitalbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(PatientHomeActivity.this,"Under Development",Toast.LENGTH_SHORT).show();
            }
        });

    }
}
