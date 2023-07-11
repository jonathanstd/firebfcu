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

import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ReadingTestPage5 extends AppCompatActivity {

    int index = 0;
    int totalPoint = 0;
    boolean ansChecked = false;
    private StorageReference storageReference;
    int chapterNumber = 1;
    char band = 'a';
    String chapterPath = "rd-" + band + '-' + chapterNumber + '/' ;
    //private ImageView questionImageView;
    ScrollView scrollViewPage5;
    LinearLayout buttonLayoutPage5;
    TextView qNumberPage5;
    TextView questionParagraph;
    TextView questionTextPage5;
    ArrayList<QModel5> al;
    RadioGroup radioGroupPage5;
    List<Character> selectedAnswers = new ArrayList<>();
    RadioButton ansButtonPage5_1;
    RadioButton ansButtonPage5_2;
    RadioButton ansButtonPage5_3;
    RadioButton ansButtonPage5_4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reading_test_page5);

        FirebaseStorage storage = FirebaseStorage.getInstance();
        storageReference = FirebaseStorage.getInstance().getReference();
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        scrollViewPage5 = findViewById(R.id.scrollViewPage5);
        buttonLayoutPage5 = findViewById(R.id.buttonLayoutPage5);
        Button btnNextPage5 = findViewById(R.id.btnNextPage5);
        Button btnPrevPage5 = findViewById(R.id.btnPrevPage5);
        questionParagraph = findViewById(R.id.questionParagraph);
        questionTextPage5 = findViewById(R.id.questionTextPage5);
        ansButtonPage5_1 = findViewById(R.id.ansButtonPage5_1);
        ansButtonPage5_2 = findViewById(R.id.ansButtonPage5_2);
        ansButtonPage5_3 = findViewById(R.id.ansButtonPage5_3);
        ansButtonPage5_4 = findViewById(R.id.ansButtonPage5_4);



        radioGroupPage5 = findViewById(R.id.radioGroupPage5);
        qNumberPage5 = findViewById(R.id.qNumberPage5);



        QModel5 question1 = new QModel5("\n曾有一項調查發現，很多員工生病的時候不敢請假，因為他們擔心老闆會不高興，覺得他們沒有責任感。" +
                "有人認為，員工會這麼想是公司的責任。" +
                "一個好的公司應該能照顧員工，而不是讓他們拿健康去換錢。" +
                "因此，讓員工有幸福感，應該是未來企業努力的方向。\n ",
                "這篇文章說了什麼內容？\n",
                "老闆應該給員工多一點兒假","常關心別人的人更有責任感","對公司有意見要勇敢說出來 "," 照顧身體比認真工作更重要 ",'D');

        QModel5 question2 = new QModel5("\n如果你每天都覺得身體很累，有一份報告或許可以告訴你原因。" +
                "這份報告提到了下面幾種可能：不愛運動、水喝得不夠多、總是把事情想得太壞、不吃早餐、吃太多沒營養的食物等。" +
                "以上幾點，只要簡單思考一下自己符合了幾項，再試著做出一些改變，想讓自己更健康一點也不難。\n",
                "在改善健康方面，下面哪一個是作者的建議？\n",
                "要培養運動的好習慣","想要吃什麼就吃什麼","平常應該多做點好事","吃早餐以後不要喝水 ",'A');

        QModel5 question3 = new QModel5("\n從前有一個地方很久都不下雨，人們不管怎麼求雨都沒有用。有一次，他們從很遠的地方，請來一位有智慧的老人，希望他可以幫幫忙。" +
                "老人在附近走了走、看了看，然後告訴他們，請蓋一間小屋，讓他住進去三天，三天當中，他任何人都不見。結果，三天後真的下雨了。大家都問他是怎麼做到的，他只\n" +
                "回答，只要自己的心安靜了，外面就安靜了，所以下雨了。\n",
                "這個故事告訴了我們什麼事？\n",
                "先照顧自己，才能幫助別人","想改變環境，就先改變心情","年輕人應該要學會尊敬老人","聰明的人知道什麼時候下雨 ",'B');

        QModel5 question4 = new QModel5("\n說到錢，每個人對它的想法、使用方式和重視程度都不一樣。" +
                "有人說：「錢是沒有性格的，它在誰的手上就像誰」。" +
                "這句話說得很有道理，人們因為對錢的看法不同，而選擇不同的生活方式。" +
                "比方說，有的人喜歡看到銀行裡的數字不斷增加，所以每天努力工作，很少花錢；有的人覺得錢只要夠用就好，不必太在意工作，因為「自己的時間」，也是一種看不到的「錢」。\n",
                "這段話說了下面哪件事？ \n",
                "錢比什麼都重要","錢可以解決所有事情","人人有自己對錢的看法","錢得放在銀行裡才有價值 ",'C');

        QModel5 question5 = new QModel5("\n以前，電影院的門口常常出現一個牌子，要觀眾別帶外面買的食物進去，如果想吃東西，只能買電影院裡賣的食物。" +
                "後來，新聞說，觀眾其實可以拒絕配合這些電影院的規矩。" +
                "說到這個問題，我認為，電影院的要求不是沒有道理，因為電影結束以後，他們還得打掃那些垃圾。" +
                "其次，如果有人帶了一些有奇怪味道的食物進電影院，也很容易影響其他看電影的人。\n",
                "作者對在電影院裡吃東西的行為怎麼看？ \n",
                "應該要避免吃有特別味道的東西","覺得吃什麼東西都是個人的自由","認為電影院的要求沒有任何道理","看電影的人都有責任要打掃垃圾 ",'A');

        al = new ArrayList<QModel5>();

        al.add(question1);
        al.add(question2);
        al.add(question3);
        al.add(question4);
        al.add(question5);


// Shuffle the order of the groups
        //Collections.shuffle(groups);


        int lastIndex = al.size() - 1;

        questionParagraph.setText(al.get(0).getQuestionParagraph());
        questionTextPage5.setText(al.get(0).getQuestionTextPage5());
        qNumberPage5.setText("第 1 題");

        QModel5 firstQuestion = al.get(0);

        ansButtonPage5_1.setText(firstQuestion.getAns1());
        ansButtonPage5_2.setText(firstQuestion.getAns2());
        ansButtonPage5_3.setText(firstQuestion.getAns3());
        ansButtonPage5_4.setText(firstQuestion.getAns4());


        btnPrevPage5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (index > 0) {
                    if (!ansChecked) {
                        radioGroupPage5.clearCheck();
                    }

                    index--;
                    questionParagraph.setText(al.get(index).getQuestionParagraph());
                    questionTextPage5.setText(al.get(index).getQuestionTextPage5());
                    QModel5 currentQuestion = al.get(index);
                    ansChecked = false;
                    qNumberPage5.setText("第 " + (index + 1) + " 題");

                    ansButtonPage5_1.setText(firstQuestion.getAns1());
                    ansButtonPage5_2.setText(firstQuestion.getAns2());
                    ansButtonPage5_3.setText(firstQuestion.getAns3());
                    ansButtonPage5_4.setText(firstQuestion.getAns4());
                } else {
                    // Navigate to the last question in ReadingTestPage
                    Intent intent = new Intent(ReadingTestPage5.this, TotalPointPage.class);
                    intent.putExtra("questionNumber", 15);
                    startActivity(intent);
                }

            }
        });


        btnNextPage5.setOnClickListener(v -> {
            if (!ansChecked) {
                radioGroupPage5.clearCheck();
            }

            if (index == lastIndex) {
                Intent intent = new Intent(ReadingTestPage5.this, ReadingTestPageB1.class);
                intent.putExtra("totalPoint", totalPoint);
                startActivity(intent);
            } else {
                index++;
                questionParagraph.setText(al.get(index).getQuestionParagraph());
                questionTextPage5.setText(al.get(index).getQuestionTextPage5());
                QModel5 currentQuestion = al.get(index);

                ansChecked = false;
                qNumberPage5.setText("第 " + (index + 1) + " 題");

                ansButtonPage5_1.setText(firstQuestion.getAns1());
                ansButtonPage5_2.setText(firstQuestion.getAns2());
                ansButtonPage5_3.setText(firstQuestion.getAns3());
                ansButtonPage5_4.setText(firstQuestion.getAns4());
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
            LinearLayout linearLayout = findViewById(R.id.buttonLayoutPage5);
            linearLayout.addView(button);
        }

        ansButtonPage5_1.setOnClickListener(v -> {
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

        ansButtonPage5_2.setOnClickListener(v -> {
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

        ansButtonPage5_3.setOnClickListener(v -> {
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

        ansButtonPage5_4.setOnClickListener(v -> {
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
    private void moveToQuestion(int questionNumber) {
        // Calculate the index of the question based on the question number
        int questionIndex = questionNumber - 1;

        if (questionIndex >= 0 && questionIndex < al.size()) {
            index = questionIndex;
            qNumberPage5.setText("第 " + (index + 1) + " 題");
            QModel5 currentQuestion = al.get(index);

            questionParagraph.setText(currentQuestion.getQuestionParagraph());
            questionTextPage5.setText(currentQuestion.getQuestionTextPage5());
            ansButtonPage5_1.setText(currentQuestion.getAns1());
            ansButtonPage5_2.setText(currentQuestion.getAns2());
            ansButtonPage5_3.setText(currentQuestion.getAns3());
            ansButtonPage5_4.setText(currentQuestion.getAns3());

            ansChecked = false;
        } else {
            // Handle the case where the question number is out of range
            Toast.makeText(this, "Invalid question number!", Toast.LENGTH_SHORT).show();
        }
    }

}