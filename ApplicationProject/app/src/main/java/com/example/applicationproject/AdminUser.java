/* ************************************
CLASS TO REMOVE USERS FROM THE DATABASE
****************************************/


package com.example.applicationproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AdminUser extends AppCompatActivity {

    Button btnRemove;
    EditText etUsername;
    TextView tvRemove;
    DatabaseReference reff;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_user);

        btnRemove = (Button)findViewById(R.id.btnRemove);
        etUsername = (EditText)findViewById(R.id.etUsername);
        tvRemove = (TextView)findViewById(R.id.tvRemove);
        reff = FirebaseDatabase.getInstance().getReference().child("User");


        //OnClickListener for remove button
        btnRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if (etUsername.getText().toString().length()!=0) {
                        reff.child(etUsername.getText().toString().trim()).removeValue();
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
