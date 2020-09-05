package com.paico.paico_tour;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.ml.vision.FirebaseVision;
import com.google.firebase.ml.vision.barcode.FirebaseVisionBarcode;
import com.google.firebase.ml.vision.barcode.FirebaseVisionBarcodeDetector;
import com.google.firebase.ml.vision.barcode.FirebaseVisionBarcodeDetectorOptions;
import com.google.firebase.ml.vision.common.FirebaseVisionImage;
import com.google.firebase.ml.vision.common.FirebaseVisionImageMetadata;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;
import com.otaliastudios.cameraview.CameraView;
import com.otaliastudios.cameraview.frame.Frame;
import com.otaliastudios.cameraview.frame.FrameProcessor;
import com.paico.paico_tour.object_classes.User;
import com.paico.paico_tour.object_classes.UserHolder;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class BarcodeReaderActivity extends AppCompatActivity {
    private CameraView cameraView;
    private ProgressBar progressBar;
    private boolean isDetected=true;
    private Button startScanning;
    private FirebaseVisionBarcodeDetectorOptions options;
    private FirebaseVisionBarcodeDetector detector;
    private boolean check=false;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_barcode_reader);
        getCameraPermission();
    }

    private void getCameraPermission() {
        Dexter.withContext(this).withPermissions(new String[]{Manifest.permission.CAMERA, Manifest.permission.RECORD_AUDIO})
                .withListener(new MultiplePermissionsListener() {
                    @Override
                    public void onPermissionsChecked(MultiplePermissionsReport multiplePermissionsReport) {
                        setUpCamera();
                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(List<PermissionRequest> list, PermissionToken permissionToken) {

                    }
                }).check();
    }

    private void setUpCamera() {
        progressBar = findViewById(R.id.activity_barcode_progress_bar);
        startScanning = findViewById(R.id.activity_barcode_start_scan_btn);
        startScanning.setEnabled(isDetected);
        startScanning.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
                isDetected = !isDetected;
                check=false;
                startScanning.setEnabled(isDetected);
            }
        });
        cameraView = findViewById(R.id.barcode_activity_camera_view);
        cameraView.setLifecycleOwner(this);
        cameraView.addFrameProcessor(new FrameProcessor() {
            @Override
            public void process(@NonNull Frame frame) {
                processImage(getVisionImageFromFrame(frame));
            }
        });
        options = new FirebaseVisionBarcodeDetectorOptions.Builder()
                .setBarcodeFormats(FirebaseVisionBarcode.FORMAT_QR_CODE)
                .build();
        detector = FirebaseVision.getInstance().getVisionBarcodeDetector(options);
    }

    private void processImage(FirebaseVisionImage image) {
        if (!isDetected) {
            detector.detectInImage(image).addOnSuccessListener(new OnSuccessListener<List<FirebaseVisionBarcode>>() {
                @Override
                public void onSuccess(List<FirebaseVisionBarcode> firebaseVisionBarcodes) {
                    processResult(firebaseVisionBarcodes);
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(BarcodeReaderActivity.this, "" + e.getMessage(), Toast.LENGTH_LONG);
                }
            });


        }

    }

    private void processResult(List<FirebaseVisionBarcode> firebaseVisionBarcodes) {
        if (firebaseVisionBarcodes.size() > 0) {
            isDetected = true;
            startScanning.setEnabled(isDetected);
            progressBar.setVisibility(View.INVISIBLE);
            for (FirebaseVisionBarcode item : firebaseVisionBarcodes) {
                int value_type = item.getValueType();
                switch (value_type) {
                    //ToDo manage barcode scanner function
                    case FirebaseVisionBarcode.TYPE_TEXT:
                        String str[] = item.getRawValue().split("/");
                        if (str[0].equals("Pico-Tour")){
                            if (Integer.valueOf(UserHolder.getInstance().getUser().getBalance())<Integer.valueOf(str[2]))
                                Toast.makeText(this,"you are low in credit",Toast.LENGTH_LONG).show();
                            else {
                                if (!check) {
                                    check=true;
                                    payDialog(str[1], str[2]);
                                }
                            }

                        }
                        break;

                    default:
                        break;

                }
            }
        }
    }
    private void payDialog(final String id, final String balance){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("You are paying "+balance+" for "+id+"\n Are you sure?").setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (Integer.valueOf(UserHolder.getInstance().getUser().getBalance())>=Integer.valueOf(balance)){
                    String newBalance = String.valueOf(Integer.valueOf(UserHolder.getInstance().getUser().getBalance()) - Integer.valueOf(balance));
                    User user = UserHolder.getInstance().getUser();
                    if (user.getTransactions() == null)
                        user.setTransactions(new ArrayList<Transactions>());
                    Transactions transaction = new Transactions();
                    String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
                    transaction.setId(FirebaseAuth.getInstance().getUid().substring(0, 2) + timeStamp);
                    transaction.setAmount("- " + balance);
                    String time= timeStamp.substring(0,4)+"/"+timeStamp.substring(4,6)+"/"+timeStamp.substring(6,8);
                    transaction.setDate(time);
                    transaction.setTitle(id);
                    user.getTransactions().add(transaction);
                    user.setBalance(newBalance);
                    FirebaseDatabase.getInstance().getReference("User/" + FirebaseAuth.getInstance().getCurrentUser().getUid()).
                            setValue(user);
                    dialog.dismiss();
                }
            }
        }).setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
             dialog.dismiss();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void createDialog(String rawValue) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(rawValue).setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public FirebaseVisionImage getVisionImageFromFrame(Frame frame) {
        byte[] data = frame.getData();
        FirebaseVisionImageMetadata metadata = new FirebaseVisionImageMetadata.Builder()
                .setFormat(FirebaseVisionImageMetadata.IMAGE_FORMAT_NV21)
                .setHeight(frame.getSize().getHeight())
                .setWidth(frame.getSize().getWidth())
                .build();
        return FirebaseVisionImage.fromByteArray(data, metadata);
    }


}
