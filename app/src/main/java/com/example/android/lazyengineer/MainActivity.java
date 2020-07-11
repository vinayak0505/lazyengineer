package com.example.android.lazyengineer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.View.OnClickListener;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.lazyengineer.Login.Login;

public class MainActivity extends AppCompatActivity {


    @SuppressLint("WrongConstant")
    @Override
    public void onBackPressed() {
        DrawerLayout mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        if(mDrawerLayout.isDrawerOpen(Gravity.START)) {
            mDrawerLayout.closeDrawer(Gravity.START);
        }
        else super.onBackPressed();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_drawer);
        if(Login.status) {
            openLoginPage();
        }
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final TextView text1 = findViewById(R.id.Text1);
        text1.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent text1Intent = new Intent( MainActivity.this,Notes.class);
                startActivity(text1Intent);
            }
        });

        ImageView imageView = findViewById(R.id.menu);
        imageView.setOnClickListener(new OnClickListener() {
            @SuppressLint("WrongConstant")
            @Override
            public void onClick(View v) {
                DrawerLayout mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
                mDrawerLayout.openDrawer(Gravity.START);
            }
        });

    }





    private void openLoginPage() {
        Intent intent = new Intent(MainActivity.this, Login.class);
        startActivity(intent);
        //open ups the login page
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
        // this will create 3 dots for me
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        return super.onOptionsItemSelected(item);
        //this will check for the option inside are menu option
    }
}

