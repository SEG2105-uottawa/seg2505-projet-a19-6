package com.example.applicationproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
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


                reff.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        for (DataSnapshot dsp : dataSnapshot.getChildren()) {
                            int flag = 0;
                            //Toast.makeText(ClientHours.this, String.valueOf(dsp.child("Hours").child("OF").getValue()),Toast.LENGTH_LONG).show();
                            try {

                                if (rbM.isChecked()) {
                                    if (String.valueOf(dsp.child("Hours").child("OM").getValue()).equals("Closed") == false && chosenHour >= Integer.parseInt(String.valueOf(dsp.child("Hours").child("OM").getValue())) && chosenHour <= Integer.parseInt(String.valueOf(dsp.child("Hours").child("CM").getValue()))) {
                                        flag = 1;
                                    }
                                }
                                if (rbT.isChecked()) {
                                    if (String.valueOf(dsp.child("Hours").child("OT").getValue()).equals("Closed") == false && chosenHour >= Integer.parseInt(String.valueOf(dsp.child("Hours").child("OT").getValue())) && chosenHour <= Integer.parseInt(String.valueOf(dsp.child("Hours").child("CT").getValue()))) {
                                        flag = 1;
                                    }
                                }
                                if (rbW.isChecked()) {
                                    if (String.valueOf(dsp.child("Hours").child("OW").getValue()).equals("Closed") == false && chosenHour >= Integer.parseInt(String.valueOf(dsp.child("Hours").child("OW").getValue())) && chosenHour <= Integer.parseInt(String.valueOf(dsp.child("Hours").child("CW").getValue()))) {
                                        flag = 1;
                                    }
                                }
                                if (rbTh.isChecked()) {
                                    if (String.valueOf(dsp.child("Hours").child("OTh").getValue()).equals("Closed") == false && chosenHour >= Integer.parseInt(String.valueOf(dsp.child("Hours").child("OTh").getValue())) && chosenHour <= Integer.parseInt(String.valueOf(dsp.child("Hours").child("CTh").getValue()))) {
                                        flag = 1;
                                    }
                                }
                                if (rbF.isChecked()) {
                                    if (String.valueOf(dsp.child("Hours").child("OF").getValue()).equals("Closed") == false && chosenHour >= Integer.parseInt(String.valueOf(dsp.child("Hours").child("OF").getValue())) && chosenHour <= Integer.parseInt(String.valueOf(dsp.child("Hours").child("CF").getValue()))) {
                                        flag = 1;
                                    }
                                }
                                if (rbS.isChecked()) {
                                    if (String.valueOf(dsp.child("OS").getValue()).equals("Closed") == false && chosenHour >= Integer.parseInt(String.valueOf(dsp.child("Hours").child("OS").getValue())) && chosenHour <= Integer.parseInt(String.valueOf(dsp.child("Hours").child("CS").getValue()))) {
                                        flag = 1;
                                    }
                                }
                                if (rbSu.isChecked()) {
                                    if (String.valueOf(dsp.child("Hours").child("OSu").getValue()).equals("Closed") == false && chosenHour >= Integer.parseInt(String.valueOf(dsp.child("Hours").child("OSu").getValue())) && chosenHour <= Integer.parseInt(String.valueOf(dsp.child("Hours").child("CSu").getValue()))) {
                                        flag = 1;
                                    }
                                }
                                if (flag == 1) {
                                    hours.add(String.valueOf(dsp.child("name").getValue()));
                                }
                            }
                            catch (Exception e){
                                continue;
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

    /*public void availableHours(String user){
        DatabaseReference reff = FirebaseDatabase.getInstance().getReference().child("Clinic").child(user).child("Hours");
        final int Monday[], Tuesday[], Wednesday[], Thursday[], Friday[], Saturday[], Sunday[];
        Monday = new int[2];
        Tuesday = new int[2];
        Wednesday = new int[2];
        Thursday = new int[2];
        Friday = new int[2];
        Saturday = new int[2];
        Sunday = new int[2];

        reff.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {


                if (dataSnapshot.child("OM").getValue().toString().equals("Closed")) {
                    Toast.makeText(ClientHours.this, "Test succesful", Toast.LENGTH_LONG).show();
                } else {
                    Monday[0] = Integer.parseInt(dataSnapshot.child("OM").getValue().toString().substring(0,1));
                }

                if (dataSnapshot.child("OT").getValue().toString().equals("Closed")) {
                    Toast.makeText(ClientHours.this, "Test succesful", Toast.LENGTH_LONG).show();
                } else {
                    Tuesday[0] = Integer.parseInt(dataSnapshot.child("OT").getValue().toString().substring(0,1));
                }
                if (dataSnapshot.child("OW").getValue().toString().equals("Closed")) {
                    Toast.makeText(ClientHours.this, "Test succesful", Toast.LENGTH_LONG).show();
                } else {
                    Wednesday[0] = Integer.parseInt(dataSnapshot.child("OW").getValue().toString().substring(0,1));
                }
                if (dataSnapshot.child("OTh").getValue().toString().equals("Closed")) {
                    Toast.makeText(ClientHours.this, "Test succesful", Toast.LENGTH_LONG).show();
                } else {
                    Thursday[0] = Integer.parseInt(dataSnapshot.child("OTh").getValue().toString().substring(0,1));
                }
                if (dataSnapshot.child("OF").getValue().toString().equals("Closed")) {
                    Toast.makeText(ClientHours.this, "Test succesful", Toast.LENGTH_LONG).show();
                } else {
                    Friday[0] = Integer.parseInt(dataSnapshot.child("OF").getValue().toString().substring(0,1));
                }
                if (dataSnapshot.child("OS").getValue().toString().equals("Closed")) {
                    Toast.makeText(ClientHours.this, "Test succesful", Toast.LENGTH_LONG).show();
                } else {
                    Saturday[0] = Integer.parseInt(dataSnapshot.child("OS").getValue().toString().substring(0,1));
                }
                if (dataSnapshot.child("OSu").getValue().toString().equals("Closed")) {
                    Toast.makeText(ClientHours.this, "Test succesful", Toast.LENGTH_LONG).show();
                } else {
                    Sunday[0] = Integer.parseInt(dataSnapshot.child("OSu").getValue().toString().substring(0,1));
                }

                if (dataSnapshot.child("CM").getValue().toString().equals("Closed")) {
                    Toast.makeText(ClientHours.this, "Test succesful", Toast.LENGTH_LONG).show();
                } else {
                    Monday[1] = Integer.parseInt(dataSnapshot.child("OC").getValue().toString().substring(3,4));
                }

                if (dataSnapshot.child("CT").getValue().toString().equals("Closed")) {
                    Toast.makeText(ClientHours.this, "Test succesful", Toast.LENGTH_LONG).show();
                } else {
                    Tuesday[1] = Integer.parseInt(dataSnapshot.child("OT").getValue().toString().substring(3,4));
                }
                if (dataSnapshot.child("CW").getValue().toString().equals("Closed")) {
                    Toast.makeText(ClientHours.this, "Test succesful", Toast.LENGTH_LONG).show();
                } else {
                    Wednesday[1] = Integer.parseInt(dataSnapshot.child("OW").getValue().toString().substring(3,4));
                }
                if (dataSnapshot.child("CTh").getValue().toString().equals("Closed")) {
                    Toast.makeText(ClientHours.this, "Test succesful", Toast.LENGTH_LONG).show();
                } else {
                    Thursday[1] = Integer.parseInt(dataSnapshot.child("OTh").getValue().toString().substring(3,4));
                }
                if (dataSnapshot.child("CF").getValue().toString().equals("Closed")) {
                    Toast.makeText(ClientHours.this, "Test succesful", Toast.LENGTH_LONG).show();
                } else {
                    Friday[1] = Integer.parseInt(dataSnapshot.child("OF").getValue().toString().substring(3,4));
                }
                if (dataSnapshot.child("CS").getValue().toString().equals("Closed")) {
                    Toast.makeText(ClientHours.this, "Test succesful", Toast.LENGTH_LONG).show();
                } else {
                    Saturday[1] = Integer.parseInt(dataSnapshot.child("OS").getValue().toString().substring(3,4));
                }
                if (dataSnapshot.child("CSu").getValue().toString().equals("Closed")) {
                    Toast.makeText(ClientHours.this, "Test succesful", Toast.LENGTH_LONG).show();
                } else {
                    Sunday[1] = Integer.parseInt(dataSnapshot.child("OSu").getValue().toString().substring(3,4));
                }






            }




            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });*/

}
