package com.student.satyam.college_manager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AdminMainActivity extends AppCompatActivity {


    FirebaseAuth mAuth;
    DatabaseReference dbRef;
    TextView textField;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_main);
        mAuth = FirebaseAuth.getInstance();
        textField = (TextView)findViewById(R.id.admin_welcome);

        if(mAuth.getCurrentUser() != null) {
            dbRef = FirebaseDatabase.getInstance().getReference().child("Admin").child(mAuth.getCurrentUser().getUid());
        }

        dbRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                //String name = dataSnapshot.child("Admin").getValue().toString();
                Admin admin = dataSnapshot.getValue(Admin.class);
                textField.setText("Welcome "+admin.getfName()+" "+admin.getlName());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }


    public void adminSignOut(View view){
        mAuth.signOut();
        Intent intent = new Intent(AdminMainActivity.this,LoginChooserActivity.class);
        startActivity(intent);
    }






}
