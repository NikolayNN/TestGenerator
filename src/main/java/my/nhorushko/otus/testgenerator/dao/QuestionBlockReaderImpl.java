package my.nhorushko.otus.testgenerator.dao;

import my.nhorushko.otus.testgenerator.model.QuestionBlock;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Repository
public class QuestionBlockReaderImpl implements QuestionBlockReader {

    private String fileName = "src/main/resources/questions.csv";
    private QuestionBlockParser questionBlockParser;

    public QuestionBlockReaderImpl(QuestionBlockParser questionBlockParser) {
        this.questionBlockParser = questionBlockParser;
    }

    public List<QuestionBlock> readAllQuestions() {

        List<QuestionBlock> questionBlocks;

        try (Stream<String> stream = Files.lines(Paths.get(fileName))) {

            questionBlocks = stream
                    .filter(line -> !line.isEmpty())
                    .map(line -> questionBlockParser.convertToQuestionBlock(line))
                    .collect(Collectors.toList());

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return questionBlocks;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}



