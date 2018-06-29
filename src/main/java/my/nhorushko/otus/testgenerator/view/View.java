package my.nhorushko.otus.testgenerator.view;

import my.nhorushko.otus.testgenerator.model.QuestionBlock;

public interface View {

    void write(String message);
    void writeQuestionBlock(QuestionBlock questionBlock);
    String readString();
    int readInt(int maxValue);
}
