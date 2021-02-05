package com.osahonojo.emailprototype;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.appcompat.widget.Toolbar;

import androidx.core.view.GravityCompat;

import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;

import android.view.MenuItem;

import android.widget.FrameLayout;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nav_drawer);

        // set toolbar as the app bar for this activity
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawerLayout = findViewById(R.id.drawerLayout);

        // navigationView contains the header and menu that appear in the drawer
        navigationView = findViewById(R.id.navView);
        navigationView.setNavigationItemSelectedListener(this);

        // set default fragment on initial fragment creation as the inbox fragment
        if (savedInstanceState == null) {
            // make fragment, update toolbar title
            onNavigationItemSelectedUtil(R.id.fragment_placeholder,
                    new InboxFragment(),
                    R.string.inbox,
                    R.id.menu_inbox);
        }

        // set up hamburger menu icon
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,
                drawerLayout,
                toolbar,
                R.string.nav_drawer_open,
                R.string.nav_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState(); // sync the state of the hamburger icon and the navigation drawer

    }

    @Override
    public boolean onNavigationItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.menu_inbox:
                // make fragment, update toolbar title
                onNavigationItemSelectedUtil(R.id.fragment_placeholder,
                        new InboxFragment(),
                        R.string.inbox,
                        R.id.menu_inbox);
                break;

            case R.id.menu_sent:
                // make fragment, update toolbar title
                onNavigationItemSelectedUtil(R.id.fragment_placeholder,
                        new SentFragment(),
                        R.string.sent,
                        R.id.menu_sent);
                break;

            default:
                break;
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed(){
        if (drawerLayout.isDrawerOpen(GravityCompat.START))
            drawerLayout.closeDrawer(GravityCompat.START);
        else
            super.onBackPressed();
    }

    // make fragment, update toolbar title
    private void onNavigationItemSelectedUtil(int layoutToBeReplaced, Fragment fragment, int toolbarTitle, int menuItem) {
        getSupportFragmentManager().beginTransaction()
                .replace(layoutToBeReplaced, fragment)
                .commit();
        toolbar.setTitle(getResources().getText(toolbarTitle));
        navigationView.setCheckedItem(menuItem);
    }
}