package com.fcu.firebfcu;

public class QModelB1 {

    String questionParagraph;
    String questionTextPageB1;
    String choice1;
    String choice2;
    String choice3;
    String choice4;
    char answer;

    public String getQuestionParagraph() {
        return questionParagraph;
    }

    public void setQuestionParagraph(String questionParagraph) {
        this.questionParagraph = questionParagraph;
    }

    public String getQuestionTextPageB1() {
        return questionTextPageB1;
    }

    public void setQuestionTextPageB1(String questionTextPageB1) {
        this.questionTextPageB1 = questionTextPageB1;
    }

    public String getChoice1() {
        return choice1;
    }

    public void setChoice1(String choice1) {
        this.choice1 = choice1;
    }

    public String getChoice2() {
        return choice2;
    }

    public void setChoice2(String choice2) {
        this.choice2 = choice2;
    }

    public String getChoice3() {
        return choice3;
    }

    public void setChoice3(String choice3) {
        this.choice3 = choice3;
    }

    public String getChoice4() {
        return choice4;
    }

    public void setChoice4(String choice4) {
        this.choice4 = choice4;
    }

    public char getAnswer() {
        return answer;
    }

    public void setAnswer(char answer) {
        this.answer = answer;
    }

    public QModelB1(String questionParagraph, String questionTextPageB1, String choice1, String choice2, String choice3, String choice4, char answer) {
        this.questionParagraph = questionParagraph;
        this.questionTextPageB1 = questionTextPageB1;
        this.choice1 = choice1;
        this.choice2 = choice2;
        this.choice3 = choice3;
        this.choice4 = choice4;
        this.answer = answer;
    }
}
