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
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ClientServices extends AppCompatActivity {

    TextView tvAddressList, tvClinic;
    ListView lvList, lvClinic;
    Button btnConfirm;
    DatabaseReference reff;
    DatabaseReference reffC;
    ArrayList<String> address, clinic, users;
    String selectedItem, currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_services);

        tvAddressList = (TextView) findViewById(R.id.tvChoose);
        lvList = (ListView) findViewById(R.id.lvList);
        tvClinic = (TextView)findViewById(R.id.tvClinic);
        lvClinic = (ListView)findViewById(R.id.lvClinic);
        btnConfirm = (Button)findViewById(R.id.btnConfirm);

        reff = FirebaseDatabase.getInstance().getReference().child("Services");
        reffC = FirebaseDatabase.getInstance().getReference().child("Clinic");

        address = new ArrayList<>();
        clinic = new ArrayList<>();
        users = new ArrayList<>();


        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, address);
        lvList.setAdapter(arrayAdapter);

        final ArrayAdapter<String> arrayAdapter1 = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, users);
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
                currentUser = lvClinic.getItemAtPosition(position).toString();

                reffC.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for (DataSnapshot dsp : dataSnapshot.getChildren()){
                            if (dsp.child("name").getValue().toString().equals(currentUser)){
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

        //For loop iterating through the services
        reff.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dsp : dataSnapshot.getChildren()) {
                    address.add(String.valueOf(dsp.getValue()));
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
                users.clear();
                arrayAdapter1.notifyDataSetChanged();

                //This iterates through the Clinics and then uses the method hasChild to check if the serviceList of the users
                //contain the selected item from the listView
                reffC.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for (DataSnapshot dsp : dataSnapshot.getChildren()){
                            if (dsp.child("serviceList").hasChild(selectedItem)){
                                users.add(dsp.child("name").getValue().toString());
                            }
                        }
                        arrayAdapter1.notifyDataSetChanged();
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
