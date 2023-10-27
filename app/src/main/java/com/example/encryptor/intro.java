package com.example.encryptor;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

public class intro extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.intro);

        new Handler().postDelayed(new Runnable() {
            @Override public void run() {
                Intent i = new Intent(intro.this, MainActivity.class); startActivity(i);
                finish(); } }, 2000);

    }
}
