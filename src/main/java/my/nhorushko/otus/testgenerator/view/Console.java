package my.nhorushko.otus.testgenerator.view;

import my.nhorushko.otus.testgenerator.model.Answer;
import my.nhorushko.otus.testgenerator.model.QuestionBlock;
import my.nhorushko.otus.testgenerator.model.Test;

import java.util.Scanner;

public class Console implements View {

    @Override
    public void write(String message) {
        System.out.println(message);
    }

    @Override
    public void writeQuestionBlock(QuestionBlock questionBlock) {

        System.out.println(questionBlock.getQuestion().getText());

        for (int i = 0; i < questionBlock.getAnswers().size(); i++) {
            Answer answer = questionBlock.getAnswers().get(i);
            System.out.println(i + ". " + answer.getText());
        }
    }

    @Override
    public String readString() {
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }

    @Override
    public int readInt(int maxValue) {

        Scanner sc = new Scanner(System.in);
        int numb = sc.nextInt();

        if(numb > maxValue || numb < 0){
            throw new RuntimeException("Error. Expect numb between 0 and " + maxValue);
        }

        return numb;
    }


}
