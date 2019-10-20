package com.example.applicationproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Login extends AppCompatActivity{


    EditText etUsername, etPassword;
    Button btnLogin;
    TextView tvResult;
    DatabaseReference reff;
    public static String password,name,username,identifier;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etUsername = (EditText) findViewById(R.id.etUsername);
        etPassword = (EditText) findViewById(R.id.etPassword);
        btnLogin = (Button) findViewById(R.id.btnLogin);
        tvResult = (TextView)findViewById(R.id.tvResult);


        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String stPassword = etPassword.getText().toString();
                reff = FirebaseDatabase.getInstance().getReference().child("User").child(etUsername.getText().toString());
                reff.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        setPassword(dataSnapshot.child("password").getValue().toString());
                        setName(dataSnapshot.child("name").getValue().toString());
                        setUsername(dataSnapshot.child("username").getValue().toString());
                        setIdentifier(dataSnapshot.child("identifier").getValue().toString());

                        tvResult.setText(password);



                        if (stPassword.equals(password)){
                            openActivity();
                        }
                        else{
                            tvResult.setText("You entered the wrong password :(");
                        }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                    }
                });
            }
        });

    }
    public void openActivity(){
        startActivity(new Intent(this, Welcome.class));
    }

    public static void setPassword(String password) {
        Login.password = password;
    }

    public static void setName(String name) {
        Login.name = name;
    }

    public static void setUsername(String username) {
        Login.username = username;
    }

    public static void setIdentifier(String identifier) {
        Login.identifier = identifier;
    }
}








