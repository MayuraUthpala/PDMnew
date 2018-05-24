package com.example.mayur.pdm;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

public class AdminGallery extends AppCompatActivity {

    private static final int PICK_IMAGE_REQUEST=1;

    private TextView select;
    private CardView upload;
    private CardView show;
    private EditText edit;
    private ImageView image;
    private ProgressBar pbar;

    private Uri mimage;

    private StorageReference mstorefef;
    private DatabaseReference mdataref;

    private StorageTask mUploadTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_gallery);

        select = findViewById(R.id.si);
        upload = findViewById(R.id.cd);
        edit = findViewById(R.id.et);
        image = findViewById(R.id.im);
        pbar = findViewById(R.id.pb);
        show = findViewById(R.id.pd);


        mstorefef = FirebaseStorage.getInstance().getReference("uploads");
        mdataref = FirebaseDatabase.getInstance().getReference("uploads");

        select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openFileChooser();

            }
        });

        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (mUploadTask != null && mUploadTask.isInProgress())
                {
                    Toast.makeText(AdminGallery.this,"upload in progress",Toast.LENGTH_SHORT).show();

                }else {
                    uploadFile();
                }
            }
        });

        show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent s = new Intent(AdminGallery.this, EditImage.class);
                startActivity(s);
            }
        });
    }

        private void openFileChooser()
    {

        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, PICK_IMAGE_REQUEST);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode==RESULT_OK && data != null && data.getData() != null)
        {
            mimage = data.getData();

            Picasso.with(this).load(mimage).into(image);

        }
    }

    private String getFileExtension(Uri uri)
    {
        ContentResolver cr = getContentResolver();
        MimeTypeMap mm = MimeTypeMap.getSingleton();

        return mm.getExtensionFromMimeType(cr.getType(uri));
    }

    private void uploadFile()
    {
        if (mimage != null)
        {
        StorageReference fileReference = mstorefef.child(System.currentTimeMillis()+"."+ getFileExtension(mimage));

        mUploadTask = fileReference.putFile(mimage)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        Handler handler=new Handler();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                pbar.setProgress(0);
                            }
                        },500);
                        Toast.makeText(AdminGallery.this, "upload successfull", Toast.LENGTH_LONG).show();
                        Upload upload = new Upload(edit.getText().toString().trim(),
                                taskSnapshot.getDownloadUrl().toString());
                        String uploadID = mdataref.push().getKey();
                        mdataref.child(uploadID).setValue(upload);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                        Toast.makeText(AdminGallery.this, e.getMessage(), Toast.LENGTH_SHORT).show();

                    }
                })
                .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                    double progress = (100.0 * taskSnapshot.getBytesTransferred()/taskSnapshot.getTotalByteCount());
                    pbar.setProgress((int) progress);
                    }
                });
        }
        else
        {
            Toast.makeText(this, "No File Selected", Toast.LENGTH_SHORT).show();
        }

    }
}
