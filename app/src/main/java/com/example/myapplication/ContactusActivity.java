package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.myapplication.databinding.ActivityContactusBinding;
import com.example.myapplication.databinding.ActivityDashbordBinding;

public class ContactusActivity extends DrawBaseActivity {

    ActivityContactusBinding activityContactusBinding;
    ImageView facebook;
    ImageView youtube;
    ImageView linkdin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        activityContactusBinding = ActivityContactusBinding.inflate(getLayoutInflater());
        setContentView(activityContactusBinding.getRoot());
        allocateActivityTitle("Contactez nous");

        facebook = (ImageView) findViewById(R.id.facebookid);
        youtube = (ImageView) findViewById(R.id.youtubeid);
        linkdin = (ImageView) findViewById(R.id.linkdinid);

        facebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoUrl("https://www.facebook.com/delicedano/");
            }
        });

        youtube.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoUrl("https://www.youtube.com/user/YaourtDeliceTunisie");
            }
        });
        linkdin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoUrl("https://fr.linkedin.com/company/d-lice-danone");
            }
        });


    }

    private void gotoUrl(String s) {

        Uri uri = Uri.parse(s);
        startActivity(new Intent(Intent.ACTION_VIEW,uri));

    }
}