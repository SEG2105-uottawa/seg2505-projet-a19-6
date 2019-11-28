package com.example.applicationproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ClinicPage extends AppCompatActivity {

    TextView tvWelcome;
    Intent intent;
    String username, name;
    DatabaseReference reff;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clinic_page);

        tvWelcome = (TextView)findViewById(R.id.tvWelcome);

        intent = getIntent();
        username = intent.getStringExtra("USERNAME");

        reff = FirebaseDatabase.getInstance().getReference().child("Clinic").child(username);

        Toast.makeText(ClinicPage.this, username, Toast.LENGTH_LONG).show();



        reff.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                name = dataSnapshot.child("name").getValue().toString();
                tvWelcome.setText("Welcome, your are on " + name + "'s page!");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
