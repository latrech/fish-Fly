package com.example.myapplication;

import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;
import android.net.rtp.AudioStream;

   public class soundPlayer {

    private static SoundPool soundPool;
    private static int hitsound;
    private static int oversound;
       private static int errorsound;
     public soundPlayer(Context context) {
         soundPool = new SoundPool(2, AudioManager.STREAM_MUSIC,0);
         hitsound = soundPool.load(context , R.raw.claps , 1);
         oversound = soundPool.load(context,R.raw.claps ,1);
         errorsound = soundPool.load(context,R.raw.error ,1);

    }

    public void hitsoundPlayer(){
         soundPool.play(hitsound , 1.0f,1.0f,1,0,1.0f);
    }
    public void playoversound(){
        soundPool.play(oversound , 1.0f,1.0f,1,0,1.0f);
    }

       public void playerrorsound() {
           soundPool.play(errorsound , 1.0f,1.0f,1,0,1.0f);
       }
   }
