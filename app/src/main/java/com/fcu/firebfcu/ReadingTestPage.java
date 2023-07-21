package com.fcu.firebfcu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Collections;

public class ReadingTestPage extends AppCompatActivity {
    int index = 0;
    int totalPoint = 0;
    boolean ansChecked = false;
    private StorageReference storageReference;
    int chapterNumber = 1;
    char band = 'a';
    String chapterPath = "rd-" + band + '-' + chapterNumber + '/';
    ScrollView scrollView;
    LinearLayout buttonLayout;
    TextView questionTextView;
    TextView qNumber;
    ImageView img1;
    ImageView img2;
    ImageView img3;
    ArrayList<QModel> randomQuestions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reading_test_page);

        questionTextView = findViewById(R.id.questionTextView);
        qNumber = findViewById(R.id.qNumber);
        img1 = findViewById(R.id.img1);
        img2 = findViewById(R.id.img2);
        img3 = findViewById(R.id.img3);

        FirebaseStorage storage = FirebaseStorage.getInstance();
        storageReference = FirebaseStorage.getInstance().getReference();

        Button btnNext1 = findViewById(R.id.btnNext1);
        Button btnPrev1 = findViewById(R.id.btnPrev1);
        Button toMenuPage = findViewById(R.id.toMenuPageA);
        Button toMainAct = findViewById(R.id.toMainAct);


        QModel question1 = new QModel("桌子上放著三種水果。 ","a110011","a110012","a110013",'B');
        QModel question2 = new QModel("老王正畫著小天的臉。","a110021","a110022","a110023",'B');
        QModel question3 = new QModel("他的房間很乾淨。 ","a110031","a110032","a110033",'A');
        QModel question4 = new QModel("王小明把李天華三個字寫在紙上。 ","a110041","a110042","a110043",'C');
        QModel question5 = new QModel("志明什麼球都玩，但是最喜歡玩足球。","a110051","a110052","a110053",'B');
        QModel question6 = new QModel("小心！你杯子裡的水快要滿了！","a110061","a110062","a110063",'C');
        QModel question7 = new QModel("過了前面的路口，再往前走一會兒就到醫院了。 ","a110071","a110072","a110073",'A');
        QModel question8 = new QModel("我平常都六點起床，可是今天晚了半小時。 ","a110081","a110082","a110083",'C');
        QModel question9 = new QModel("張先生開車的時候喜歡聽音樂。","a110091","a110092","a110093",'C');
        QModel question10 = new QModel("這幾天晚上的風好大。","a110101","a110102","a110103",'B');
        QModel question11 = new QModel("妹妹看完信以後，心情很愉快。 ","a110111","a110112","a110113",'B');
        QModel question12 = new QModel("我們全家下個月就要搬到院子裡有大樹的房子住了。 ","a110121","a110122","a110123",'B');
        QModel question13 = new QModel("因為媽媽怕高，所以沒和爸爸一起爬過山。 ","a110131","a110132","a110133",'C');
        QModel question14 = new QModel("他們約好下課以後，先在學校門口見面，再一起去打球。","a110141","a110142","a110143",'C');
        QModel question15 = new QModel("小美和朋友想到百貨公司買帽子，可是最後只買了點心就離開了。 ","a110151","a110152","a110153",'B');
        QModel question16 = new QModel("那杯牛奶被喝了一半。","a210011","a210012","a210013",'C');
        QModel question17 = new QModel("我家一共三口人。","a210021","a210022","a210023",'C');
        QModel question18 = new QModel("樹下一個禮物也沒有。","a210031","a210032","a210033",'B');
        QModel question20 = new QModel("餐廳的樓上有洗手間","a210041","a210042","a210043",'A');
        QModel question21 = new QModel("沒戴眼鏡的那位先生就是林明華。","a210051","a210052","a210053",'B');
        QModel question22 = new QModel("公車快兩點才來。","a210061","a210062","a210063",'A');
        QModel question23 = new QModel("學生看著黑板上的句子說話。","a210071","a210072","a210073",'C');
        QModel question24 = new QModel("弟弟喜歡用電腦玩遊戲。","a210081","a210082","a210083",'A');
        QModel question25 = new QModel("樓梯上的門不知道被誰打開了。","a210091","a210092","a210093",'C');
        QModel question26 = new QModel("大明受傷了，他現在躺在醫院，不能站也不能坐。","a210101","a210102","a210103",'B');

        ArrayList<QModel> al = new ArrayList<QModel>();
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
        al.add(question11);
        al.add(question12);
        al.add(question13);
        al.add(question14);
        al.add(question15);
        al.add(question16);
        al.add(question17);
        al.add(question18);
        al.add(question20);
        al.add(question21);
        al.add(question22);
        al.add(question23);
        al.add(question24);
        al.add(question25);
        al.add(question26);


        // Shuffle the question list
        Collections.shuffle(al);

        // Select the first 5 questions
        randomQuestions = new ArrayList<>(al.subList(0, 5));
        int lastIndex = randomQuestions.size() - 1;

        ArrayList<Button> questionButtons = new ArrayList<>();

        StorageReference imageRef = storageReference.child(chapterPath + randomQuestions.get(0).getImage1() + ".jpg");
        imageRef.getDownloadUrl().addOnSuccessListener(uri -> {
            Picasso.get().load(uri.toString()).into(img1);
        }).addOnFailureListener(exception -> {
            // Handle any errors that occurred during image download
            exception.printStackTrace();
        });

        StorageReference imageRef2 = storageReference.child(chapterPath + randomQuestions.get(0).getImage2() + ".jpg");
        imageRef2.getDownloadUrl().addOnSuccessListener(uri -> {
            Picasso.get().load(uri.toString()).into(img2);
        }).addOnFailureListener(exception -> {
            // Handle any errors that occurred during image download
            exception.printStackTrace();
        });

        StorageReference imageRef3 = storageReference.child(chapterPath + randomQuestions.get(0).getImage3() + ".jpg");
        imageRef3.getDownloadUrl().addOnSuccessListener(uri -> {
            Picasso.get().load(uri.toString()).into(img3);
        }).addOnFailureListener(exception -> {
            // Handle any errors that occurred during image download
            exception.printStackTrace();
        });

        questionTextView.setText(randomQuestions.get(0).getQuestion());
        qNumber.setText("第 1 / 15 題");

        Intent intentLast = getIntent();
        boolean isLastQuestion = intentLast.getBooleanExtra("lastQuestion", false);
        if (isLastQuestion) {
            // Navigate to the last question in ReadingTestPage
            // Update the code according to your logic
        }

        toMenuPage.setOnClickListener(v -> {
            Intent menuPage = new Intent(ReadingTestPage.this, MenuPageA.class);
            startActivity(menuPage);
        });

        toMainAct.setOnClickListener(v -> {
            Intent menuPage = new Intent(ReadingTestPage.this, ReadingPage.class);
            startActivity(menuPage);
        });

        btnNext1.setOnClickListener(v -> {
            if (index == lastIndex) {
                Intent intent = new Intent(ReadingTestPage.this, ReadingTestPage2.class);
                intent.putExtra("totalPoint", totalPoint); // Pass the current total points
                startActivity(intent);
            } else {
                index++;
                questionTextView.setText(randomQuestions.get(index).getQuestion());

                StorageReference imgRef = storageReference.child(chapterPath + randomQuestions.get(index).getImage1() + ".jpg");
                imgRef.getDownloadUrl().addOnSuccessListener(uri -> {
                    Picasso.get().load(uri.toString()).into(img1);
                }).addOnFailureListener(exception -> {
                    // Handle any errors that occurred during image download
                    exception.printStackTrace();
                });

                StorageReference imgRef2 = storageReference.child(chapterPath + randomQuestions.get(index).getImage2() + ".jpg");
                imgRef2.getDownloadUrl().addOnSuccessListener(uri -> {
                    Picasso.get().load(uri.toString()).into(img2);
                }).addOnFailureListener(exception -> {
                    // Handle any errors that occurred during image download
                    exception.printStackTrace();
                });

                StorageReference imgRef3 = storageReference.child(chapterPath + randomQuestions.get(index).getImage3() + ".jpg");
                imgRef3.getDownloadUrl().addOnSuccessListener(uri -> {
                    Picasso.get().load(uri.toString()).into(img3);
                }).addOnFailureListener(exception -> {
                    // Handle any errors that occurred during image download
                    exception.printStackTrace();
                });

                ansChecked = false;
                qNumber.setText("第 " + (index + 1) + " / 15 題");
            }
        });

        btnPrev1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (index > 0) {
                    index--;
                    qNumber.setText("第 " + (index + 1) + " / 15 題");
                    questionTextView.setText(randomQuestions.get(index).getQuestion());

                    StorageReference imgRef = storageReference.child(chapterPath + randomQuestions.get(index).getImage1() + ".jpg");
                    imgRef.getDownloadUrl().addOnSuccessListener(uri -> {
                        Picasso.get().load(uri).into(img1);
                    });

                    StorageReference imgRef2 = storageReference.child(chapterPath + randomQuestions.get(index).getImage2() + ".jpg");
                    imgRef2.getDownloadUrl().addOnSuccessListener(uri -> {
                        Picasso.get().load(uri).into(img2);
                    });

                    StorageReference imgRef3 = storageReference.child(chapterPath + randomQuestions.get(index).getImage3() + ".jpg");
                    imgRef3.getDownloadUrl().addOnSuccessListener(uri -> {
                        Picasso.get().load(uri).into(img3);
                    });

                    ansChecked = false;
                } else {
                    // Display a message or perform any other action when reaching the first question
                    Toast.makeText(ReadingTestPage.this, "First question reached!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        img1.setOnClickListener(v -> {
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

        img2.setOnClickListener(v -> {
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

        img3.setOnClickListener(v -> {
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
            questionTextView.setText(randomQuestions.get(index).getQuestion());

            // Load the images for the specified question (similar to the existing code)
            // ...

            ansChecked = false;
            qNumber.setText("第 " + (index + 1) + " / 15 題");
        } else {
            // Handle the case when the question number is out of bounds
            Toast.makeText(this, "Invalid question number", Toast.LENGTH_SHORT).show();
        }
    }

    private void displayQuestion(int questionIndex) {
        if (questionIndex >= 0 && questionIndex < randomQuestions.size()) {
            QModel question = randomQuestions.get(questionIndex);
            questionTextView.setText(question.getQuestion());

            // Load and display the images for the question (similar to your existing code)
            // ...

            ansChecked = false;
            qNumber.setText("第 " + (index + 1) + " / 15 題");
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
                Intent intentReadingTestPage2 = new Intent(ReadingTestPage.this, ReadingTestPage2.class);
                intentReadingTestPage2.putExtra("totalPoint", totalPoint); // Pass the total points to ReadingTestPage2
                startActivity(intentReadingTestPage2);
            }
        }, 300);
    }
}
