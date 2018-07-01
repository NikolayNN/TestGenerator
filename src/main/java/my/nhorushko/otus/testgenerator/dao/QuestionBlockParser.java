package my.nhorushko.otus.testgenerator.dao;

import my.nhorushko.otus.testgenerator.model.Answer;
import my.nhorushko.otus.testgenerator.model.Question;
import my.nhorushko.otus.testgenerator.model.QuestionBlock;

public class QuestionBlockParser {

    private final String csvSeparator;
    private final String questionToken;
    private final String answerTrueToken;
    private final String answerFalseToken;

    public QuestionBlockParser(String csvSeparator, String questionToken, String answerTrueToken, String answerFalseToken) {
        this.csvSeparator = csvSeparator;
        this.questionToken = questionToken;
        this.answerTrueToken = answerTrueToken;
        this.answerFalseToken = answerFalseToken;
    }

    public QuestionBlock convertToQuestionBlock(String line) {

        if (line == null || line.isEmpty()){
            return new QuestionBlock();
        }

        String[] questionBlockArray = line.trim().split(csvSeparator);

        QuestionBlock result = new QuestionBlock();
        result.setQuestion(questionParser(questionBlockArray[0]));

        for (int i = 1; i < questionBlockArray.length; i++) {

            result.addAnswer(answerParser(questionBlockArray[i]));
        }

        return result;
    }

    private Question questionParser(String line) {

        if (!line.startsWith(questionToken)) {
            throw new IllegalArgumentException("Wrong format. Each line have to starts with question with token " + questionToken);
        }

        return new Question(line.substring(questionToken.length()));
    }

    private Answer answerParser(String line) {

        if (line.startsWith(answerTrueToken)) {
            return new Answer(line.substring(answerTrueToken.length()), true);
        }
        if (line.startsWith(answerFalseToken)) {
            return new Answer(line.substring(answerFalseToken.length()), false);
        }

        throw new IllegalArgumentException("Wrong format. Each answer have to starts with token {true} or {false}");
    }
}
