package com.example.applicationproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class EmployeeWelcome extends AppCompatActivity {


    Button btnInfo, btnContinue, btnHours, btnLogOut;
    TextView tvWelcome;
    DatabaseReference reff;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_welcome);


        btnInfo = (Button)findViewById(R.id.btnInfo);
        btnContinue = (Button)findViewById(R.id.btnContinue);
        btnHours = (Button)findViewById(R.id.btnHours);
        tvWelcome = (TextView)findViewById(R.id.test);
        btnLogOut = (Button)findViewById(R.id.btnLogOut);
        reff = FirebaseDatabase.getInstance().getReference().child("Clinic").child(Login.username).child("Hours");


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

        reff.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                try {
                    dataSnapshot.child("OM").getValue().toString();
                } catch (NullPointerException NPE){reff.child("OM").setValue("0000");}
                try {
                    dataSnapshot.child("CM").getValue().toString();
                } catch (NullPointerException NPE){reff.child("CM").setValue("0000");}
                try {
                    dataSnapshot.child("OT").getValue().toString();
                } catch (NullPointerException NPE){reff.child("OT").setValue("0000");}
                try {
                    dataSnapshot.child("CT").getValue().toString();
                } catch (NullPointerException NPE){reff.child("CT").setValue("0000");}
                try {
                    dataSnapshot.child("OW").getValue().toString();
                } catch (NullPointerException NPE){reff.child("OW").setValue("0000");}
                try {
                    dataSnapshot.child("CW").getValue().toString();
                } catch (NullPointerException NPE){reff.child("CW").setValue("0000");}
                try {
                    dataSnapshot.child("OTh").getValue().toString();
                } catch (NullPointerException NPE){reff.child("OTh").setValue("0000");}
                try {
                    dataSnapshot.child("CTh").getValue().toString();
                } catch (NullPointerException NPE){reff.child("CTh").setValue("0000");}
                try {
                    dataSnapshot.child("OF").getValue().toString();
                } catch (NullPointerException NPE){reff.child("OF").setValue("0000");}
                try {
                    dataSnapshot.child("CF").getValue().toString();
                } catch (NullPointerException NPE){reff.child("CF").setValue("0000");}
                try {
                    dataSnapshot.child("OS").getValue().toString();
                } catch (NullPointerException NPE){reff.child("OS").setValue("0000");}
                try {
                    dataSnapshot.child("CS").getValue().toString();
                } catch (NullPointerException NPE){reff.child("CS").setValue("0000");}
                try {
                    dataSnapshot.child("OSu").getValue().toString();
                } catch (NullPointerException NPE){reff.child("OSu").setValue("0000");}
                try {
                    dataSnapshot.child("CSu").getValue().toString();
                } catch (NullPointerException NPE){reff.child("CSu").setValue("0000");}
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

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
