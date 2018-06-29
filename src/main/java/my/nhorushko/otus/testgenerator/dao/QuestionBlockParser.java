package my.nhorushko.otus.testgenerator.dao;

import my.nhorushko.otus.testgenerator.model.Answer;
import my.nhorushko.otus.testgenerator.model.Question;
import my.nhorushko.otus.testgenerator.model.QuestionBlock;

public class QuestionBlockParser {

    private String csvSeparator;
    private String questionToken;
    private String answerTrueToken;
    private String answerFalseToken;

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

    public void setCsvSeparator(String csvSeparator) {
        this.csvSeparator = csvSeparator;
    }

    public void setQuestionToken(String questionToken) {
        this.questionToken = questionToken;
    }

    public void setAnswerTrueToken(String answerTrueToken) {
        this.answerTrueToken = answerTrueToken;
    }

    public void setAnswerFalseToken(String answerFalseToken) {
        this.answerFalseToken = answerFalseToken;
    }
}
