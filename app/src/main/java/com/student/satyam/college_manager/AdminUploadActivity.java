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
    private EditText notice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_upload);
        storageReference = FirebaseStorage.getInstance().getReference();
        databaseReference = FirebaseDatabase.getInstance().getReference("Admin Images");
        notice = findViewById(R.id.admin_notice);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode,final Intent data) {
        super.onActivityResult(requestCode,resultCode,data);
        if(resultCode == RESULT_OK){
            storageReference.child("Admin").child(data.getData().getLastPathSegment()).putFile(data.getData()).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(Exception exception) {
                    Toast.makeText(AdminUploadActivity.this,"Could Not Upload Data",Toast.LENGTH_LONG).show();
                }
            }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    ImageUploadInfo imageUploadInfo = new ImageUploadInfo(data.getData().getLastPathSegment(),taskSnapshot.getDownloadUrl().toString());
                    databaseReference.child(databaseReference.push().getKey()).setValue(imageUploadInfo).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(AdminUploadActivity.this,e.getMessage(),Toast.LENGTH_LONG).show();
                        }
                    });
                    Toast.makeText(AdminUploadActivity.this,"Successfully Uploaded",Toast.LENGTH_LONG).show();
                }
            });
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
        FirebaseDatabase.getInstance().getReference().child("Admin Notice").setValue(notice.getText().toString()).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Toast.makeText(AdminUploadActivity.this,"Notice Sent",Toast.LENGTH_LONG).show();
            }
        });

    }






}
