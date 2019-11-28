package com.example.applicationproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ClinicPage extends AppCompatActivity {

    TextView tvWelcome, tvCurrent, tvTime;
    Button btnBook, btnRefresh;
    Intent intent;
    String username, name;
    DatabaseReference reff;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clinic_page);

        tvWelcome = (TextView)findViewById(R.id.tvWelcome);
        tvCurrent = (TextView)findViewById(R.id.tvCurrent);
        tvTime = (TextView)findViewById(R.id.tvTime);
        btnRefresh = (Button)findViewById(R.id.btnRefresh);
        btnBook = (Button)findViewById(R.id.btnBook);

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


        btnBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openBooking(username);
            }
        });
    }

    public void openBooking(String user){
        Intent intent = new Intent(this, Booking.class);
        intent.putExtra("USERNAME", user );
        startActivity(intent);

    }
}
