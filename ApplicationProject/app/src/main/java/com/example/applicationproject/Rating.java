package com.example.applicationproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;
import java.util.Date;
import java.text.SimpleDateFormat;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Rating extends AppCompatActivity {

    RatingBar ratingBar;
    EditText commentsBox;
    Button btnSubmit;
    Intent intent;
    String username;

    DatabaseReference reff;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clinic_rating);

        ratingBar = (RatingBar) findViewById(R.id.ratingBar);
        commentsBox = (EditText) findViewById(R.id.commentsBox);
        btnSubmit = (Button) findViewById(R.id.btnSubmit);

        intent = getIntent();
        username = intent.getStringExtra("USERNAME");

        //Toast.makeText(Rating.this, username, Toast.LENGTH_LONG).show();


        reff = FirebaseDatabase.getInstance().getReference().child("Clinic").child(username);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ratingBar.getRating() == 0) {
                    Toast.makeText(Rating.this, "Please enter a rating before submitting", Toast.LENGTH_LONG).show();
                } else {
                    SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
                    Date date = new Date(System.currentTimeMillis());
                    reff.child("Rating").child(formatter.format(date)).child("comments").setValue(commentsBox.getText().toString());
                    reff.child("Rating").child(formatter.format(date)).child("rating").setValue(ratingBar.getRating());
                    Toast.makeText(Rating.this, "Thank you for your feedback", Toast.LENGTH_LONG).show();
                    finish();


                }
            }
        });
    }
}
