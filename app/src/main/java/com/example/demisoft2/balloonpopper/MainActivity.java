package com.example.demisoft2.balloonpopper;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

public class MainActivity extends AppCompatActivity {

    private ViewGroup mContentView;
    private int[] mBalloonColours = new int[3];
    private int mNextColor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mBalloonColours[0] = Color.argb(255,255,0,0);
        mBalloonColours[1] = Color.argb(255,0,255,0);
        mBalloonColours[2] = Color.argb(255,0,0,255);

        getWindow().setBackgroundDrawableResource(R.drawable.modern_background);
        mContentView = (ViewGroup) findViewById(R.id.activity_main);

        mContentView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                setToFullScreen();
            }
        });

        mContentView.setOnTouchListener(new View.OnTouchListener(){
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if(motionEvent.getAction() == MotionEvent.ACTION_UP){
                    Balloon b = new Balloon(MainActivity.this, mBalloonColours[mNextColor], 100);
                    b.setX(motionEvent.getX());
                    b.setY(motionEvent.getY());
                    mContentView.addView(b);

                    if(mNextColor + 1 == mBalloonColours.length){
                        mNextColor=0;
                    }
                    else {
                        mNextColor ++;
                    }
                }
                return false;
            }
        });
    }

    private void setToFullScreen(){
        ViewGroup rootLayout = (ViewGroup) findViewById(R.id.activity_main);

        rootLayout.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
    }

    @Override
    protected void onResume() {
        super.onResume();
        setToFullScreen();
    }
}
