package com.example.ticams;

import android.support.v4.app.Fragment;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.ticams.Dtos.LoginDto;
import com.example.ticams.services.ApiClient;
import com.example.ticams.services.ApiInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

   NavigationView navigationView;
    public Fragment fragment;
    private DrawerLayout drawer;
    private ConstraintLayout nav_header;
    private Long customerId;
    private Long userId;
    private String tokenExpected;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.navigation_view);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView = findViewById(R.id.nav_view);
        drawer = findViewById(R.id.drawer_layout);
        View mView = navigationView.getHeaderView(0);
        nav_header = mView.findViewById(R.id.nav_header);
        nav_header.setScrollContainer(true);

        fragment = new MainFragment();
        SupportActionBarInitializer.setSupportActionBarTitle(getSupportActionBar(), "Dashboard");
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.content_frame,fragment);
            /*if(!fragment.equals(MainFragment.class)){
                ft.addToBackStack("previous");
            }*/
        ft.commit();





    }



    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return false;
    }


}
