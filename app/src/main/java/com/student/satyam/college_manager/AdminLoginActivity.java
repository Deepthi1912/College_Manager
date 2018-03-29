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
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_admin_login);
            firebaseAuth = FirebaseAuth.getInstance();

            email = (EditText) findViewById(R.id.admin_login_email);
            password = (EditText) findViewById(R.id.admin_login_password);
            class MyClickListener implements View.OnClickListener {
                @Override
                public void onClick(View view) {
                    ((EditText) view).setText("");
                }
            }
            MyClickListener mcl = new MyClickListener();
            email.setOnClickListener(mcl);
            password.setOnClickListener(mcl);
        }






    private int checker()
    {
        if(email.getText().toString().equals(""))
        {
           return 1;
        }
        if(password.getText().toString().equals(""))
        {
          return 2;
        }
        return 0;
    }



    public void adminLogin(View view){
        int check = checker();
        if(check==0){
        firebaseAuth.signInWithEmailAndPassword(email.getText().toString(),password.getText().toString()).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful())
                {
                    Toast.makeText(AdminLoginActivity.this,"Logged In Successfully",Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(AdminLoginActivity.this,AdminMainActivity.class);
                    startActivity(intent);
                }

                else
                    {
                        Toast.makeText(AdminLoginActivity.this,"Wrong Email or Password",Toast.LENGTH_LONG).show();
                    }

                } }
        );}

        else if(check==1){
            Toast.makeText(AdminLoginActivity.this,"Email field cannot be empty!!",Toast.LENGTH_LONG).show();
        }

        else if(check==2){
            Toast.makeText(AdminLoginActivity.this,"Password required!!",Toast.LENGTH_LONG).show();
        }


    }


    public void adminSignup(View view){
        Intent intent = new Intent(this,AdminSignupActivity.class);
        startActivity(intent);
    }
}
