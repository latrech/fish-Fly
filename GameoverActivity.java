package com.example.myapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class GameoverActivity extends AppCompatActivity {

    private Button play_again ;
    private TextView Sscore;
    private String score;
    private Button exit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gameover);


        score = getIntent().getExtras().get("scorefinale").toString();
        Sscore = (TextView)findViewById(R.id.sc) ;

        play_again = (Button) findViewById(R.id.play_again);

        play_again.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mainintent = new Intent(GameoverActivity.this,MainActivity.class);
                startActivity(mainintent);

            }
        });

       Sscore.setText("scrore = "+ score);

        exit = (Button) findViewById(R.id.exit);
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder builder = new AlertDialog.Builder(GameoverActivity.this);
                builder.setMessage("are you sure want to exit ?")
                        .setCancelable(false)
                        .setPositiveButton("yes" ,new DialogInterface.OnClickListener(){
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                GameoverActivity.super.onBackPressed();

                            }
                        })


                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.cancel();

                            }
                        });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();


            }
        });
    }

    @Override
    public void onBackPressed() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("are you sure want to exit ?")
                .setCancelable(false)
                .setPositiveButton("yes" ,new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
              GameoverActivity.super.onBackPressed();

            }
        })


    .setNegativeButton("No", new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialogInterface, int i) {
            dialogInterface.cancel();

        }
    });
    AlertDialog alertDialog = builder.create();
    alertDialog.show();


    }
}
