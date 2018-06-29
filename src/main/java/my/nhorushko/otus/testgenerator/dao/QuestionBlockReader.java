package my.nhorushko.otus.testgenerator.dao;

import my.nhorushko.otus.testgenerator.model.QuestionBlock;

import java.io.IOException;
import java.util.List;

public interface QuestionBlockReader {

    List<QuestionBlock> readAllQuestions();
}
