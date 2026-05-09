package com.sara.navigationdrawerdemo;

import android.os.Bundle;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

import androidx.fragment.app.Fragment;

public class MainActivity extends AppCompatActivity {

    DrawerLayout drawerLayout;
    NavigationView navView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawerLayout = findViewById(R.id.drawer_layout);
        navView = findViewById(R.id.nav_view);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar,
                R.string.open, R.string.close);

        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        // 🔥 AJOUT IMPORTANT → fragment par défaut
        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, new HomeFragment())
                    .commit();

            navView.setCheckedItem(R.id.nav_home);
        }

        navView.setNavigationItemSelectedListener(item -> {

            Fragment fragment = null;

            if (item.getItemId() == R.id.nav_home) {
                fragment = new HomeFragment();
            } else if (item.getItemId() == R.id.nav_profile) {
                fragment = new ProfileFragment();
            } else if (item.getItemId() == R.id.nav_list) {
                fragment = new ListFragment();
            }

            if (fragment != null) {
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_container, fragment)
                        .commit();
            }

            drawerLayout.closeDrawers();
            return true;
        });
    }
}