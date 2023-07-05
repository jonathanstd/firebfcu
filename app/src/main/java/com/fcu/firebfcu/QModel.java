package com.fcu.firebfcu;

public class QModel {

    private String question;
    private String image1;
    private String image2;
    private String image3;
    private char answer;

    public QModel(String question1, String image1, String image2, String image3, char answer) {
        this.question = question1; this.image1 = image1; this.image2 = image2; this.image3 = image3; this.answer = answer; }

    public String getQuestion() {
        return question;
    }

    public String getImage1() {
        return image1;
    }

    public String getImage2() {
        return image2;
    }

    public String getImage3() {
        return image3;
    }

    public char getAnswer() {
        return answer;
    }
}