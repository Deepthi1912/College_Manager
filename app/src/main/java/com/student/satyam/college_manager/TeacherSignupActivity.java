package com.student.satyam.college_manager;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
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

import java.util.ArrayList;
import java.util.List;

public class TeacherSignupActivity extends AppCompatActivity {

    private EditText tfName;
    private EditText tlName;
    private Spinner tcollegename;
    private EditText tpassword;
    private EditText temail;
    private EditText tconfpassword;
    private CheckBox[] cb =  new CheckBox[8];
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
        tpassword = findViewById(R.id.teacher_login_password);
        cb[0] = findViewById(R.id.cb1_teacher_signup_activity);
        cb[1] = findViewById(R.id.cb2_teacher_signup_activity);
        cb[2] = findViewById(R.id.cb3_teacher_signup_activity);
        cb[3] = findViewById(R.id.cb4_teacher_signup_activity);
        cb[4] = findViewById(R.id.cb5_teacher_signup_activity);
        cb[5] = findViewById(R.id.cb6_teacher_signup_activity);
        cb[6] = findViewById(R.id.cb7_teacher_signup_activity);
        cb[7] = findViewById(R.id.cb8_teacher_signup_activity);
        tconfpassword = findViewById(R.id.teacher_confpassword_signup);
        tref = FirebaseDatabase.getInstance().getReference("Teacher");
        tmAuth = FirebaseAuth.getInstance();
        tregProgress =  new ProgressDialog(TeacherSignupActivity.this);
        teacher = new Teacher("","","","","");

    }
    private void getValues() {
        List<String> sem = new ArrayList<String>();
        for(int i=0;i<8;i++){
            if(cb[i].isChecked()){
                sem.add(cb[i].getText().toString());
            }
        }
        teacher.setfName(tfName.getText().toString().trim());
        teacher.setlName(tlName.getText().toString().trim());
        teacher.setCollegename(tcollegename.toString().trim());
        teacher.setEmail(temail.getText().toString().trim());
        teacher.setSem(sem);


    }

    private int checker(Teacher teacher){
        String s[] = {"","","","","","","",""};
        if(!tconfpassword.getText().toString().equals(tpassword.getText().toString())){return 1;}
        if(teacher.getfName().equals("")){return 2;}
        if(teacher.getEmail().equals("")) {return 3;}
        if(teacher.getCollegename().equals("")) {return 4;}
        if(teacher.getSem().equals(s)) {return 5;}
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
                                        Intent intent = new Intent(TeacherSignupActivity.this,TeacherLoginActivity.class);
                                        startActivity(intent);
                                        finish();
                                    }
                                     else {
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

        else if(check == 5){
            Toast.makeText(TeacherSignupActivity.this, "Select atleast one semester!!", Toast.LENGTH_LONG).show();

        tregProgress.dismiss();

    }



}
}
