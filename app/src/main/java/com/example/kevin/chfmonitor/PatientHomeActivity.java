package com.example.kevin.chfmonitor;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.parse.ParseUser;

public class PatientHomeActivity extends AppCompatActivity {

    Button updateprofbtn,plogoutbtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_home);


        ParseUser currentUser = ParseUser.getCurrentUser();

        updateprofbtn= (Button) findViewById(R.id.updateprofID);
        plogoutbtn= (Button) findViewById(R.id.plogoutID);

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

    }
}
