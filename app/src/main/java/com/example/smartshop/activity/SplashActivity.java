package com.example.smartshop.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.smartshop.MainActivity;
import com.example.smartshop.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SplashActivity extends AppCompatActivity {

    @BindView(R.id.splash_img)
    ImageView splashImg;

    @BindView(R.id.splash_text)
    TextView splashText;

    SharedPreferences spf;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ButterKnife.bind (this);
        getSupportActionBar().hide();

        spf=getSharedPreferences("user_detail",MODE_PRIVATE);

        Thread thread=new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                    if(spf.contains("userID")){
                        Intent intent=new Intent(SplashActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    }
                    else {
                        Intent intent=new Intent(SplashActivity.this, LoginActivity.class);
                        startActivity(intent);
                        finish();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();
        getSupportActionBar().hide();
        Animation splashani= AnimationUtils.loadAnimation(this, R.anim.myanimation);
        Animation splashani1= AnimationUtils.loadAnimation(this, R.anim.blind_anim);
        splashImg.setAnimation(splashani);
        splashText.setAnimation(splashani1);
    }
}
