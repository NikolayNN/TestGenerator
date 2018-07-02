package my.nhorushko.otus.testgenerator.dao;

import my.nhorushko.otus.testgenerator.model.QuestionBlock;
import my.nhorushko.otus.testgenerator.service.AppLocalization;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class QuestionBlockReaderImpl implements QuestionBlockReader {

    private final AppLocalization appLocalization;
    private final QuestionBlockParser questionBlockParser;

    public QuestionBlockReaderImpl(AppLocalization appLocalization, QuestionBlockParser questionBlockParser) {
        this.appLocalization = appLocalization;
        this.questionBlockParser = questionBlockParser;
    }

    public List<QuestionBlock> readAllQuestions() {

        List<QuestionBlock> questionBlocks;

        try (Stream<String> stream = Files.lines(Paths.get(appLocalization.getQuestionFilePath()))) {

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



