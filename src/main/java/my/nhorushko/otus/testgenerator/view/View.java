package my.nhorushko.otus.testgenerator.view;

import my.nhorushko.otus.testgenerator.model.QuestionBlock;

public interface View {

    void write(String message);
    void write(QuestionBlock questionBlock);
    String readNotBlankString();
    int readInt(int maxValue);
}
