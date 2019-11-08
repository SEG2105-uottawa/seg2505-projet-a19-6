/* ****************************
TEMPORARY CLIENT EMPLOYEE CLASS
 *******************************/

package com.example.applicationproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class Welcome extends AppCompatActivity{


    TextView tvWelcome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        tvWelcome = (TextView) findViewById(R.id.tvWelcome);


        if (Login.identifier.equals("1")) {
            tvWelcome.setText("Welcome " + Login.name + ". You are registered as a Client!");
        } else if (Login.identifier.equals("2")) {
            tvWelcome.setText("Welcome " + Login.name + ". You are registered as an employee!");
        }
    }





}
