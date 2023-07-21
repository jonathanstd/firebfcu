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

public class ReadingTestPage3 extends AppCompatActivity {

    int index = 0;
    int totalPoint = 0;
    boolean ansChecked = false;
    private StorageReference storageReference;
    int chapterNumber = 1;
    char band = 'a';
    String chapterPath = "rd-" + band + '-' + chapterNumber + '/' ;
    //private ImageView questionImageView;
    ScrollView scrollView3;
    LinearLayout buttonLayout3;
    TextView qNumber3;
    TextView txtView;
    ImageView imgView;
    ArrayList<QModel3> randomQuestions;
    RadioGroup ansGroup;
    List<Character> selectedAnswers = new ArrayList<>();
    RadioButton ansButton13;
    RadioButton ansButton23;
    RadioButton ansButton33;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reading_test_page3);

        FirebaseStorage storage = FirebaseStorage.getInstance();
        storageReference = FirebaseStorage.getInstance().getReference();

        Button toMenuPage = findViewById(R.id.toMenuPageA);
        Button btnNext3 = findViewById(R.id.btnNext3);
        Button btnPrev3 = findViewById(R.id.btnPrev3);
        imgView = findViewById(R.id.imgView);
        txtView = findViewById(R.id.qText);
        ansButton13 = findViewById(R.id.ansButton13);
        ansButton23 = findViewById(R.id.ansButton23);
        ansButton33 = findViewById(R.id.ansButton33);

        ansGroup = findViewById(R.id.radioGroup);
        qNumber3 = findViewById(R.id.qNumber3);

        QModel3 question1 = new QModel3("a13031","____著眼鏡的小女孩在看書。","穿","帶","戴",'C');
        QModel3 question2 = new QModel3("a13031","她一邊看書，一邊____筷子吃麵。 ","帶","用","找",'B');
        QModel3 question3 = new QModel3("a13031","那個小女孩 ____有一隻狗。","旁邊","前邊","後邊",'A');
        QModel3 question4 = new QModel3("a13031","那隻狗 ____睡覺。 ","在","要","是",'A');
        QModel3 question5 = new QModel3("a13031","小女孩 ____小狗是好朋友。","有","跟","一起",'B');
        QModel3 question6 = new QModel3("a13032","九月五日是小女孩的 ____。 ","生活","生日","星期日",'B');
        QModel3 question7 = new QModel3("a13032","大家都 ____她慶祝。","幫","讓","對",'A');
        QModel3 question8 = new QModel3("a13032","她 ____到很多禮物。 ","收","寄","借",'A');
        QModel3 question9 = new QModel3("a13032","所以，她今天非常____。 ","熱鬧","舒服","高興",'C');
        QModel3 question10 = new QModel3("a13032","希望明年能 ____德國去玩。 ","到","去","來",'A');

        ArrayList<QModel3> al = new ArrayList<QModel3>();

        al.add(question1);
        al.add(question2);
        al.add(question3);
        al.add(question4);
        al.add(question5);
        al.add(question6);
        al.add(question7);
        al.add(question8);
        al.add(question9);
        al.add(question10);

        List<QModel3> group1 = new ArrayList<>(al.subList(0, 5));
        List<QModel3> group2 = new ArrayList<>(al.subList(5, 10));

// Shuffle the order of the groups
        List<List<QModel3>> groups = new ArrayList<>();
        groups.add(group1);
        groups.add(group2);
        Collections.shuffle(groups);



// Concatenate the shuffled groups into randomQuestions
        randomQuestions = new ArrayList<>();
        for (List<QModel3> group : groups) {
            randomQuestions.addAll(group);
        }

        int lastIndex = randomQuestions.size() - 1;

        //loadImage(al.get(index).getqImage());

        //String imagePath = al.get(index).getqImage();

        txtView.setText(randomQuestions.get(0).getqText());
        qNumber3.setText("第 " + (index + 1) + " / 10 題");


        QModel3 firstQuestion = randomQuestions.get(0);

        ansButton13.setText(firstQuestion.getAns1());
        ansButton23.setText(firstQuestion.getAns2());
        ansButton33.setText(firstQuestion.getAns3());

        Intent intentPoint = getIntent();
        int receivedPoint = intentPoint.getIntExtra("totalPoint", 0);
        totalPoint += receivedPoint;

        toMenuPage.setOnClickListener(v -> {
            Intent menuPage = new Intent(ReadingTestPage3.this, MenuPageA.class);
            startActivity(menuPage);
        });

        Button toMainAct = findViewById(R.id.toMainAct);
        toMainAct.setOnClickListener(v -> {
            Intent menuPage = new Intent(ReadingTestPage3.this, ReadingPage.class);
            startActivity(menuPage);
        });

        btnNext3.setOnClickListener(v -> {
            if (!ansChecked) {
                ansGroup.clearCheck();
            }

            if (index == lastIndex) {
                Intent intent = new Intent(ReadingTestPage3.this, ReadingTestPage4.class);
                intent.putExtra("totalPoint", totalPoint); // Pass the updated total points
                startActivity(intent);
            } else {
                index++;
                txtView.setText(randomQuestions.get(index).getqText());
                QModel3 currentQuestion = randomQuestions.get(index);
                StorageReference imgRef = storageReference.child(chapterPath + randomQuestions.get(index).getqImage() + ".jpg");
                imgRef.getDownloadUrl().addOnSuccessListener(uri -> {
                    Picasso.get().load(uri.toString()).into(imgView);
                }).addOnFailureListener(exception -> {
                    // Handle any errors that occurred during image download
                    exception.printStackTrace();
                });
                ansChecked = false;
                qNumber3.setText("第 " + (index + 1) + " / 10 題");

                ansButton13.setText(currentQuestion.getAns1());
                ansButton23.setText(currentQuestion.getAns2());
                ansButton33.setText(currentQuestion.getAns3());
            }
        });

        btnPrev3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (index > 0) {
                    if (!ansChecked) {
                        ansGroup.clearCheck();
                    }

                    index--;
                    txtView.setText(randomQuestions.get(index).getqText());
                    QModel3 currentQuestion = randomQuestions.get(index);
                    StorageReference imgRef = storageReference.child(chapterPath + randomQuestions.get(index).getqImage() + ".jpg");
                    imgRef.getDownloadUrl().addOnSuccessListener(uri -> {
                        Picasso.get().load(uri.toString()).into(imgView);
                    }).addOnFailureListener(exception -> {
                        // Handle any errors that occurred during image download
                        exception.printStackTrace();
                    });
                    ansChecked = false;
                    qNumber3.setText("第 " + (index + 1) + " / 10 題");

                    ansButton13.setText(currentQuestion.getAns1());
                    ansButton23.setText(currentQuestion.getAns2());
                    ansButton33.setText(currentQuestion.getAns3());
                } else {
                    // Navigate to the last question in ReadingTestPage
                    Intent intent = new Intent(ReadingTestPage3.this, ReadingTestPage2.class);
                    intent.putExtra("questionNumber", 15);
                    startActivity(intent);
                }
            }
        });

        ansButton13.setOnClickListener(v -> {
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

        ansButton23.setOnClickListener(v -> {
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

        ansButton33.setOnClickListener(v -> {
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
        Intent intent = getIntent();
        int questionNumber = intent.getIntExtra("questionNumber", -1);
        if (questionNumber != -1) {
            // Navigate to the specified question number
            navigateToQuestion(questionNumber);
        }
    }

    private void navigateToQuestion(int questionNumber) {
        if (questionNumber >= 1 && questionNumber <= randomQuestions.size()) {
            index = questionNumber - 1;
            txtView.setText(randomQuestions.get(index).getqText());
            QModel3 currentQuestion = randomQuestions.get(index);
            StorageReference imgRef = storageReference.child(chapterPath + randomQuestions.get(index).getqImage() + ".jpg");
            imgRef.getDownloadUrl().addOnSuccessListener(uri -> {
                Picasso.get().load(uri.toString()).into(imgView);
            }).addOnFailureListener(exception -> {
                // Handle any errors that occurred during image download
                exception.printStackTrace();
            });
            ansChecked = false;
            qNumber3.setText("第 " + (index + 1) + " / 10 題");

            ansButton13.setText(currentQuestion.getAns1());
            ansButton23.setText(currentQuestion.getAns2());
            ansButton33.setText(currentQuestion.getAns3());
        } else {
            // Handle the case when the question number is out of bounds
            Toast.makeText(this, "Invalid question number", Toast.LENGTH_SHORT).show();
        }
    }

    private void displayQuestion(int questionIndex) {
        if (questionIndex >= 0 && questionIndex < randomQuestions.size()) {
            txtView.setText(randomQuestions.get(index).getqText());
            QModel3 currentQuestion = randomQuestions.get(index);
            StorageReference imgRef = storageReference.child(chapterPath + randomQuestions.get(index).getqImage() + ".jpg");
            imgRef.getDownloadUrl().addOnSuccessListener(uri -> {
                Picasso.get().load(uri.toString()).into(imgView);
            }).addOnFailureListener(exception -> {
                // Handle any errors that occurred during image download
                exception.printStackTrace();
            });
            ansChecked = false;
            qNumber3.setText("第 " + (index + 1) + " / 10 題");

            ansButton13.setText(currentQuestion.getAns1());
            ansButton23.setText(currentQuestion.getAns2());
            ansButton33.setText(currentQuestion.getAns3());
        } else {
            // Handle the case when an invalid question index is passed
            Toast.makeText(this, "Invalid question index", Toast.LENGTH_SHORT).show();
        }
    }

    private void handleAnswerButtonClick(char selectedAnswer) {
        if (randomQuestions.get(index).getAnswer() == selectedAnswer && !ansChecked) {
            totalPoint += 2;
            ansChecked = true;
        }

        // Display the selected answer (A, B, or C) in a toast message
        Toast.makeText(getApplicationContext(), String.valueOf(selectedAnswer), Toast.LENGTH_SHORT).show();

        // Delay the navigation to the next question for a short period (300 milliseconds) to show the toast message
        Handler handler = new Handler();
        handler.postDelayed(() -> {
            // Navigate to the next question if not the last question
            int lastIndex = randomQuestions.size() - 1;
            if (index < lastIndex) {
                index++;
                displayQuestion(index);
            } else {
                // Navigate to the next activity when it's the last question
                Intent intentReadingTestPage2 = new Intent(ReadingTestPage3.this, ReadingTestPage4.class);
                intentReadingTestPage2.putExtra("totalPoint", totalPoint); // Pass the total points to ReadingTestPage2
                startActivity(intentReadingTestPage2);
            }
        }, 300);
    }
}