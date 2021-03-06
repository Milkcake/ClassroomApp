package com.example.riko.classroomapplication;

//-- Toolbar & DrawerLayout --//
import android.content.Intent;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
//---------------------------//

public class TeacherActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    //<------------------------------------------------>
    final String TAG = "TTwTT";
    //-- DrawerLayout --***//
    private DrawerLayout drawerLayout;
    private TextView textUsername;
    private TextView textStatus;
    private TextView textName;
    private Toolbar toolbar;
    private NavigationView navigationView;
    private View headerView;
    //<------------------------------------------------>



    private boolean doubleBackToExitPressedOnce;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher);

        initInstance();
        displayDrawerLayout();
        initFirebase();

        //------ Replace null Fragment -----***//
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new TeacherCoursesFragment()).commit();
            navigationView.setCheckedItem(R.id.nav_coures);
        }

    }


    //<------------------------------- Pattern drawerLayout ---------------------------------------->//
    private void initInstance() {
        // TODO;
        //-- Toolbar & DrawerLayout --***//
        toolbar = findViewById(R.id.toolbar);
        drawerLayout = findViewById(R.id.drawerLayout);
        navigationView = findViewById(R.id.nav_view);
        //-----------------------------------------------//
        //------------ Receive Intent from SignIn ------------***//
        headerView = navigationView.getHeaderView(0);
        textUsername = headerView.findViewById(R.id.txtUsername);
        textStatus = headerView.findViewById(R.id.txtStatus);
        textName = headerView.findViewById(R.id.txtName);
        //------------------------------------------------------//
    }


    private void displayDrawerLayout() {
        setSupportActionBar(toolbar);
        navigationView.setNavigationItemSelectedListener(this);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
    }

    private void initFirebase() {
        //Init Firebase SignIn
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference table_member = database.getReference("Member");
        table_member.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Intent intent = getIntent();
                String userName = intent.getStringExtra("Username");
                String status = intent.getStringExtra("Status");
                String name = intent.getStringExtra("Name");
                textUsername.setText(userName);
                textStatus.setText(status);
                textName.setText(name);
                Log.d(TAG, String.valueOf(textUsername));
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }

    //-- Toolbar & DrawerLayout --***//
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.nav_coures:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new TeacherCoursesFragment()).commit();
                break;
            case R.id.nav_changepw:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new ChangepwFragment()).commit();
                break;
            case R.id.nav_deleteaccount:
                Toast.makeText(this, "Delete Account", Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_logout:
                signOut();
                Toast.makeText(this, "Sign out", Toast.LENGTH_SHORT).show();
                break;
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
    private void signOut() {
        Intent intent = new Intent(TeacherActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    //--------- Back Press --------------------***//
    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else if (doubleBackToExitPressedOnce){
            super.onBackPressed();
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_HOME);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            finish();
            System.exit(1);
            return;
        }
        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce=false;
            }
        }, 2000);
    }
    //-----------------------------------------------//
    //<---------------------------------------------------------------------------------------------------->//
    /*
    *
    *
    *
    *
    *
    */
    //--------- Function ---------------------------//




}
