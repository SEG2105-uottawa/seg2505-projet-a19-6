package com.example.applicationproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class EmployeeInfo extends AppCompatActivity {

    EditText etAddress, etPhone, etName, etInsurance, etPay;
    TextView tvInstruction, tvTypeInfo, tvPayInfo;
    Button btnSave;
    DatabaseReference reff;
    Clinic clinic;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_info);

        etAddress = (EditText)findViewById(R.id.etAddress);
        etPhone = (EditText)findViewById(R.id.etPhone);
        etName = (EditText)findViewById(R.id.etPhone);
        etInsurance = (EditText)findViewById(R.id.etInsurance);
        etPay = (EditText)findViewById(R.id.etPay);
        tvInstruction = (TextView)findViewById(R.id.tvInstruction);
        tvTypeInfo = (TextView)findViewById(R.id.tvTypeInfo);
        tvPayInfo = (TextView)findViewById(R.id.tvPayInfo);
        btnSave = (Button)findViewById(R.id.btnSave);



        reff = FirebaseDatabase.getInstance().getReference().child("Clinic");

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clinic = new Clinic(etPhone.getText().toString(), etAddress.getText().toString(), etName.getText().toString(), etInsurance.getText().toString(), etPay.getText().toString(), Login.username);
                /*clinic.setAddress(etAddress.getText().toString());
                clinic.setInsuranceType(etInsurance.getText().toString());
                clinic.setName(etName.getText().toString());
                clinic.setPayMethod(etPay.getText().toString());
                clinic.setPhoneNum(etPhone.getText().toString());*/
                reff.child(Login.username).setValue(clinic);
            }
        });

    }

    public Clinic getClinic(){
        return clinic;
    }

}
