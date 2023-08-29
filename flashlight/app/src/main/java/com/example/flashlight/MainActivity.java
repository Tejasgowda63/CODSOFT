package com.example.flashlight;

import androidx.appcompat.app.AppCompatActivity;

import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    ConstraintLayout flash;
    CameraManager cameraManager;
    String cameraId;
    TextView lb;
    Boolean state=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lb=findViewById(R.id.textView);
        flash=findViewById(R.id.cl);
        flash.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View view) {

                if(state == false){
                    try{
                        cameraManager= (CameraManager) getSystemService(CAMERA_SERVICE);
                        cameraId=cameraManager.getCameraIdList()[0];
                        cameraManager.setTorchMode(cameraId,!state);
                        flash.setBackgroundResource(R.drawable.on);
                        lb.setText("ON");
                        state=true;

                    } catch (CameraAccessException e) {
                        e.printStackTrace();
                    }
                }
                else {
                    try {
                        cameraManager= (CameraManager) getSystemService(CAMERA_SERVICE);
                        cameraId=cameraManager.getCameraIdList()[0];
                        cameraManager.setTorchMode(cameraId,!state);
                        flash.setBackgroundResource(R.drawable.off);
                        lb.setText("OFF");
                        state=false;
                    } catch (CameraAccessException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

    }
}