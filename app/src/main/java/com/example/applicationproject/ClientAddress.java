package com.example.applicationproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ClientAddress extends AppCompatActivity {
    TextView tvAddressList, tvClinic;
    ListView lvList, lvClinic;
    Button btnConfirm;
    DatabaseReference reff;
    ArrayList<String> address, clinic;
    String selectedItem, selectedClinic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_address);

        tvAddressList = (TextView)findViewById(R.id.tvChoose);
        tvClinic = (TextView)findViewById(R.id.tvClinic);
        lvList = (ListView)findViewById(R.id.lvList);
        lvClinic = (ListView)findViewById(R.id.lvClinic);
        btnConfirm = (Button)findViewById(R.id.btnConfirm);

        reff = FirebaseDatabase.getInstance().getReference().child("Clinic");

        address = new ArrayList<>();
        clinic = new ArrayList<>();

        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, address);
        lvList.setAdapter(arrayAdapter);

        final ArrayAdapter<String> arrayAdapter1 = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, clinic);
        lvClinic.setAdapter(arrayAdapter1);

        lvList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectedItem = lvList.getItemAtPosition(position).toString();
            }
        });

        lvClinic.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectedClinic = lvClinic.getItemAtPosition(position).toString();

                reff.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        //Can be improved by saving the username of the chosen User
                        for (DataSnapshot dsp : dataSnapshot.getChildren()){
                            if (dsp.child("name").getValue().toString().equals(selectedClinic)){
                                openClinic(dsp.getKey());
                            }

                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });




        //For loop iterating through the addresses
        reff.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dsp : dataSnapshot.getChildren()){
                    address.add(String.valueOf(dsp.child("address").getValue()));
                }

                arrayAdapter.notifyDataSetChanged();


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clinic.clear();
                arrayAdapter.notifyDataSetChanged();

                reff.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for (DataSnapshot dsp : dataSnapshot.getChildren()){
                            if (dsp.child("address").getValue().toString().equals(selectedItem)){
                                clinic.add(dsp.child("name").getValue().toString());
                                arrayAdapter1.notifyDataSetChanged();
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

    public void openClinic(String user){
        Intent intent = new Intent(this, ClinicPage.class);
        intent.putExtra("USERNAME", user );
        startActivity(intent);

    }
}
