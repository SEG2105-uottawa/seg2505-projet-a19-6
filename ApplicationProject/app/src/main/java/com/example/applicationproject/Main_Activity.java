/* ************************************************************
REGISTRATION PAGE. CREATES USERS AND ADDS THEM TO THE DATABASE
 **************************************************************/


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

public class Main_Activity extends AppCompatActivity implements View.OnClickListener {

    EditText etName,etUsername,etPassword,etIdentifier;
    Button btnSave, btnLogin;
    TextView tvIdentifier, tvRegister;
    DatabaseReference reff;
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_activity);

        etName = (EditText)findViewById(R.id.etName);
        etUsername = (EditText)findViewById(R.id.etUsername);
        etPassword = (EditText)findViewById(R.id.etPassword);
        etIdentifier = (EditText)findViewById(R.id.etIdentifier);
        btnSave = (Button)findViewById(R.id.btnSave);
        btnLogin = (Button)findViewById(R.id.btnLogin);
        tvIdentifier = (TextView)findViewById(R.id.tvIdentifier);
        tvRegister = (TextView)findViewById(R.id.tvRegister);
        user = new User();
        reff = FirebaseDatabase.getInstance().getReference().child("User");

        //My first way of setting ClickListener. Not updated because not necessary
        btnSave.setOnClickListener(this);
        btnLogin.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnLogin:
                Toast.makeText(Main_Activity.this, "Going to login", Toast.LENGTH_LONG).show();
                startActivity(new Intent(this, Login.class));

                break;

            case R.id.btnSave:

                /*try {
                    reff.child(etUsername.getText().toString()).getKey();
                    Toast.makeText(Main_Activity.this, "This username is taken", Toast.LENGTH_LONG).show();
                }

                catch(NullPointerException nll) {*/


                    /*reff.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            String username = dataSnapshot.getValue(String.class);

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });*/


                    try {
                        int id = Integer.parseInt(etIdentifier.getText().toString().trim());
                        user.setName(etName.getText().toString().trim());
                        user.setUsername(etUsername.getText().toString().trim());
                        user.setPassword(etPassword.getText().toString().trim());
                        user.setIdentifier(id);
                        reff.child(etUsername.getText().toString().trim()).setValue(user);

                    } catch (Exception e) {
                        Toast.makeText(Main_Activity.this, "Data is wrong or missing", Toast.LENGTH_LONG).show();
                        break;
                    }


                    Toast.makeText(Main_Activity.this, "Data inserted", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(this, Login.class));
                    break;






        }
    }
}
