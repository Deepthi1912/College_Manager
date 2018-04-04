package com.student.satyam.college_manager;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class AdminSignupActivity extends AppCompatActivity {

    private EditText fName;
    private EditText lName;
    private EditText collegename;
    private Spinner state;
    private EditText city;
    private EditText pincode;
    private EditText password;
    private EditText email;
    private EditText confpassword;
    private FirebaseAuth mAuth;
    private ProgressDialog regProgress;
    private DatabaseReference ref;
    private Admin admin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_signup);
        fName = (EditText)findViewById(R.id.admin_first_name);
        lName = (EditText)findViewById(R.id.admin_second_name);
        email = (EditText)findViewById(R.id.admin_email);
        collegename = (EditText)findViewById(R.id.admin_college_name);
        state = (Spinner) findViewById(R.id.admin_state);
        city = (EditText)findViewById(R.id.admin_city);
        pincode = (EditText)findViewById(R.id.admin_pincode);
        password = (EditText)findViewById(R.id.admin_password);
        confpassword = (EditText)findViewById(R.id.admin_confpassword);
        admin = new Admin();
        ref = FirebaseDatabase.getInstance().getReference("Admin");
        mAuth = FirebaseAuth.getInstance();
        regProgress =  new ProgressDialog(AdminSignupActivity.this);

    }

    private void getValues() {
        admin.setfName(fName.getText().toString().trim());
        admin.setlName(lName.getText().toString().trim());
        admin.setCollegename(collegename.getText().toString().trim());
        admin.setCity(city.getText().toString().trim());
        admin.setEmail(email.getText().toString().trim());
        admin.setState(state.getSelectedItem().toString().trim());
        admin.setPincode(pincode.getText().toString().trim());
        admin.setUid("0");

    }


    private int checker(Admin admin){

        if(!confpassword.getText().toString().equals(password.getText().toString())){return 2;}
        if(admin.getfName().equals("")){return 3;}
        if(admin.getEmail().equals("")) {return 4;}
        if(admin.getCity().equals("")) {return 5;}
        if(admin.getCollegename().equals("")) {return 6;}
        if(admin.getState().equals("")){return  7;}
        if(admin.getPincode().length() != 6){return 1;}
        return 0;
    }




    public void signupAdmin(View view) {
        regProgress.setTitle("Creating New Account");
        regProgress.setMessage("Signing You Up");
        regProgress.setCanceledOnTouchOutside(false);
        regProgress.show();
        getValues();
        int check = checker(admin);
        if (check == 0){
            mAuth.createUserWithEmailAndPassword(admin.getEmail(), password.getText().toString())
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        admin.setUid(mAuth.getCurrentUser().getUid());
                                        ref.child(admin.getUid()).setValue(admin).addOnFailureListener(new OnFailureListener() {
                                            @Override
                                            public void onFailure(@NonNull Exception e) {
                                                Toast.makeText(AdminSignupActivity.this,e.getMessage(), Toast.LENGTH_SHORT).show();
                                            }
                                        });
                                        ref.child("Colleges").child((collegename.getText().toString().trim().replaceAll("[^a-zA-Z0-9\\s+]", ""))).setValue("");
                                        Toast.makeText(AdminSignupActivity.this, "Authentication succeeded.", Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(AdminSignupActivity.this,AdminMainActivity.class);
                                        startActivity(intent);
                                    } else {
                                        Toast.makeText(AdminSignupActivity.this, "Invalid Email or Weak Password", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            }
                    );
        }

        else if(check == 1){
             Toast.makeText(AdminSignupActivity.this, "Wrong Pincode", Toast.LENGTH_SHORT).show();
        }

        else if(check == 2){
             Toast.makeText(AdminSignupActivity.this, "Password didnt match", Toast.LENGTH_SHORT).show();
        }

        else if(check == 3){
            Toast.makeText(AdminSignupActivity.this, "First name field can't be empty!!", Toast.LENGTH_LONG).show();
        }

        else if(check == 4){
            Toast.makeText(AdminSignupActivity.this, "Email field can't be empty!!", Toast.LENGTH_LONG).show();
        }

        else if(check == 5){
            Toast.makeText(AdminSignupActivity.this, "City field can't be empty!!", Toast.LENGTH_LONG).show();
        }

        else if(check == 6){
            Toast.makeText(AdminSignupActivity.this, "College name field can't be empty!!", Toast.LENGTH_LONG).show();
        }

        else if(check == 7){
            Toast.makeText(AdminSignupActivity.this, "State field can't be empty!!", Toast.LENGTH_LONG).show();
        }

        regProgress.dismiss();

    }




}

