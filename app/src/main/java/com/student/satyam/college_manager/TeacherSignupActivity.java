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

public class TeacherSignupActivity extends AppCompatActivity {

    private EditText tfName;
    private EditText tlName;
    private Spinner tcollegename;
    private EditText tpassword;
    private EditText temail;
    private EditText tconfpassword;
    private FirebaseAuth tmAuth;
    private ProgressDialog tregProgress;
    private FirebaseDatabase tdatabase;
    private DatabaseReference tref;
    private Teacher teacher;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_signup);
        tfName = findViewById(R.id.teacher_firstname_signup);
        tlName = findViewById(R.id.teacher_lastname_signup);
        temail = findViewById(R.id.teacher_email_signup);
        tcollegename = findViewById(R.id.teacher_collegename_signup);
        tpassword = findViewById(R.id.admin_login_password);
        tconfpassword = findViewById(R.id.teacher_confpassword_signup);
        tref = FirebaseDatabase.getInstance().getReference("Teacher");
        tmAuth = FirebaseAuth.getInstance();
        tregProgress =  new ProgressDialog(TeacherSignupActivity.this);

    }
    private void getValues() {
        teacher.setfName(tfName.getText().toString().trim());
        teacher.setlName(tlName.getText().toString().trim());
        teacher.setCollegename(tcollegename.toString().replaceAll("[^A-Za-z0-9]", "").trim());
        teacher.setEmail(temail.getText().toString().trim());

    }

    private int checker(Teacher teacher){
        if(!tconfpassword.getText().toString().equals(tpassword.getText().toString())){return 1;}
        if(teacher.getfName().equals("")){return 2;}
        if(teacher.getEmail().equals("")) {return 3;}
        if(teacher.getCollegename().equals("")) {return 4;}
        return 0;
    }

    public void signupteacher(View view) {
        tregProgress.setTitle("Creating New Account");
        tregProgress.setMessage("Signing You Up");
        tregProgress.setCanceledOnTouchOutside(false);
        tregProgress.show();
        getValues();
        int check = checker(teacher);
        if (check == 0){
            tmAuth.createUserWithEmailAndPassword(teacher.getEmail(), tpassword.getText().toString())
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        teacher.setUid(tmAuth.getCurrentUser().getUid());
                                        tref.child(teacher.getUid()).setValue(teacher).addOnFailureListener(new OnFailureListener() {
                                            @Override
                                            public void onFailure(@NonNull Exception e) {
                                                Toast.makeText(TeacherSignupActivity.this,e.getMessage(), Toast.LENGTH_SHORT).show();
                                            }
                                        });
                                        Toast.makeText(TeacherSignupActivity.this, "Authentication succeeded.", Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(TeacherSignupActivity.this,Teacher.class);
                                        startActivity(intent);
                                    } else {
                                        Toast.makeText(TeacherSignupActivity.this, "Invalid Email or Weak Password", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            }
                    );
        }

        else if(check == 1){
            Toast.makeText(TeacherSignupActivity.this, "Password didnt match", Toast.LENGTH_SHORT).show();
        }

        else if(check == 2){
            Toast.makeText(TeacherSignupActivity.this, "First name field can't be empty!!", Toast.LENGTH_SHORT).show();
        }

        else if(check == 3){
            Toast.makeText(TeacherSignupActivity.this, "Email field can't be empty!!", Toast.LENGTH_LONG).show();
        }

        else if(check == 4){
            Toast.makeText(TeacherSignupActivity.this, "College name field can't be empty!!", Toast.LENGTH_LONG).show();
        }

        tregProgress.dismiss();

    }



}
