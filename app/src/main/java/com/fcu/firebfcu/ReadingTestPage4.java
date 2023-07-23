package com.fcu.firebfcu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ReadingTestPage4 extends AppCompatActivity {

    int index = 0;
    int totalPoint = 0;
    int totalCorrectPoints = 0;
    boolean ansChecked = false;
    private StorageReference storageReference;
    int chapterNumber = 1;
    char band = 'a';
    String chapterPath = "rd-" + band + '-' + chapterNumber + '/' ;
    //private ImageView questionImageView;
    ScrollView scrollViewPage4;
    LinearLayout buttonLayoutPage4;
    TextView qNumberPage4;
    TextView questionChoice;
    TextView questionTextPage4;
    ArrayList<QModel4> al;
    RadioGroup radioGroupPage4;
    List<Character> selectedAnswers = new ArrayList<>();

    RadioButton ansButtonPage4_1;
    RadioButton ansButtonPage4_2;
    RadioButton ansButtonPage4_3;
    RadioButton ansButtonPage4_4;
    RadioButton ansButtonPage4_5;
    RadioButton ansButtonPage4_6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reading_test_page4);

        FirebaseStorage storage = FirebaseStorage.getInstance();
        storageReference = FirebaseStorage.getInstance().getReference();

        Button btnNextPage4 = findViewById(R.id.btnNextPage4);
        Button btnPrevPage4 = findViewById(R.id.btnPrevPage4);
        Button toMenuPage = findViewById(R.id.toMenuPageA);
        questionChoice = findViewById(R.id.questionChoice);
        questionTextPage4 = findViewById(R.id.questionTextPage4);

        ansButtonPage4_1 = findViewById(R.id.ansButtonPage4_1);
        ansButtonPage4_2 = findViewById(R.id.ansButtonPage4_2);
        ansButtonPage4_3 = findViewById(R.id.ansButtonPage4_3);
        ansButtonPage4_4 = findViewById(R.id.ansButtonPage4_4);
        ansButtonPage4_5 = findViewById(R.id.ansButtonPage4_5);
        ansButtonPage4_6 = findViewById(R.id.ansButtonPage4_6);

        radioGroupPage4 = findViewById(R.id.radioGroupPage4);
        qNumberPage4 = findViewById(R.id.qNumberPage4);


        QModel4 question1 = new QModel4("\"昨天晚上我覺得很不舒服，(I)，所以很早就睡覺" +
                "了。今天早上起來，(II)。我去看病，醫生說我感冒了，給了我一些藥，(III)要多休息，多喝水，才會快點好。" +
                "這幾天的天氣一會兒熱，一會兒冷，(IV)。我要" +
                "(V)，不要再感冒了。 \"","第 [ I ] 空格對的字，是什麼？","還告訴我","頭有點兒痛","很容易生病","覺得很舒服","更不舒服了","多注意自己的身體",'B');
        QModel4 question2 = new QModel4("\"昨天晚上我覺得很不舒服，(I)，所以很早就睡覺" +
                "了。今天早上起來，(II)。我去看病，醫生說我感冒了，給了我一些藥，(III)要多休息，多喝水，才會快點好。" +
                "這幾天的天氣一會兒熱，一會兒冷，(IV)。我要" +
                "(V)，不要再感冒了。 \"","第 [ II ] 空格對的字，是什麼？ ","還告訴我","頭有點兒痛","很容易生病","覺得很舒服","更不舒服了","多注意自己的身體",'B');
        QModel4 question3 = new QModel4("\"昨天晚上我覺得很不舒服，(I)，所以很早就睡覺" +
                "了。今天早上起來，(II)。我去看病，醫生說我感冒了，給了我一些藥，(III)要多休息，多喝水，才會快點好。" +
                "這幾天的天氣一會兒熱，一會兒冷，(IV)。我要" +
                "(V)，不要再感冒了。 \"","第 [ III ] 空格對的字，是什麼？","還告訴我","頭有點兒痛","很容易生病","覺得很舒服","更不舒服了","多注意自己的身體",'B');
        QModel4 question4 = new QModel4("\"昨天晚上我覺得很不舒服，(I)，所以很早就睡覺" +
                "了。今天早上起來，(II)。我去看病，醫生說我感冒了，給了我一些藥，(III)要多休息，多喝水，才會快點好。" +
                "這幾天的天氣一會兒熱，一會兒冷，(IV)。我要" +
                "(V)，不要再感冒了。 \"","第 [ IV ] 空格對的字，是什麼？ ","還告訴我","頭有點兒痛","很容易生病","覺得很舒服","更不舒服了","多注意自己的身體",'B');
        QModel4 question5 = new QModel4("\"昨天晚上我覺得很不舒服，(I)，所以很早就睡覺" +
                "了。今天早上起來，(II)。我去看病，醫生說我感冒了，給了我一些藥，(III)要多休息，多喝水，才會快點好。" +
                "這幾天的天氣一會兒熱，一會兒冷，(IV)。我要" +
                "(V)，不要再感冒了。 \"","第 [ V ] 空格對的字，是什麼？","還告訴我","頭有點兒痛","很容易生病","覺得很舒服","更不舒服了","多注意自己的身體",'B');

        al = new ArrayList<QModel4>();

        al.add(question1);
        al.add(question2);
        al.add(question3);
        al.add(question4);
        al.add(question5);


// Shuffle the order of the groups
        //Collections.shuffle(groups);


        int lastIndex = al.size() - 1;

        questionChoice.setText(al.get(0).getquestionChoice());
        questionTextPage4.setText(al.get(0).getQuestionTextPage4());
        qNumberPage4.setText("第 1 / 5 題");

        QModel4 firstQuestion = al.get(0);

        ansButtonPage4_1.setText(firstQuestion.getAns1());
        ansButtonPage4_2.setText(firstQuestion.getAns2());
        ansButtonPage4_3.setText(firstQuestion.getAns3());
        ansButtonPage4_4.setText(firstQuestion.getAns4());
        ansButtonPage4_5.setText(firstQuestion.getAns5());
        ansButtonPage4_6.setText(firstQuestion.getAns6());


        btnPrevPage4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (index > 0) {
                    if (!ansChecked) {
                        radioGroupPage4.clearCheck();
                    }

                    index--;
                    questionChoice.setText(al.get(index).getquestionChoice());
                    questionTextPage4.setText(al.get(index).getQuestionTextPage4());
                    QModel4 currentQuestion = al.get(index);
                    ansChecked = false;
                    qNumberPage4.setText("第 " + (index + 1) + " / 5 題");

                    ansButtonPage4_1.setText(firstQuestion.getAns1());
                    ansButtonPage4_2.setText(firstQuestion.getAns2());
                    ansButtonPage4_3.setText(firstQuestion.getAns3());
                    ansButtonPage4_4.setText(firstQuestion.getAns4());
                    ansButtonPage4_5.setText(firstQuestion.getAns5());
                    ansButtonPage4_6.setText(firstQuestion.getAns6());
                } else {
                    // Navigate to the last question in ReadingTestPage
                    Intent intent = new Intent(ReadingTestPage4.this, ReadingTestPage3.class);
                    intent.putExtra("questionNumber", 10);
                    startActivity(intent);
                }

            }
        });

        Intent intentPoint = getIntent();
        int receivedPoint = intentPoint.getIntExtra("totalPoint", 0);
        totalPoint += receivedPoint;

        toMenuPage.setOnClickListener(v -> {
            Intent menuPage = new Intent(ReadingTestPage4.this, MenuPageA.class);
            startActivity(menuPage);
        });

        Button toMainAct = findViewById(R.id.toMainAct);
        toMainAct.setOnClickListener(v -> {
            Intent menuPage = new Intent(ReadingTestPage4.this, ReadingPage.class);
            startActivity(menuPage);
        });

        totalPoint = 0;
        SharedPreferences sharedPreferences = getSharedPreferences("ReadingTestPagePrefs", MODE_PRIVATE);
        totalCorrectPoints = sharedPreferences.getInt("totalCorrectPoints", 0);
        Button finish = findViewById(R.id.finish);
        finish.setOnClickListener(v -> {
            // Save the total correct points in SharedPreferences
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putInt("totalCorrectPoints", totalPoint);
            editor.apply();

            // Retrieve the total points earned from previous pages
            int totalPointsFromPreviousPages = getIntent().getIntExtra("totalPoint", 0);

            // Calculate the cumulative total points across all pages
            int totalCorrectPoints = totalPointsFromPreviousPages + totalPoint;

            // Navigate to the TotalPointPage and pass the cumulative total points as an extra
            Intent intent = new Intent(ReadingTestPage4.this, TotalPointPage.class);
            intent.putExtra("totalPoint", totalCorrectPoints);
            startActivity(intent);
        });


        btnNextPage4.setOnClickListener(v -> {
            if (!ansChecked) {
                radioGroupPage4.clearCheck();
            }

            if (index == lastIndex) {
                Intent intent = new Intent(ReadingTestPage4.this, ReadingTestPage5.class);
                intent.putExtra("totalPoint", totalPoint); // Pass the updated total points
                startActivity(intent);
            } else {
                index++;
                questionChoice.setText(al.get(index).getquestionChoice());
                questionTextPage4.setText(al.get(index).getQuestionTextPage4());
                QModel4 currentQuestion = al.get(index);

                ansChecked = false;
                qNumberPage4.setText("第 " + (index + 1) + " / 5 題");

                ansButtonPage4_1.setText(firstQuestion.getAns1());
                ansButtonPage4_2.setText(firstQuestion.getAns2());
                ansButtonPage4_3.setText(firstQuestion.getAns3());
                ansButtonPage4_4.setText(firstQuestion.getAns4());
                ansButtonPage4_5.setText(firstQuestion.getAns5());
                ansButtonPage4_6.setText(firstQuestion.getAns6());
            }
        });

        ansButtonPage4_1.setOnClickListener(v -> {
            handleAnswerButtonClick('A');
        });

        ansButtonPage4_2.setOnClickListener(v -> {
            handleAnswerButtonClick('B');
        });

        ansButtonPage4_3.setOnClickListener(v -> {
            handleAnswerButtonClick('C');
        });
        ansButtonPage4_4.setOnClickListener(v -> {
            handleAnswerButtonClick('D');
        });

        ansButtonPage4_5.setOnClickListener(v -> {
            handleAnswerButtonClick('E');
        });

        ansButtonPage4_6.setOnClickListener(v -> {
            handleAnswerButtonClick('F');
        });


        ansButtonPage4_1.setOnClickListener(v -> {
            if (al.get(index).getAnswer() == 'A' && !ansChecked) {
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

        ansButtonPage4_2.setOnClickListener(v -> {
            if (al.get(index).getAnswer() == 'B' && !ansChecked) {
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

        ansButtonPage4_3.setOnClickListener(v -> {
            if (al.get(index).getAnswer() == 'C' && !ansChecked) {
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

        ansButtonPage4_4.setOnClickListener(v -> {
            if (al.get(index).getAnswer() == 'D' && !ansChecked) {
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

        ansButtonPage4_5.setOnClickListener(v -> {
            if (al.get(index).getAnswer() == 'E' && !ansChecked) {
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

        ansButtonPage4_6.setOnClickListener(v -> {
            if (al.get(index).getAnswer() == 'F' && !ansChecked) {
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
        Intent intent = getIntent();
        int questionNumber = intent.getIntExtra("questionNumber", -1);
        if (questionNumber != -1) {
            // Navigate to the specified question number
            navigateToQuestion(questionNumber);
        }
    }

    private void navigateToQuestion(int questionNumber) {
        if (questionNumber >= 1 && questionNumber <= al.size()) {
            index = questionNumber - 1;
            questionChoice.setText(al.get(index).getquestionChoice());
            questionTextPage4.setText(al.get(index).getQuestionTextPage4());
            QModel4 firstQuestion = al.get(index);
            ansChecked = false;
            qNumberPage4.setText("第 " + (index + 1) + " / 5 題");

            ansButtonPage4_1.setText(firstQuestion.getAns1());
            ansButtonPage4_2.setText(firstQuestion.getAns2());
            ansButtonPage4_3.setText(firstQuestion.getAns3());
            ansButtonPage4_4.setText(firstQuestion.getAns4());
            ansButtonPage4_5.setText(firstQuestion.getAns5());
            ansButtonPage4_6.setText(firstQuestion.getAns6());
        } else {
            // Handle the case when the question number is out of bounds
            Toast.makeText(this, "Invalid question number", Toast.LENGTH_SHORT).show();
        }
    }

    private void displayQuestion(int questionIndex) {
        if (questionIndex >= 0 && questionIndex < al.size()) {
            QModel4 question = al.get(questionIndex);
            questionChoice.setText(al.get(index).getquestionChoice());
            questionTextPage4.setText(al.get(index).getQuestionTextPage4());
            QModel4 firstQuestion = al.get(index);
            ansChecked = false;
            qNumberPage4.setText("第 " + (index + 1) + " / 5 題");

            ansButtonPage4_1.setText(firstQuestion.getAns1());
            ansButtonPage4_2.setText(firstQuestion.getAns2());
            ansButtonPage4_3.setText(firstQuestion.getAns3());
            ansButtonPage4_4.setText(firstQuestion.getAns4());
            ansButtonPage4_5.setText(firstQuestion.getAns5());
            ansButtonPage4_6.setText(firstQuestion.getAns6());
        } else {
            // Handle the case when an invalid question index is passed
            Toast.makeText(this, "Invalid question index", Toast.LENGTH_SHORT).show();
        }
    }

    private void handleAnswerButtonClick(char selectedAnswer) {
        if (al.get(index).getAnswer() == selectedAnswer && !ansChecked) {
            totalPoint += 2; // Increment by 2 for each correct answer
            ansChecked = true;
        }

        // Display the selected answer (A, B, or C) in a toast message
        Toast.makeText(getApplicationContext(), String.valueOf(selectedAnswer), Toast.LENGTH_SHORT).show();

        // Delay the navigation to the next question for a short period (300 milliseconds) to show the toast message
        Handler handler = new Handler();
        handler.postDelayed(() -> {
            // Navigate to the next question if not the last question
            int lastIndex = al.size() - 1;
            if (index < lastIndex) {
                index++;
                displayQuestion(index);
            } else {
                // Save the total correct points in SharedPreferences
                SharedPreferences sharedPreferences = getSharedPreferences("ReadingTestPagePrefs", MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putInt("totalCorrectPoints", totalPoint);
                editor.apply();

                // Navigate to the TotalPointPage
                Intent intent = new Intent(ReadingTestPage4.this, TotalPointPage.class);
                startActivity(intent);
            }
        }, 300);
    }
}