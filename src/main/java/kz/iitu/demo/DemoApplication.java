package kz.iitu.demo;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Random;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.scan("com.example.demo");
        context.refresh();
        AccountService bankService = context.getBean("Service", ATM.class);
        Random rand = new Random();

        Client randomClient = bankService.getBank().getClients().get(rand.nextInt(bankService.getBank().getClients().size()));
        bankService.createPin(randomClient);
        ((AnnotationConfigApplicationContext) context).close();

    }
}
