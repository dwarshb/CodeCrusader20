package com.bhadra.dwarsh.codecrusader20;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class SplashScreen extends Activity {
     int splashtime = 3000; // 3000 miliseconds = 3 seconds
    boolean active = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        //Thread to delay this activity for 3 sec
        Thread splashThread = new Thread() {
            @Override
            public void run() {
                try {
                    int wait = 0;
                    while (active & wait < splashtime) {
                        sleep(80);
                        wait = wait + 100;
                    }
                } catch (Exception e) {
                    Log.e("Splash",e.getMessage());
                } finally {
                    finish();
                    startActivity(new Intent(SplashScreen.this,MainActivity.class));
                }
            }
        };
        splashThread.start();
    }
}

