package my.nhorushko.otus.testgenerator.dao;

import my.nhorushko.otus.testgenerator.model.Answer;
import my.nhorushko.otus.testgenerator.model.Question;
import my.nhorushko.otus.testgenerator.model.QuestionBlock;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.Arrays;

import static org.junit.Assert.*;

public class QuestionBlockParserTest {

    private QuestionBlockParser questionBlockParser;

    @Rule
    public ExpectedException thrown= ExpectedException.none();

    @Before
    public void setUp() {
        questionBlockParser = new QuestionBlockParser();
    }

    @Test
    public void convertToQuestionBlock() {

        //given
        final String givenString = "{Q}question;{true}answer1;{false}answer2";
        final QuestionBlock expectedQuestionBlock = new QuestionBlock();
        expectedQuestionBlock.setAnswers(
                Arrays.asList(new Answer("answer1", true), new Answer("answer2", false)));
        expectedQuestionBlock.setQuestion(new Question("question"));

        //when
        QuestionBlock actualQuestionBlock = questionBlockParser.convertToQuestionBlock(givenString);

        //then
        assertEquals(expectedQuestionBlock, actualQuestionBlock);
    }

    @Test
    public void convertToQuestionBlockIfStringIsNull() {

        //given
        final String givenString = null;
        final QuestionBlock expectedQuestionBlock = new QuestionBlock();

        //when
        QuestionBlock actualQuestionBlock = questionBlockParser.convertToQuestionBlock(givenString);

        //then
        assertEquals(expectedQuestionBlock, actualQuestionBlock);
    }

    @Test
    public void convertToQuestionBlockIfStringIsBlank() {

        //given
        final String givenString = null;
        final QuestionBlock expectedQuestionBlock = new QuestionBlock();

        //when
        QuestionBlock actualQuestionBlock = questionBlockParser.convertToQuestionBlock(givenString);

        //then
        assertEquals(expectedQuestionBlock, actualQuestionBlock);
    }

    @Test
    public void convertToQuestionBlockIfStringNotContainsQuestionToken() {

        //given
        final String givenString = "question;{true}answer1;{false}answer2";

        //then
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Wrong format. Each line have to starts with question with token {Q}");

        //when
        questionBlockParser.convertToQuestionBlock(givenString);
    }
}