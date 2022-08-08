package com.example.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.myapplication.databinding.ActivityAboutusBinding;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AboutusActivity extends DrawBaseActivity {

    ActivityAboutusBinding activityAboutusBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        activityAboutusBinding=ActivityAboutusBinding.inflate(getLayoutInflater());
        setContentView(activityAboutusBinding.getRoot());
        allocateActivityTitle("À propos");

    }
}