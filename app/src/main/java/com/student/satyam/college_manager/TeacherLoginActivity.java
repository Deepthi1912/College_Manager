package com.student.satyam.college_manager;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class TeacherLoginActivity extends AppCompatActivity {
    FirebaseAuth tfirebaseAuth;
    EditText temail;
    EditText tpassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_chooser);
        tfirebaseAuth = FirebaseAuth.getInstance();

        temail = (EditText) findViewById(R.id.admin_login_email);
        tpassword = (EditText) findViewById(R.id.admin_login_password);
        class MyClickListener implements View.OnClickListener {
            @Override
            public void onClick(View view) {
                ((EditText) view).setText("");
            }
        }
        MyClickListener mcl = new MyClickListener();
        temail.setOnClickListener(mcl);
        tpassword.setOnClickListener(mcl);
    }

    private int checker()
    {
        if(temail.getText().toString().equals(""))
        {
            return 1;
        }
        if(tpassword.getText().toString().equals(""))
        {
            return 2;
        }
        return 0;
    }
    
    public void TeacherLogin(View view){
        int check = checker();
        if(check==0){
        tfirebaseAuth.signInWithEmailAndPassword(temail.getText().toString(),tpassword.getText().toString()).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful())
                {
                    Toast.makeText(TeacherLoginActivity.this,"Logged In Successfully",Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(TeacherLoginActivity.this,AdminMainActivity.class);
                    startActivity(intent);
                }

                else
                {
                    Toast.makeText(TeacherLoginActivity.this,"Wrong Email or Password",Toast.LENGTH_LONG).show();
                }

            } }
        );}

        else if(check==1){
        Toast.makeText(TeacherLoginActivity.this,"Email field cannot be empty!!",Toast.LENGTH_LONG).show();
    }

        else if(check==2){
        Toast.makeText(TeacherLoginActivity.this,"Password required!!",Toast.LENGTH_LONG).show();
    }
    }
    
    
    public void signupTeacher(View view){
        Intent intent = new Intent(this,TeacherSignupActivity.class);
        startActivity(intent);
    }
}
