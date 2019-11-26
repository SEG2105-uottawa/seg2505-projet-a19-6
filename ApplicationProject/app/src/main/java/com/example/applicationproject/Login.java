/* ***********************************************************************************************
CLASS TO LOGIN. CHECKS IF PASSWORD ENTERED IS SAME AS PASSWORD ASSOCIATED TO USERNAME IN DATABASE
 *************************************************************************************************/

package com.example.applicationproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
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

public class Login extends AppCompatActivity{


    EditText etUsername, etPassword;
    Button btnLogin, btnShowHide;
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
        btnShowHide = (Button) findViewById(R.id.btnShowHide);
        tvResult = (TextView)findViewById(R.id.tvResult);

        //OnClickListener for button login. Verifies if info entered is right

            btnLogin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try{
                    final String stPassword = etPassword.getText().toString();
                    final String stUsername = etUsername.getText().toString();

                        reff = FirebaseDatabase.getInstance().getReference().child("User").child(etUsername.getText().toString());
                        reff.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                try {
                                    setPassword(dataSnapshot.child("password").getValue().toString());
                                    setName(dataSnapshot.child("name").getValue().toString());
                                    setUsername(dataSnapshot.child("username").getValue().toString());
                                    setIdentifier(dataSnapshot.child("identifier").getValue().toString());
                                }catch(Exception e){
                                    Toast.makeText(Login.this, "Wrong input", Toast.LENGTH_LONG).show();
                                    return;
                                }

                                if (stUsername.equals("admin") && stPassword.equals("5T5ptQ")) {
                                    openAdmin();
                                } else if (stPassword.equals(password)) {
                                    if (identifier.equals("2")){
                                        openEmployee();
                                    }
                                    else if(identifier.equals("1")){
                                        openClient();
                                    }
                                } else {
                                    tvResult.setText("You entered the wrong username or password");
                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {
                            }
                        });
                    }
                    catch(Exception e){
                        Toast.makeText(Login.this, "Invalid input", Toast.LENGTH_LONG).show();
                    }
                }
            });

        //On click listener for Show/Hide button for password
            btnShowHide.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (btnShowHide.getText().equals("SHOW")) {
                        etPassword.setTransformationMethod(null); //shows password
                        btnShowHide.setText("HIDE");
                        etPassword.setSelection(etPassword.getText().length()); //replaces cursor at the end
                    }
                    else {
                        etPassword.setTransformationMethod(new PasswordTransformationMethod()); //hides password
                        btnShowHide.setText("SHOW");
                        etPassword.setSelection(etPassword.getText().length()); //replaces cursor at the end
                    }
                }
            });

    }

    //Goto methods
    public void openClient(){
            startActivity(new Intent(this, ClientWelcome.class));
    }


    public void openAdmin(){
        startActivity(new Intent(this, AdminWelcome.class));
    }

    public void openEmployee(){
        startActivity(new Intent(this, EmployeeWelcome.class));
    }

    //Setters
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








