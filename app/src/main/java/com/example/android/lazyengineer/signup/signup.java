package com.example.android.lazyengineer.signup;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.android.lazyengineer.Login.LoginAsyncTask;
import com.example.android.lazyengineer.R;

public class signup extends AppCompatActivity {
    public static Activity activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        Button login = (Button) findViewById(R.id.SignUp);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                check();
            }
        });
    }
    private void check(){
        TextView textView = (TextView) findViewById(R.id.SignUpNotice);
        textView.setText("Loading");
        EditText signUpUserName = (EditText) findViewById(R.id.SignUpUserName);
        EditText phoneNumber = (EditText) findViewById(R.id.PhoneNumber);
        EditText signUpEmail = (EditText) findViewById(R.id.SignUpEmail);
        EditText password = (EditText) findViewById(R.id.Password);
        EditText confirmPassword = (EditText) findViewById(R.id.ConfirmPassword);
        String uri = "https://lazyengineer.tech/leapi/register/?username=" + signUpUserName.getText() + "&password=" + password.getText() + "&email=" + signUpEmail.getText() + "&phone=" + phoneNumber.getText();
        new SignUpAsyncTask(this).execute(uri);
    }
}