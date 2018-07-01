package my.nhorushko.otus.testgenerator.dao;

import my.nhorushko.otus.testgenerator.model.QuestionBlock;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class QuestionBlockReaderImpl implements QuestionBlockReader {

    private final String fileName;
    private final QuestionBlockParser questionBlockParser;

    public QuestionBlockReaderImpl(@Value("${path.question.file}") String fileName, QuestionBlockParser questionBlockParser) {
        this.fileName = fileName;
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
}



