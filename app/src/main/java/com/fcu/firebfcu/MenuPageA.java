package com.fcu.firebfcu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class MenuPageA extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_page_a);

        Button toChapt1 = findViewById(R.id.toChapt1);
        Button toChapt2 = findViewById(R.id.toChapt2);
        Button toChapt3 = findViewById(R.id.toChapt3);
        Button toChapt4 = findViewById(R.id.toChapt4);
        Button toChapt5 = findViewById(R.id.toChapt5);

        toChapt1.setOnClickListener(v -> {
            Intent part1 = new Intent(MenuPageA.this, Chapter1.class);
            startActivity(part1);
        });

        /*toChapt2.setOnClickListener(v -> {
            Intent part2 = new Intent(MenuPageA.this, Chapter1.class);
            startActivity(part2);
        });

        toChapt3.setOnClickListener(v -> {
            Intent part3 = new Intent(MenuPageA.this, Chapter1.class);
            startActivity(part3);
        });

        toChapt4.setOnClickListener(v -> {
            Intent part4 = new Intent(MenuPageA.this, Chapter1.class);
            startActivity(part4);
        });

        toChapt5.setOnClickListener(v -> {
            Intent part5 = new Intent(MenuPageA.this, Chapter1.class);
            startActivity(part5);
        });*/
    }
}