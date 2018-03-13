package com.example.kevin.chfmonitor;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.parse.ParseUser;

public class AdminHomeActivity extends AppCompatActivity {

    Button alogoutbtn,remdocbtn,rempatbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_home);

        alogoutbtn= (Button) findViewById(R.id.alogoutID);
        rempatbtn= (Button) findViewById(R.id.rempatID);
        remdocbtn= (Button) findViewById(R.id.remdocID);

        rempatbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(AdminHomeActivity.this, RemovePatientActivity.class);
                startActivity(i);
            }
        });

        remdocbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(AdminHomeActivity.this, RemoveDoctorActivity.class);
                startActivity(i);
            }
        });

        alogoutbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ParseUser.logOut();
                Intent i = new Intent(AdminHomeActivity.this, MainActivity.class);
                startActivity(i);
                finish();
            }
        });


    }
}
