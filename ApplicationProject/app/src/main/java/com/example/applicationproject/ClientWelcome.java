package com.example.applicationproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class ClientWelcome extends AppCompatActivity {

    TextView tvWelcome, tvChoice;
    Button btnAddress, btnHours, btnServices;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_welcome);

        tvWelcome = (TextView) findViewById(R.id.tvWelcome);
        tvChoice = (TextView) findViewById(R.id.tvChoice);
        btnAddress = (Button) findViewById(R.id.btnAddress);
        btnHours = (Button) findViewById(R.id.btnHours);
        btnServices = (Button) findViewById(R.id.btnService);


        tvWelcome.setText("Welcome " + Login.name + ". You are registered as a client!");

        btnAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAddress();
            }
        });

        btnHours.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openHours();
            }
        });

    }

    public void openHours(){
        startActivity(new Intent(this, ClientHours.class));
    }

    public void openServices(){
        startActivity(new Intent(this, ClientServices.class));
    }


    public void openAddress(){
        startActivity(new Intent(this, ClientAddress.class));
    }

}
