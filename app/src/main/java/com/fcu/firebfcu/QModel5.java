package com.fcu.firebfcu;

public class QModel5 {

    String questionParagraph;

    String questionTextPage5;

    String ans1;

    String ans2;

    String ans3;
    String ans4;

    char answer;

    public String getQuestionParagraph() {
        return questionParagraph;
    }

    public void setQuestionParagraph(String questionParagraph) {
        this.questionParagraph = questionParagraph;
    }

    public String getQuestionTextPage5() {
        return questionTextPage5;
    }

    public void setQuestionTextPage5(String questionTextPage5) {
        this.questionTextPage5 = questionTextPage5;
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

    public String getAns4() {
        return ans4;
    }

    public void setAns4(String ans4) {
        this.ans4 = ans4;
    }

    public char getAnswer() {
        return answer;
    }

    public void setAnswer(char answer) {
        this.answer = answer;
    }

    public QModel5(String questionParagraph,
                   String questionTextPage5,
                   String ans1,
                   String ans2,
                   String ans3,
                   String ans4,
                   char answer) {
        this.questionParagraph = questionParagraph;
        this.questionTextPage5 = questionTextPage5;
        this.ans1 = ans1;
        this.ans2 = ans2;
        this.ans3 = ans3;
        this.ans4 = ans4;
        this.answer = answer; }



}