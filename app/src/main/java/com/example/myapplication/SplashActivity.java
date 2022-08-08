package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.SupportActionModeWrapper;

import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;

public class SplashActivity extends AppCompatActivity {

    LottieAnimationView animation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        //getSupportActionBar().hide();
        //navigation barre
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        TextView textView = findViewById(R.id.textView);
        TextView textView1 = findViewById(R.id.textView1);
        animation=findViewById(R.id.animation_view);


        textView.animate().translationX(1000).setDuration(1000).setStartDelay(3000);
        textView1.animate().translationX(1000).setDuration(1000).setStartDelay(3000);



        Thread thread = new Thread(){

            public void run (){
                try{
                    Thread.sleep(5500);
                }
                catch(Exception e){
                    e.printStackTrace();
                }
                finally {
                    Intent intent = new Intent(SplashActivity.this, WelcomeActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
    };
        thread.start();

    }
}