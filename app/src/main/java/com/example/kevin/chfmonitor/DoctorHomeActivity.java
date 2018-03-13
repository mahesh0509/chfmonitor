package com.example.kevin.chfmonitor;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.parse.FindCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;

public class DoctorHomeActivity extends AppCompatActivity {

    private static final String TAG = "DoctorHomeActivity";
    ListView plistLV;
    List<String> userlist;
    Button dlogoutbtn;

    ArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_home);

        Parse.initialize(this);
        plistLV= (ListView) findViewById(R.id.patientlistID);
        ParseQuery<ParseUser> query=ParseUser.getQuery();
        dlogoutbtn= (Button) findViewById(R.id.dlogoutID);


        userlist = new ArrayList<>();
        adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,userlist);

        query.findInBackground(new FindCallback<ParseUser>() {
            @Override
            public void done(List<ParseUser> objects, ParseException e) {
                if (objects.size()>0){

                    for (ParseUser user:objects){

                       String username = user.getUsername();
                       String userrole=user.getString("role");
                       if(userrole.equals("p"))
                            userlist.add(username);
                       else
                           continue;
                    }

                    plistLV.setAdapter(adapter);

                }

            }
        });

        dlogoutbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ParseUser.logOut();
                Intent i=new Intent(DoctorHomeActivity.this,MainActivity.class);
                startActivity(i);
                finish();
            }
        });

        plistLV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

             String patientname=adapterView.getItemAtPosition(i).toString();
             Intent ii=new Intent(DoctorHomeActivity.this,DoctorPatientViewActivity.class);
             ii.putExtra("patname",patientname);
                Log.i(TAG, "onItemClick: success"+patientname);
                startActivity(ii);

            }
        });

            }

    }
