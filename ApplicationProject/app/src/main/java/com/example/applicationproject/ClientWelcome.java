package com.example.applicationproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

public class ClientWelcome extends AppCompatActivity {

    TextView tvWelcome, tvChoice, tvType;
    Button btnAddress, btnHours, btnServices, btnLogOut, btnSearch;
    EditText etName;
    DatabaseReference reff;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_welcome);

        tvWelcome = (TextView) findViewById(R.id.tvWelcome);
        tvChoice = (TextView) findViewById(R.id.tvChoice);
        btnAddress = (Button) findViewById(R.id.btnAddress);
        btnHours = (Button) findViewById(R.id.btnHours);
        btnServices = (Button) findViewById(R.id.btnServices);
        btnLogOut = (Button)findViewById(R.id.btnLogOut);
        btnSearch = (Button)findViewById(R.id.btnSearch);
        tvType = (TextView)findViewById(R.id.tvType);
        etName = (EditText)findViewById(R.id.etName);
        reff = FirebaseDatabase.getInstance().getReference().child("Clinic");


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

        btnServices.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openServices();
            }
        });

        //OnClickListener for Log Out
        btnLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openLogin();

            }
        });


        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reff.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        String name = etName.getText().toString();
                        for (DataSnapshot dsp : dataSnapshot.getChildren()){

                            if (name.equals(String.valueOf(dsp.child("name").getValue()))){

                                openClinic(dsp.getKey());
                            } else {
                                Toast.makeText(ClientWelcome.this, "Invalid name", Toast.LENGTH_LONG ).show();
                            }
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });


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

    public void openLogin(){
        startActivity(new Intent(this, Login.class));
        finish();
    }

    public void openClinic(String user){
        Intent intent = new Intent(this, ClinicPage.class);
        intent.putExtra("USERNAME", user );
        startActivity(intent);

    }

}
