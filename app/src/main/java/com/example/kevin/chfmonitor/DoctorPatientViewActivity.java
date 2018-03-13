package com.example.kevin.chfmonitor;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

public class DoctorPatientViewActivity extends AppCompatActivity {

    private static final String TAG = "DoctorPatientViewActivity";
    TextView nameTV,hdlTV,bpTV,ageTV,totalchlTV,riskscoreTV;

    PieChart pieChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_patient_view);

        String patientname = getIntent().getStringExtra("patname");

        pieChart = (PieChart) findViewById(R.id.chart);

        nameTV= (TextView) findViewById(R.id.nameID);
        hdlTV= (TextView) findViewById(R.id.hdlID);
        bpTV= (TextView) findViewById(R.id.bpID);
        ageTV= (TextView) findViewById(R.id.ageID);
        totalchlTV= (TextView) findViewById(R.id.totalchlID);
        riskscoreTV= (TextView) findViewById(R.id.riskscoreID);

        getobjid(patientname);

    }
    private void getobjid(String patientname) {

        ParseQuery<ParseObject> query = ParseQuery.getQuery("UserDetails");

        query.whereEqualTo("username", patientname);
        query.getFirstInBackground(new GetCallback<ParseObject>() {
            @Override
            public void done(ParseObject object, ParseException e) {


                String obj = object.getString("objId");
                getUserDetails(obj);

            }
        });
    }


    private void getUserDetails(String obj) {

        ParseQuery<ParseObject> query = ParseQuery.getQuery("UserDetails");
        query.getInBackground(obj, new GetCallback<ParseObject>() {

            @Override
            public void done(ParseObject obj, ParseException arg1) {

                int hdl = obj.getInt("hdlCholestrol");
                int age = obj.getInt("age");
                int bp = obj.getInt("bloodpressure");
                int tchl = obj.getInt("totalCholestrol");
                int risk=obj.getInt("riskScore");
                String username=obj.getString("username");
                displayInGraph(hdl, age,tchl, bp);
                displayPatientDetails(hdl,age,tchl,bp,risk,username);
            }

        });
    }

    private void displayPatientDetails(int hdl, int age, int tchl, int bp, int risk,String username) {

        String shdl=Integer.toString(hdl);
        String sbp=Integer.toString(bp);
        String sage=Integer.toString(age);
        String stchl=Integer.toString(tchl);
        String srisk=Integer.toString(risk);

        nameTV.setText("Name: ");
        hdlTV.setText("HDL Cholestrol: ");
        bpTV.setText("Blood pressure: ");
        ageTV.setText("Age: ");
        totalchlTV.setText("Total Cholestrol: ");
        riskscoreTV.setText("Risk Score: ");

        nameTV.append(username);
        hdlTV.append(shdl);
        bpTV.append(sbp);
        ageTV.append(sage);
        totalchlTV.append(stchl);
        riskscoreTV.append(srisk);

    }

    private void displayInGraph(int hdl, int age, int tchl,int bp) {
        List<PieEntry> entries = new ArrayList<>();

        entries.add(new PieEntry(hdl, "HDL Cholestrol"));
        entries.add(new PieEntry(age, "age"));
        entries.add(new PieEntry(bp, "Blood Pressure"));
        entries.add(new PieEntry(tchl, "Total Cholestrol"));

        PieDataSet set = new PieDataSet(entries, "Patient Data");

        set.setColors(ColorTemplate.COLORFUL_COLORS);
        PieData data = new PieData(set);
        pieChart.setData(data);
        pieChart.invalidate();

    }

}
