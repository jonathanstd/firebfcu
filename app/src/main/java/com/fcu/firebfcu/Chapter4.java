package com.fcu.firebfcu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Chapter4 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chapter4);
        Button btnNum1 = findViewById(R.id.toNum1);
        Button btnNum2 = findViewById(R.id.toNum2);
        Button btnNum3 = findViewById(R.id.toNum3);
        Button btnNum4 = findViewById(R.id.toNum4);
        Button btnNum5 = findViewById(R.id.toNum5);

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
    }

    public void startReadingTestPageWithQuestionNumber(int questionNumber) {
        // Start the ReadingTestPage activity and pass the question number as an extra
        Intent intent = new Intent(this, ReadingTestPage4.class);
        intent.putExtra("questionNumber", questionNumber);
        startActivity(intent);
    }
}