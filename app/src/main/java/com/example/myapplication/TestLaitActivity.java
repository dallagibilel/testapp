package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.databinding.ActivityTestLaitBinding;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DecimalFormat;

public class TestLaitActivity extends DrawBaseActivity {
    ActivityTestLaitBinding activityTestLaitBinding;

    String[] spinAlcool = {"","Negatif","Positif"};
    String[] spinAtb = {"","Negatif","Positif"};

    String[] spinStatut = {"","Enlevé","Nonn"};
    String[] spinCompar = {"","C1","C2","C3","C4","C5","C6"};
    DecimalFormat num = new DecimalFormat(".000");
    AutoCompleteTextView autoCompleteTxt,autoCompleteTxt2,autoCompleteTxt3,autoCompleteTxt4;
    ArrayAdapter<String> adapterItems,adapterItemsAtb,adapterItemsStatu,adapterItemsCompar;

    EditText txtDensite,txtJauge,txtVolume;
    Button button;
    String item,itematb;
    String itemStatu,itemCompar;
    Testlait testlait;

    ProgressBar progressBar;

    DatabaseReference databaseReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityTestLaitBinding = ActivityTestLaitBinding.inflate(getLayoutInflater());
        setContentView(activityTestLaitBinding.getRoot());
        allocateActivityTitle("Test");

        button = findViewById(R.id.save);
        progressBar = (ProgressBar) findViewById(R.id.progressBarSignIn);

        autoCompleteTxt = findViewById(R.id.auto_complete_txt);
        autoCompleteTxt2 = findViewById(R.id.auto_complete_txt2);

        autoCompleteTxt3 = findViewById(R.id.auto_complete_txt3);
        autoCompleteTxt4 = findViewById(R.id.auto_complete_txt4);

        txtDensite = (EditText) findViewById(R.id.textDensite);
        txtJauge  = (EditText) findViewById(R.id.textJauge);
        txtVolume = (EditText) findViewById(R.id.textvolume);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference("value");


        testlait = new Testlait();

        adapterItems = new ArrayAdapter<String>(this,R.layout.list_item,spinAlcool);
        autoCompleteTxt.setAdapter(adapterItems);

        adapterItemsAtb = new ArrayAdapter<String>(this,R.layout.list_item,spinAtb);
        autoCompleteTxt2.setAdapter(adapterItemsAtb);

        adapterItemsStatu = new ArrayAdapter<String>(this,R.layout.list_item,spinStatut);
        autoCompleteTxt3.setAdapter(adapterItemsStatu);

        adapterItemsCompar = new ArrayAdapter<String>(this,R.layout.list_item,spinCompar);
        autoCompleteTxt4.setAdapter(adapterItemsCompar);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //progressBar.setVisibility(View.VISIBLE);

                String textDensite = txtDensite.getText().toString().trim();
                String textJauge = txtJauge.getText().toString().trim();
                String textVolume = txtVolume.getText().toString().trim();

                //SaveValue();
                //SaveValue();

                if (item ==null || item==""){
                    autoCompleteTxt.setError("Alcool est obligatoire");
                    autoCompleteTxt.requestFocus();
                    return;
                }
                if (itematb==null || itematb==""){
                    autoCompleteTxt2.setError("A-T-B est obligatoire");
                    autoCompleteTxt2.requestFocus();
                    return;
                }
                if (itemStatu==null || itemStatu==""){
                    autoCompleteTxt3.setError("Statut est obligatoire");
                    autoCompleteTxt3.requestFocus();
                    return;
                }
                if (itemCompar==null || itemCompar==""){
                    autoCompleteTxt4.setError("Compartiment est obligatoire");
                    autoCompleteTxt4.requestFocus();
                    return;
                }
                if (textDensite.isEmpty()){
                    txtDensite.setError("Densite est obligatoire");
                    txtDensite.requestFocus();
                    return;
                }

                if (textJauge.isEmpty()){
                    txtJauge.setError("Jauge est obligatoire");
                    txtJauge.requestFocus();
                    return;
                }
                if (textVolume.isEmpty()){
                    txtVolume.setError("Volume est obligatoire");
                    txtVolume.requestFocus();
                    return;
                }
                else
                {
                    testlait.setSpinAlcool(item);
                    testlait.setSpinAtb(itematb);
                    testlait.setSpinStatut(itemStatu);
                    testlait.setSpinCompar(itemCompar);
                    testlait.setTextDensite(textDensite);
                    testlait.setTextJauge(textJauge);
                    testlait.setTextVolume(textVolume+"L");

                    String id= databaseReference.push().getKey();
                    databaseReference.child(id).setValue(testlait);
                    Toast.makeText(TestLaitActivity.this, "mrigelll", Toast.LENGTH_SHORT).show();
                    //startActivity(new Intent(SignUpUserActivity.this ,SignInActivity.class));
                    //progressBar.setVisibility(View.GONE);
                    startActivity(new Intent(TestLaitActivity.this , DashbordActivity.class));
                }

                /*if (itematb=="null"){
                    Toast.makeText(TestLaitActivity.this, "widd thabet fel theni ", Toast.LENGTH_SHORT).show();
                }else {
                    testlait.setSpinAtb(itematb);
                    String id= databaseReference.push().getKey();
                    databaseReference.child(id).setValue(testlait);
                    Toast.makeText(TestLaitActivity.this, "mrigelllssss", Toast.LENGTH_SHORT).show();

                }*/

            }
        });

        autoCompleteTxt.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               // String item = parent.getItemAtPosition(position).toString();
                String spinAlcool = ((TextView) view).getText().toString();
                item = spinAlcool;
               // Toast.makeText(getApplicationContext(),"Item: "+item,Toast.LENGTH_SHORT).show();
            }
        });

        autoCompleteTxt2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // String item = parent.getItemAtPosition(position).toString();
                String spinatb = ((TextView) view).getText().toString();
                itematb = spinatb;
                //Toast.makeText(getApplicationContext(),"Item: "+item,Toast.LENGTH_SHORT).show();
            }
        });

        autoCompleteTxt3.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String spinstatu = ((TextView) view).getText().toString();
                itemStatu = spinstatu;
                //Toast.makeText(getApplicationContext(),"Item: "+item,Toast.LENGTH_SHORT).show();

            }
        });

        autoCompleteTxt4.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // String item = parent.getItemAtPosition(position).toString();
                String spincom = ((TextView) view).getText().toString();
                itemCompar = spincom;
                //Toast.makeText(getApplicationContext(),"Item: "+item,Toast.LENGTH_SHORT).show();
            }
        });






    }

    /*private void SaveValue(String ) {
        if (item =="Alcool"){
            Toast.makeText(this, "pleaseee check", Toast.LENGTH_SHORT).show();
    }
        else{
            testlait.setSpinAlcool(item);
            String id= databaseReference.push().getKey();
            databaseReference.child(id).setValue(testlait);
            finish();
            Toast.makeText(this, "mrigelll", Toast.LENGTH_SHORT).show();

        }


    }*/
}