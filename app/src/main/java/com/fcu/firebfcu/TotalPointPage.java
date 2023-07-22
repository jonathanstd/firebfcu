package com.fcu.firebfcu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class TotalPointPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_total_point_page);

        TextView point = findViewById(R.id.finalPoint);

        // Retrieve the total correct points for each page from SharedPreferences
        int totalPointPage1 = getPointsForPage("ReadingTestPagePrefs");
        int totalPointPage2 = getPointsForPage("ReadingTestPage2Prefs");
        int totalPointPage3 = getPointsForPage("ReadingTestPage3Prefs");
        int totalPointPage4 = getPointsForPage("ReadingTestPage4Prefs");
        int totalPointPage5 = getPointsForPage("ReadingTestPage5Prefs");

        // Calculate the total points earned across all pages
        int totalCorrectPoints = totalPointPage1 + totalPointPage2 + totalPointPage3 + totalPointPage4 + totalPointPage5;

        // Display the total correct points on the TotalPointPage
        String convert = Integer.toString(totalCorrectPoints);
        point.setText(convert);

        Button home = findViewById(R.id.homeBtn);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(TotalPointPage.this, MainActivity.class));
            }
        });
    }

    // Helper method to retrieve the total points for a specific page from SharedPreferences
    private int getPointsForPage(String pageKey) {
        SharedPreferences sharedPreferences = getSharedPreferences(pageKey, MODE_PRIVATE);
        return sharedPreferences.getInt("totalCorrectPoints", 0);
    }

    public void onBackPressed() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
    }
}
