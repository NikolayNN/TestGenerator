package my.nhorushko.otus.testgenerator.view;

import my.nhorushko.otus.testgenerator.model.QuestionBlock;

import java.util.Set;

public interface View {

    void write(String message);

    void write(QuestionBlock questionBlock);

    String readNotBlankString();

    String readSpecifyString(Set<String> stringVariants);

    int readInt(int maxValue);
}
