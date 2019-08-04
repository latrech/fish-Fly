package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

public class FlyingFishView extends View
{
    private Bitmap fish[] = new Bitmap[2];
    private int fishx = 10;
    private int fishy;
    private int fishspeed;
    private boolean touch = false;
    private soundPlayer claps;


    private int canvaswhidth, canvashight;

    private int yellowx , yellowy, yellospeed =16;
    private Paint yellowpaint = new Paint();

    private int greenx, greeny, greenspeed=20;
    private Paint greenpaint = new Paint();

    private int redx,redy,redspeed =25,level=1;
    private Paint redpaint = new Paint();

    private int score, lifcountreoffish;

    private Bitmap backgroundimg;
    private Paint scorepaint = new Paint();
    private Bitmap life[]= new Bitmap[2];

    public FlyingFishView(Context context) {
        super(context);
        fish[0] = BitmapFactory.decodeResource(getResources(), R.drawable.fish1);
        fish[1] = BitmapFactory.decodeResource(getResources(), R.drawable.fish2);
        backgroundimg = BitmapFactory.decodeResource(getResources(), R.drawable.background);
        scorepaint.setColor(Color.WHITE);
        scorepaint.setTextSize(70);
        scorepaint.setTypeface(Typeface.DEFAULT_BOLD);
        scorepaint.setAntiAlias(true);
          claps = new soundPlayer(context);


        yellowpaint.setColor(Color.YELLOW);
        yellowpaint.setAntiAlias(false);

        greenpaint.setColor(Color.GREEN);
        greenpaint.setAntiAlias(false);

        redpaint.setColor(Color.RED);
        redpaint.setAntiAlias(false);

        life[0]=BitmapFactory.decodeResource(getResources(), R.drawable.hearts);
        life[1]=BitmapFactory.decodeResource(getResources(), R.drawable.heart_grey);

        fishy = 550;
        score = 0;
        lifcountreoffish= 3 ;

    }


    @Override
    protected void onDraw(Canvas canvas) {
        canvaswhidth = canvas.getWidth();
        canvashight = canvas.getHeight();

    super.onDraw(canvas);


        canvas.drawBitmap(backgroundimg,0,0,null);

        int minfishy = fish[0].getHeight();
        int maxfichy = canvashight -fish[0].getHeight() * 3;
        fishy=fishy+fishspeed;

        if (fishy < minfishy)
        {
            fishy = minfishy;
        }
        if (fishy > maxfichy)
        {
            fishy = maxfichy;
        }
        fishspeed = fishspeed + 2;

        if (touch){
            canvas.drawBitmap(fish[1],fishx,fishy,null);
            touch= false;
        }else {
            canvas.drawBitmap(fish[0],fishx,fishy,null);
        }


        yellowx = yellowx - yellospeed;

        if (hitballchecker(yellowx,yellowy))
        {
            score = score + 10;
            yellowx = - 100;
            claps.hitsoundPlayer();
        }
        if (yellowx<0)
        {
            yellowx = canvaswhidth + 21;
            yellowy = (int) Math.floor(Math.random()*(maxfichy-minfishy)+minfishy);
        }

        //create balls//

        canvas.drawCircle(yellowx,yellowy,25,yellowpaint);

                  ///balls desapeer//

        greenx = greenx - greenspeed;

        if (hitballchecker(greenx,greeny))
        {
            score = score + 30;
            greenx = - 100;
            claps.playoversound();
        }
        if (greenx<0)
        {
            greenx = canvaswhidth + 21;
            greeny = (int) Math.floor(Math.random()*(maxfichy-minfishy)+minfishy);
        }

        //create balls//

        canvas.drawCircle(greenx,greeny,45,greenpaint);



        // the red ball
              if (score>100){
                  redspeed=35;
                  level= 2;

              }
        if (score>300){
            redspeed=55;
            level = 3;

        }
        redx = redx - redspeed;

        if (hitballchecker(redx,redy))
        {

            redx = - 100;
            claps.playerrorsound();
            lifcountreoffish--;
            if (lifcountreoffish==0)
            {
                Toast.makeText(getContext(), "Game Over", Toast.LENGTH_SHORT).show();
                Intent gameoverintent = new Intent(getContext() , GameoverActivity.class);
                gameoverintent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                gameoverintent.putExtra("scorefinale", score);
                getContext().startActivity(gameoverintent);
            }
        }
        if (redx<0)
        {
            redx = canvaswhidth + 21;
            redy = (int) Math.floor(Math.random()*(maxfichy-minfishy)+minfishy);
        }

        //create balls//

        canvas.drawCircle(redx,redy,35,redpaint);
        canvas.drawText("Score :"+score,20,60,scorepaint);
        canvas.drawText("level :"+level,20,130,scorepaint);
        for (int i=0;i<3;i++)
        {
            int x = (int)(580 + life[0].getWidth()*1.5*i);
            int y = 30;
            if (i < lifcountreoffish)
            {
                canvas.drawBitmap(life[0],x,y,null);
            }
            else {
                canvas.drawBitmap(life[1],x,y,null);
            }

        }


       // canvas.drawBitmap(life[0],580,10,null);
       // canvas.drawBitmap(life[0],680,10,null);
       // canvas.drawBitmap(life[0],780,10,null);
    }

    public boolean  hitballchecker(int x, int y)
    {
        if(fishx < x && x < (fishx+fish[0].getWidth()) && fishy < y && y < (fishy + fish[0].getHeight()))
        {
            return true;
        }
        return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN){
            touch= true;
            //vitesse du poisson
            fishspeed = -22;


        }
        return true;
    }

}