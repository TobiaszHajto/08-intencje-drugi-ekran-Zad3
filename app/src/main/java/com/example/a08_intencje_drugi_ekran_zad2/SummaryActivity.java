package com.example.a08_intencje_drugi_ekran_zad2;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class SummaryActivity extends AppCompatActivity {

    public static final String EXTRA_SERVICE = "SERVICE";
    public static final String EXTRA_DATE = "DATE";
    public static final String EXTRA_NAME = "NAME";
    public static final String EXTRA_PHONE = "PHONE";
    public static final String EXTRA_FIRST_VISIT = "FIRST_VISIT";
    public static final String EXTRA_NUMBER = "NUMBER";

    private TextView tvSummary;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary);

        tvSummary = findViewById(R.id.tvSummary);
        tvSummary.setText(pobierzPodsumowanie());

        findViewById(R.id.btnEdit).setOnClickListener(v -> poprawDane());
        findViewById(R.id.btnConfirm).setOnClickListener(v -> potwierdz());
        findViewById(R.id.btnShare).setOnClickListener(v -> udostepnij());
    }

    private String pobierzPodsumowanie() {

        Intent intent = getIntent();

        String service = intent.getStringExtra(EXTRA_SERVICE);
        String date = intent.getStringExtra(EXTRA_DATE);
        String name = intent.getStringExtra(EXTRA_NAME);
        String phone = intent.getStringExtra(EXTRA_PHONE);

        boolean firstVisit = intent.getBooleanExtra(EXTRA_FIRST_VISIT, false);

        String firstVisitText;

        if (firstVisit) {
            firstVisitText = getString(R.string.yes_short);
        } else {
            firstVisitText = getString(R.string.no_short);
        }

        return getString(
                R.string.summary_format,
                service,
                date,
                name,
                phone,
                firstVisitText
        );
    }

    private void potwierdz() {

        String number = "RW/" + (1000 + new Random().nextInt(9000));

        Intent result = new Intent();
        result.putExtra(EXTRA_NUMBER, number);

        setResult(RESULT_OK, result);
        finish();
    }

    private void poprawDane() {
        setResult(RESULT_CANCELED);
        finish();
    }

    private void udostepnij() {

        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT, tvSummary.getText().toString());

        try {
            startActivity(Intent.createChooser(
                    intent,
                    getString(R.string.share_via)
            ));
        } catch (ActivityNotFoundException e) {
            Toast.makeText(
                    this,
                    R.string.error_no_app,
                    Toast.LENGTH_SHORT
            ).show();
        }
    }
}