package my.nhorushko.otus.testgenerator;

import my.nhorushko.otus.testgenerator.controller.ApplicationController;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class Main {



    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("/context.xml");
        ApplicationController app = context.getBean(ApplicationController.class);

        app.run();
    }

}
