package com.student.satyam.college_manager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class LoginChooserActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_chooser);
    }

    public void openStudentLogin(View view) {
        Intent intent = new Intent(this,StudentLoginActivity.class);
        startActivity(intent);
    }

    public void openTeacherLogin(View view){
        Intent intent = new Intent(this,TeacherLoginActivity.class);
        startActivity(intent);
    }

    public void openAdminLogin(View view){
        Intent intent = new Intent(this,AdminLoginActivity.class);
        startActivity(intent);
    }

}
