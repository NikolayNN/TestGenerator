package my.nhorushko.otus.testgenerator.service;

import my.nhorushko.otus.testgenerator.model.Test;

public class TestChekerService {

    public int calculateCorrectAnswers(Test test){

        return (int) test.getDecision().entrySet()
                .stream()
                .filter(x -> x.getValue().isCorrect())
                .count();
    }
}
