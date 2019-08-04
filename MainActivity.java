package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    private FlyingFishView gameview;
    private Handler handler = new Handler();
    private final static long interval = 30;
    private soundPlayer sound;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sound = new soundPlayer(this);
        gameview = new FlyingFishView(this);
        setContentView(gameview);


        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run()
            {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        gameview.invalidate();

                    }
                });
            }
        },0,interval);

    }
}
