package com.paico.paico_tour;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;
import com.paico.paico_tour.object_classes.User;
import com.paico.paico_tour.object_classes.UserHolder;

import java.io.File;
import java.util.List;

import static android.app.Activity.RESULT_OK;

public class SettingFragment extends Fragment {
    private EditText fullName;
    private TextView phone;
    private TextView balance;

    private Button submit;
    private Button edit;

    private ImageView gallery;
    private ProgressBar progressBar;

    private static final int GALLERY_INTENT=2;

    private StorageReference mStorageRef;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.setting_fragment, container, false);
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getStoragePermission();
        findView(view);
        setView();
        onClick();

    }

    private void setView() {
        User user = UserHolder.getInstance().getUser();
        if (user.getName()==null)
            fullName.setHint("No Name Exist");
        else
            fullName.setText(user.getName());
        phone.setText(user.getPhoneNumber());
        balance.setText(user.getBalance());
        if (user.getProfilePic()!=null)
            new ImageLoader(gallery,progressBar).execute(user.getProfilePic());
        else
            progressBar.setVisibility(View.INVISIBLE);


    }

    private void onClick() {
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FirebaseDatabase.getInstance().getReference("User/" + FirebaseAuth.getInstance().getCurrentUser().getUid()).child("name")
                        .setValue(fullName.getText().toString());
            }
        });

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK);

                intent.setType("image/*");
                startActivityForResult(intent,GALLERY_INTENT);

            }
        });
    }

    private void getStoragePermission() {
        Dexter.withContext(getContext()).withPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE})
                .withListener(new MultiplePermissionsListener() {
                    @Override
                    public void onPermissionsChecked(MultiplePermissionsReport multiplePermissionsReport) {
                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(List<PermissionRequest> list, PermissionToken permissionToken) {

                    }
                }).check();
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        final ProgressDialog progressDialog=new ProgressDialog(getContext());
        progressDialog.setMessage("Uploading...");
        progressDialog.show();
        if (requestCode==GALLERY_INTENT && resultCode == RESULT_OK)
        mStorageRef = FirebaseStorage.getInstance().getReference();

        Uri uri = data.getData();


        StorageReference riversRef = mStorageRef.child("profiles").child(uri.getLastPathSegment());

        riversRef.putFile(uri)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        // Get a URL to the uploaded content
                        Uri downloadUrl = taskSnapshot.getUploadSessionUri();
                        User user=UserHolder.getInstance().getUser();
                        user.setProfilePic(downloadUrl.toString());
                        progressDialog.dismiss();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception exception) {
                        exception.printStackTrace();
                        progressDialog.dismiss();
                        Toast.makeText(getContext(),"Upload failed",Toast.LENGTH_LONG).show();
                        // Handle unsuccessful uploads
                        // ...
                    }
                });

    }

    private void findView(View view) {
        fullName = view.findViewById(R.id.setting_fragment_name);
        phone = view.findViewById(R.id.setting_fragment_phone);
        balance = view.findViewById(R.id.setting_fragment_email);
        submit = view.findViewById(R.id.setting_fragment_submit_btn);
        edit = view.findViewById(R.id.setting_fragment_edit_btn);
        gallery = view.findViewById(R.id.setting_fragment_gallery);
        progressBar = view.findViewById(R.id.setting_fragment_profile_pic_progress);
    }
}
