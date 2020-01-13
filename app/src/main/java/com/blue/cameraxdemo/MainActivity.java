package com.blue.cameraxdemo;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.camera.core.CameraX;
import androidx.camera.core.ImageCapture;
import androidx.camera.core.ImageCaptureConfig;
import androidx.camera.core.Preview;
import androidx.camera.core.PreviewConfig;

import android.graphics.Matrix;
import android.os.Bundle;
import android.util.Log;
import android.util.Size;
import android.view.Surface;
import android.view.TextureView;
import android.view.ViewGroup;

import java.io.File;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity {
    private TextureView viewFinder;
    /**
     * 线程池
     */
    private Executor  executor = Executors.newSingleThreadExecutor();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewFinder = findViewById(R.id.view_finder);
        startCamera();
    }
    /**
     *
     */
    private void startCamera() {
        PreviewConfig previewConfig = new PreviewConfig.Builder()
                .setTargetResolution(new Size(640, 480))
                .build();
        Preview preview = new Preview(previewConfig);
        preview.setOnPreviewOutputUpdateListener(output -> {
            //更新图片
            ViewGroup parent = (ViewGroup) viewFinder.getParent();
            parent.removeView(viewFinder);
            parent.addView(viewFinder, 0);

            viewFinder.setSurfaceTexture(output.getSurfaceTexture());
            updateTransform();
        });
//        CameraX.bindToLifecycle(MainActivity.this, preview);
        ImageCaptureConfig imageCaptureConfig=new ImageCaptureConfig.Builder()
                .setCaptureMode(ImageCapture.CaptureMode.MIN_LATENCY).build();

        ImageCapture imageCapture=new ImageCapture(imageCaptureConfig);

        findViewById(R.id.capture_button).setOnClickListener(v -> {
            File file=new File(getExternalMediaDirs()[0],System.currentTimeMillis()+".jpg");
            imageCapture.takePicture(file, executor, new ImageCapture.OnImageSavedListener() {
                @Override
                public void onImageSaved(@NonNull File file) {
                    Log.v("TAG","-------图片保存的地址   "+file.getPath());
                }
                @Override
                public void onError(@NonNull ImageCapture.ImageCaptureError imageCaptureError, @NonNull String message, @Nullable Throwable cause) {
                    Log.v("TAG","---------图片保存 错误   ");
                }
            });
        });
        CameraX.bindToLifecycle(this, preview, imageCapture);
    }
    /**
     *
     */
    private void updateTransform() {
        Matrix matrix = new Matrix();
        float centerX = viewFinder.getWidth() / 2f;

        float centerY = viewFinder.getHeight() / 2f;

        //获取屏幕旋转角度
        float rotationDegrees = 0;
        switch ((int) viewFinder.getRotation()) {
            case Surface.ROTATION_0:
                rotationDegrees = 0;
                break;
            case Surface.ROTATION_90:
                rotationDegrees = 90;
                break;
            case Surface.ROTATION_180:
                rotationDegrees = 180;
                break;
            case Surface.ROTATION_270:
                rotationDegrees = 270;
                break;
            default:
                break;
        }
        matrix.postRotate(-rotationDegrees, centerX, centerY);
        viewFinder.setTransform(matrix);
    }
}
