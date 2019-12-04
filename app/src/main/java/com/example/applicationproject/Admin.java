/* **************************************
CLASS TO ADD, MODIFY, OR REMOVE SERVICES
 *****************************************/

package com.example.applicationproject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import android.widget.Toast;


import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Admin extends AppCompatActivity{

    ListView lvService;
    EditText etAdd, etModify;
    Button btnAdd, btnRemove, btnModify;
    DatabaseReference reff, reffC, reffD;
    String serviceItem;
    ArrayList<String> serviceList;
    String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        lvService = (ListView)findViewById(R.id.lvService);
        etAdd = (EditText)findViewById(R.id.etAdd);
        etModify = (EditText)findViewById(R.id.etModify);
        btnAdd = (Button)findViewById(R.id.btnAdd);
        btnRemove = (Button)findViewById(R.id.btnRemove);
        btnModify = (Button)findViewById(R.id.btnModify);
        serviceList = new ArrayList<>();
        reff = FirebaseDatabase.getInstance().getReference().child("Services");
        reffC = FirebaseDatabase.getInstance().getReference().child("Clinic");


        //Link ListView to arrayList
        final ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, serviceList);
        lvService.setAdapter(arrayAdapter);

        //serviceItem is the selected item in the list
        lvService.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                serviceItem = lvService.getItemAtPosition(position).toString();
            }
        });

        //OnClickListener for remove button
        btnRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (serviceItem == null){
                    Toast.makeText(Admin.this, "Invalid Selection", Toast.LENGTH_LONG).show();
                }
                else {
                    name = serviceItem.toString();
                    reff.child(serviceItem).removeValue();

                    //non functional for loop
                    reffC.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            for (DataSnapshot b : dataSnapshot.getChildren()){
                                for (DataSnapshot a: dataSnapshot.child("serviceList").getChildren()) {
                                    if (a.child(serviceItem).exists()) {
                                        reffD = a.child(serviceItem).getRef();
                                        reffD.removeValue();
                                        reff.child(serviceItem).removeValue();
                                    }

                                    //public void onCancelled (@NonNull DatabaseError databaseError){

                                    //}
                                }
                            }
                        }


                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                    try {
                        //reffC.child(name).child("serviceList").child(serviceItem).removeValue();
                        Toast.makeText(Admin.this, name, Toast.LENGTH_LONG).show();
                    }
                    catch (NullPointerException e) {

                    }
                }
            }

        });


        //OnClickListener for add button
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String service = etAdd.getText().toString();
                if (service.length()==0){
                    Toast.makeText(Admin.this, "Field is empty", Toast.LENGTH_LONG).show();
                    return;
                }

                reff.child(service).setValue(service); //Adding the service to firebase
            }
        });

        //OnClickListener for modify button
        btnModify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (serviceItem == null){
                    Toast.makeText(Admin.this, "Invalid Selection", Toast.LENGTH_LONG).show();
                }
                else {
                    reff.child(serviceItem).removeValue();
                }
                if (etModify.getText().toString().length() == 0){
                    Toast.makeText(Admin.this, "Field is empty", Toast.LENGTH_LONG).show();
                    return;
                }
                reff.child(etModify.getText().toString().trim()).setValue(etModify.getText().toString().trim());
            }
        });


        reff.addChildEventListener(new ChildEventListener() {

            //Adds the new service to the serviceList arrayList to update the list
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                String value = dataSnapshot.getValue(String.class);

                serviceList.add(value);
                arrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            //Removes the selected service from the arrayList thus updating the list
            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
                String value = dataSnapshot.getValue(String.class);
                serviceList.remove(value);
                arrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }

}
