package my.nhorushko.otus.testgenerator.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class QuestionBlock {

    private Question question;
    private List<Answer> answers;

    public QuestionBlock() {
    }

    public QuestionBlock(Question question, List<Answer> answers) {
        this.question = question;
        this.answers = answers;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }

    public void addAnswer(Answer answer) {

        if (answers == null) {
            answers = new ArrayList<>();
        }

        answers.add(answer);
    }

    @Override
    public String toString() {
        return "QuestionBlock{" +
                "question=" + question +
                ", answers=" + answers +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        QuestionBlock that = (QuestionBlock) o;
        return Objects.equals(question, that.question) &&
                Objects.equals(answers, that.answers);
    }

    @Override
    public int hashCode() {

        return Objects.hash(question, answers);
    }
}
