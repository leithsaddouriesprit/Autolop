package tn.esprit.autoloc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@EnableAspectJAutoProxy
public class AutolocApplication {

    public static void main(String[] args) {
        SpringApplication.run(AutolocApplication.class, args);
    }

}
