package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignInAdminActivity extends AppCompatActivity {

    EditText editTextUserName;
    EditText editTextPassword;
    TextView textViewForgotPassword;
    ProgressBar progressBar;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in_admin);
        editTextUserName = (EditText) findViewById(R.id.textLogin);
        editTextPassword = (EditText) findViewById(R.id.textPassword);
        textViewForgotPassword = (TextView) findViewById(R.id.forgot);
        progressBar = (ProgressBar) findViewById(R.id.progressBarSignIn);
        mAuth = FirebaseAuth.getInstance();
    }

    public void buttonSignInAdminScrSignClicked(View v){

        String userName = editTextUserName.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();

        if(!Patterns.EMAIL_ADDRESS.matcher(userName).matches()){
            editTextUserName.setError("Saisissez une adresse e-mail valide");
            editTextUserName.requestFocus();
        }
        if (password.isEmpty()){
            editTextPassword.setError("Le mot de passe est obligatoire");
            editTextPassword.requestFocus();
            return;
        }
        if(password.length()< 6 ){
            editTextPassword.setError("au minimum 6 caractères");
            editTextPassword.requestFocus();
        }

        progressBar.setVisibility(View.VISIBLE);
        mAuth.signInWithEmailAndPassword(userName, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    progressBar.setVisibility(View.GONE);
                    Toast.makeText(SignInAdminActivity.this,"L'utilisateur s'est connecté avec succès",Toast.LENGTH_LONG).show();
                    startActivity(new Intent(SignInAdminActivity.this ,SignUpUserActivity.class));
                }
                else {
                    progressBar.setVisibility(View.GONE);
                    Toast.makeText(SignInAdminActivity.this,"L'utilisateur a échoué à se connecter",Toast.LENGTH_LONG).show();


                }
            }
        });




    }

}