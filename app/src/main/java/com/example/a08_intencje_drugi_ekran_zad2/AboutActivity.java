package com.example.a08_intencje_drugi_ekran_zad2;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

public class AboutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.d("CYKL", "AboutActivity: onCreate");

        setContentView(R.layout.activity_about);
    }

    @Override
    protected void onStop() {
        Log.d("CYKL", "AboutActivity: onStop");
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        Log.d("CYKL", "AboutActivity: onDestroy");
        super.onDestroy();
    }
}