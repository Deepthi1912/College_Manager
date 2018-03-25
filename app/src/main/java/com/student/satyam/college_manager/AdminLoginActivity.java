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
import com.google.firebase.auth.FirebaseUser;


public class AdminLoginActivity extends AppCompatActivity {

    FirebaseAuth firebaseAuth;
    EditText email;
    EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);
        firebaseAuth = FirebaseAuth.getInstance();
        email = (EditText)findViewById(R.id.admin_login_email);
        password = (EditText)findViewById(R.id.admin_login_password);

    }


    public void adminLogin(View view){
        firebaseAuth.signInWithEmailAndPassword(email.getText().toString(),password.getText().toString()).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(AdminLoginActivity.this,"Logged In Successfully",Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(AdminLoginActivity.this,AdminMainActivity.class);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(AdminLoginActivity.this,"Wrong Email or Password",Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public void adminSignup(View view){
        Intent intent = new Intent(this,AdminSignupActivity.class);
        startActivity(intent);

    }
}
