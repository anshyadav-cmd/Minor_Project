package com.example.whereru;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class SplashScreen extends AppCompatActivity {
    private static boolean isSplahLoaded = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(!isSplahLoaded) {
            setContentView(R.layout.splash_screen);
            int secondDelayed = 1;
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    startActivity(new Intent(SplashScreen.this, Login.class));
                    finish();
                }
            }, secondDelayed * 1500);
            isSplahLoaded = true;
        }else {
            Intent goToMainActivity = new Intent(SplashScreen.this, Login.class);
                      goToMainActivity.setFlags(Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT);
                      startActivity(goToMainActivity);
                     finish();
        }
    }
}
