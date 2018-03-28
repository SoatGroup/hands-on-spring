package fr.soat.spring5.step1;

import fr.soat.spring5.MetricsGenerator;
import fr.soat.spring5.ReactiveDemoApplication;
import fr.soat.spring5.internal.StaticMetricsGenerator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Primary;
import org.springframework.test.context.junit4.SpringRunner;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

/**
 * @author Soat - 10/3/17.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {B_MetricsServiceTest.MetricGeneratorConf.class})
public class B_MetricsServiceTest {

    @Autowired
    private MetricsService service;

    @Autowired
    private MetricsGenerator staticGenerator;

    @Configuration
    @Import(ReactiveDemoApplication.class)
    public static class MetricGeneratorConf {
        @Bean
        @Primary
        public MetricsGenerator metricsGenerator() {
            return new StaticMetricsGenerator();
        }
    }

    @Test
    public void readFile() {

        Flux<String> result = service.readFile();

        String[] expected = staticGenerator.createStats()
                .split("\n");

        StepVerifier.create(result)
                .expectNext(expected)
                .verifyComplete();
    }


    @Test
    public void toMetrics() {

        Flux<Metrics> result = service.toMetrics()
                .take(1);

        Metrics expected = new Metrics();
        expected.setName("MemTotal");
        expected.setValue("12345 kB");


        StepVerifier.create(result)
                .expectNext(expected)
                .verifyComplete();
    }


    @Test
    public void fileToFlux() {
        Flux<Metrics> result = service.watchFile()
                .take(1);

        Metrics expected = new Metrics();
        expected.setName("MemTotal");
        expected.setValue("12345 kB");

        StepVerifier.create(result)
                .expectNext(expected)
                .verifyComplete();
    }

    @Test
    public void filterFileToFlux() {
        Flux<Metrics> result = service.watchFile("MemFree")
                .take(1);

        Metrics expected = new Metrics();
        expected.setName("MemFree");
        expected.setValue("4567 kB");

        StepVerifier.create(result)
                .expectNext(expected)
                .verifyComplete();
    }

}