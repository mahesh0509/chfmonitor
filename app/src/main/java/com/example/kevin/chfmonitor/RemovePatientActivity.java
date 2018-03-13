package com.example.kevin.chfmonitor;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.parse.FindCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;

public class RemovePatientActivity extends AppCompatActivity {

    ListView rplistLV;
    List<String> rpuserlist;

    ArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remove_patient);

        Parse.initialize(this);
        rplistLV= (ListView) findViewById(R.id.rempatlistID);
        ParseQuery<ParseUser> query=ParseUser.getQuery();

        rpuserlist = new ArrayList<>();
        adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,rpuserlist);

        query.findInBackground(new FindCallback<ParseUser>() {
            @Override
            public void done(List<ParseUser> objects, ParseException e) {
                if (objects.size()>0){

                    for (ParseUser user:objects){

                        String username = user.getUsername();
                        String userrole=user.getString("role");
                        if(userrole.equals("p"))
                            rpuserlist.add(username);
                        else
                            continue;
                    }

                    rplistLV.setAdapter(adapter);
                }
            }
        });

        rplistLV.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView <?> parentAdapter, View view, final int position,
                                    long id) {

                AlertDialog.Builder builder = new AlertDialog.Builder(RemovePatientActivity.this);
                builder.setCancelable(true);
                builder.setTitle("Warning");
                builder.setMessage("Delete selected Patient?");
                builder.setPositiveButton("Confirm",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });
                builder.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                    }
                });

                AlertDialog dialog = builder.create();
                dialog.show();

            }
        });
    }
}
