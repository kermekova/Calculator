package com.example.calculator;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private TextView textView;
    private String first, second;
    private boolean isOperationClick;
    private String operation;
    private double result;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.text_view);
        button = findViewById(R.id.btn_equal);
    }

    public void onNumberClick(View view) {
        String textButton = ((MaterialButton) view).getText().toString();
        if (textButton.equals("AC")) {
            textView.setText("0");
            first = "";
            second = "";
            result = 0;
            operation = null;
        } else if (textButton.equals(".")) {
            if (!textView.getText().toString().contains(".")) {
                textView.append(".");
            }
        } else if (textView.getText().toString().equals("0") || isOperationClick) {
            textView.setText(textButton);
        } else {
            textView.append(textButton);
        }
        isOperationClick = false;
    }

    public void onOperationClick(View view) {
        int id = view.getId();
        double num = Double.parseDouble(textView.getText().toString());

        if (id == R.id.btn_plus) {
            first = textView.getText().toString();
            operation = "+";
        } else if (id == R.id.btn_divide) {
            first = textView.getText().toString();
            operation = "/";
        } else if (id == R.id.btn_minus) {
            first = textView.getText().toString();
            operation = "-";
        } else if (id == R.id.btn_multiply) {
            first = textView.getText().toString();
            operation = "*";
        } else if (id == R.id.btn_equal) {
            second = textView.getText().toString();
            calculateResult();
        } else if (id == R.id.btn_percent) {
            double value = Double.parseDouble(textView.getText().toString()) / 100;
            textView.setText(String.valueOf(value));
        } else if (operation!= null){
            switch (operation) {
                case "+":
                    result = Double.parseDouble(first) + (Double.parseDouble(first) * num / 100);
                    break;
                case "-":
                    result = Double.parseDouble(first) - (Double.parseDouble(first) * num / 100);
                    break;
                case "*":
                    result = Double.parseDouble(first) * (num / 100);
                    break;
                case "/":
                    result = Double.parseDouble(first) / (num / 100);
                    break;
            }
        }
        else if (id == R.id.btn_plus_minus) {
            double value = Double.parseDouble(textView.getText().toString()) * -1;
            textView.setText(String.valueOf(value));
        }
        isOperationClick = true;
    }


    private void calculateResult() {
        if (operation != null) {
            if (first.contains(".") || second.contains(".")) {
                double firstDouble = Double.parseDouble(first);
                double secondDouble = Double.parseDouble(second);
                switch (operation) {
                    case "+":
                        result = firstDouble + secondDouble;
                        break;
                    case "-":
                        result = firstDouble - secondDouble;
                        break;
                    case "*":
                        result = firstDouble * secondDouble;
                        break;
                    case "/":
                        if (secondDouble == 0) {
                            textView.setText("Error");
                            return;
                        } else {
                            result = firstDouble / secondDouble;
                        }
                        break;
                }
                textView.setText(String.valueOf(result));
            } else {
                int firstInt = Integer.parseInt(first);
                int secondInt = Integer.parseInt(second);
                switch (operation) {
                    case "+":
                        result = firstInt + secondInt;
                        break;
                    case "-":
                        result = firstInt - secondInt;
                        break;
                    case "*":
                        result = firstInt * secondInt;
                        break;
                    case "/":
                        if (secondInt == 0) {
                            textView.setText("Error");
                            return;
                        } else {
                            result = firstInt / secondInt;
                        }
                        break;
                }
                textView.setText(String.valueOf((int) result));
            }
            button.setVisibility(View.VISIBLE);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                    intent.putExtra("result", result);
                    startActivity(intent);
                    finish();
                }
            });
        }
    }
}
