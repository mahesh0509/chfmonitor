package com.example.kevin.chfmonitor;

import android.content.Intent;
import android.util.Log;

import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

/**
 * Created by KEVIN on 3/1/2018.
 */

public class Riskscore {


    private static final String TAG = "Riskscore";

    public Riskscore() {
    }

    public int age(int age)
    {

        if(age>19 && age<35)
            return -9;
        else if (age>34 && age<40)
            return -4;
        else if (age>39 && age<45)
            return 0;
        else if (age>44 && age<50)
            return 3;
        else if (age>49 && age<55)
            return 6;
        else if (age>54 && age<60)
            return 8;
        else if (age>59 && age<65)
            return 10;
        else if (age>64 && age<70)
            return 12;
        else if (age>69 && age<75)
            return 14;
        else if (age>74 && age<80)
            return 16;
        else
            return 0;

    }

    public int totalCholestrol(int age, int chl)
    {

        if (chl<160)
            return 0;
        else if (chl>159 && chl<200)
        {
            if(age>19 && age<40)
                return 4;
            else if (age>39 && age<40)
                return 3;
            else if (age>49 && age<60)
                return 2;
            else if (age>59 && age<70)
                return 1;
            else if (age>69 && age<80)
                return 0;
            else
                return 0;
        }
        else if (chl>199 && chl<240)
        {
            if(age>19 && age<40)
                return 7;
            else if (age>39 && age<40)
                return 5;
            else if (age>49 && age<60)
                return 3;
            else if (age>59 && age<70)
                return 1;
            else if (age>69 && age<80)
                return 0;
            else
                return 0;
        }
        else if (chl>239 && chl<280)
        {
            if(age>19 && age<40)
                return 9;
            else if (age>39 && age<40)
                return 6;
            else if (age>49 && age<60)
                return 4;
            else if (age>59 && age<70)
                return 2;
            else if (age>69 && age<80)
                return 1;
            else
                return 0;
        }
        else if (chl>279)
        {
            if(age>19 && age<40)
                return 11;
            else if (age>39 && age<40)
                return 8;
            else if (age>49 && age<60)
                return 5;
            else if (age>59 && age<70)
                return 3;
            else if (age>69 && age<80)
                return 1;
            else
                return 0;
        }
        else
            return 0;
    }

    public int hdlCholestrol(int hdl)
    {
        if (hdl<41)
            return 2;
        else if (hdl>39 && hdl<50)
            return 1;
        else if (hdl>49 && hdl<60)
            return 0;
        else if (hdl>60)
            return -1;
        else
            return 0;
    }

    public int systolicBlood(int sbp)
    {
        if(sbp<121)
            return 0;
        else if (sbp>119 && sbp<130)
            return 1;
        else if (sbp>129 && sbp<140)
            return 2;
        else if (sbp>139 && sbp<160)
            return 2;
        else if (sbp>160)
            return 3;
        else
            return 0;
    }

//    public int smoker(int age)
//    {
//        if(age>19 && age<40)
//            return 8;
//        else if (age>39 && age<40)
//            return 5;
//        else if (age>49 && age<60)
//            return 3;
//        else if (age>59 && age<70)
//            return 1;
//        else if (age>69 && age<80)
//            return 1;
//        else
//            return 0;
//    }

    public int scoreCalculation(int age,int sbp,int hdl,int chl)
    {
        int score;
        score=age(age)+totalCholestrol(age,chl)+hdlCholestrol(hdl)+systolicBlood(sbp);

        Log.i(TAG, "scoreCalculation: score" +score);

        return score;
    }


    public void getUserDetails(String username, final String obj)
    {
        ParseQuery<ParseObject> query=ParseQuery.getQuery("UserDetails");



        query.whereEqualTo("username",username);
        query.getFirstInBackground(new GetCallback<ParseObject>() {
            @Override
            public void done(ParseObject object, ParseException e) {

                int age=object.getInt("age");
                int sbp=object.getInt("bloodpressure");
                int hdl=object.getInt("hdlCholestrol");
                int chl=object.getInt("totalCholestrol");

                Log.i(TAG, "done: successful"+age);

                 int score=scoreCalculation(age,sbp,hdl,chl);
                 updateScore(score,obj);
            }
        });


    }

    public void updateScore(final int score, String obj)
    {
        ParseQuery<ParseObject> query = ParseQuery.getQuery("UserDetails");


// Retrieve the object by id
        query.getInBackground(obj, new GetCallback<ParseObject>() {
            public void done(ParseObject object, ParseException e) {

                object.put("riskScore", score);
                object.saveInBackground();

                Log.i(TAG, "done: Successful");
            }
        });
    }



}
