package fr.soat.spring5;

import fr.soat.spring5.step1.MetricsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import javax.annotation.PostConstruct;

/**
 * Spring boot demo application.
 */
@SpringBootApplication
public class ReactiveDemoApplication {

    @Bean
    public MetricsGenerator metricsGenerator() {
        return new MetricsGenerator();
    }

    @Autowired
    private ApplicationContext context;

    @PostConstruct
    public void displayFilename() {
        MetricsService service = context.getBean(MetricsService.class);
        System.out.println("(Java Configuration) FICHIER ---> " + service.getFilename());
    }

    public static void main(String[] args) {
        SpringApplication.run(ReactiveDemoApplication.class, args);
    }

}
