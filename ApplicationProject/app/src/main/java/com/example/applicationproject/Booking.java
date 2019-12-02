package com.example.applicationproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class Booking extends AppCompatActivity {

    Button btnConfirm;
    TextView tvDate, tvSelectTime, tvTime;
    CalendarView cvDate;

    Intent intent;
    String username, date;
    int time, dayChosen;


    DatabaseReference reff;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);

        //Gives current time and date
        final Calendar calendar = Calendar.getInstance();
        SimpleDateFormat format1 = new SimpleDateFormat("yyyyMMdd");
        final String currentDate = format1.format(calendar.getTime());
        SimpleDateFormat format = new SimpleDateFormat("HHmm");
        final String currentTime = format.format(calendar.getTime());

        btnConfirm = (Button)findViewById(R.id.btnConfirm);
        tvDate = (TextView)findViewById(R.id.tvDate);
        tvSelectTime = (TextView)findViewById(R.id.tvSelectTime);
        tvTime = (TextView)findViewById(R.id.tvTime);
        cvDate = (CalendarView)findViewById(R.id.cvDate);

        intent = getIntent();
        username = intent.getStringExtra("USERNAME");


        reff = FirebaseDatabase.getInstance().getReference().child("Clinic").child(username);

        cvDate.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                date = Integer.toString(year) + Integer.toString(month+1) + Integer.toString(dayOfMonth);
                dayChosen = calendar.get(Calendar.DAY_OF_WEEK);
            }
        });

        tvTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerDialog timePickerDialog = new TimePickerDialog(Booking.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int hourOfDay, int minutes) {
                        String x = Integer.toString(hourOfDay) + Integer.toString(minutes);

                        if (hourOfDay < 10){
                            if (minutes < 10){
                                tvTime.setText("0" + hourOfDay + ":0" + minutes);
                                time = Integer.parseInt(x + "0");
                            } else {
                                tvTime.setText("0" + hourOfDay + ":" + minutes);
                                time = Integer.parseInt(x);
                            }
                        } else {
                            if (minutes < 10){
                                tvTime.setText(hourOfDay + ":0" + minutes);
                                time = Integer.parseInt(x + "0");
                            } else {
                                tvTime.setText(hourOfDay + ":" + minutes);
                                time = Integer.parseInt(x);
                            }
                        }


                    }
                }, 0, 0, true);
                timePickerDialog.show();
            }
        });



        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reff.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        int flag = 0;
                        if (dayChosen == 0){
                            if(dataSnapshot.child("Hours").child("OSu").getValue().toString().equals("Closed") == false && time >= Integer.parseInt(dataSnapshot.child("Hours").child("OSu").getValue().toString()) && time <= Integer.parseInt(dataSnapshot.child("Hours").child("CSu").getValue().toString())){
                                flag = 1;
                            }
                        }
                        if (dayChosen == 1){
                            if(dataSnapshot.child("Hours").child("OM").getValue().toString().equals("Closed") == false && time >= Integer.parseInt(dataSnapshot.child("Hours").child("OM").getValue().toString()) && time <= Integer.parseInt(dataSnapshot.child("Hours").child("CM").getValue().toString())){
                                flag = 1;
                            }
                        }
                        if (dayChosen == 2){
                            if(dataSnapshot.child("Hours").child("OT").getValue().toString().equals("Closed") == false && time >= Integer.parseInt(dataSnapshot.child("Hours").child("OT").getValue().toString()) && time <= Integer.parseInt(dataSnapshot.child("Hours").child("CT").getValue().toString())){
                                flag = 1;
                            }
                        }
                        if (dayChosen == 3){
                            if(dataSnapshot.child("Hours").child("OW").getValue().toString().equals("Closed") == false && time >= Integer.parseInt(dataSnapshot.child("Hours").child("OW").getValue().toString()) && time <= Integer.parseInt(dataSnapshot.child("Hours").child("Hours").child("CW").getValue().toString())){
                                flag = 1;
                            }
                        }
                        if (dayChosen == 4){
                            if(dataSnapshot.child("Hours").child("OTh").getValue().toString().equals("Closed") == false && time >= Integer.parseInt(dataSnapshot.child("Hours").child("OTh").getValue().toString()) && time <= Integer.parseInt(dataSnapshot.child("Hours").child("CTh").getValue().toString())){
                                flag = 1;
                            }
                        }
                        if (dayChosen == 5){
                            if(dataSnapshot.child("Hours").child("OF").getValue().toString().equals("Closed") == false && time >= Integer.parseInt(dataSnapshot.child("Hours").child("OF").getValue().toString()) && time <= Integer.parseInt(dataSnapshot.child("Hours").child("CF").getValue().toString())){
                                flag = 1;
                            }
                        }
                        if (dayChosen == 6){
                            if(dataSnapshot.child("Hours").child("OS").getValue().toString().equals("Closed") == false && time >= Integer.parseInt(dataSnapshot.child("Hours").child("OS").getValue().toString()) && time <= Integer.parseInt(dataSnapshot.child("Hours").child("CS").getValue().toString())){
                                flag = 1;
                            }
                        }

                        if (flag == 0){
                            Toast.makeText(Booking.this, "The clinic is closed at this time",Toast.LENGTH_LONG).show();
                            return;
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

                try {
                    if ((Integer.parseInt(currentDate) < Integer.parseInt(date)) || (Integer.parseInt(currentDate) == Integer.parseInt(date) && Integer.parseInt(currentTime) <= time)) {


                        reff.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                int flag = 0;
                                for (DataSnapshot dsp : dataSnapshot.child("Booking").getChildren()) {
                                    int minute = Integer.parseInt((date + time).substring(10));
                                    int fireMinute = Integer.parseInt(dsp.getKey().substring(10));
                                    int fireMinutePlusFifteen = Integer.parseInt(dsp.getKey().substring(10)) + 15;
                                    String choseDate = (date + time).substring(0, 10);
                                    String fireDate = dsp.getKey().substring(0, 10);
                                    if (fireMinutePlusFifteen >= 60) {
                                        if (choseDate.equals(fireDate) && minute >= 45) {
                                            Toast.makeText(Booking.this, "This time is already taken", Toast.LENGTH_LONG).show();


                                            flag = 1;
                                            break;
                                        }
                                    } else if ((minute >= fireMinute) && (choseDate.equals(fireDate)) && (minute <= fireMinutePlusFifteen)) {
                                        Toast.makeText(Booking.this, "This time is already taken", Toast.LENGTH_LONG).show();

                                        flag = 1;
                                        break;
                                    }
                                }
                                if (flag == 0) {
                                    reff.child("Booking").child(date + time).setValue(date + time + Login.username);
                                    Toast.makeText(Booking.this, "Booking made", Toast.LENGTH_LONG).show();
                                    finish();
                                }

                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });
                    } else {
                        Toast.makeText(Booking.this, "You cannot book in the past", Toast.LENGTH_LONG).show();
                    }
                } catch (Exception e){
                    Toast.makeText(Booking.this, "Be sure to enter a date and a time", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
