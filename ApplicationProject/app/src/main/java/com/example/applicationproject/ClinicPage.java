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

import java.text.SimpleDateFormat;
import java.util.Calendar;

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

        final Calendar calendar = Calendar.getInstance();
        SimpleDateFormat format1 = new SimpleDateFormat("yyyyMMdd");
        final int currentDate = Integer.parseInt(format1.format(calendar.getTime()));
        SimpleDateFormat format = new SimpleDateFormat("HHmm");
        final int currentTime = Integer.parseInt(format.format(calendar.getTime()));
        final int currentHour = Integer.parseInt(format.format(calendar.getTime()).substring(0,2));
        final int currentMinute = Integer.parseInt(format.format(calendar.getTime()).substring(2));


        reff.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                int waitTime = 0;
                int tempHour = currentHour;
                int tempMinute = currentMinute;
                for (DataSnapshot dsp : dataSnapshot.child("Booking").getChildren()){
                    int date = Integer.parseInt(dsp.getKey().substring(0,8));
                    int hour = Integer.parseInt(dsp.getKey().substring(8,10));
                    int minute = Integer.parseInt(dsp.getKey().substring(10));
                    if (currentDate == date){
                        if (tempMinute < 45){
                            if (tempHour == hour && minute >= tempMinute && minute <= (tempMinute+15)) {
                                    waitTime += 15 + (minute - tempMinute);

                            }
                            if(tempMinute >= 15){
                                if (tempHour == hour && (minute + 15) > tempMinute && (minute + 15) < (tempMinute +15)){
                                    waitTime += (minute + 15) - tempMinute;
                                }
                            } else if(tempHour == hour+1 && (minute + 15) > 60 && ((minute + 15)-60) > tempMinute && ((minute + 15)-60) < (tempMinute+15)){
                                waitTime += ((minute + 15)-60) - tempMinute;
                            }
                        } else{
                            if (tempHour == hour && (minute + 15) > tempMinute && (minute + 15) < (tempMinute+15) ){
                                waitTime += (minute + 15) - tempMinute;
                            } else if (tempHour == hour && minute >= tempMinute && minute <= (tempMinute+15)){
                                waitTime += 15 + (minute - tempMinute);
                            } else if (tempHour == hour-1 && ((tempMinute+15)>60 && minute < tempMinute && minute < (tempMinute + 15)-60)){
                                waitTime += (60-tempMinute) + minute + 15;
                                }
                            }
                        }
                        tempMinute += waitTime;
                    }
                tvTime.setText(waitTime + " minutes");
                }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }

        });





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


        btnRefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reff.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        int waitTime = 0;
                        int tempHour = currentHour;
                        int tempMinute = currentMinute;
                        for (DataSnapshot dsp : dataSnapshot.child("Booking").getChildren()){
                            int date = Integer.parseInt(dsp.getKey().substring(0,8));
                            int hour = Integer.parseInt(dsp.getKey().substring(8,10));
                            int minute = Integer.parseInt(dsp.getKey().substring(10));
                            if (currentDate == date){
                                if (tempMinute < 45){
                                    if(tempMinute >= 15){
                                        if (tempHour == hour && minute >= tempMinute && minute <= (tempMinute+15)) {
                                            waitTime += 15 + (minute - tempMinute);

                                        }
                                        if (tempHour == hour && (minute + 15) > tempMinute && (minute + 15) < (tempMinute +15)){
                                            waitTime += (minute + 15) - tempMinute;

                                        }
                                    }
                                }
                            }
                            tempMinute += waitTime;
                        }
                        tvTime.setText(waitTime + " minutes");
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });
    }

    public void openBooking(String user){
        Intent intent = new Intent(this, Booking.class);
        intent.putExtra("USERNAME", user );
        startActivity(intent);

    }
}
