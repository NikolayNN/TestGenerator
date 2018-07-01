package my.nhorushko.otus.testgenerator.view;

import my.nhorushko.otus.testgenerator.model.Answer;
import my.nhorushko.otus.testgenerator.model.QuestionBlock;
import my.nhorushko.otus.testgenerator.model.Test;
import org.springframework.stereotype.Service;

import java.util.InputMismatchException;
import java.util.Scanner;

@Service
public class Console implements View {

    @Override
    public void write(String message) {
        System.out.println(message);
    }

    @Override
    public void write(QuestionBlock questionBlock) {

        System.out.println(questionBlock.getQuestion().getText());

        for (int i = 0; i < questionBlock.getAnswers().size(); i++) {
            Answer answer = questionBlock.getAnswers().get(i);
            System.out.println(i + ". " + answer.getText());
        }
    }

    @Override
    public String readNotBlankString() {

        String str = "";

        Scanner scanner = new Scanner(System.in);

        while (str.isEmpty() || str == null) {
            str = scanner.nextLine();
        }

        return str;
    }

    @Override
    public int readInt(int maxValue) {

        int numb = Integer.MIN_VALUE;

        while (numb < 0) {
            try {
                numb = new Scanner(System.in).nextInt();
            } catch (InputMismatchException ex) {
                write("Error. Expect numb between 0 and " + maxValue);
                continue;
            }

            if (numb > maxValue || numb < 0) {
                write("Error. Expect numb between 0 and " + maxValue);
                numb = Integer.MIN_VALUE;
            }
        }
        return numb;
    }
}
