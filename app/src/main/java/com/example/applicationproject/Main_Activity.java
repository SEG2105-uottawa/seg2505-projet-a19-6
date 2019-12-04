/* ************************************************************
REGISTRATION PAGE. CREATES USERS AND ADDS THEM TO THE DATABASE
 **************************************************************/


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

public class Main_Activity extends AppCompatActivity implements View.OnClickListener {

    EditText etName,etUsername,etPassword,etIdentifier;
    Button btnSave, btnLogin, btnShowHide;
    TextView tvIdentifier, tvRegister;
    DatabaseReference reff, reff1;
    User user;
    boolean isUserExisting;

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
        btnShowHide = (Button) findViewById(R.id.btnShowHide);
        tvIdentifier = (TextView)findViewById(R.id.tvIdentifier);
        tvRegister = (TextView)findViewById(R.id.tvRegister);
        user = new User();
        reff = FirebaseDatabase.getInstance().getReference().child("User");

        etPassword.setTransformationMethod(new PasswordTransformationMethod()); //hides the password

        //My first way of setting ClickListener. Not updated because not necessary
        btnSave.setOnClickListener(this);
        btnLogin.setOnClickListener(this);
        btnShowHide.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnLogin:
                startActivity(new Intent(this, Login.class));

                break;

            case R.id.btnShowHide:
                if (btnShowHide.getText().equals("SHOW")) {
                    etPassword.setTransformationMethod(null); //shows password
                    btnShowHide.setText("HIDE");
                    etPassword.setSelection(etPassword.getText().length()); //replaces cursor at the end
                    break;
                }
                else {
                    etPassword.setTransformationMethod(new PasswordTransformationMethod()); //hides password
                    btnShowHide.setText("SHOW");
                    etPassword.setSelection(etPassword.getText().length()); //replaces cursor at the end
                    break;
                }

            case R.id.btnSave:

                try {
                    int id = Integer.parseInt(etIdentifier.getText().toString().trim());
                    if ((id == 1 || id == 2) & !(etName.getText().toString().trim().isEmpty()) & !(etUsername.getText().toString().trim().isEmpty()) & !(etPassword.getText().toString().trim().isEmpty())) {
                        user.setIdentifier(id);
                        user.setName(etName.getText().toString().trim());
                        user.setUsername(etUsername.getText().toString().trim());
                        user.setPassword(etPassword.getText().toString().trim());
                        isValid();
                        if (isUserExisting) {
                            break;
                        }
                        else {
                            reff.child(etUsername.getText().toString().trim()).setValue(user);
                            Toast.makeText(Main_Activity.this, "Data inserted", Toast.LENGTH_LONG).show();
                            startActivity(new Intent(this, Login.class));
                            break;
                        }
                    }
                    else {
                        Toast.makeText(Main_Activity.this, "Data is wrong or missing", Toast.LENGTH_LONG).show();
                        break;
                    }
                }catch (Exception e) {
                    Toast.makeText(Main_Activity.this, "Data is wrong or missing", Toast.LENGTH_LONG).show();
                    break;
                }
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
        }
    }


    public void isValid(){
        reff1 = FirebaseDatabase.getInstance().getReference().child("User").child(etUsername.getText().toString());
        reff1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    Toast.makeText(Main_Activity.this, "Username already exists", Toast.LENGTH_LONG).show();
                    isUserExisting = true;
                }
                else{
                    isUserExisting = false;
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
    }
}
