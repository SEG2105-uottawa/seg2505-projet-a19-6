package com.example.applicationproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class EmployeeWelcome extends AppCompatActivity {


    Button btnInfo, btnContinue, btnHours, btnLogOut;
    TextView tvWelcome;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_welcome);


        btnInfo = (Button)findViewById(R.id.btnInfo);
        btnContinue = (Button)findViewById(R.id.btnContinue);
        btnHours = (Button)findViewById(R.id.btnHours);
        tvWelcome = (TextView)findViewById(R.id.test);
        btnLogOut = (Button)findViewById(R.id.btnLogOut);


        tvWelcome.setText("Welcome " + Login.name + ". You are registered as an employee!");

        btnInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openEmployeeInfo();
            }
        });

        btnContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openEmployeeServices();
            }
        });

        btnHours.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openEmployeeHours();
            }
        });



        //OnClickListener for Log Out
        btnLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openLogin();

            }
        });

    }

    public void openEmployeeInfo(){
        startActivity(new Intent(this, EmployeeInfo.class));
    }

    public void openEmployeeServices(){
        startActivity(new Intent(this, EmployeeServices.class));
    }

    public void openEmployeeHours(){
        startActivity(new Intent(this, EmployeeHours.class));
    }

    public void openLogin(){
        startActivity(new Intent(this, Login.class));
        finish();
    }


}
