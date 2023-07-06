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
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
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
    TextView qNumber3;
    TextView txtView;
    ImageView imgView;
    ArrayList<QModel3> randomQuestions;
    RadioGroup ansGroup;
    List<Character> selectedAnswers = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reading_test_page3);

        FirebaseStorage storage = FirebaseStorage.getInstance();
        storageReference = FirebaseStorage.getInstance().getReference();


        Button btnNext2 = findViewById(R.id.btnNext2);
        Button btnPrev2 = findViewById(R.id.btnPrev2);
        imgView = findViewById(R.id.imgView);
        txtView = findViewById(R.id.qText);
        RadioButton ansButton1 = findViewById(R.id.ansButton1);
        RadioButton ansButton2 = findViewById(R.id.ansButton2);
        RadioButton ansButton3 = findViewById(R.id.ansButton3);
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
        QModel3 question10 = new QModel3("a120a1303225","希望明年能 ____德國去玩。 ","到","去","來",'A');

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

        txtView.setText(randomQuestions.get(0).getqText());
        qNumber3.setText("第 1 題");

        Collections.shuffle(al);
        // Select the first 6 questions
        randomQuestions = new ArrayList<>(al.subList(0, 15));
        int lastIndex = randomQuestions.size() - 1;

        //loadImage(al.get(index).getqImage());

        String imagePath = al.get(index).getqImage();

        btnPrev2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (index > 0) {
                    if (!ansChecked) {
                        ansGroup.clearCheck();
                    }

                    index--;

                    QModel3 currentQuestion = randomQuestions.get(index);
                    StorageReference imgRef = storageReference.child(chapterPath + randomQuestions.get(index).getqImage() + ".jpg");
                    imgRef.getDownloadUrl().addOnSuccessListener(uri -> {
                        Picasso.get().load(uri.toString()).into(imgView);
                    }).addOnFailureListener(exception -> {
                        // Handle any errors that occurred during image download
                        exception.printStackTrace();
                    });
                    ansChecked = false;
                    qNumber3.setText("第 " + (index + 1) + " 題");

                    ansButton1.setText(currentQuestion.getAns1());
                    ansButton2.setText(currentQuestion.getAns2());
                    ansButton3.setText(currentQuestion.getAns3());
                } else {
                    // Navigate to the last question in ReadingTestPage
                    Intent intent = new Intent(ReadingTestPage3.this, ReadingTestPage2.class);
                    intent.putExtra("questionNumber", 15);
                    startActivity(intent);
                }

            }
        });


        btnNext2.setOnClickListener(v -> {
            if (!ansChecked) {
                ansGroup.clearCheck();
            }

            if (index == lastIndex) {
                Intent intent = new Intent(ReadingTestPage3.this, TotalPointPage.class);
                intent.putExtra("totalPoint", totalPoint);
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
                qNumber3.setText("第 " + (index + 1) + " 題");

                ansButton1.setText(currentQuestion.getAns1());
                ansButton2.setText(currentQuestion.getAns2());
                ansButton3.setText(currentQuestion.getAns3());
            }
        });

        for (int i = 1; i <= 15; i++) {
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
            LinearLayout linearLayout = findViewById(R.id.buttonLayout);
            linearLayout.addView(button);
        }

        ansButton1.setOnClickListener(v -> {
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

        ansButton2.setOnClickListener(v -> {
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

        ansButton3.setOnClickListener(v -> {
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

    }
    private void moveToQuestion(int questionNumber) {
        // Calculate the index of the question based on the question number
        int questionIndex = questionNumber - 1;

        if (questionIndex >= 0 && questionIndex < randomQuestions.size()) {
            index = questionIndex;
            qNumber3.setText("第 " + (index + 1) + " 題");

            StorageReference imgRef = storageReference.child(chapterPath + randomQuestions.get(index).getqImage() + ".jpg");
            imgRef.getDownloadUrl().addOnSuccessListener(uri -> {
                Picasso.get().load(uri.toString()).into(imgView);
            }).addOnFailureListener(exception -> {
                // Handle any errors that occurred during image download
                exception.printStackTrace();
            });

            ansChecked = false;
        } else {
            // Handle the case where the question number is out of range
            Toast.makeText(this, "Invalid question number!", Toast.LENGTH_SHORT).show();
        }
    }

}