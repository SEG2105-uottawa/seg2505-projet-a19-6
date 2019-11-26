package com.example.applicationproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class EmployeeHours extends AppCompatActivity{

    TextView tvOM, tvCM, tvOT, tvCT, tvOW, tvCW, tvOTh, tvCTh, tvOF, tvCF, tvOS, tvCS, tvOSu, tvCSu;
    CheckBox rbM, rbT, rbW, rbTh, rbF, rbS, rbSu;
    TimePickerDialog timePickerDialog;
    DatabaseReference reff;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_hours);

        tvOM = (TextView)findViewById(R.id.tvOM);
        tvCM = (TextView)findViewById(R.id.tvCM);
        tvOT = (TextView)findViewById(R.id.tvOT);
        tvCT = (TextView)findViewById(R.id.tvCT);
        tvOW = (TextView)findViewById(R.id.tvOW);
        tvCW = (TextView)findViewById(R.id.tvCW);
        tvOTh = (TextView)findViewById(R.id.tvOTh);
        tvCTh = (TextView)findViewById(R.id.tvCTh);
        tvOF = (TextView)findViewById(R.id.tvOF);
        tvCF = (TextView)findViewById(R.id.tvCF);
        tvOS = (TextView)findViewById(R.id.tvOS);
        tvCS = (TextView)findViewById(R.id.tvCS);
        tvOSu = (TextView)findViewById(R.id.tvOSu);
        tvCSu = (TextView)findViewById(R.id.tvCSu);

        rbM = (CheckBox)findViewById(R.id.rbM);
        rbT = (CheckBox)findViewById(R.id.rbT);
        rbW = (CheckBox)findViewById(R.id.rbW);
        rbTh = (CheckBox)findViewById(R.id.rbTh);
        rbF = (CheckBox)findViewById(R.id.rbF);
        rbS = (CheckBox)findViewById(R.id.rbS);
        rbSu = (CheckBox)findViewById(R.id.rbSu);

        reff = FirebaseDatabase.getInstance().getReference().child("Clinic").child(Login.username).child("Hours");

        reff.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                try {
                    tvOM.setText(dataSnapshot.child("OM").getValue().toString().substring(0,1) + dataSnapshot.child("OM").getValue().toString().substring(3,4));
                } catch (NullPointerException NPE){reff.child("OM").setValue("0000");}
                try {
                    tvCM.setText(dataSnapshot.child("CM").getValue().toString().substring(0,1) + dataSnapshot.child("CM").getValue().toString().substring(3,4));
                } catch (NullPointerException NPE){reff.child("CM").setValue("0000");}
                try {
                    tvOT.setText(dataSnapshot.child("OT").getValue().toString().substring(0,1) + dataSnapshot.child("OT").getValue().toString().substring(3,4));
                } catch (NullPointerException NPE){reff.child("OT").setValue("0000");}
                try {
                    tvCT.setText(dataSnapshot.child("CT").getValue().toString().substring(0,1) + dataSnapshot.child("CT").getValue().toString().substring(3,4));
                } catch (NullPointerException NPE){reff.child("CT").setValue("0000");}
                try {
                    tvOW.setText(dataSnapshot.child("OW").getValue().toString().substring(0,1) + dataSnapshot.child("OW").getValue().toString().substring(3,4));
                } catch (NullPointerException NPE){reff.child("OW").setValue("0000");}
                try {
                    tvCW.setText(dataSnapshot.child("CW").getValue().toString().substring(0,1) + dataSnapshot.child("CW").getValue().toString().substring(3,4));
                } catch (NullPointerException NPE){reff.child("CW").setValue("0000");}
                try {
                    tvOTh.setText(dataSnapshot.child("OTh").getValue().toString().substring(0,1) + dataSnapshot.child("OTh").getValue().toString().substring(3,4));
                } catch (NullPointerException NPE){reff.child("OTh").setValue("0000");}
                try {
                    tvCTh.setText(dataSnapshot.child("CTh").getValue().toString().substring(0,1) + dataSnapshot.child("CTh").getValue().toString().substring(3,4));
                } catch (NullPointerException NPE){reff.child("CTh").setValue("0000");}
                try {
                    tvOF.setText(dataSnapshot.child("OF").getValue().toString().substring(0,1) + dataSnapshot.child("OF").getValue().toString().substring(3,4));
                } catch (NullPointerException NPE){reff.child("OF").setValue("0000");}
                try {
                    tvCF.setText(dataSnapshot.child("CF").getValue().toString().substring(0,1) + dataSnapshot.child("CF").getValue().toString().substring(3,4));
                } catch (NullPointerException NPE){reff.child("CF").setValue("0000");}
                try {
                    tvOS.setText(dataSnapshot.child("OS").getValue().toString().substring(0,1) + dataSnapshot.child("OS").getValue().toString().substring(3,4));
                } catch (NullPointerException NPE){reff.child("OS").setValue("0000");}
                try {
                    tvCS.setText(dataSnapshot.child("CS").getValue().toString().substring(0,1) + dataSnapshot.child("CS").getValue().toString().substring(3,4));
                } catch (NullPointerException NPE){reff.child("CS").setValue("0000");}
                try {
                    tvOSu.setText(dataSnapshot.child("OSu").getValue().toString().substring(0,1) + dataSnapshot.child("OSu").getValue().toString().substring(3,4));
                } catch (NullPointerException NPE){reff.child("OSu").setValue("0000");}
                try {
                    tvCSu.setText(dataSnapshot.child("CSu").getValue().toString().substring(0,1) + dataSnapshot.child("CSu").getValue().toString().substring(3,4));
                } catch (NullPointerException NPE){reff.child("CSu").setValue("0000");}
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        rbM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closed(tvOM, tvCM, rbM, "OM", "CM");
            }
        });

        rbT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closed(tvOT, tvCT, rbT, "OT", "CT");
            }
        });

        rbW.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closed(tvOW, tvCW, rbW, "OW", "CW");
            }
        });

        rbTh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closed(tvOTh, tvCTh, rbTh, "OTh", "CTh");
            }
        });

        rbF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closed(tvOF, tvCF, rbF, "OF", "CF");
            }
        });


        rbS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closed(tvOS, tvCS, rbS, "OS", "CF");
            }
        });

        rbSu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closed(tvOSu, tvCSu, rbSu, "OSu", "CSu");
            }
        });



        tvOM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerDialog timePickerDialog = new TimePickerDialog(EmployeeHours.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int hourOfDay, int minutes) {
                        if (hourOfDay < 10){
                            if (minutes < 10){
                                tvOM.setText("0" + hourOfDay + ":0" + minutes);
                                reff.child("OM").setValue("0" + hourOfDay + "0" + minutes);
                            } else {
                                tvOM.setText("0" + hourOfDay + ":" + minutes);
                                reff.child("OM").setValue("0" + hourOfDay + minutes);
                            }
                        } else {
                            if (minutes < 10){
                                tvOM.setText(hourOfDay + ":0" + minutes);
                                reff.child("OM").setValue(hourOfDay + "0" + minutes);
                            } else {
                                tvOM.setText(hourOfDay + ":" + minutes);
                                reff.child("OM").setValue(hourOfDay + minutes);
                            }
                        }


                    }
                }, 0, 0, true);
                timePickerDialog.show();
            }
        });



        tvCM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerDialog timePickerDialog = new TimePickerDialog(EmployeeHours.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int hourOfDay, int minutes) {
                        if (hourOfDay < 10){
                            if (minutes < 10){
                                tvCM.setText("0" + hourOfDay + ":0" + minutes);
                                reff.child("CM").setValue("0" + hourOfDay + "0" + minutes);
                            } else {
                                tvCM.setText("0" + hourOfDay + ":" + minutes);
                                reff.child("CM").setValue("0" + hourOfDay + minutes);
                            }
                        } else {
                            if (minutes < 10){
                                tvCM.setText(hourOfDay + ":0" + minutes);
                                reff.child("CM").setValue(hourOfDay + "0" + minutes);
                            } else {
                                tvCM.setText(hourOfDay + ":" + minutes);
                                reff.child("CM").setValue(hourOfDay + minutes);
                            }
                        }
                    }
                }, 0, 0, true);
                timePickerDialog.show();
            }
        });

        tvOT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerDialog timePickerDialog = new TimePickerDialog(EmployeeHours.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int hourOfDay, int minutes) {
                        if (hourOfDay < 10){
                            if (minutes < 10){
                                tvOT.setText("0" + hourOfDay + ":0" + minutes);
                                reff.child("OT").setValue("0" + hourOfDay + "0" + minutes);
                            } else {
                                tvOT.setText("0" + hourOfDay + ":" + minutes);
                                reff.child("OT").setValue("0" + hourOfDay + minutes);
                            }
                        } else {
                            if (minutes < 10){
                                tvOT.setText(hourOfDay + ":0" + minutes);
                                reff.child("OT").setValue(hourOfDay + "0" + minutes);
                            } else {
                                tvOT.setText(hourOfDay + ":" + minutes);
                                reff.child("OT").setValue(hourOfDay + minutes);
                            }
                        }
                    }
                }, 0, 0, true);
                timePickerDialog.show();
            }
        });

        tvCT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerDialog timePickerDialog = new TimePickerDialog(EmployeeHours.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int hourOfDay, int minutes) {
                        if (hourOfDay < 10){
                            if (minutes < 10){
                                tvCT.setText("0" + hourOfDay + ":0" + minutes);
                                reff.child("CT").setValue("0" + hourOfDay + "0" + minutes);
                            } else {
                                tvCT.setText("0" + hourOfDay + ":" + minutes);
                                reff.child("CT").setValue("0" + hourOfDay + minutes);
                            }
                        } else {
                            if (minutes < 10){
                                tvCT.setText(hourOfDay + ":0" + minutes);
                                reff.child("CT").setValue(hourOfDay + "0" + minutes);
                            } else {
                                tvCT.setText(hourOfDay + ":" + minutes);
                                reff.child("CT").setValue(hourOfDay + minutes);
                            }
                        }
                    }
                }, 0, 0, true);
                timePickerDialog.show();
            }
        });

        tvOW.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerDialog timePickerDialog = new TimePickerDialog(EmployeeHours.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int hourOfDay, int minutes) {
                        if (hourOfDay < 10){
                            if (minutes < 10){
                                tvOW.setText("0" + hourOfDay + ":0" + minutes);
                                reff.child("OW").setValue("0" + hourOfDay + "0" + minutes);
                            } else {
                                tvOW.setText("0" + hourOfDay + ":" + minutes);
                                reff.child("OW").setValue("0" + hourOfDay +  minutes);
                            }
                        } else {
                            if (minutes < 10){
                                tvOW.setText(hourOfDay + ":0" + minutes);
                                reff.child("OW").setValue(hourOfDay + "0" + minutes);
                            } else {
                                tvOW.setText(hourOfDay + ":" + minutes);
                                reff.child("OW").setValue(hourOfDay +  minutes);
                            }
                        }
                    }
                }, 0, 0, true);
                timePickerDialog.show();
            }
        });

        tvCW.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerDialog timePickerDialog = new TimePickerDialog(EmployeeHours.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int hourOfDay, int minutes) {
                        if (hourOfDay < 10){
                            if (minutes < 10){
                                tvCW.setText("0" + hourOfDay + ":0" + minutes);
                                reff.child("CW").setValue("0" + hourOfDay + "0" + minutes);
                            } else {
                                tvCW.setText("0" + hourOfDay + ":" + minutes);
                                reff.child("CW").setValue("0" + hourOfDay + minutes);
                            }
                        } else {
                            if (minutes < 10){
                                tvCW.setText(hourOfDay + ":0" + minutes);
                                reff.child("CW").setValue(hourOfDay + "0" + minutes);
                            } else {
                                tvCW.setText(hourOfDay + ":" + minutes);
                                reff.child("CW").setValue(hourOfDay + minutes);
                            }
                        }
                    }
                }, 0, 0, true);
                timePickerDialog.show();
            }
        });

        tvOTh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerDialog timePickerDialog = new TimePickerDialog(EmployeeHours.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int hourOfDay, int minutes) {
                        if (hourOfDay < 10){
                            if (minutes < 10){
                                tvOTh.setText("0" + hourOfDay + ":0" + minutes);
                                reff.child("OTh").setValue("0" + hourOfDay + "0" + minutes);
                            } else {
                                tvOTh.setText("0" + hourOfDay + ":" + minutes);
                                reff.child("OTh").setValue("0" + hourOfDay + minutes);
                            }
                        } else {
                            if (minutes < 10){
                                tvOTh.setText(hourOfDay + ":0" + minutes);
                                reff.child("OTh").setValue(hourOfDay + "0" + minutes);
                            } else {
                                tvOTh.setText(hourOfDay + ":" + minutes);
                                reff.child("OTh").setValue(hourOfDay + minutes);
                            }
                        }
                    }
                }, 0, 0, true);
                timePickerDialog.show();
            }
        });

        tvCTh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerDialog timePickerDialog = new TimePickerDialog(EmployeeHours.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int hourOfDay, int minutes) {
                        if (hourOfDay < 10){
                            if (minutes < 10){
                                tvCTh.setText("0" + hourOfDay + ":0" + minutes);
                                reff.child("CTh").setValue("0" + hourOfDay + "0" + minutes);
                            } else {
                                tvCTh.setText("0" + hourOfDay + ":" + minutes);
                                reff.child("CTh").setValue("0" + hourOfDay + minutes);
                            }
                        } else {
                            if (minutes < 10){
                                tvCTh.setText(hourOfDay + ":0" + minutes);
                                reff.child("CTh").setValue(hourOfDay + "0" + minutes);
                            } else {
                                tvCTh.setText(hourOfDay + ":" + minutes);
                                reff.child("CTh").setValue(hourOfDay + minutes);
                            }
                        }
                    }
                }, 0, 0, true);
                timePickerDialog.show();
            }
        });

        tvOF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerDialog timePickerDialog = new TimePickerDialog(EmployeeHours.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int hourOfDay, int minutes) {
                        if (hourOfDay < 10){
                            if (minutes < 10){
                                tvOF.setText("0" + hourOfDay + ":0" + minutes);
                                reff.child("OF").setValue("0" + hourOfDay + "0" + minutes);
                            } else {
                                tvOF.setText("0" + hourOfDay + ":" + minutes);
                                reff.child("OF").setValue("0" + hourOfDay + minutes);
                            }
                        } else {
                            if (minutes < 10){
                                tvOF.setText(hourOfDay + ":0" + minutes);
                                reff.child("OF").setValue(hourOfDay + "0" + minutes);
                            } else {
                                tvOF.setText(hourOfDay + ":" + minutes);
                                reff.child("OF").setValue(hourOfDay + minutes);
                            }
                        }
                    }
                }, 0, 0, true);
                timePickerDialog.show();
            }
        });

        tvCF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerDialog timePickerDialog = new TimePickerDialog(EmployeeHours.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int hourOfDay, int minutes) {
                        if (hourOfDay < 10){
                            if (minutes < 10){
                                tvCF.setText("0" + hourOfDay + ":0" + minutes);
                                reff.child("CF").setValue("0" + hourOfDay + "0" + minutes);
                            } else {
                                tvCF.setText("0" + hourOfDay + ":" + minutes);
                                reff.child("CF").setValue("0" + hourOfDay + minutes);
                            }
                        } else {
                            if (minutes < 10){
                                tvCF.setText(hourOfDay + ":0" + minutes);
                                reff.child("CF").setValue(hourOfDay + "0" + minutes);
                            } else {
                                tvCF.setText(hourOfDay + ":" + minutes);
                                reff.child("CF").setValue(hourOfDay + minutes);
                            }
                        }
                    }
                }, 0, 0, true);
                timePickerDialog.show();
            }
        });

        tvOS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerDialog timePickerDialog = new TimePickerDialog(EmployeeHours.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int hourOfDay, int minutes) {
                        if (hourOfDay < 10){
                            if (minutes < 10){
                                tvOS.setText("0" + hourOfDay + ":0" + minutes);
                                reff.child("OS").setValue("0" + hourOfDay + "0" + minutes);
                            } else {
                                tvOS.setText("0" + hourOfDay + ":" + minutes);
                                reff.child("OS").setValue("0" + hourOfDay + minutes);
                            }
                        } else {
                            if (minutes < 10){
                                tvOS.setText(hourOfDay + ":0" + minutes);
                                reff.child("OS").setValue(hourOfDay + "0" + minutes);
                            } else {
                                tvOS.setText(hourOfDay + ":" + minutes);
                                reff.child("OS").setValue(hourOfDay + minutes);
                            }
                        }
                    }
                }, 0, 0, true);
                timePickerDialog.show();
            }
        });

        tvCS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerDialog timePickerDialog = new TimePickerDialog(EmployeeHours.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int hourOfDay, int minutes) {
                        if (hourOfDay < 10){
                            if (minutes < 10){
                                tvCS.setText("0" + hourOfDay + ":0" + minutes);
                                reff.child("CS").setValue("0" + hourOfDay + "0" + minutes);
                            } else {
                                tvCS.setText("0" + hourOfDay + ":" + minutes);
                                reff.child("CS").setValue("0" + hourOfDay + minutes);
                            }
                        } else {
                            if (minutes < 10){
                                tvCS.setText(hourOfDay + ":0" + minutes);
                                reff.child("CS").setValue(hourOfDay + "0" + minutes);
                            } else {
                                tvCS.setText(hourOfDay + ":" + minutes);
                                reff.child("CS").setValue(hourOfDay + minutes);
                            }
                        }
                    }
                }, 0, 0, true);
                timePickerDialog.show();
            }
        });

        tvOSu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerDialog timePickerDialog = new TimePickerDialog(EmployeeHours.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int hourOfDay, int minutes) {
                        if (hourOfDay < 10){
                            if (minutes < 10){
                                tvOSu.setText("0" + hourOfDay + ":0" + minutes);
                                reff.child("COSu").setValue("0" + hourOfDay + "0" + minutes);
                            } else {
                                tvOSu.setText("0" + hourOfDay + ":" + minutes);
                                reff.child("COSu").setValue("0" + hourOfDay + minutes);
                            }
                        } else {
                            if (minutes < 10){
                                tvOSu.setText(hourOfDay + ":0" + minutes);
                                reff.child("COSu").setValue(hourOfDay + "0" + minutes);
                            } else {
                                tvOSu.setText(hourOfDay + ":" + minutes);
                                reff.child("COSu").setValue(hourOfDay + minutes);
                            }
                        }
                    }
                }, 0, 0, true);
                timePickerDialog.show();
            }
        });

        tvCSu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerDialog timePickerDialog = new TimePickerDialog(EmployeeHours.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int hourOfDay, int minutes) {
                        if (hourOfDay < 10){
                            if (minutes < 10){
                                tvCSu.setText("0" + hourOfDay + ":0" + minutes);
                                reff.child("CCSu").setValue("0" + hourOfDay + "0" + minutes);
                            } else {
                                tvCSu.setText("0" + hourOfDay + ":" + minutes);
                                reff.child("CCSu").setValue("0" + hourOfDay + minutes);
                            }
                        } else {
                            if (minutes < 10){
                                tvCSu.setText(hourOfDay + ":0" + minutes);
                                reff.child("CCSu").setValue(hourOfDay + "0" + minutes);
                            } else {
                                tvCSu.setText(hourOfDay + ":" + minutes);
                                reff.child("CCSu").setValue(hourOfDay + minutes);
                            }
                        }
                    }
                }, 0, 0, true);
                timePickerDialog.show();
            }
        });



    }

    public void closed (TextView open, TextView close, CheckBox y, String idO, String idC){

        if (y.isChecked()){
            open.setClickable(false);
            close.setClickable(false);
            open.setText("Closed");
            close.setText("Closed");
            reff.child(idO).setValue("Closed");
            reff.child(idC).setValue("Closed");
        } else {
            open.setClickable(true);
            close.setClickable(true);
            open.setText("00:00");
            close.setText("00:00");
            reff.child(idC).setValue("0000");
            reff.child(idO).setValue("0000");
        }

    }





}
