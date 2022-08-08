package com.example.myapplication;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.zxing.Result;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class qrscanner extends AppCompatActivity implements ZXingScannerView.ResultHandler
{
    FirebaseUser user;
    FirebaseAuth mAuth;
    ZXingScannerView scannerView;
    DatabaseReference testLaitDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        scannerView = new ZXingScannerView(this);
        setContentView(scannerView);
        //testLaitDb = FirebaseDatabase.getInstance().getReference("Tank");

           mAuth =FirebaseAuth.getInstance();
           user = mAuth.getCurrentUser();


        Dexter.withContext(getApplicationContext())
                .withPermission(Manifest.permission.CAMERA)
                .withListener(new PermissionListener() {
                    @Override
                    public void onPermissionGranted(PermissionGrantedResponse permissionGrantedResponse) {
                         scannerView.startCamera();
                    }

                    @Override
                    public void onPermissionDenied(PermissionDeniedResponse permissionDeniedResponse) {

                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(PermissionRequest permissionRequest, PermissionToken permissionToken) {
                     permissionToken.continuePermissionRequest();
                    }
                }).check();

    }


    @Override
    public void handleResult(Result rawResult) {
        String data = rawResult.getText().toString();

        DatabaseReference userLogedDb = FirebaseDatabase.getInstance().getReference("userLoged");
        DatabaseReference emailDb =userLogedDb.child("email");
        DatabaseReference testLaitDb1 = userLogedDb.child("Tank");
        testLaitDb = testLaitDb1;

        testLaitDb.push().setValue(data)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(qrscanner.this);
                        builder.setTitle("Resultat du Tank");
                        builder.setMessage(rawResult.getText());
                        builder.setNegativeButton("annuler", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                                finish();
                            }
                        });
                        builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Toast.makeText(qrscanner.this, "Ajout effectue avec succés", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(qrscanner.this ,TestLaitActivity.class));


                            }
                        }).show();
                        // DashbordActivity.qrtext.setText(rawResult.getText());
                        //onBackPressed();

                    }
                });

       emailDb.child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                .setValue(user.getEmail());

    }

    @Override
    protected void onPause() {
        super.onPause();
        scannerView.stopCamera();
    }

    @Override
    protected void onResume() {
        super.onResume();
        scannerView.setResultHandler(this);
        scannerView.startCamera();
    }


}