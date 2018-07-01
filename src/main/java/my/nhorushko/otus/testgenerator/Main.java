package my.nhorushko.otus.testgenerator;

import my.nhorushko.otus.testgenerator.controller.ApplicationController;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;

@ComponentScan
@PropertySource("classpath:/application.properties")
public class Main {

    public static void main(String[] args) {

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Main.class);
        ApplicationController app = context.getBean(ApplicationController.class);

        app.run();
    }
}
