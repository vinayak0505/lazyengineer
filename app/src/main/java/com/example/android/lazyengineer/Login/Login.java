package com.example.android.lazyengineer.Login;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.android.lazyengineer.MainActivity;
import com.example.android.lazyengineer.Notes;
import com.example.android.lazyengineer.R;
import com.example.android.lazyengineer.signup.signup;


public class Login extends AppCompatActivity {

    public static boolean check = false;
    private String TAG = "Login2";
    public static boolean status = false;// check for login activity status
    public static String text;
    public static Activity activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity = this;
        setContentView(R.layout.activity_login);

        Button login = (Button) findViewById(R.id.Login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                check();
            }
        });

        TextView textView = (TextView) findViewById(R.id.LoginToSignUp);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this, signup.class);
            }
        });
    }

    private void check(){
        TextView textView = (TextView) findViewById(R.id.Notice);
        textView.setText("Loading");
        EditText userName = (EditText) findViewById(R.id.editTextTextPersonName);
        EditText password = (EditText) findViewById(R.id.CheckPassword);
        String uri = "https://lazyengineer.tech/leapi/login/?username=" + userName.getText() + "&password=" + password.getText();
        new LoginAsyncTask(this).execute(uri);
    }
}