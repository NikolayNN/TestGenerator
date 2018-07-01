package my.nhorushko.otus.testgenerator.controller;

import my.nhorushko.otus.testgenerator.model.QuestionBlock;
import my.nhorushko.otus.testgenerator.model.Test;
import my.nhorushko.otus.testgenerator.model.User;
import my.nhorushko.otus.testgenerator.service.TestCheckerService;
import my.nhorushko.otus.testgenerator.service.TestGeneratorService;
import my.nhorushko.otus.testgenerator.view.View;
import org.springframework.stereotype.Controller;

@Controller
public class ApplicationController {

    private final View view;

    private final TestGeneratorService testGeneratorService;

    private final TestCheckerService testCheckerService;

    public ApplicationController(View view, TestGeneratorService testGeneratorService, TestCheckerService testCheckerService) {
        this.view = view;
        this.testGeneratorService = testGeneratorService;
        this.testCheckerService = testCheckerService;
    }

    public void run() {

        view.write("enter your name");
        String userName = view.readNotBlankString();

        view.write("enter your surname");
        String userSurname = view.readNotBlankString();

        Test test = testGeneratorService.generate(new User(userName, userSurname));

        for (QuestionBlock questionBlock : test.getQuestionBlocks()) {

            view.write(questionBlock);

            int chosenAnswerNumber = view.readInt(questionBlock.getAnswers().size() - 1);

            test.addDecision(questionBlock, questionBlock.getAnswers().get(chosenAnswerNumber));
        }

        view.write(String.format("You answered correctly for %d questions", testCheckerService.calculateCorrectAnswers(test)));
    }
}
