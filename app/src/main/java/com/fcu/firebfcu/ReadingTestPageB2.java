package com.fcu.firebfcu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.List;

public class ReadingTestPageB2 extends AppCompatActivity {

    int index = 0;
    int totalPoint = 0;
    boolean ansChecked = false;
    private StorageReference storageReference;
    int chapterNumber = 1;
    char band = 'b';
    String chapterPath = "rd-" + band + '-' + chapterNumber + '/' ;
    //private ImageView questionImageView;
    TextView qNumberPageB2;
    TextView questionParagraphB2;
    TextView questionTextPageB2;
    ArrayList<QModelB2> al;
    RadioGroup radioGroupPageB2;
    List<Character> selectedAnswers = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reading_test_page_b2);

        FirebaseStorage storage = FirebaseStorage.getInstance();
        storageReference = FirebaseStorage.getInstance().getReference();

        Button btnNextPageB2 = findViewById(R.id.btnNextPageB2);
        Button btnPrevPageB2 = findViewById(R.id.btnPrevPageB2);
        questionParagraphB2 = findViewById(R.id.questionParagraphB2);
        questionTextPageB2 = findViewById(R.id.questionTextPageB2);
        RadioButton ansButtonPageB2_1 = findViewById(R.id.ansButtonPageB2_1);
        RadioButton ansButtonPageB2_2 = findViewById(R.id.ansButtonPageB2_2);
        RadioButton ansButtonPageB2_3 = findViewById(R.id.ansButtonPageB2_3);
        RadioButton ansButtonPageB2_4 = findViewById(R.id.ansButtonPageB2_4);



        radioGroupPageB2 = findViewById(R.id.radioGroupPageB2);
        qNumberPageB2 = findViewById(R.id.qNumberPageB2);



        QModelB2 question1 = new QModelB2(

                "上星期小明第一次到韓國旅遊。他在那兒玩了五天四夜，其中一天，他去了韓國最高的山，那時候，山上下起了雪，他覺得這樣的風景真美。大部分的時間，他坐電車到幾個大城市去參觀有名的地方，也吃了不少味道特別的韓國菜。小明覺得韓國很好玩，他打算學習韓國的語言，以後有機會的話，他想到韓國讀書。",

                "下面哪一個是對的？",
                "小明已經去過韓國兩次了","小明想學習怎麼做韓國菜","小明覺得韓國不怎麼好玩","小明在韓國住了四個晚上",'D');

        QModelB2 question2 = new QModelB2(

                "孩子，這是你改搭校車回家的第一天。媽媽沒忘記你早上出門前，不停地告訴我搭校車是件很恐怖的事，那語氣裡充滿了面對不確定的不安，更讓媽媽覺得不忍心。然而我只是笑著看你，聽你把所有預期中可怕的後果全部說完，然後篤定地告訴你：「這是你人生的另一個開始，你要學著去面對你自己的人生！」",

                "在這段短文裡，母親面對孩子的不安時，態度怎麼樣？",
                "既害怕又不忍心","不知道該怎麼辦","覺得孩子很可憐","鼓勵孩子自己解決",'D');

        QModelB2 question3 = new QModelB2(

                "今天下午小英在圖書館看了一部電影，這部電影叫做「旅館主人」，講的是一個人怎麼成功的故事。電影中的人本來是一個只有小學畢業、沒有工作、身上只剩一百塊錢的人，後來卻變成一家旅館的老闆。旅館的生意非常好，所以他變得很有錢。這原來是一本小說的故事，因為這本小說寫得很棒，就被拍成電影了。小英覺得這部電影拍得很好，也聽說小說寫得不錯，之後想看看小說和電影的故事內容是不是一樣的。",

                "旅館主人」講的是什麼故事？",
                "一個旅館老闆變成沒有工作的人","一個只有小學畢業的人，變成旅館老闆","一個沒有工作的人，變成旅館的服務生","一個本來很有錢，後來身上只剩一百塊錢的人",'B');

        QModelB2 question4 = new QModelB2(

                "今天下午小英在圖書館看了一部電影，這部電影叫做「旅館主人」，講的是一個人怎麼成功的故事。電影中的人本來是一個只有小學畢業、沒有工作、身上只剩一百塊錢的人，後來卻變成一家旅館的老闆。旅館的生意非常好，所以他變得很有錢。這原來是一本小說的故事，因為這本小說寫得很棒，就被拍成電影了。小英覺得這部電影拍得很好，也聽說小說寫得不錯，之後想看看小說和電影的故事內容是不是一樣的。",

                "下面哪一個是錯的？",
                "小英還沒看過「旅館主人」的小說","「旅館主人」是小英在圖書館看的電影","小英認為「旅館主人」這部電影拍得不錯","小英覺得「旅館主人」的小說和電影沒什麼不同",'D');

        QModelB2 question5 = new QModelB2(

                "在我的國家，每年都有颱風，颱風有的大，有的小。如果來的是大颱風，對我們的生活就有嚴重的影響。今年八月有個大颱風，下大雨、吹大風，火車沒辦法開，飛機停飛，所有人不上班、不上課，大部分的人不敢出去，只能在家看颱風的新聞。好不容易等到颱風離開，很多屋子卻都進水了，許多樹、房子被吹倒了，連橋也被大水沖壞了。聽說這個週末又有一個颱風要來，真希望它是個小颱風。",

                "這段文章主要在說什麼？",
                "颱風季節都是在八月","大颱風對人們生活的影響","如果颱風來，要準備的東西","如果小颱風來，會發生的事情",'B');

        QModelB2 question6 = new QModelB2(

                "在我的國家，每年都有颱風，颱風有的大，有的小。如果來的是大颱風，對我們的生活就有嚴重的影響。今年八月有個大颱風，下大雨、吹大風，火車沒辦法開，飛機停飛，所有人不上班、不上課，大部分的人不敢出去，只能在家看颱風的新聞。好不容易等到颱風離開，很多屋子卻都進水了，許多樹、房子被吹倒了，連橋也被大水沖壞了。聽說這個週末又有一個颱風要來，真希望它是個小颱風。",

                "下面哪一個是錯的？",
                "今年八月的颱風雨下得很大","大颱風來的時候，火車停開","因為這個大颱風，許多房子倒了","雖然大颱風來，還是要繼續上班",'D');

        al = new ArrayList<QModelB2>();

        al.add(question1);
        al.add(question2);
        al.add(question3);
        al.add(question4);
        al.add(question5);
        al.add(question6);


// Shuffle the order of the groups
        //Collections.shuffle(groups);


        int lastIndex = al.size() - 1;

        questionParagraphB2.setText(al.get(0).getQuestionParagraphB2());
        questionTextPageB2.setText(al.get(0).getQuestionTextB2());
        qNumberPageB2.setText("第 1 題");

        QModelB2 firstQuestion = al.get(0);

        ansButtonPageB2_1.setText(firstQuestion.getChoice1());
        ansButtonPageB2_2.setText(firstQuestion.getChoice2());
        ansButtonPageB2_3.setText(firstQuestion.getChoice3());
        ansButtonPageB2_4.setText(firstQuestion.getChoice4());


        btnPrevPageB2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (index > 0) {
                    if (!ansChecked) {
                        radioGroupPageB2.clearCheck();
                    }

                    index--;
                    questionParagraphB2.setText(al.get(index).getQuestionParagraphB2());
                    questionTextPageB2.setText(al.get(index).getQuestionTextB2());
                    QModelB2 currentQuestion = al.get(index);
                    ansChecked = false;
                    qNumberPageB2.setText("第 " + (index + 1) + " 題");

                    ansButtonPageB2_1.setText(firstQuestion.getChoice1());
                    ansButtonPageB2_2.setText(firstQuestion.getChoice2());
                    ansButtonPageB2_3.setText(firstQuestion.getChoice3());
                    ansButtonPageB2_4.setText(firstQuestion.getChoice4());
                } else {
                    // Navigate to the last question in ReadingTestPage
                    Intent intent = new Intent(ReadingTestPageB2.this, ReadingTestPageB1.class);
                    intent.putExtra("questionNumber", 15);
                    startActivity(intent);
                }

            }
        });


        btnNextPageB2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!ansChecked) {
                    radioGroupPageB2.clearCheck();
                }

                if (index == lastIndex) {
                    Intent intent = new Intent(ReadingTestPageB2.this, ReadingTestPageB2ver2.class);
                    intent.putExtra("totalPoint", totalPoint);
                    startActivity(intent);
                } else if (index < lastIndex) {
                    index++;
                    questionParagraphB2.setText(al.get(index).getQuestionParagraphB2());
                    questionTextPageB2.setText(al.get(index).getQuestionTextB2());
                    QModelB2 currentQuestion = al.get(index);

                    ansChecked = false;
                    qNumberPageB2.setText("第 " + (index + 1) + " 題");

                    ansButtonPageB2_1.setText(currentQuestion.getChoice1());
                    ansButtonPageB2_2.setText(currentQuestion.getChoice2());
                    ansButtonPageB2_3.setText(currentQuestion.getChoice3());
                    ansButtonPageB2_4.setText(currentQuestion.getChoice4());
                }
            }
        });

        ansButtonPageB2_1.setOnClickListener(v -> {
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

        ansButtonPageB2_2.setOnClickListener(v -> {
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

        ansButtonPageB2_3.setOnClickListener(v -> {
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

        ansButtonPageB2_4.setOnClickListener(v -> {
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