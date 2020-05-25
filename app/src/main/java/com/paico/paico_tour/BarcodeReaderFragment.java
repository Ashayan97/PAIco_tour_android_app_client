package com.paico.paico_tour;

import android.Manifest;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.vision.Detector;
import com.google.firebase.ml.vision.barcode.FirebaseVisionBarcode;
import com.google.firebase.ml.vision.barcode.FirebaseVisionBarcodeDetector;
import com.google.firebase.ml.vision.barcode.FirebaseVisionBarcodeDetectorOptions;
import com.google.firebase.ml.vision.common.FirebaseVisionImage;
import com.google.firebase.ml.vision.common.FirebaseVisionImageMetadata;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;
import com.otaliastudios.cameraview.CameraView;
import com.otaliastudios.cameraview.frame.Frame;
import com.otaliastudios.cameraview.frame.FrameProcessor;

import java.util.List;

public class BarcodeReaderFragment extends AppCompatActivity {
    private CameraView cameraView;
    private boolean isDetected;
    private ImageButton startScanning;
    private FirebaseVisionBarcodeDetectorOptions options;
    private FirebaseVisionBarcodeDetector detector;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.activity_barcode);

    }

    private void getCameraPermission() {
        Dexter.withContext(this).withPermission(Manifest.permission.CAMERA).withListener(new PermissionListener() {
            @Override
            public void onPermissionGranted(PermissionGrantedResponse permissionGrantedResponse) {
                setUpCamera();
            }


            @Override
            public void onPermissionDenied(PermissionDeniedResponse permissionDeniedResponse) {
                Toast.makeText(BarcodeReaderFragment.this, "we need your permission to use camera for your payment", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onPermissionRationaleShouldBeShown(PermissionRequest permissionRequest, PermissionToken permissionToken) {

            }
        }).check();

    }

    private void setUpCamera() {
        startScanning = findViewById(R.id.activity_barcode_start_scan_btn);
        startScanning.setEnabled(isDetected);
        startScanning.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isDetected = !isDetected;
            }
        });
        cameraView.findViewById(R.id.barcode_fragment_camera_view);
        cameraView.setLifecycleOwner(this);
        cameraView.addFrameProcessor(new FrameProcessor() {
            @Override
            public void process(@NonNull Frame frame) {
                processImage(getVisionImageFromFrame(frame));
            }
        });
    }

    private void processImage(FirebaseVisionImage image) {
        if (!isDetected){
            detector.detectInImage(image).addOnSuccessListener(new OnSuccessListener<List<FirebaseVisionBarcode>>() {
                @Override
                public void onSuccess(List<FirebaseVisionBarcode> firebaseVisionBarcodes) {
                    processResult(firebaseVisionBarcodes);
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(BarcodeReaderFragment.this,""+e.getMessage(),Toast.LENGTH_LONG);
                }
            });


        }

    }

    private void processResult(List<FirebaseVisionBarcode> firebaseVisionBarcodes) {
        if(firebaseVisionBarcodes.size()>0){
            isDetected=true;
            startScanning.setEnabled(isDetected);
            for (FirebaseVisionBarcode item:firebaseVisionBarcodes){
                int value_type = item.getValueType();
                switch (value_type){
                    case FirebaseVisionBarcode.TYPE_TEXT:
                        createDialog(item.getRawValue());
                }
            }
        }
    }

    private void createDialog(String rawValue) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(rawValue).setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        AlertDialog dialog=builder.create();
        dialog.show();
    }

    public FirebaseVisionImage getVisionImageFromFrame(Frame frame){
        byte[] data=frame.getData();
        FirebaseVisionImageMetadata metadata=new FirebaseVisionImageMetadata.Builder()
                .setFormat(FirebaseVisionImageMetadata.IMAGE_FORMAT_NV21)
                .setHeight(frame.getSize().getHeight())
                .setWidth(frame.getSize().getWidth())
                .build();
        return FirebaseVisionImage.fromByteArray(data,metadata);
    }


}
