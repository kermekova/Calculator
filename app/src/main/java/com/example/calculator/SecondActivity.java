package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {
    TextView resultView;
    Button buttonNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_activity);

        Intent intent = getIntent();
        int result = intent.getIntExtra("result", 0);

        resultView = findViewById(R.id.result_textView);
        resultView.setText(String.valueOf(result));
    }

    public void clickNext(View view) {
        finish();
    }
}
