package my.nhorushko.otus.testgenerator.controller;

import my.nhorushko.otus.testgenerator.model.QuestionBlock;
import my.nhorushko.otus.testgenerator.model.Test;
import my.nhorushko.otus.testgenerator.model.User;
import my.nhorushko.otus.testgenerator.service.AppLocalization;
import my.nhorushko.otus.testgenerator.service.TestCheckerService;
import my.nhorushko.otus.testgenerator.service.TestGeneratorService;
import my.nhorushko.otus.testgenerator.view.View;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Controller
public class ApplicationController {

    private final View view;

    private final TestGeneratorService testGeneratorService;

    private final TestCheckerService testCheckerService;

    private final AppLocalization appLocalization;

    private Set<String> availableLanguages;

    public ApplicationController(View view, TestGeneratorService testGeneratorService,
                                 TestCheckerService testCheckerService, AppLocalization appLocalization,
                                 @Value("${application.available.localizations}") String availableLanguages) {
        this.view = view;
        this.testGeneratorService = testGeneratorService;
        this.testCheckerService = testCheckerService;
        this.appLocalization = appLocalization;
        this.availableLanguages = new HashSet(Arrays.asList(availableLanguages.split(" ")));
    }

    public void run() {

        view.write(appLocalization.getLocalizationMessage("select.language", new String[]{availableLanguages.toString()}));
        String selectedLanguage = view.readSpecifyString(availableLanguages);
        appLocalization.setLocale(selectedLanguage);

        view.write(appLocalization.getLocalizationMessage("enter.name"));
        String userName = view.readNotBlankString();

        view.write(appLocalization.getLocalizationMessage("enter.surname"));
        String userSurname = view.readNotBlankString();

        Test test = testGeneratorService.generate(new User(userName, userSurname));

        for (QuestionBlock questionBlock : test.getQuestionBlocks()) {

            view.write(questionBlock);

            int chosenAnswerNumber = view.readInt(questionBlock.getAnswers().size() - 1);

            test.addDecision(questionBlock, questionBlock.getAnswers().get(chosenAnswerNumber));
        }

        view.write(appLocalization.getLocalizationMessage("test.result",
                new String[]{String.valueOf(testCheckerService.calculateCorrectAnswers(test))}));
    }
}
