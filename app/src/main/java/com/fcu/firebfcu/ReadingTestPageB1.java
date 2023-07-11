package com.fcu.firebfcu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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

public class ReadingTestPageB1 extends AppCompatActivity {

    int index = 0;
    int totalPoint = 0;
    boolean ansChecked = false;
    private StorageReference storageReference;
    int chapterNumber = 1;
    char band = 'b';
    String chapterPath = "rd-" + band + '-' + chapterNumber + '/' ;
    //private ImageView questionImageView;
    TextView qNumberPageB1;
    TextView questionParagraph;
    TextView questionTextPageB1;
    ArrayList<QModelB1> al;
    RadioGroup radioGroupPageB1;
    List<Character> selectedAnswers = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reading_test_page_b1);

        FirebaseStorage storage = FirebaseStorage.getInstance();
        storageReference = FirebaseStorage.getInstance().getReference();

        Button btnNextPageB1 = findViewById(R.id.btnNextPageB1);
        Button btnPrevPageB1 = findViewById(R.id.btnPrevPageB1);
        questionParagraph = findViewById(R.id.questionParagraph);
        questionTextPageB1 = findViewById(R.id.questionTextPageB1);
        RadioButton ansButtonPageB1_1 = findViewById(R.id.ansButtonPageB1_1);
        RadioButton ansButtonPageB1_2 = findViewById(R.id.ansButtonPageB1_2);
        RadioButton ansButtonPageB1_3 = findViewById(R.id.ansButtonPageB1_3);
        RadioButton ansButtonPageB1_4 = findViewById(R.id.ansButtonPageB1_4);



        radioGroupPageB1 = findViewById(R.id.radioGroupPageB1);
        qNumberPageB1 = findViewById(R.id.qNumberPageB1);



        QModelB1 question1 = new QModelB1(

                "\"這一段短文有幾個空格，每個空格有四個選項，請選出正確的答案完成短文。昨晚張太太的肚子很痛，感覺小孩快要生出來了，所以她(1)送進了醫院。張先生也(2)張太太到醫院去，他又高興又緊張，因為他就要(3)爸爸了。五個小時以後，小孩生出來了，是一個可愛的小女生。張先生和張太太(4)這個孩子想了一個名字，叫「張美樂」，(5)她將來「美麗」又「快樂」。\"",

                "第(1)空格對的字，是什麼？",
                "把","能","被","會",'C');

        QModelB1 question2 = new QModelB1(

                "\"這一段短文有幾個空格，每個空格有四個選項，請選出正確的答案完成短文。昨晚張太太的肚子很痛，感覺小孩快要生出來了，所以她(1)送進了醫院。張先生也(2)張太太到醫院去，他又高興又緊張，因為他就要(3)爸爸了。五個小時以後，小孩生出來了，是一個可愛的小女生。張先生和張太太(4)這個孩子想了一個名字，叫「張美樂」，(5)她將來「美麗」又「快樂」。\"",

                "第(2)空格對的字，是什麼？",
                "幫","害","陪","請",'C');

        QModelB1 question3 = new QModelB1(

                "\"這一段短文有幾個空格，每個空格有四個選項，請選出正確的答案完成短文。昨晚張太太的肚子很痛，感覺小孩快要生出來了，所以她(1)送進了醫院。張先生也(2)張太太到醫院去，他又高興又緊張，因為他就要(3)爸爸了。五個小時以後，小孩生出來了，是一個可愛的小女生。張先生和張太太(4)這個孩子想了一個名字，叫「張美樂」，(5)她將來「美麗」又「快樂」。\"",

                "第(3)空格對的字，是什麼？",
                "當","成","裝","弄",'A');

        QModelB1 question4 = new QModelB1(

                "\"這一段短文有幾個空格，每個空格有四個選項，請選出正確的答案完成短文。昨晚張太太的肚子很痛，感覺小孩快要生出來了，所以她(1)送進了醫院。張先生也(2)張太太到醫院去，他又高興又緊張，因為他就要(3)爸爸了。五個小時以後，小孩生出來了，是一個可愛的小女生。張先生和張太太(4)這個孩子想了一個名字，叫「張美樂」，(5)她將來「美麗」又「快樂」。\"",

                "第(4)空格對的字，是什麼？",
                "教","為","讓","養",'B');

        QModelB1 question5 = new QModelB1(

                "\"這一段短文有幾個空格，每個空格有四個選項，請選出正確的答案完成短文。昨晚張太太的肚子很痛，感覺小孩快要生出來了，所以她(1)送進了醫院。張先生也(2)張太太到醫院去，他又高興又緊張，因為他就要(3)爸爸了。五個小時以後，小孩生出來了，是一個可愛的小女生。張先生和張太太(4)這個孩子想了一個名字，叫「張美樂」，(5)她將來「美麗」又「快樂」。\"",

                "第(5)空格對的字，是什麼？",
                "覺得","要求","決定","希望",'D');

        al = new ArrayList<QModelB1>();

        al.add(question1);
        al.add(question2);
        al.add(question3);
        al.add(question4);
        al.add(question5);


// Shuffle the order of the groups
        //Collections.shuffle(groups);


        int lastIndex = al.size() - 1;

        questionParagraph.setText(al.get(0).getQuestionParagraph());
        questionTextPageB1.setText(al.get(0).getQuestionTextPageB1());
        qNumberPageB1.setText("第 1 題");

        QModelB1 firstQuestion = al.get(0);

        ansButtonPageB1_1.setText(firstQuestion.getChoice1());
        ansButtonPageB1_2.setText(firstQuestion.getChoice2());
        ansButtonPageB1_3.setText(firstQuestion.getChoice3());
        ansButtonPageB1_4.setText(firstQuestion.getChoice4());


        btnPrevPageB1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (index > 0) {
                    if (!ansChecked) {
                        radioGroupPageB1.clearCheck();
                    }

                    index--;
                    questionParagraph.setText(al.get(index).getQuestionParagraph());
                    questionTextPageB1.setText(al.get(index).getQuestionTextPageB1());
                    QModelB1 currentQuestion = al.get(index);
                    ansChecked = false;
                    qNumberPageB1.setText("第 " + (index + 1) + " 題");

                    ansButtonPageB1_1.setText(firstQuestion.getChoice1());
                    ansButtonPageB1_2.setText(firstQuestion.getChoice2());
                    ansButtonPageB1_3.setText(firstQuestion.getChoice3());
                    ansButtonPageB1_4.setText(firstQuestion.getChoice4());
                } else {
                    // Navigate to the last question in ReadingTestPage
                    Intent intent = new Intent(ReadingTestPageB1.this, ReadingTestPage5.class);
                    intent.putExtra("questionNumber", 15);
                    startActivity(intent);
                }

            }
        });


        btnNextPageB1.setOnClickListener(v -> {
            if (!ansChecked) {
                radioGroupPageB1.clearCheck();
            }

            if (index == lastIndex) {
                Intent intent = new Intent(ReadingTestPageB1.this, ReadingTestPageB2.class);
                intent.putExtra("totalPoint", totalPoint);
                startActivity(intent);
            } else {
                index++;
                questionParagraph.setText(al.get(index).getQuestionParagraph());
                questionTextPageB1.setText(al.get(index).getQuestionTextPageB1());
                QModelB1 currentQuestion = al.get(index);

                ansChecked = false;
                qNumberPageB1.setText("第 " + (index + 1) + " 題");

                ansButtonPageB1_1.setText(firstQuestion.getChoice1());
                ansButtonPageB1_2.setText(firstQuestion.getChoice2());
                ansButtonPageB1_3.setText(firstQuestion.getChoice3());
                ansButtonPageB1_4.setText(firstQuestion.getChoice4());
            }
        });

        ansButtonPageB1_1.setOnClickListener(v -> {
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

        ansButtonPageB1_2.setOnClickListener(v -> {
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

        ansButtonPageB1_3.setOnClickListener(v -> {
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

        ansButtonPageB1_4.setOnClickListener(v -> {
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

    }



}