package com.example.kevin.chfmonitor;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.GetCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;
import com.parse.SignUpCallback;

public class PatientRegisterActivity extends AppCompatActivity {

    private static final String TAG = "PatientRegisterActivity";

    ParseObject userProfile;

    EditText nameET,emailET,usernameET,passwordET,rpasswordET,numberET;
    Button patientregisterbtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_register);

        //Parse.initialize(this);

        nameET= (EditText) findViewById(R.id.nameID);
        emailET= (EditText) findViewById(R.id.emailID);
        usernameET= (EditText) findViewById(R.id.usernameID);
        numberET= (EditText) findViewById(R.id.numberID);
        passwordET= (EditText) findViewById(R.id.passwordID);
        rpasswordET= (EditText) findViewById(R.id.retypepasswordID);
        patientregisterbtn= (Button) findViewById(R.id.patientregisterID);

        patientregisterbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                userRegistration();

            }

        });


    }


    private void userRegistration(){

        String sname = nameET.getText().toString();
        String semail = emailET.getText().toString();
        final String susername = usernameET.getText().toString();
        String snumber = numberET.getText().toString();
        String spassword = passwordET.getText().toString();
        String srpassword = rpasswordET.getText().toString();

        ParseUser user = new ParseUser();
        user.setUsername(susername);
        if (!spassword.equals(srpassword))
            Toast.makeText(PatientRegisterActivity.this, "Passwords does not match!", Toast.LENGTH_LONG).show();
        else {
            user.setPassword(spassword);
            user.setEmail(semail);
            user.put("name", sname);
            user.put("phone", snumber);
            user.put("role", "p");
        }

        user.signUpInBackground(new SignUpCallback() {
            @Override
            public void done(ParseException e) {
                if (e==null){

                    userDetails(susername);

                    Intent i=new Intent(PatientRegisterActivity.this,PatientHomeActivity.class);
                    startActivity(i);
                }
                else
                {
                    Toast.makeText(PatientRegisterActivity.this,"Registration failed!",Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

    private void userDetails(String username) {

        userProfile=new ParseObject("UserDetails");
        userProfile.put("age",40);
        userProfile.put("hdlCholestrol",50);
        userProfile.put("bloodpressure",100);
        userProfile.put("totalCholestrol",120);
        userProfile.put("riskScore",2);
        //userProfile.put("smoker",false);
        userProfile.put("username",username);
        userProfile.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                if(e==null)
                {
                    Toast.makeText(PatientRegisterActivity.this, "Success", Toast.LENGTH_SHORT).show();

                   String objid =  userProfile.getObjectId();

                    Log.i(TAG, "done: "+objid);

                    saveobjid(objid);

                }
                else
                    Toast.makeText(PatientRegisterActivity.this, "Failed!", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void saveobjid(final String objid)
    {

        ParseQuery<ParseObject> query=ParseQuery.getQuery("UserDetails");

        query.getInBackground(objid, new GetCallback<ParseObject>() {
            @Override
            public void done(ParseObject userProfile, ParseException e) {
                if (e==null){

                    userProfile.put("objId",objid);
                    userProfile.saveInBackground();
                }
                else{

                    Toast.makeText(PatientRegisterActivity.this, "failed!!", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }


}
