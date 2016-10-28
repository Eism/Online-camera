package com.example.onlinecamera.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.onlinecamera.R;

public class ErrorActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_error);
    }

    @Override
    public void onBackPressed() {
        finishActivity(0);
        finish();
        moveTaskToBack(true);
    }
}
