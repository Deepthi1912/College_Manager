package com.student.satyam.college_manager;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * Created by JAISMENE VERMA on 30-03-2018.
 */

public class TeacherMainActivity extends AppCompatActivity{

    FirebaseAuth tAuth;
    DatabaseReference dbref;
    TextView textField;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_main);
        tAuth=FirebaseAuth.getInstance();
        textField=(TextView)findViewById(R.id.teacher_welcome);

        if(tAuth.getCurrentUser()!=null)
        {
            dbref = FirebaseDatabase.getInstance().getReference().child("Admin").child(tAuth.getCurrentUser().getUid());
        }
        dbref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Teacher teacher= dataSnapshot.getValue(Teacher.class);
                textField.setText("Welcome "+teacher.getfName()+" "+teacher.getlName());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public void teacherSignOut(View view)
    {
        tAuth.signOut();
        Intent intent=new Intent(TeacherMainActivity.this,LoginChooserActivity.class);
        startActivity(intent);
    }


}
