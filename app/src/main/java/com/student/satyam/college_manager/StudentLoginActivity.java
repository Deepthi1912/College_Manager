package com.student.satyam.college_manager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class StudentLoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_login);
    }

    public void signupStudent(View view){
        Intent intent = new Intent(this,StudentSignupActivity.class);
        startActivity(intent);
    }
}
