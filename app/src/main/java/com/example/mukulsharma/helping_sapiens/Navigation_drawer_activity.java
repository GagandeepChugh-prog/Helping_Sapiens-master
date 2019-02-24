package com.example.mukulsharma.helping_sapiens;


import android.app.ActionBar;
import android.app.Fragment;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.app.FragmentTransaction;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;



public class  Navigation_drawer_activity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    android.support.v7.app.ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_drawer_activity);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
       // displaySelectedScreen(R.id.glucose);
        actionBar=getSupportActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#ff067c")));


    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.navigationdrawer, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void displaySelectedScreen(int id){
        Fragment fragment=null;
        switch (id)
        {
            case R.id.glucose:
                Intent a=new Intent(Navigation_drawer_activity.this,Submit_Request.class);
                Bundle bundle=getIntent().getExtras();
                String email=bundle.getString("Email");
                a.putExtra("Email",email);
                startActivity(a);
                break;
            case R.id.bp:
                Intent in=new Intent(Navigation_drawer_activity.this,Read_Request.class);
                Bundle bund=getIntent().getExtras();
                String emails=bund.getString("Email");
                in.putExtra("Email",emails);
                startActivity(in);
                break;
           case R.id.bodyweight:
               Intent show=new Intent(Navigation_drawer_activity.this,Pending_Request.class);
               Bundle bundl=getIntent().getExtras();
               String emailss=bundl.getString("Email");
               show.putExtra("Email",emailss);
               startActivity(show);
                break;

            case R.id.bmi:
                Intent showall=new Intent(Navigation_drawer_activity.this,View_All.class);
                startActivity(showall);
                break;
/*
            case R.id.reminder:
                Intent s=new Intent(graphactivity.this,TaskHome.class);
                startActivity(s);
                //fragment=new addreminder();
                break;

            case R.id.aboutus:
                fragment=new aboutus();
                break;
            case R.id.diet:
                fragment=new diet();
                break;
            case R.id.excercise:
                fragment=new excercise();
                break;

            case R.id.doctor:
                Intent g=new Intent(graphactivity.this,GoogleMapsActivity.class);
                startActivity(g);

            */
        }

        if(fragment!=null)
        {
            android.app.FragmentTransaction ft=getFragmentManager().beginTransaction();
            ft.replace(R.id.content_graphactivity,fragment);
            ft.commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        displaySelectedScreen(id);


        return true;
    }


}

