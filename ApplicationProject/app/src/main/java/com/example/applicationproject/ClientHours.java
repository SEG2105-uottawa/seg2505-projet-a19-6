package com.example.applicationproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ClientHours extends AppCompatActivity {

    TextView tvHourList;
    ListView lvList;
    Button btnTest;
    RadioButton rbM, rbT, rbW, rbTh, rbF, rbS, rbSu;
    DatabaseReference reff;
    ArrayList<String> hours;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_hours);

        rbM = (RadioButton)findViewById(R.id.rbM);
        rbT = (RadioButton)findViewById(R.id.rbT);
        rbW = (RadioButton)findViewById(R.id.rbW);
        rbTh = (RadioButton)findViewById(R.id.rbTh);
        rbF = (RadioButton)findViewById(R.id.rbF);
        rbS = (RadioButton)findViewById(R.id.rbS);
        rbSu = (RadioButton)findViewById(R.id.rbSu);

        tvHourList = (TextView)findViewById(R.id.tvChoose);
        lvList = (ListView)findViewById(R.id.lvList);
        btnTest = (Button)findViewById(R.id.btnConfirm);

        reff = FirebaseDatabase.getInstance().getReference().child("Clinic");

        hours = new ArrayList<>();

        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, hours);
        lvList.setAdapter(arrayAdapter);


        reff.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dsp : dataSnapshot.getChildren()){
                    hours.add(String.valueOf(dsp.child("address").getValue()));
                }

                arrayAdapter.notifyDataSetChanged();


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        btnTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                availableHours("e");
            }
        });



    }

    public void availableHours(String user){
        DatabaseReference reff = FirebaseDatabase.getInstance().getReference().child("Clinic").child(user).child("Hours");
        final int Monday[], Tuesday[], Wednesday[], Thursday[], Friday[], Saturday[], Sunday[];
        Monday = new int[2];
        Tuesday = new int[2];
        Wednesday = new int[2];
        Thursday = new int[2];
        Friday = new int[2];
        Saturday = new int[2];
        Sunday = new int[2];

        reff.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {


                if (dataSnapshot.child("OM").getValue().toString().equals("Closed")) {
                    Toast.makeText(ClientHours.this, "Test succesful", Toast.LENGTH_LONG).show();
                } else {
                    Monday[0] = Integer.parseInt(dataSnapshot.child("OM").getValue().toString().substring(0,1));
                }

                if (dataSnapshot.child("OT").getValue().toString().equals("Closed")) {
                    Toast.makeText(ClientHours.this, "Test succesful", Toast.LENGTH_LONG).show();
                } else {
                    Tuesday[0] = Integer.parseInt(dataSnapshot.child("OT").getValue().toString().substring(0,1));
                }
                if (dataSnapshot.child("OW").getValue().toString().equals("Closed")) {
                    Toast.makeText(ClientHours.this, "Test succesful", Toast.LENGTH_LONG).show();
                } else {
                    Wednesday[0] = Integer.parseInt(dataSnapshot.child("OW").getValue().toString().substring(0,1));
                }
                if (dataSnapshot.child("OTh").getValue().toString().equals("Closed")) {
                    Toast.makeText(ClientHours.this, "Test succesful", Toast.LENGTH_LONG).show();
                } else {
                    Thursday[0] = Integer.parseInt(dataSnapshot.child("OTh").getValue().toString().substring(0,1));
                }
                if (dataSnapshot.child("OF").getValue().toString().equals("Closed")) {
                    Toast.makeText(ClientHours.this, "Test succesful", Toast.LENGTH_LONG).show();
                } else {
                    Friday[0] = Integer.parseInt(dataSnapshot.child("OF").getValue().toString().substring(0,1));
                }
                if (dataSnapshot.child("OS").getValue().toString().equals("Closed")) {
                    Toast.makeText(ClientHours.this, "Test succesful", Toast.LENGTH_LONG).show();
                } else {
                    Saturday[0] = Integer.parseInt(dataSnapshot.child("OS").getValue().toString().substring(0,1));
                }
                if (dataSnapshot.child("OSu").getValue().toString().equals("Closed")) {
                    Toast.makeText(ClientHours.this, "Test succesful", Toast.LENGTH_LONG).show();
                } else {
                    Sunday[0] = Integer.parseInt(dataSnapshot.child("OSu").getValue().toString().substring(0,1));
                }

                if (dataSnapshot.child("CM").getValue().toString().equals("Closed")) {
                    Toast.makeText(ClientHours.this, "Test succesful", Toast.LENGTH_LONG).show();
                } else {
                    Monday[1] = Integer.parseInt(dataSnapshot.child("OC").getValue().toString().substring(3,4));
                }

                if (dataSnapshot.child("CT").getValue().toString().equals("Closed")) {
                    Toast.makeText(ClientHours.this, "Test succesful", Toast.LENGTH_LONG).show();
                } else {
                    Tuesday[1] = Integer.parseInt(dataSnapshot.child("OT").getValue().toString().substring(3,4));
                }
                if (dataSnapshot.child("CW").getValue().toString().equals("Closed")) {
                    Toast.makeText(ClientHours.this, "Test succesful", Toast.LENGTH_LONG).show();
                } else {
                    Wednesday[1] = Integer.parseInt(dataSnapshot.child("OW").getValue().toString().substring(3,4));
                }
                if (dataSnapshot.child("CTh").getValue().toString().equals("Closed")) {
                    Toast.makeText(ClientHours.this, "Test succesful", Toast.LENGTH_LONG).show();
                } else {
                    Thursday[1] = Integer.parseInt(dataSnapshot.child("OTh").getValue().toString().substring(3,4));
                }
                if (dataSnapshot.child("CF").getValue().toString().equals("Closed")) {
                    Toast.makeText(ClientHours.this, "Test succesful", Toast.LENGTH_LONG).show();
                } else {
                    Friday[1] = Integer.parseInt(dataSnapshot.child("OF").getValue().toString().substring(3,4));
                }
                if (dataSnapshot.child("CS").getValue().toString().equals("Closed")) {
                    Toast.makeText(ClientHours.this, "Test succesful", Toast.LENGTH_LONG).show();
                } else {
                    Saturday[1] = Integer.parseInt(dataSnapshot.child("OS").getValue().toString().substring(3,4));
                }
                if (dataSnapshot.child("CSu").getValue().toString().equals("Closed")) {
                    Toast.makeText(ClientHours.this, "Test succesful", Toast.LENGTH_LONG).show();
                } else {
                    Sunday[1] = Integer.parseInt(dataSnapshot.child("OSu").getValue().toString().substring(3,4));
                }






            }




            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
