package com.fcu.firebfcu;

public class QModel4 {

    String qText1;

    String qText2;

    String ans1;

    String ans2;

    String ans3;
    String ans4;

    public String getAns4() {
        return ans4;
    }

    public void setAns4(String ans4) {
        this.ans4 = ans4;
    }

    public String getAns5() {
        return ans5;
    }

    public void setAns5(String ans5) {
        this.ans5 = ans5;
    }

    public String getAns6() {
        return ans6;
    }

    public void setAns6(String ans6) {
        this.ans6 = ans6;
    }

    String ans5;

    String ans6;

    char answer;

    public QModel4(String qText1, String qText2, String ans1, String ans2, String ans3,String ans4, String ans5, String ans6, char answer) {
        this.qText1 = qText1;
        this.qText2 = qText2;
        this.ans1 = ans1;
        this.ans2 = ans2;
        this.ans3 = ans3;
        this.ans4 = ans4;
        this.ans5 = ans5;
        this.ans6 = ans6;
        this.answer = answer; }

    public String getqText1() {
        return qText1;
    }

    public void setqText1(String qText1) {
        this.qText1 = qText1;
    }

    public String getqText2() {
        return qText2;
    }

    public void setqText2(String qText2) {
        this.qText2 = qText2;
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
}