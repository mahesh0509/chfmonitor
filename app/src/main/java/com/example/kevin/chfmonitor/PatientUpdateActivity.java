package com.example.kevin.chfmonitor;

import android.app.DownloadManager;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.List;

public class PatientUpdateActivity extends AppCompatActivity {

    private static final String TAG = "PatientUpdateActivity";

    EditText ageET,hdlET,bloodpressureET,totalchlET;
    Button savebtn;
    Riskscore risk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_update);

         risk=new Riskscore();

        ParseUser currentUser = ParseUser.getCurrentUser();

        final String username=currentUser.getUsername();
        Log.i(TAG, "onCreate: " +username);


        ageET= (EditText) findViewById(R.id.ageID);
        hdlET= (EditText) findViewById(R.id.hdlID);
        bloodpressureET= (EditText) findViewById(R.id.bloodpressureID);
        totalchlET= (EditText) findViewById(R.id.totalchlID);
        savebtn= (Button) findViewById(R.id.saveID);



        savebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                getobjid(username);

            }
        });

    }

    private void getobjid(final String username) {

        ParseQuery<ParseObject> query=ParseQuery.getQuery("UserDetails");

        query.whereEqualTo("username",username);
        query.getFirstInBackground(new GetCallback<ParseObject>() {
            @Override
            public void done(ParseObject object, ParseException e) {


                    String obj=object.getString("objId");
                    Log.i(TAG, "done: "+obj);
                    updateUser(obj,username);
            }
        });
    }


    private void updateUser(final String obj,final String username) {

        ParseQuery<ParseObject> query = ParseQuery.getQuery("UserDetails");

        final int ag= Integer.parseInt(ageET.getText().toString());
        final int hd= Integer.parseInt(hdlET.getText().toString());
        final int bp= Integer.parseInt(bloodpressureET.getText().toString());
        final int tc= Integer.parseInt(totalchlET.getText().toString());


// Retrieve the object by id
        query.getInBackground(obj, new GetCallback<ParseObject>() {
            public void done(ParseObject object, ParseException e) {

                    object.put("age", ag);
                    object.put("hdlCholestrol", hd);
                    object.put("bloodpressure", bp);
                    object.put("totalCholestrol", tc);
                    object.saveInBackground();

                    risk.getUserDetails(username,obj);

                    Log.i(TAG, "done: Successful");
                }
        });
    }


}
