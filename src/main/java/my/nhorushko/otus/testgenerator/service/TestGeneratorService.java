package my.nhorushko.otus.testgenerator.service;

import my.nhorushko.otus.testgenerator.dao.QuestionBlockReader;
import my.nhorushko.otus.testgenerator.model.QuestionBlock;
import my.nhorushko.otus.testgenerator.model.Test;
import my.nhorushko.otus.testgenerator.model.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class TestGeneratorService {

    private int questionsCount = 5;

    private QuestionBlockReader questionBlockReader;

    public TestGeneratorService(QuestionBlockReader questionBlockReader) {
        this.questionBlockReader = questionBlockReader;
    }

    public Test generate(User executor) {

        List<QuestionBlock> questionBlocksSource = questionBlockReader.readAllQuestions();

        if (questionBlocksSource.size() == 0) {
            throw new RuntimeException("Question list is empty");
        }

        if (questionBlocksSource.size() <= questionsCount){
            return new Test(questionBlocksSource, executor);
        }

        List<QuestionBlock> testQuestionBlocks = new ArrayList<>();

        while (testQuestionBlocks.size() != questionsCount) {

            int randomInt = new Random().nextInt(questionBlocksSource.size());
            testQuestionBlocks.add(questionBlocksSource.get(randomInt));
            questionBlocksSource.remove(randomInt);
        }

        return new Test(testQuestionBlocks, executor);
    }

    public void setQuestionsCount(int questionsCount) {
        this.questionsCount = questionsCount;
    }
}
