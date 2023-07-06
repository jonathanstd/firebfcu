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

public class ReadingTestPage2 extends AppCompatActivity {

    int index = 0;
    int totalPoint = 0;
    boolean ansChecked = false;
    private StorageReference storageReference;
    int chapterNumber = 1;
    char band = 'a';
    String chapterPath = "rd-" + band + '-' + chapterNumber + '/' ;
    //private ImageView questionImageView;
    TextView qNumber2;
    ImageView imgView;
    ArrayList<QModel2> randomQuestions;
    RadioGroup ansGroup;
    List<Character> selectedAnswers = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reading_test_page2);

        FirebaseStorage storage = FirebaseStorage.getInstance();
        storageReference = FirebaseStorage.getInstance().getReference();


        Button btnNext2 = findViewById(R.id.btnNext2);
        Button btnPrev2 = findViewById(R.id.btnPrev2);
        imgView = findViewById(R.id.imgView);
        RadioButton ansButton1 = findViewById(R.id.ansButton1);
        RadioButton ansButton2 = findViewById(R.id.ansButton2);
        RadioButton ansButton3 = findViewById(R.id.ansButton3);
        ansGroup = findViewById(R.id.radioGroup);
        qNumber2 = findViewById(R.id.qNumber2);
        qNumber2.setText("第 1 題");

        QModel2 question1 = new QModel2("a12016","小狗正追著小貓。","屋子裡面什麼都沒有。","幾隻小鳥停在屋子上面。 ",'C');
        QModel2 question2 = new QModel2("a12017","這裡有樹和一間房子。","房子的前面停了幾輛車。","有一些人站在房子旁邊。 ",'A');
        QModel2 question3 = new QModel2("a12018","她正在休息。"," 她在床上睡覺。"," 她正寫著作業。",'A');
        QModel2 question4 = new QModel2("a12019","短頭髮的小姐在喝酒。 "," 長頭髮的小姐穿裙子。"," 穿裙子的小姐拿著皮包。",'B');
        QModel2 question5 = new QModel2("a12020","小吃店九點以前休息。 ","  小吃店十二點開始休息。。"," 十二點以後可以去小吃店吃飯。 ",'C');
        QModel2 question6 = new QModel2("a12021","一天吃三粒。","三天吃一次。","先吃飯再吃藥。",'C');
        QModel2 question7 = new QModel2("a12022","中午以後買票比較貴。","上午買兩張票要 500 元。","想看電影要中午以後才行。 ",'A');
        QModel2 question8 = new QModel2("a12023","教室的門是關著的。","這位老師今天穿裙子。","這位老師的頭髮短短的。",'B');
        QModel2 question9 = new QModel2("a12024","他們現在正在上課。 ","他們從學校走出來。","男孩走在女孩的前面。",'C');
        QModel2 question10 = new QModel2("a12025","這家餐廳不賣飲料。","餐廳裡一個人都沒有。","這位女客人買了兩杯果汁。",'C');
        QModel2 question11 = new QModel2("a12026","小美下午三點多才到旅館。","在這兒住一晚要兩百多塊。","小美帶了好幾件大的行李。 ",'B');
        QModel2 question12 = new QModel2("a12027","那位男生正在賣麵包。","等車的小姐拿著一袋水果。","有一位小姐在等七十六號公車。  ",'B');
        QModel2 question13 = new QModel2("a12028","王小姐買了三雙鞋子。 ","這家店只賣鞋子和襪子。","這家店也賣褲子和皮包。 ",'C');
        QModel2 question14 = new QModel2("a12029","李天明教台北人英文。 "," 李天明要找外國人學英文。"," 李天明覺得學中文很便宜。",'B');
        QModel2 question15 = new QModel2("a12030","兩個人一起去,就有紅茶可以喝。 ","一個人不到兩萬就可以去韓國旅行。","三個人一起去的話，一共可以少給 1000 元。 ",'B');
        QModel2 question16 = new QModel2("a22016","這裡是機場。","火車到車站了。","很多人在等車。",'B');
        QModel2 question17 = new QModel2("a22017","他們換了新家具。","他們忘了打開窗戶。","他們兩個人搬不了沙發。 ",'A');
        QModel2 question18 = new QModel2("a22018","老師準備上課。","學生們正在休息。","教室裡有人在說話。",'A');
        QModel2 question19 = new QModel2("a22019","郵局在銀行旁邊。 ","銀行的對面是郵局。","車站在郵局的前面。",'B');
        QModel2 question20 = new QModel2("a22020","他們點了麵和湯。","他們覺得很傷心。","女生坐在男生的右邊。",'C');

        ArrayList<QModel2> al = new ArrayList<QModel2>();
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
        al.add(question19);
        al.add(question20);


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

                    QModel2 currentQuestion = randomQuestions.get(index);
                    StorageReference imgRef = storageReference.child(chapterPath + randomQuestions.get(index).getqImage() + ".jpg");
                    imgRef.getDownloadUrl().addOnSuccessListener(uri -> {
                        Picasso.get().load(uri.toString()).into(imgView);
                    }).addOnFailureListener(exception -> {
                        // Handle any errors that occurred during image download
                        exception.printStackTrace();
                    });
                    ansChecked = false;
                    qNumber2.setText("第 " + (index + 1) + " 題");

                    ansButton1.setText(currentQuestion.getAns1());
                    ansButton2.setText(currentQuestion.getAns2());
                    ansButton3.setText(currentQuestion.getAns3());
                } else {
                    // Navigate to the last question in ReadingTestPage
                    Intent intent = new Intent(ReadingTestPage2.this, ReadingTestPage.class);
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
                Intent intent = new Intent(ReadingTestPage2.this, ReadingTestPage3.class);
                intent.putExtra("totalPoint", totalPoint);
                startActivity(intent);
            } else {
                index++;
                QModel2 currentQuestion = randomQuestions.get(index);
                StorageReference imgRef = storageReference.child(chapterPath + randomQuestions.get(index).getqImage() + ".jpg");
                imgRef.getDownloadUrl().addOnSuccessListener(uri -> {
                    Picasso.get().load(uri.toString()).into(imgView);
                }).addOnFailureListener(exception -> {
                    // Handle any errors that occurred during image download
                    exception.printStackTrace();
                });
                ansChecked = false;
                qNumber2.setText("第 " + (index + 1) + " 題");

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
            qNumber2.setText("第 " + (index + 1) + " 題");

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