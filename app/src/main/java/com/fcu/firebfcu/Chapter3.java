package com.fcu.firebfcu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Chapter3 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chapter3);
        Button btnNum1 = findViewById(R.id.toNum1);
        Button btnNum2 = findViewById(R.id.toNum2);
        Button btnNum3 = findViewById(R.id.toNum3);
        Button btnNum4 = findViewById(R.id.toNum4);
        Button btnNum5 = findViewById(R.id.toNum5);
        Button btnNum6 = findViewById(R.id.toNum6);
        Button btnNum7 = findViewById(R.id.toNum7);
        Button btnNum8 = findViewById(R.id.toNum8);
        Button btnNum9 = findViewById(R.id.toNum9);
        Button btnNum10 = findViewById(R.id.toNum10);

        btnNum1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startReadingTestPageWithQuestionNumber(1);
            }
        });

        btnNum2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startReadingTestPageWithQuestionNumber(2);
            }
        });

        btnNum3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startReadingTestPageWithQuestionNumber(3);
            }
        });

        btnNum4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startReadingTestPageWithQuestionNumber(4);
            }
        });

        btnNum5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startReadingTestPageWithQuestionNumber(5);
            }
        });

        btnNum6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startReadingTestPageWithQuestionNumber(6);
            }
        });

        btnNum7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startReadingTestPageWithQuestionNumber(7);
            }
        });

        btnNum8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startReadingTestPageWithQuestionNumber(8);
            }
        });

        btnNum9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startReadingTestPageWithQuestionNumber(9);
            }
        });

        btnNum10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startReadingTestPageWithQuestionNumber(10);
            }
        });
    }

    public void startReadingTestPageWithQuestionNumber(int questionNumber) {
        // Start the ReadingTestPage activity and pass the question number as an extra
        Intent intent = new Intent(this, ReadingTestPage3.class);
        intent.putExtra("questionNumber", questionNumber);
        startActivity(intent);
    }
}