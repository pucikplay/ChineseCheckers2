package tp.checkers.springtest;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {
    public static void main(String[] args) {
        ApplicationContext appContext = new ClassPathXmlApplicationContext("spring-configuration.xml");
        SpringTest test = (SpringTest) appContext.getBean("test");
        test.testAdding();
        test.testPrinting();
    }
}
