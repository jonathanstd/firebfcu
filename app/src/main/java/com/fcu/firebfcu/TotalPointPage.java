package com.fcu.firebfcu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class TotalPointPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_total_point_page);
        Intent intent = getIntent();
        int totalPoint = intent.getIntExtra("totalPoint", 0);
        String convert = Integer.toString(totalPoint);

        TextView point = findViewById(R.id.finalPoint);
        point.setText(convert);

        Button home = findViewById(R.id.homeBtn);

        home.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                startActivity(new Intent(TotalPointPage.this, MainActivity.class));
            }
        });
    }
}