package com.student.satyam.college_manager;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class AdminUploadActivity extends AppCompatActivity {

    private DatabaseReference databaseReference;
    private StorageReference storageReference;
    private EditText subject;
    private EditText notice;
    String mime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_upload);
        storageReference = FirebaseStorage.getInstance().getReference();
        databaseReference = FirebaseDatabase.getInstance().getReference("Admin Images");
        notice = findViewById(R.id.admin_notice);
        subject = findViewById(R.id.admin_notice_subject);
        class clickListener implements View.OnClickListener {
            @Override
            public void onClick(View view) {
                ((EditText)view).setText("");
            }
        }
        clickListener cl = new clickListener();
        notice.setOnClickListener(cl);
        subject.setOnClickListener(cl);





    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode,final Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null) {
            mime = this.getContentResolver().getType(data.getData());
            if (mime.contains("image") || mime.contains("video") || mime.contains("text") || mime.contains("application/pdf") || mime.contains("application/powerpoint") || mime.contains("application/msword")) {
                final String key = databaseReference.push().getKey();
                if (resultCode == RESULT_OK) {
                    storageReference.child("Admin Images").child(key).putFile(data.getData()).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(Exception exception) {
                            Toast.makeText(AdminUploadActivity.this, "Could Not Upload Data", Toast.LENGTH_LONG).show();
                        }
                    }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            ImageUploadInfo imageUploadInfo = new ImageUploadInfo(key, taskSnapshot.getDownloadUrl().toString(), data.getData().getLastPathSegment());
                            databaseReference.child(key).setValue(imageUploadInfo).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(AdminUploadActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
                                }
                            });
                            Toast.makeText(AdminUploadActivity.this, "Upload successful", Toast.LENGTH_LONG).show();
                        }
                    });
                }
            }
            else
            {
                Toast.makeText(AdminUploadActivity.this, "Could Not Upload this type of data", Toast.LENGTH_LONG).show();
            }
        }
    }
    public void performFileSearch(View view) {


        // ACTION_OPEN_DOCUMENT is the intent to choose a file via the system's file
        // browser.
        Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);

        // Filter to only show results that can be "opened", such as a
        // file (as opposed to a list of contacts or timezones)
        intent.addCategory(Intent.CATEGORY_OPENABLE);

        intent.setType("*/*");
        startActivityForResult(intent, 42);

    }


    public void publishChanges(View view){
        String sub = subject.getText().toString().trim();
        if(sub.equals(""))
        {
            Toast.makeText(AdminUploadActivity.this,"Fill the subject",Toast.LENGTH_LONG).show();
        }
        else{
        FirebaseDatabase.getInstance().getReference().child("Admin Notice").child(sub).setValue(new AdminNotice(sub,notice.getText().toString())).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Toast.makeText(AdminUploadActivity.this,"Notice Sent",Toast.LENGTH_LONG).show();
            }
        });
        }
    }







}
