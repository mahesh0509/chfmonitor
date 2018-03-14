package com.example.kevin.chfmonitor;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseInstallation;
import com.parse.ParsePush;
import com.parse.ParseQuery;
import com.parse.ParseUser;

public class MainActivity extends AppCompatActivity {


    EditText usernameET,passwordET;
    Button loginbtn,doctorbtn,patientbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Parse.initialize(this);
       /* ParseInstallation installation = ParseInstallation.getCurrentInstallation();
        installation.put("GCMSenderId", "1089144247359");
        installation.saveInBackground();*/


        usernameET= (EditText) findViewById(R.id.usernameID);
        passwordET= (EditText) findViewById(R.id.passwordID);
        doctorbtn= (Button) findViewById(R.id.iamdoctorID);
        patientbtn= (Button) findViewById(R.id.iampatientID);
        loginbtn= (Button) findViewById(R.id.loginID);
        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String susername=usernameET.getText().toString();
                String spassword=passwordET.getText().toString();

                ParseUser.logInInBackground(susername, spassword, new LogInCallback() {
                    @Override
                    public void done(ParseUser user, ParseException e) {

                        if(e==null) {

                            String userrole = user.getString("role");

                            switch (userrole) {
                                case "d": {
                                    Intent i = new Intent(MainActivity.this, DoctorHomeActivity.class);
                                    startActivity(i);
                                    finish();
                                    break;
                                }
                                case "p": {
                                    Intent i = new Intent(MainActivity.this, PatientHomeActivity.class);
                                    startActivity(i);
                                    finish();
                                    break;
                                }
                                default: {
                                    Intent i = new Intent(MainActivity.this, AdminHomeActivity.class);
                                    startActivity(i);
                                    finish();
                                    break;
                                }
                            }

                        }
                        else
                        {
                            Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
        patientbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(MainActivity.this,PatientRegisterActivity.class);
                startActivity(i);
            }


        });

        doctorbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(MainActivity.this,DoctorRegistrationActivity.class);
                startActivity(i);
            }

        });
}}
