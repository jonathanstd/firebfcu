package com.fcu.firebfcu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ReadingTestPage4 extends AppCompatActivity {

    int index = 0;
    int totalPoint = 0;
    boolean ansChecked = false;
    private StorageReference storageReference;
    //int chapterNumber = 1;
    char band = 'a';
    //String chapterPath = "rd-" + band + '-' + chapterNumber + '/' ;
    //private ImageView questionImageView;
    TextView qNumber4;
    TextView qText14;
    TextView qText24;
    ArrayList<QModel4> randomQuestions;
    RadioGroup radioGroup4;
    List<Character> selectedAnswers = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reading_test_page4);

        FirebaseStorage storage = FirebaseStorage.getInstance();
        storageReference = FirebaseStorage.getInstance().getReference();


        Button btnNext4 = findViewById(R.id.btnNext4);
        Button btnPrev4 = findViewById(R.id.btnPrev4);
        qText14 = findViewById(R.id.qText14);
        qText24 = findViewById(R.id.qText24);
        RadioButton ansButton14 = findViewById(R.id.ansButton14);
        RadioButton ansButton24 = findViewById(R.id.ansButton24);
        RadioButton ansButton34 = findViewById(R.id.ansButton34);
        RadioButton ansButton44 = findViewById(R.id.ansButton44);
        RadioButton ansButton54 = findViewById(R.id.ansButton54);
        RadioButton ansButton64 = findViewById(R.id.ansButton64);
        radioGroup4 = findViewById(R.id.radioGroup4);
        qNumber4 = findViewById(R.id.qNumber4);



        QModel4 question1 = new QModel4("\"昨天晚上我覺得很不舒服，    （I）    ，所以很早就睡覺\n" +
                "了。今天早上起來，    （II）    。我去看病，醫生說我感冒了，給了我一些藥，    （III）    要多休息，多喝水，才會快點好。\n" +
                "這幾天的天氣一會兒熱，一會兒冷，    （IV）    。我要 \n" +
                "   （V）   ，不要再感冒了。 \"","第 [ I ] 空格對的字，是什麼？","還告訴我","頭有點兒痛","很容易生病","覺得很舒服","更不舒服了","多注意自己的身體 ",'B');
        QModel4 question2 = new QModel4("\"昨天晚上我覺得很不舒服，    （I）    ，所以很早就睡覺\n" +
                "了。今天早上起來，    （II）    。我去看病，醫生說我感冒了，給了我一些藥，    （III）    要多休息，多喝水，才會快點好。\n" +
                "這幾天的天氣一會兒熱，一會兒冷，    （IV）    。我要 \n" +
                "   （V）   ，不要再感冒了。 \"","第 [ II ] 空格對的字，是什麼？","還告訴我","頭有點兒痛","很容易生病","覺得很舒服","更不舒服了","多注意自己的身體 ",'E');
        QModel4 question3 = new QModel4("\"昨天晚上我覺得很不舒服，    （I）    ，所以很早就睡覺\n" +
                "了。今天早上起來，    （II）    。我去看病，醫生說我感冒了，給了我一些藥，    （III）    要多休息，多喝水，才會快點好。\n" +
                "這幾天的天氣一會兒熱，一會兒冷，    （IV）    。我要 \n" +
                "   （V）   ，不要再感冒了。 \"","第 [ III ] 空格對的字，是什麼？","還告訴我","頭有點兒痛","很容易生病","覺得很舒服","更不舒服了","多注意自己的身體 ",'A');
        QModel4 question4 = new QModel4("\"昨天晚上我覺得很不舒服，    （I）    ，所以很早就睡覺\n" +
                "了。今天早上起來，    （II）    。我去看病，醫生說我感冒了，給了我一些藥，    （III）    要多休息，多喝水，才會快點好。\n" +
                "這幾天的天氣一會兒熱，一會兒冷，    （IV）    。我要 \n" +
                "   （V）   ，不要再感冒了。 \"","第 [ IV ] 空格對的字，是什麼？","還告訴我","頭有點兒痛","很容易生病","覺得很舒服","更不舒服了","多注意自己的身體 ",'C');
        QModel4 question5 = new QModel4("\"昨天晚上我覺得很不舒服，    （I）    ，所以很早就睡覺\n" +
                "了。今天早上起來，    （II）    。我去看病，醫生說我感冒了，給了我一些藥，    （III）    要多休息，多喝水，才會快點好。\n" +
                "這幾天的天氣一會兒熱，一會兒冷，    （IV）    。我要 \n" +
                "   （V）   ，不要再感冒了。 \"","第 [ V ] 空格對的字，是什麼？","還告訴我","頭有點兒痛","很容易生病","覺得很舒服","更不舒服了","多注意自己的身體 ",'F');


        ArrayList<QModel4> al = new ArrayList<QModel4>();
        al.add(question1);
        al.add(question2);
        al.add(question3);
        al.add(question4);
        al.add(question5);

        randomQuestions = new ArrayList<>(al.subList(0, 5));
        int lastIndex = randomQuestions.size() - 1;

        qText14.setText(randomQuestions.get(0).getqText1());
        qText24.setText(randomQuestions.get(0).getqText2());
        qNumber4.setText("第 1 題");

        Collections.shuffle(al);
        // Select the first 6 questions



        //loadImage(al.get(index).getqImage());

        //String imagePath = al.get(index).getqImage();

        btnPrev4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (index > 0) {
                    if (!ansChecked) {
                        radioGroup4.clearCheck();
                    }
                    index--;
                    QModel4 currentQuestion = randomQuestions.get(index);

                    ansChecked = false;
                    qNumber4.setText("第 " + (index + 1) + " 題");

                    ansButton14.setText(currentQuestion.getAns1());
                    ansButton24.setText(currentQuestion.getAns2());
                    ansButton34.setText(currentQuestion.getAns3());
                    ansButton44.setText(currentQuestion.getAns4());
                    ansButton54.setText(currentQuestion.getAns5());
                    ansButton64.setText(currentQuestion.getAns6());

                    qText14.setText(randomQuestions.get(0).getqText1());
                    qText24.setText(randomQuestions.get(0).getqText2());
                } else {
                    // Navigate to the last question in ReadingTestPage
                    Intent intent = new Intent(ReadingTestPage4.this, ReadingTestPage3.class);
                    intent.putExtra("questionNumber", 15);
                    startActivity(intent);
                }

            }
        });


        btnNext4.setOnClickListener(v -> {
            if (!ansChecked) {
                radioGroup4.clearCheck();
            }

            if (index == lastIndex) {
                Intent intent = new Intent(ReadingTestPage4.this, TotalPointPage.class);
                intent.putExtra("totalPoint", totalPoint);
                startActivity(intent);
            } else {
                index++;
                qText14.setText(randomQuestions.get(0).getqText1());
                qText24.setText(randomQuestions.get(0).getqText2());
                QModel4 currentQuestion = randomQuestions.get(index);

                ansChecked = false;
                qNumber4.setText("第 " + (index + 1) + " 題");

                ansButton14.setText(currentQuestion.getAns1());
                ansButton24.setText(currentQuestion.getAns2());
                ansButton34.setText(currentQuestion.getAns3());
                ansButton44.setText(currentQuestion.getAns4());
                ansButton54.setText(currentQuestion.getAns5());
                ansButton64.setText(currentQuestion.getAns6());
            }
        });

        for (int i = 1; i <= 5; i++) {
            int questionNumber = i;
            Button button = new Button(this);
            button.setText(String.valueOf(questionNumber));
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    moveToQuestion(questionNumber);
                }
            });
            // Add the button to the LinearLayout
            LinearLayout buttonLayout4 = findViewById(R.id.buttonLayout);
            buttonLayout4.addView(button);
        }

        ansButton14.setOnClickListener(v -> {
            if (randomQuestions.get(index).getAnswer() == 'A' && !ansChecked) {
                totalPoint += 2;
                ansChecked = true;
            }
            // Handle the click event here
            Toast toast = Toast.makeText(getApplicationContext(), "A", Toast.LENGTH_SHORT);
            toast.show();

            Handler handler = new Handler();
            handler.postDelayed(() -> {
                toast.cancel();  // Dismiss the toast after a custom delay
                // Perform any other actions after the toast is dismissed
            }, 300);  // Set a custom delay of 1000 milliseconds (1 second)
        });

        ansButton24.setOnClickListener(v -> {
            if (randomQuestions.get(index).getAnswer() == 'B' && !ansChecked) {
                totalPoint += 2;
                ansChecked = true;
            }
            // Handle the click event here
            Toast toast = Toast.makeText(getApplicationContext(), "B", Toast.LENGTH_SHORT);
            toast.show();

            Handler handler = new Handler();
            handler.postDelayed(() -> {
                toast.cancel();  // Dismiss the toast after a custom delay
                // Perform any other actions after the toast is dismissed
            }, 300);  // Set a custom delay of 2000 milliseconds (2 seconds)
        });

        ansButton34.setOnClickListener(v -> {
            if (randomQuestions.get(index).getAnswer() == 'C' && !ansChecked) {
                totalPoint += 2;
                ansChecked = true;
            }
            // Handle the click event here
            Toast toast = Toast.makeText(getApplicationContext(), "C", Toast.LENGTH_SHORT);
            toast.show();

            Handler handler = new Handler();
            handler.postDelayed(() -> {
                toast.cancel();  // Dismiss the toast after a custom delay
                // Perform any other actions after the toast is dismissed
            }, 300);  // Set a custom delay of 3000 milliseconds (3 seconds)
        });

        ansButton44.setOnClickListener(v -> {
            if (randomQuestions.get(index).getAnswer() == 'D' && !ansChecked) {
                totalPoint += 2;
                ansChecked = true;
            }
            // Handle the click event here
            Toast toast = Toast.makeText(getApplicationContext(), "D", Toast.LENGTH_SHORT);
            toast.show();

            Handler handler = new Handler();
            handler.postDelayed(() -> {
                toast.cancel();  // Dismiss the toast after a custom delay
                // Perform any other actions after the toast is dismissed
            }, 300);  // Set a custom delay of 3000 milliseconds (3 seconds)
        });

        ansButton54.setOnClickListener(v -> {
            if (randomQuestions.get(index).getAnswer() == 'E' && !ansChecked) {
                totalPoint += 2;
                ansChecked = true;
            }
            // Handle the click event here
            Toast toast = Toast.makeText(getApplicationContext(), "E", Toast.LENGTH_SHORT);
            toast.show();

            Handler handler = new Handler();
            handler.postDelayed(() -> {
                toast.cancel();  // Dismiss the toast after a custom delay
                // Perform any other actions after the toast is dismissed
            }, 300);  // Set a custom delay of 3000 milliseconds (3 seconds)
        });

        ansButton64.setOnClickListener(v -> {
            if (randomQuestions.get(index).getAnswer() == 'F' && !ansChecked) {
                totalPoint += 2;
                ansChecked = true;
            }
            // Handle the click event here
            Toast toast = Toast.makeText(getApplicationContext(), "F", Toast.LENGTH_SHORT);
            toast.show();

            Handler handler = new Handler();
            handler.postDelayed(() -> {
                toast.cancel();  // Dismiss the toast after a custom delay
                // Perform any other actions after the toast is dismissed
            }, 300);  // Set a custom delay of 3000 milliseconds (3 seconds)
        });

    }
    private void moveToQuestion(int questionNumber) {
        // Calculate the index of the question based on the question number
        int questionIndex = questionNumber - 1;

        if (questionIndex >= 0 && questionIndex < randomQuestions.size()) {
            index = questionIndex;
            qNumber4.setText("第 " + (index + 1) + " 題");

            ansChecked = false;
        } else {
            // Handle the case where the question number is out of range
            Toast.makeText(this, "Invalid question number!", Toast.LENGTH_SHORT).show();
        }
    }

}