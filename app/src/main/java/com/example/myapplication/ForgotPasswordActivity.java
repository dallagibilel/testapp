package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ForgotPasswordActivity extends AppCompatActivity {

    EditText editTextEmail;
    FirebaseAuth mAuth;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        editTextEmail = (EditText) findViewById(R.id.editTextForgotPasswordEmail);
        //progressBar = (ProgressBar) findViewById(R.id.forgotPasswordActivity);
        mAuth = FirebaseAuth.getInstance();

    }

    public void forgetPasswordResetBtnPressed(View v){
        resetPassword();

    }
    private void resetPassword(){
        String txtmail = editTextEmail.getText().toString().trim();
        if (!Patterns.EMAIL_ADDRESS.matcher(txtmail).matches()

        ) {editTextEmail.setError("Please Enter Valid Email");
            editTextEmail.requestFocus();
            return;
        }

        progressBar.setVisibility(View.VISIBLE);
        mAuth.sendPasswordResetEmail(txtmail).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()){
                    Toast.makeText(ForgotPasswordActivity.this, "Please check you Email to reset Password",Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(ForgotPasswordActivity.this, SignInActivity.class);
                    startActivity(intent);
                    progressBar.setVisibility(View.GONE);
                }
                else {
                    progressBar.setVisibility(View.GONE);
                    Toast.makeText(ForgotPasswordActivity.this, "Failed  to reset Password",Toast.LENGTH_LONG).show();

                }
            }
        });

    }

}