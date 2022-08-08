package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class DrawBaseActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    FirebaseUser user;
    private DatabaseReference reference;
    private String userID;

    DrawerLayout drawerLayout;
    FirebaseAuth mAuth;

    @Override
    public void setContentView(View view) {
        drawerLayout = (DrawerLayout) getLayoutInflater().inflate(R.layout.activity_draw_base, null);
        FrameLayout container = drawerLayout.findViewById(R.id.activityContainer);
         container.addView(view);
        super.setContentView(drawerLayout);
        Toolbar toolbar = drawerLayout.findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);
        mAuth =FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();

        NavigationView navigationView = drawerLayout.findViewById(R.id.nav_view);
        navigationView.setItemIconTintList(null);
        navigationView.setNavigationItemSelectedListener(this);
        updateNavHeader();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawerLayout, toolbar,R.string.neu_drawer_open,R.string.neu_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        drawerLayout.closeDrawer(GravityCompat.START);
        mAuth = FirebaseAuth.getInstance();
        switch (item.getItemId()){

            case R.id.nav_Laite:
                startActivity(new Intent(this, DashbordActivity.class));
                overridePendingTransition(0,0);
                break;

            case R.id.nav_Contactez:
                startActivity(new Intent(this, ContactusActivity.class));
                overridePendingTransition(0,0);
                break;

            case R.id.nav_Apropos:
                startActivity(new Intent(this, AboutusActivity.class));
                overridePendingTransition(0,0);
                break;

            case R.id.nav_Deconnexion:
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(this, SignInActivity.class));
                Toast.makeText(this,"Vous avez été déconnecté avec succès",Toast.LENGTH_LONG).show();
                overridePendingTransition(0,0);
                break;
        }

        return false;
    }

    protected void allocateActivityTitle(String titleString){

        if (getSupportActionBar() !=null){
            getSupportActionBar().setTitle(titleString);
        }
    }

    public void updateNavHeader(){
        NavigationView navigationView = drawerLayout.findViewById(R.id.nav_view);
        View headerView = navigationView.getHeaderView(0);
        TextView navEmail = headerView.findViewById(R.id.emailadress);
        navEmail.setText(user.getEmail());


    }
}