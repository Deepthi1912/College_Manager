package com.student.satyam.college_manager;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

public class AdminEditSettingActivity extends AppCompatActivity {


    private DatabaseReference databaseReference;
    private FirebaseUser firebaseUser;
    private EditText fName;
    private EditText lName;
    private EditText collegename;
    private Spinner state;
    private EditText city;
    private EditText pincode;
    private EditText password;
    private EditText email;
    private EditText confpassword;
    private String pass;
    private String ema;
    private Admin admin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_edit_setting);
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        databaseReference = FirebaseDatabase.getInstance().getReference().child("Admin").child(firebaseUser.getUid());
        fName = findViewById(R.id.admin_first_name);
        lName = findViewById(R.id.admin_second_name);
        email = findViewById(R.id.admin_email);
        collegename = findViewById(R.id.admin_college_name);
        state = findViewById(R.id.admin_state);
        city = findViewById(R.id.admin_city);
        pincode = findViewById(R.id.admin_pincode);
        password = findViewById(R.id.admin_password);
        confpassword = findViewById(R.id.admin_confpassword);
        Toast.makeText(AdminEditSettingActivity.this, "Leave the field empty if you dont want to update", Toast.LENGTH_SHORT).show();
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                admin = dataSnapshot.getValue(Admin.class);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private Admin changeAdmin(Admin a){

        if (!fName.getText().toString().equals("")) {
            a.setfName(fName.getText().toString());
        }
        if (!lName.getText().toString().equals("")) {
            a.setlName(lName.getText().toString());
        }
        if (!ema.equals("")) {
            a.setEmail(ema);
        }
        if (!state.getSelectedItem().toString().equals("")) {
            a.setState(state.getSelectedItem().toString());
        }
        if (!city.getText().toString().equals("")) {
            a.setCity(city.getText().toString());
        }
        if (!collegename.getText().toString().equals("")) {
           a.setCollegenameRaw(collegename.getText().toString());
        }
        if (!pincode.getText().toString().equals("")) {
           a.setPincode(pincode.getText().toString());
        }

        return a;


    }


    public void adminChangeSetting(View view) {
        ema = email.getText().toString();
        pass = password.getText().toString();
        databaseReference.setValue(null).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(AdminEditSettingActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        if (pass.equals(""))
        {
            if(!(ema.equals(""))) {
                firebaseUser.updateEmail(ema).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(AdminEditSettingActivity.this, "Invalid email", Toast.LENGTH_SHORT).show();
                    }
                }).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid){changeAdmin(admin);
                    }
                });
            }

            if(ema.equals("")) {
                changeAdmin(admin);
            }
        }

        else {
        if (pass.equals(confpassword.getText().toString()) && pass.length()>=6){
            if(ema.equals(""))
                {
                    firebaseUser.updatePassword(pass).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(AdminEditSettingActivity.this, "Weak Password!!", Toast.LENGTH_SHORT).show();
                        }
                    }).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            changeAdmin(admin);
                        }
                    });
                }
                else
                {
                    firebaseUser.updatePassword(pass).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            firebaseUser.updateEmail(ema).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(AdminEditSettingActivity.this, "Invalid Email", Toast.LENGTH_SHORT).show();
                                }
                            }).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    changeAdmin(admin);
                                }
                            });
                        }
                    });
                }
            }
            else
            {
                if(!pass.equals(confpassword.getText().toString())){Toast.makeText(AdminEditSettingActivity.this, "Passwords Did Not Match", Toast.LENGTH_SHORT).show();}
                else{Toast.makeText(AdminEditSettingActivity.this, "Weak Password", Toast.LENGTH_SHORT).show();}
            }
        }
        databaseReference.setValue(admin);
        Intent intent = new Intent(AdminEditSettingActivity.this,AdminMainActivity.class);
        startActivity(intent);
    }



    public void adminSettingsCancel(View view){
        Intent intent = new Intent(AdminEditSettingActivity.this,AdminMainActivity.class);
        startActivity(intent);
    }





















}