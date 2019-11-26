/* ************************************
CLASS TO REMOVE USERS FROM THE DATABASE
****************************************/


package com.example.applicationproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class AdminUser extends AppCompatActivity {

    Button btnRemove;
    ListView lvList;
    TextView tvRemove;
    DatabaseReference reff;
    DatabaseReference reffC;
    ArrayList<String> list;
    String item;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_user);

        btnRemove = (Button)findViewById(R.id.btnRemove);
        lvList = (ListView)findViewById(R.id.lvList);
        tvRemove = (TextView)findViewById(R.id.tvRemove);
        reff = FirebaseDatabase.getInstance().getReference().child("User");
        reffC = FirebaseDatabase.getInstance().getReference().child("Clinic");
        list = new ArrayList<>();

        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, list);
        lvList.setAdapter(arrayAdapter);


        reff.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dsp : dataSnapshot.getChildren() ){
                    if (String.valueOf(dsp.child("username").getValue()).equals("admin") == false) {

                        list.add(String.valueOf(dsp.child("username").getValue()));
                    }
                }

                arrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        lvList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                item = lvList.getItemAtPosition(position).toString();

            }
        });


        //OnClickListener for remove button
        btnRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if (item != null) {
                        reff.child(item).removeValue();
                        reffC.child(item).removeValue();
                        list.remove(item);
                        arrayAdapter.notifyDataSetChanged();
                    }else{
                        Toast.makeText(AdminUser.this, "Field is null", Toast.LENGTH_LONG).show();
                    }

                }catch(Exception e){
                    Toast.makeText(AdminUser.this, "Wrong input", Toast.LENGTH_LONG).show();
                    return;
                }
            }
        });
    }
}
