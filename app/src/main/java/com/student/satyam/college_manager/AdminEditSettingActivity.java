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
        pass = password.getText().toString();
        Toast.makeText(AdminEditSettingActivity.this, "Leave the field empty if you dont want to update", Toast.LENGTH_SHORT).show();
    }

    public void adminChangeSetting(View view) {
        if (pass.equals(""))
        {
            if (!fName.getText().toString().equals("")) {
                databaseReference.child("fName").setValue(fName.getText().toString());
            }

            if (!lName.getText().toString().equals("")) {
                databaseReference.child("lName").setValue(lName.getText().toString());
            }

            if (!email.getText().toString().equals("")) {
                firebaseUser.updateEmail(email.getText().toString());

            }
            if (!state.getSelectedItem().toString().equals("")) {
                databaseReference.child("state").setValue(state.getSelectedItem().toString());
            }

            if (!collegename.getText().toString().equals("")) {
                databaseReference.child("collegename").setValue(collegename.getText().toString());
            }
            if (!city.getText().toString().equals("")) {
                databaseReference.child("city").setValue(city.getText().toString());
            }
            if (!pincode.getText().toString().equals("")) {
                databaseReference.child("fname").setValue(fName.getText().toString());
            }
        } else {

            if (pass.equals(confpassword.getText().toString()))
            {
                firebaseUser.updatePassword(pass).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(AdminEditSettingActivity.this, "Weak Password!!", Toast.LENGTH_SHORT).show();
                    }
                });

                firebaseUser.updatePassword(pass).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        if (!fName.getText().toString().equals("")) {
                            databaseReference.child("fName").setValue(fName.getText().toString());
                        }

                        if (!lName.getText().toString().equals("")) {
                            databaseReference.child("lName").setValue(lName.getText().toString());
                        }

                        if (!email.getText().toString().equals("")) {
                            firebaseUser.updateEmail(email.getText().toString());

                        }
                        if (!state.getSelectedItem().toString().equals("")) {
                            databaseReference.child("state").setValue(state.getSelectedItem().toString());
                        }

                        if (!collegename.getText().toString().equals("")) {
                            databaseReference.child("collegename").setValue(collegename.getText().toString());
                        }
                        if (!city.getText().toString().equals("")) {
                            databaseReference.child("city").setValue(city.getText().toString());
                        }
                        if (!pincode.getText().toString().equals("")) {
                            databaseReference.child("fname").setValue(fName.getText().toString());
                        }
                        Toast.makeText(AdminEditSettingActivity.this, "Settings Successfully Updated", Toast.LENGTH_SHORT).show();
                    }
                });

            }
            else
            {
                Toast.makeText(AdminEditSettingActivity.this, "Passwords Did Not Match", Toast.LENGTH_SHORT).show();
            }
        }

        Intent intent = new Intent(AdminEditSettingActivity.this,AdminMainActivity.class);
        startActivity(intent);
    }



    public void adminSettingsCancel(View view){
        Intent intent = new Intent(AdminEditSettingActivity.this,AdminMainActivity.class);
        startActivity(intent);
    }





















}