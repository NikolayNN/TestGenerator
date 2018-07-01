package my.nhorushko.otus.testgenerator.service;

import my.nhorushko.otus.testgenerator.model.Test;
import org.springframework.stereotype.Service;

@Service
public class TestCheckerService {

    public int calculateCorrectAnswers(Test test){

        return (int) test.getDecision().entrySet()
                .stream()
                .filter(x -> x.getValue().isCorrect())
                .count();
    }
}
