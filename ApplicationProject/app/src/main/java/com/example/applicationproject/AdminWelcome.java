/* ******************************************************
CLASS TO CHOOSE TO GO TO SERVICES OR USERS ON ADMIN SIDE
 *********************************************************/

package com.example.applicationproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class AdminWelcome extends AppCompatActivity {

    TextView tvWelcome;
    Button btnService, btnUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_welcome);

        tvWelcome = (TextView)findViewById(R.id.tvWelcome);
        btnService = (Button)findViewById(R.id.btnService);
        btnUser = (Button)findViewById(R.id.btnUser);


        //OnClickListener for service button
        btnService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openServices();
            }
        });

        //OnClickListener for user button
        btnUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openUsers();

            }
        });
    }

    //GOTO methods
    public void openServices(){
        startActivity(new Intent(this, Admin.class));
    }

    public void openUsers(){
        startActivity(new Intent(this, AdminUser.class));
    }
}
