package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.myapplication.databinding.ActivityDashbordBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;

public class DashbordActivity extends DrawBaseActivity {

    Button qrbtn;
    public static TextView qrtext;
    private FirebaseAuth mAuth;


    DatabaseReference testLaitDb;
    private ActivityDashbordBinding activityDashbordBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashbord);
        activityDashbordBinding = ActivityDashbordBinding.inflate(getLayoutInflater());
        setContentView(activityDashbordBinding.getRoot());
        allocateActivityTitle("Delice danone");

        qrbtn = (Button) findViewById(R.id.qrbtn);
        qrtext = (TextView) findViewById(R.id.qrtext);

        qrbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), qrscanner.class));
            }
        });
    }
}
