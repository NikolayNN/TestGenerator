package my.nhorushko.otus.testgenerator.controller;

import my.nhorushko.otus.testgenerator.model.QuestionBlock;
import my.nhorushko.otus.testgenerator.model.Test;
import my.nhorushko.otus.testgenerator.model.User;
import my.nhorushko.otus.testgenerator.service.TestChekerService;
import my.nhorushko.otus.testgenerator.service.TestGeneratorService;
import my.nhorushko.otus.testgenerator.view.View;
import org.springframework.stereotype.Controller;

import java.util.InputMismatchException;

@Controller
public class ApplicationController {

    private View view;

    private TestGeneratorService testGeneratorService;

    private TestChekerService testChekerService;

    public ApplicationController(View view, TestGeneratorService testGeneratorService, TestChekerService testChekerService) {
        this.view = view;
        this.testGeneratorService = testGeneratorService;
        this.testChekerService = testChekerService;
    }

    public void run() {

        String userName = "";
        while (userName.isEmpty()) {
            view.write("enter your name");
            userName = view.readString();
        }

        String userSurname = "";
        while (userSurname.isEmpty()) {
            view.write("enter your surname");
            userSurname = view.readString();
        }

        Test test = testGeneratorService.generate(new User(userName, userSurname));

        for (QuestionBlock questionBlock : test.getQuestionBlocks()) {

            int chosenAnswerNumber = -10;

            while (chosenAnswerNumber < 0) {

                try {

                    view.writeQuestionBlock(questionBlock);
                    view.write("Select the number of the question:");
                    chosenAnswerNumber = Integer.valueOf(view.readInt(questionBlock.getAnswers().size() - 1));

                } catch (InputMismatchException ex) {
                    view.write("Error. Please Input number from 0 to " + (questionBlock.getAnswers().size() - 1));
                    view.write("______________________");

                } catch (Exception ex) {
                    view.write(ex.getMessage());
                    view.write("______________________");
                }
            }
            test.addDecision(questionBlock, questionBlock.getAnswers().get(chosenAnswerNumber));
        }

        view.write(String.format("You answered correctly for %d questions", testChekerService.calculateCorrectAnswers(test)));
    }
}
