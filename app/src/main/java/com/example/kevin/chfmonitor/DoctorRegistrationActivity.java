package com.example.kevin.chfmonitor;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class DoctorRegistrationActivity extends AppCompatActivity {

    EditText nameET,usernameET,emailET,passwordET,rpasswordET;
    Button doctorregisterbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_registration);

        Parse.initialize(this);

        nameET= (EditText) findViewById(R.id.nameID);
        usernameET= (EditText) findViewById(R.id.usernameID);
        emailET= (EditText) findViewById(R.id.emailID);
        passwordET= (EditText) findViewById(R.id.passwordID);
        rpasswordET= (EditText) findViewById(R.id.rpasswordID);
        doctorregisterbtn= (Button) findViewById(R.id.doctorregisterID);

        doctorregisterbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String sname=nameET.getText().toString();
                String susername=usernameET.getText().toString();
                String semail=emailET.getText().toString();
                String spassword=passwordET.getText().toString();
                String srpassword=rpasswordET.getText().toString();

               ParseUser user=new ParseUser();
               user.setUsername(susername);
               if(!spassword.equals(srpassword))
                   Toast.makeText(DoctorRegistrationActivity.this,"Passwords does not match!",Toast.LENGTH_LONG).show();
               else
                    user.setPassword(spassword);
               user.setEmail(semail);
               user.put("role", "d");
               user.put("name",sname);

               user.signUpInBackground(new SignUpCallback() {
                   @Override
                   public void done(ParseException e) {

                       if(e==null)
                       {
                           finish();
                           Intent i=new Intent(DoctorRegistrationActivity.this,DoctorHomeActivity.class);
                           startActivity(i);

                       }
                       else
                       {
                           Toast.makeText(DoctorRegistrationActivity.this, "Registration Failed!", Toast.LENGTH_SHORT).show();
                       }
                   }
               });
            }
        });
    }
}
