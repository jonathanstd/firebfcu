package com.fcu.firebfcu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Chapter1 extends AppCompatActivity {

    public void startReadingTestPageWithQuestionNumber(View view) {
        Button clickedButton = (Button) view;
        String buttonText = clickedButton.getText().toString();
        int questionNumber = Integer.parseInt(buttonText.replaceAll("[\\D]", ""));

        // Start the ReadingTestPage activity and pass the question number as an extra
        Intent intent = new Intent(this, ReadingTestPage.class);
        intent.putExtra("questionNumber", questionNumber);
        startActivity(intent);
    }
}