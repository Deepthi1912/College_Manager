package com.student.satyam.college_manager;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AdminSignupActivity extends AppCompatActivity {

    private EditText fName;
    private EditText lName;
    private EditText collegename;
    private EditText state;
    private EditText city;
    private EditText pincode;
    private EditText password;
    private EditText email;
    //private FirebaseAuth mAuth;
    FirebaseDatabase database;
    DatabaseReference ref;
    Admin admin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_signup);
        fName = findViewById(R.id.admin_first_name);
        lName = findViewById(R.id.admin_second_name);
        email = findViewById(R.id.admin_email);
        collegename = findViewById(R.id.admin_college_name);
        state = findViewById(R.id.admin_state);
        city = findViewById(R.id.admin_city);
        pincode = findViewById(R.id.admin_pincode);
        password = findViewById(R.id.admin_password);
        admin = new Admin();
        database = FirebaseDatabase.getInstance();
        ref = database.getReference("Admin");
        //mAuth = FirebaseAuth.getInstance();
    }

    private void getValues(){
        admin.setfName(fName.getText().toString());
        admin.setlName(lName.getText().toString());
        admin.setCollegename(collegename.getText().toString());
        admin.setCity(city.getText().toString());
        admin.setEmail(email.getText().toString());
        admin.setPassword(password.getText().toString());
        admin.setState(state.getText().toString());
        admin.setPincode(pincode.getText().toString());

    }






    public void signupAdmin(View view){
        getValues();
        ref.child(admin.getEmail()).setValue(admin);
        Toast.makeText(AdminSignupActivity.this,"Data Inserted",Toast.LENGTH_LONG).show();


    }
}
