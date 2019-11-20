package com.example.applicationproject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class EmployeeServices extends AppCompatActivity {

    ListView lvAvailableServices, lvCurrentServices;
    Button btnAdd, btnRemove;
    ArrayList<String> availableServ, currentServ;
    DatabaseReference reff, reffE;
    String serviceItem, currentItem;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_services);

        reff = FirebaseDatabase.getInstance().getReference().child("Services");
        reffE = FirebaseDatabase.getInstance().getReference().child("Clinic").child(Login.username).child("serviceList");


        availableServ = new ArrayList<>();
        currentServ = new ArrayList<>();

        lvAvailableServices = (ListView)findViewById(R.id.lvAvailableServices);
        lvCurrentServices = (ListView)findViewById(R.id.lvCurrentServices);

        btnAdd = (Button)findViewById(R.id.btnAdd);
        btnRemove = (Button)findViewById(R.id.btnRemove);


        //Linking the availableService list to the ListView
        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, availableServ);
        lvAvailableServices.setAdapter(arrayAdapter);

        final ArrayAdapter<String> arrayAdapter1 = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, currentServ );
        lvCurrentServices.setAdapter(arrayAdapter1);




        reff.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                //VERY IMPORTANT FOR LATER: iterate through the tab Services in Firebase and adds the children to the arraylist
                for(DataSnapshot dsp: dataSnapshot.getChildren()){
                    availableServ.add(String.valueOf(dsp.getValue()));
                }

                //Update the array adapter
                arrayAdapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



        //Modifies the arrayList/ListView on action on Firebase. Runs once to start, then for every action
        reffE.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                String value = dataSnapshot.getValue(String.class);


                //Add selected service to arrayList to update the ListView
                currentServ.add(value);

                //Update the ListView
                arrayAdapter1.notifyDataSetChanged();

            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
                String value = dataSnapshot.getValue(String.class);

                currentServ.remove(value);

                arrayAdapter1.notifyDataSetChanged();

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        lvAvailableServices.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                serviceItem = lvAvailableServices.getItemAtPosition(position).toString();
            }
        });

        lvCurrentServices.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                currentItem = lvCurrentServices.getItemAtPosition(position).toString();
            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Add selected service to Firebase
                reffE.child(serviceItem).setValue(serviceItem);

            }
        });

        btnRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentItem == null){
                    Toast.makeText(EmployeeServices.this, "Invalid Selection", Toast.LENGTH_LONG).show();
                } else {
                    //Remove selected item from Firebase
                    reffE.child(currentItem).removeValue();
                }

            }
        });



    }



}
