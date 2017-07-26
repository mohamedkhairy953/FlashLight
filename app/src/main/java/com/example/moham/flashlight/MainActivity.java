package com.example.moham.flashlight;

import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {
    ImageButton btn;
    Camera camera;
    Camera.Parameters parameters;
   boolean is_flash,is_on;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn = (ImageButton) findViewById(R.id.btn);
        if (getApplicationContext().getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA)) {
            camera = Camera.open();
            parameters = camera.getParameters();
            is_flash=true;

        }
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(is_flash){
                    if(!is_on){

                        btn.setImageResource(R.drawable.on_icon);
                        parameters.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);
                        camera.setParameters(parameters);
                        camera.startPreview();
                        is_on=true;



                    }else {
                        btn.setImageResource(R.drawable.off_icon);
                        parameters.setFlashMode(Camera.Parameters.ANTIBANDING_OFF);
                        camera.setParameters(parameters);
                        camera.stopPreview();

                        is_on=false;



                    }






                }else{
                    final AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setTitle("Error");
                    builder.setMessage(" Camera not available ");
                    builder.setPositiveButton("Ok ", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                            finish();
                        }
                    });
                    AlertDialog alertDialog = builder.create();
                    alertDialog.show();

                }
            }
        });








    }

    @Override
    protected void onStop() {
        if(camera!=null){
            camera.release();
            camera=null;
        }
        super.onStop();
    }
}
