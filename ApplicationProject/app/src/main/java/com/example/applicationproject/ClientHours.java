package com.example.applicationproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ClientHours extends AppCompatActivity {

    TextView tvChoose, tvHour;
    ListView lvList;
    Button btnConfirm;
    CheckBox rbM, rbT, rbW, rbTh, rbF, rbS, rbSu;
    DatabaseReference reff;
    ArrayList<String> hours;
    String selectedItem;
    int chosenHour;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_hours);

        rbM = (CheckBox)findViewById(R.id.rbM);
        rbT = (CheckBox)findViewById(R.id.rbT);
        rbW = (CheckBox)findViewById(R.id.rbW);
        rbTh = (CheckBox)findViewById(R.id.rbTh);
        rbF = (CheckBox)findViewById(R.id.rbF);
        rbS = (CheckBox)findViewById(R.id.rbS);
        rbSu = (CheckBox)findViewById(R.id.rbSu);

        tvChoose = (TextView)findViewById(R.id.tvChoose);
        tvHour = (TextView)findViewById(R.id.tvHour);
        lvList = (ListView)findViewById(R.id.lvList);
        btnConfirm = (Button)findViewById(R.id.btnConfirm);

        reff = FirebaseDatabase.getInstance().getReference().child("Clinic");

        hours = new ArrayList<>();

        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, hours);
        lvList.setAdapter(arrayAdapter);

        lvList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectedItem = lvList.getItemAtPosition(position).toString();
                reff.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for (DataSnapshot dsp : dataSnapshot.getChildren()){
                            if (dsp.child("name").getValue().toString().equals(selectedItem)){
                                openClinic(dsp.getKey());
                            }
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });

        tvHour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerDialog timePickerDialog = new TimePickerDialog(ClientHours.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int hourOfDay, int minutes) {
                        String x = Integer.toString(hourOfDay) + Integer.toString(minutes);

                        if (hourOfDay < 10){
                            if (minutes < 10){
                                tvHour.setText("0" + hourOfDay + ":0" + minutes);
                                chosenHour = Integer.parseInt(x + "0");
                            } else {
                                tvHour.setText("0" + hourOfDay + ":" + minutes);
                                chosenHour = Integer.parseInt(x);
                            }
                        } else {
                            if (minutes < 10){
                                tvHour.setText(hourOfDay + ":0" + minutes);
                                chosenHour = Integer.parseInt(x + "0");
                            } else {
                                tvHour.setText(hourOfDay + ":" + minutes);
                                chosenHour = Integer.parseInt(x);
                            }
                        }


                    }
                }, 0, 0, true);
                timePickerDialog.show();
            }
        });



        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                hours.clear();
                arrayAdapter.notifyDataSetChanged();



                reff.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        for (DataSnapshot dsp : dataSnapshot.getChildren()) {
                            int flag = 0;

                            try {

                                if (rbM.isChecked()) {
                                    if (String.valueOf(dsp.child("Hours").child("OM").getValue()) != null && String.valueOf(dsp.child("Hours").child("CM").getValue()) != null && String.valueOf(dsp.child("Hours").child("OM").getValue()).equals("Closed") == false && chosenHour > Integer.parseInt(String.valueOf(dsp.child("Hours").child("OM").getValue())) && chosenHour < Integer.parseInt(String.valueOf(dsp.child("Hours").child("CM").getValue()))) {
                                        flag = 1;

                                    }
                                }
                                if (rbT.isChecked()) {
                                    if (String.valueOf(dsp.child("Hours").child("OT").getValue()) != null && String.valueOf(dsp.child("Hours").child("CT").getValue()) != null && String.valueOf(dsp.child("Hours").child("OT").getValue()).equals("Closed") == false && chosenHour > Integer.parseInt(String.valueOf(dsp.child("Hours").child("OT").getValue())) && chosenHour < Integer.parseInt(String.valueOf(dsp.child("Hours").child("CT").getValue()))) {
                                        flag = 1;

                                    }
                                }
                                if (rbW.isChecked()) {
                                    if (String.valueOf(dsp.child("Hours").child("OW").getValue()) != null && String.valueOf(dsp.child("Hours").child("CW").getValue()) != null && String.valueOf(dsp.child("Hours").child("OW").getValue()).equals("Closed") == false && chosenHour > Integer.parseInt(String.valueOf(dsp.child("Hours").child("OW").getValue())) && chosenHour < Integer.parseInt(String.valueOf(dsp.child("Hours").child("CW").getValue()))) {
                                        flag = 1;
                                    }
                                }
                                if (rbTh.isChecked()) {
                                    if (String.valueOf(dsp.child("Hours").child("OTh").getValue()) != null && String.valueOf(dsp.child("Hours").child("CTh").getValue()) != null && String.valueOf(dsp.child("Hours").child("OTh").getValue()).equals("Closed") == false && chosenHour > Integer.parseInt(String.valueOf(dsp.child("Hours").child("OTh").getValue())) && chosenHour < Integer.parseInt(String.valueOf(dsp.child("Hours").child("CTh").getValue()))) {
                                        flag = 1;
                                    }
                                }
                                if (rbF.isChecked()) {
                                    if (String.valueOf(dsp.child("Hours").child("OF").getValue()) != null && String.valueOf(dsp.child("Hours").child("CF").getValue()) != null && String.valueOf(dsp.child("Hours").child("OF").getValue()).equals("Closed") == false && chosenHour > Integer.parseInt(String.valueOf(dsp.child("Hours").child("OF").getValue())) && chosenHour < Integer.parseInt(String.valueOf(dsp.child("Hours").child("CF").getValue()))) {
                                        flag = 1;
                                    }
                                }
                                if (rbS.isChecked()) {
                                    if (String.valueOf(dsp.child("Hours").child("OS").getValue()) != null && String.valueOf(dsp.child("Hours").child("CS").getValue()) != null && String.valueOf(dsp.child("Hours").child("OS").getValue()).equals("Closed") == false && chosenHour > Integer.parseInt(String.valueOf(dsp.child("Hours").child("OS").getValue())) && chosenHour < Integer.parseInt(String.valueOf(dsp.child("Hours").child("CS").getValue()))) {
                                        flag = 1;
                                    }
                                }
                                if (rbSu.isChecked()) {
                                    if (String.valueOf(dsp.child("Hours").child("OSu").getValue()) != null && String.valueOf(dsp.child("Hours").child("CSu").getValue()) != null && String.valueOf(dsp.child("Hours").child("OSu").getValue()).equals("Closed") == false && chosenHour > Integer.parseInt(String.valueOf(dsp.child("Hours").child("OSu").getValue())) && chosenHour < Integer.parseInt(String.valueOf(dsp.child("Hours").child("CSu").getValue()))) {
                                        flag = 1;
                                    }
                                }
                                if (flag == 1) {

                                    hours.add(String.valueOf(dsp.child("name").getValue()));
                                }
                            }
                            catch (Exception e){

                            }
                        }
                        arrayAdapter.notifyDataSetChanged();
                    }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
             });

             }
        });
    }

    public void openClinic(String user){
        Intent intent = new Intent(this, ClinicPage.class);
        intent.putExtra("USERNAME", user );
        startActivity(intent);
    }
}
