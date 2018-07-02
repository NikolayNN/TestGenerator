package my.nhorushko.otus.testgenerator.model;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Test {

    private final List<QuestionBlock> questionBlocks;

    private final User executor;

    private Map<QuestionBlock, Answer> decision;

    public Test(List<QuestionBlock> questionBlocks, User executor) {
        this.questionBlocks = questionBlocks;
        this.executor = executor;
    }

    public List<QuestionBlock> getQuestionBlocks() {
        return questionBlocks;
    }

    public Map<QuestionBlock, Answer> getDecision() {
        return decision;
    }

    public User getExecutor() {
        return executor;
    }

    public void addDecision(QuestionBlock questionBlock, Answer chosenAnswer){

        if (decision == null){
            decision = new LinkedHashMap<>();
        }

        decision.put(questionBlock, chosenAnswer);
    }
}
