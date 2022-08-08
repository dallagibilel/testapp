package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.webkit.WebView;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class SignUpUserActivity extends AppCompatActivity {

    EditText signUpEmail;
    EditText signUpNom;
    EditText signUpPrenom;
    EditText signUpNumero;
    EditText signUpPass;
    EditText signUpConfPass;
    ProgressBar progressBar;
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_user);
        signUpEmail = (EditText) findViewById(R.id.textemail);
        signUpNom = (EditText) findViewById(R.id.textnom);
        signUpPrenom =(EditText) findViewById(R.id.textprenom);
        signUpNumero=(EditText) findViewById(R.id.textnumero);
        signUpPass=(EditText)findViewById(R.id.textPassword);
        signUpConfPass=(EditText) findViewById(R.id.textconfpassword);
        progressBar = (ProgressBar) findViewById(R.id.progressBarSignIn);
        mAuth = FirebaseAuth.getInstance();
    }

    public void buttonSignUpClicked(View v){

        String email = signUpEmail.getText().toString().trim();
        String nom = signUpNom.getText().toString().trim();
        String prenom = signUpPrenom.getText().toString().trim();
        String numero = signUpNumero.getText().toString().trim();
        String password = signUpPass.getText().toString().trim();
        String confpass = signUpConfPass.getText().toString().trim();

        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches() || email.isEmpty()){
            signUpEmail.setError("Saisissez une adresse e-mail valide »");
            signUpEmail.requestFocus();
            return;
        }
        if (nom.isEmpty()){
            signUpNom.setError("Le nom est obligatoire");
            signUpNom.requestFocus();
            return;
        }else if (nom.length() >10){
            signUpNom.setError("Maximum 8 caracteres");
            signUpNom.requestFocus();
            return;
        }
        if (prenom.isEmpty()){
            signUpPrenom.setError("Le prenom est obligatoire");
        }else if (prenom.length()>10){
            signUpPrenom.setError("8 caracteres maximum");
            signUpPrenom.requestFocus();
            return;
        }
        if (numero.isEmpty()){
            signUpNumero.setError("Le numero est obligatoire");
            signUpNumero.requestFocus();
            return;
        }else if ((numero.length() !=8)){
            signUpNumero.setError("Ce champ contient 8 caracteres");
            signUpNumero.requestFocus();
            return;
        }
        if (password.isEmpty()){
            signUpPass.setError("Le mot de passe est obligatoire");
            signUpPass.requestFocus();
            return;
        }

        if(password.length()< 6){
            signUpPass.setError("au minimum 6 caractères");
            signUpPass.requestFocus();
        }
        if (confpass.isEmpty()){
            signUpConfPass.setError("Le mot de passe est obligatoire");
            signUpConfPass.requestFocus();
            return;
        }
        if (!confpass.matches(password)){
            signUpConfPass.setError("Mot de passe incorrect");
            signUpConfPass.requestFocus();
            return;
        }

        progressBar.setVisibility(View.VISIBLE);
        mAuth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()){
                            User user = new User(email,nom,prenom,numero,password,confpass);
                            FirebaseDatabase.getInstance().getReference("Users")
                                            .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                                    .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if (task.isSuccessful()){
                                                Toast.makeText(SignUpUserActivity.this,"Inscription a été effectué avec succès",Toast.LENGTH_LONG).show();
                                                startActivity(new Intent(SignUpUserActivity.this ,SignInActivity.class));
                                                progressBar.setVisibility(View.GONE);
                                            }
                                            else {
                                                Toast.makeText(SignUpUserActivity.this,"Échec de l'inscription",Toast.LENGTH_LONG).show();
                                                progressBar.setVisibility(View.GONE);
                                            }
                                        }
                                    });
                        }
                        else{
                            Toast.makeText(SignUpUserActivity.this,"Échec de l'inscription veuillez réessayer",Toast.LENGTH_LONG).show();
                            progressBar.setVisibility(View.GONE);
                        }


                    }
                });


    }

}