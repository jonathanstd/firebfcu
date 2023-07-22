package com.fcu.firebfcu;

public class QModel2 {

    public String getqImage() {
        return qImage;
    }

    public void setqImage(String qImage) {
        this.qImage = qImage;
    }

    public String getAns1() {
        return ans1;
    }

    public void setAns1(String ans1) {
        this.ans1 = ans1;
    }

    public String getAns2() {
        return ans2;
    }

    public void setAns2(String ans2) {
        this.ans2 = ans2;
    }

    public String getAns3() {
        return ans3;
    }

    public void setAns3(String ans3) {
        this.ans3 = ans3;
    }

    public char getAnswer() {
        return answer;
    }

    public void setAnswer(char answer) {
        this.answer = answer;
    }


    public QModel2(String qImage, String ans1, String ans2, String ans3, char answer) {
        this.qImage = qImage; this.ans1 = ans1; this.ans2 = ans2; this.ans3 = ans3; this.answer = answer; }

    String qImage;

String ans1;

String ans2;

String ans3;

//String selectedAns;

char answer;
    private char selectedAnswer;

    public QModel2(char selectedAnswer) {
        // Initialize your fields
        // ...
        this.selectedAnswer = selectedAnswer;
    }

    public char getSelectedAnswer() {
        return selectedAnswer;
    }

    public void setSelectedAnswer(char selectedAnswer) {
        this.selectedAnswer = selectedAnswer;
    }


}