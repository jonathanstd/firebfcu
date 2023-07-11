package com.fcu.firebfcu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
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

public class ReadingTestPageB2ver2 extends AppCompatActivity {

    int index = 0;
    int totalPoint = 0;
    boolean ansChecked = false;
    private StorageReference storageReference;
    int chapterNumber = 1;
    char band = 'b';
    String chapterPath = "rd-" + band + '-' + chapterNumber + '/' ;

    TextView questionNumberB22_2;
    ImageView questionImage;
    TextView questionTextPageB2_2;
    ArrayList<QModelB2_2> randomQuestions;

    List<Character> selectedAnswers = new ArrayList<>();

    RadioGroup radioGroupB22_2;
    RadioButton ansButtonPageB22_1;
    RadioButton ansButtonPageB22_2;
    RadioButton ansButtonPageB22_3;
    RadioButton ansButtonPageB22_4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reading_test_page_b2ver2);

        FirebaseStorage storage = FirebaseStorage.getInstance();
        storageReference = FirebaseStorage.getInstance().getReference();


        Button btnNextPageB2_2 = findViewById(R.id.btnNextPageB2_2);
        Button btnPrevPageB2_2 = findViewById(R.id.btnPrevPageB2_2);
        questionImage = findViewById(R.id.questionImage);

        ansButtonPageB22_1 = findViewById(R.id.ansButtonPageB22_1);
        ansButtonPageB22_2 = findViewById(R.id.ansButtonPageB22_2);
        ansButtonPageB22_3 = findViewById(R.id.ansButtonPageB22_3);
        ansButtonPageB22_4 = findViewById(R.id.ansButtonPageB22_4);

        Intent intent = getIntent();
        int receivedPoint = intent.getIntExtra("point", 0);

        // Add the received point to the totalPoint
        totalPoint += receivedPoint;

        radioGroupB22_2 = findViewById(R.id.radioGroupB22_2);
        questionNumberB22_2 = findViewById(R.id.questionNumberB22_2);
        questionNumberB22_2.setText("第 1 題");

        QModelB2_2 question1 = new QModelB2_2("b12024","關於這四個廣告，下面哪一個是對的？","這四個廣告都是賣電腦的","小陳賣的電腦用了一年了","李小姐的電腦要賣 9500 元","早上九點可以打電話給小陳",'B');
        QModelB2_2 question2 = new QModelB2_2("b12024","如果你想要買新電腦，應該找誰？","小陳","李小姐","王先生","林先生",'C');
        QModelB2_2 question3 = new QModelB2_2("b12026","這封信說了小美的什麼事？","她決定出國念書","她來美國已經兩年了","她男朋友找到了新工作","下週五晚上，她的學校有活動",'C');
        QModelB2_2 question4 = new QModelB2_2("b12026","關於這封信，下面哪一個不對？","小美本來打算出國念書","明真希望小美參加學校晚會","明真先收到了一封小美寄來的信","小美想知道大忠的工作做得好不好",'D');
        QModelB2_2 question5 = new QModelB2_2("b12028","這是心美的記事本，下面哪一個不對？","心美星期四有會議","心美週末要看電影","心美星期二上數學課","心美打算送禮物給小英",'C');
        QModelB2_2 question6 = new QModelB2_2("b12028","心美哪幾天要上班？","星期日和星期一","星期一和星期二","星期四和星期五","星期五和星期一",'D');

        ArrayList<QModelB2_2> al = new ArrayList<QModelB2_2>();
        al.add(question1);
        al.add(question2);
        al.add(question3);
        al.add(question4);
        al.add(question5);
        al.add(question6);


        Collections.shuffle(al);
        // Select the first 6 questions
        randomQuestions = new ArrayList<>(al.subList(0, 15));
        int lastIndexPage2 = randomQuestions.size() - 1;

        QModelB2_2 firstQuestion = randomQuestions.get(0);
        StorageReference imgRef = storageReference.child(chapterPath + randomQuestions.get(index).getQuestionImage() + ".jpg");
        imgRef.getDownloadUrl().addOnSuccessListener(uri -> {
            Picasso.get().load(uri.toString()).into(questionImage);
        }).addOnFailureListener(exception -> {
            // Handle any errors that occurred during image download
            exception.printStackTrace();
        });
        ansButtonPageB22_1.setText(firstQuestion.getChoice1());
        ansButtonPageB22_2.setText(firstQuestion.getChoice2());
        ansButtonPageB22_3.setText(firstQuestion.getChoice3());
        ansButtonPageB22_4.setText(firstQuestion.getChoice4());

        //loadImage(al.get(index).getqImage());

        //String imagePath = al.get(index).getqImage();

        btnPrevPageB2_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (index > 0) {
                    if (!ansChecked) {
                        radioGroupB22_2.clearCheck();
                    }

                    index--;

                    QModelB2_2 currentQuestion = randomQuestions.get(index);
                    StorageReference imgRef = storageReference.child(chapterPath + randomQuestions.get(index).getQuestionImage() + ".jpg");
                    imgRef.getDownloadUrl().addOnSuccessListener(uri -> {
                        Picasso.get().load(uri.toString()).into(questionImage);
                    }).addOnFailureListener(exception -> {
                        // Handle any errors that occurred during image download
                        exception.printStackTrace();
                    });
                    ansChecked = false;
                    questionNumberB22_2.setText("第 " + (index + 1) + " 題");

                    ansButtonPageB22_1.setText(firstQuestion.getChoice1());
                    ansButtonPageB22_2.setText(firstQuestion.getChoice2());
                    ansButtonPageB22_3.setText(firstQuestion.getChoice3());
                    ansButtonPageB22_4.setText(firstQuestion.getChoice4());
                } else {
                    // Navigate to the last question in ReadingTestPage
                    Intent intent = new Intent(ReadingTestPageB2ver2.this, ReadingTestPageB2.class);
                    intent.putExtra("questionNumber", 15);
                    startActivity(intent);
                }

            }
        });


        btnNextPageB2_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!ansChecked) {
                    radioGroupB22_2.clearCheck();
                }

                if (index == lastIndexPage2) {
                    Intent intent = new Intent(ReadingTestPageB2ver2.this, TotalPointPage.class);
                    //intent.putExtra("lastQuestion", true);
                    startActivity(intent);
                } else {
                    index++;
                    QModelB2_2 currentQuestion = randomQuestions.get(index);
                    StorageReference imgRef = storageReference.child(chapterPath + randomQuestions.get(index).getQuestionImage() + ".jpg");
                    imgRef.getDownloadUrl().addOnSuccessListener(uri -> {
                        Picasso.get().load(uri.toString()).into(questionImage);
                    }).addOnFailureListener(exception -> {
                        // Handle any errors that occurred during image download
                        exception.printStackTrace();
                    });
                    ansChecked = false;
                    questionNumberB22_2.setText("第 " + (index + 1) + " 題");

                    ansButtonPageB22_1.setText(firstQuestion.getChoice1());
                    ansButtonPageB22_2.setText(firstQuestion.getChoice2());
                    ansButtonPageB22_3.setText(firstQuestion.getChoice3());
                    ansButtonPageB22_4.setText(firstQuestion.getChoice4());
                }
            }
        });

        ansButtonPageB22_1.setOnClickListener(v -> {
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

        ansButtonPageB22_2.setOnClickListener(v -> {
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

        ansButtonPageB22_3.setOnClickListener(v -> {
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

        ansButtonPageB22_4.setOnClickListener(v -> {
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

    }


}