package com.paico.paico_tour;

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
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.paico.paico_tour.object_classes.User;
import com.paico.paico_tour.object_classes.UserHolder;

import java.io.File;

import static android.app.Activity.RESULT_OK;

public class SettingFragment extends Fragment {
    private EditText fullName;
    private EditText password;
    private EditText phone;
    private EditText date;
    private EditText email;

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
        findView(view);
        setView();
        onClick();

    }

    private void setView() {
        User user = UserHolder.getInstance().getUser();
        if (user.getName().equals(null))
            fullName.setHint("No Name Exist");
        else
            fullName.setText(user.getName());
        phone.setText(user.getPhoneNumber());
        if (user.getProfilePic()!=null)
            new ImageLoader(gallery,progressBar).execute(user.getProfilePic());


    }

    private void onClick() {
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO
                edit.setClickable(true);
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

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        final ProgressDialog progressDialog=new ProgressDialog(getContext());
        progressDialog.setMessage("Uploading...");
        progressDialog.show();
        if (requestCode==GALLERY_INTENT && resultCode == RESULT_OK)
        mStorageRef = FirebaseStorage.getInstance().getReference();

        Uri uri = data.getData();

        Uri file = Uri.fromFile(new File("path/to/images/rivers.jpg"));
        StorageReference riversRef = mStorageRef.child("profiles").child(uri.getLastPathSegment());

        riversRef.putFile(file)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        // Get a URL to the uploaded content
                        Uri downloadUrl = taskSnapshot.getUploadSessionUri();

                        progressDialog.dismiss();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception exception) {
                        Toast.makeText(getContext(),"Upload failed",Toast.LENGTH_LONG).show();
                        // Handle unsuccessful uploads
                        // ...
                    }
                });

    }

    private void findView(View view) {
        fullName = view.findViewById(R.id.setting_fragment_name);
        phone = view.findViewById(R.id.setting_fragment_phone);
        email = view.findViewById(R.id.setting_fragment_email);
        submit = view.findViewById(R.id.setting_fragment_submit_btn);
        edit = view.findViewById(R.id.setting_fragment_edit_btn);
        gallery = view.findViewById(R.id.setting_fragment_gallery);
        progressBar = view.findViewById(R.id.setting_fragment_profile_pic_progress);
    }
}
