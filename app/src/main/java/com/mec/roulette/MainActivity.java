package com.mec.roulette;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    
    private TextView resultTV;
    private ImageView rouletteImg;
    private Random mRandom = new Random();
    private int mDegree = 0;
    private static final float FACTOR = 360 / 37f;
    private String[] numbers = new String[]{"0", "26", "3", "35", "12", "28", "7", "29", "18",
            "22", "9", "31", "14", "20", "1", "33", "16", "24", "5", "10", "23", "8", "30",
            "11", "36", "13", "27", "6", "34", "17", "25", "2", "21", "4", "19", "15", "32"};
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }
    
    public void init() {
        resultTV = findViewById(R.id.resultTv);
        rouletteImg = findViewById(R.id.rouletteImg);
    }
    
    
    public void onClickStart(View view) {
        int oldDegree = mDegree % 360;
        mDegree = mRandom.nextInt(3600) + 720;
        RotateAnimation animation = getRotateAnimation(oldDegree);
        rouletteImg.startAnimation(animation);
    }
    
    private RotateAnimation getRotateAnimation(int oldDegree) {
        RotateAnimation animation = new RotateAnimation(oldDegree, mDegree,
                RotateAnimation.RELATIVE_TO_SELF, 0.5f,
                RotateAnimation.RELATIVE_TO_SELF, 0.5f);
        animation.setDuration(4000);
        animation.setFillAfter(true);
        animation.setInterpolator(new DecelerateInterpolator());
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                resultTV.setText("");
            }
            
            @Override
            public void onAnimationEnd(Animation animation) {
                resultTV.setText(getResult());
            }
            
            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });
        return animation;
    }
    
    private String getResult() {
        String result;
        float degree = (mDegree + FACTOR / 2) % 360;
        int count = (int) (degree / FACTOR);
        if (count == 0) {
            result = "Zero";
            resultTV.setTextColor(Color.GREEN);
        } else if (count % 2 == 0) {
            result = numbers[count] + " Красный";
            resultTV.setTextColor(Color.RED);
        } else {
            result = numbers[count] + " Черный";
            resultTV.setTextColor(Color.BLACK);
        }
        return result;
    }
    
}
