package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class splechActivity extends AppCompatActivity {

    private Button play;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splech);
        Thread thread = new Thread() {
            @Override
            public void run() {
                try {
                    play = (Button) findViewById(R.id.play);
                    play.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent mainintent = new Intent(splechActivity.this,MainActivity.class);
                            startActivity(mainintent);

                        }
                    });

                }
                catch (Exception e)
                {
                    e.printStackTrace();

                }
                //finally {
               //     Intent mainintent = new Intent(splechActivity.this,MainActivity.class);
                //    startActivity(mainintent);

                //}
            }
        };
        thread.start();
    }

    @Override
    protected void onPause()
    {
        super.onPause();
        finish();
    }
}
