package com.example.applicationproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.TextView;
import android.widget.TimePicker;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Booking extends AppCompatActivity {

    Button btnConfirm;
    TextView tvDate, tvSelectTime, tvTime;
    CalendarView cvDate;

    Intent intent;
    String username, date;
    int time;

    DatabaseReference reff;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);

        btnConfirm = (Button)findViewById(R.id.btnConfirm);
        tvDate = (TextView)findViewById(R.id.tvDate);
        tvSelectTime = (TextView)findViewById(R.id.tvSelectTime);
        tvTime = (TextView)findViewById(R.id.tvTime);
        cvDate = (CalendarView)findViewById(R.id.cvDate);

        intent = getIntent();
        username = intent.getStringExtra("USERNAME");

        reff = FirebaseDatabase.getInstance().getReference().child("Clinic").child(username).child("Booking");

        cvDate.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                date = Integer.toString(dayOfMonth + month + year);
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
                reff.setValue(date + time + Login.username);

            }
        });
    }
}
