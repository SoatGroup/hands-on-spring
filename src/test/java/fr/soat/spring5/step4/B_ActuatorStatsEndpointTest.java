package fr.soat.spring5.step4;

import fr.soat.spring5.step1.B_MetricsServiceTest;
import fr.soat.spring5.step1.Metrics;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.Arrays;
import java.util.List;

/**
 * @author Soat - 2/1/18.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(
        properties = {"jenkins.url = http://localhost:8089/"},
        classes = {B_MetricsServiceTest.MetricGeneratorConf.class})
public class B_ActuatorStatsEndpointTest {

    @Autowired
    private B_ActuatorStatsEndpoint actuatorStatsEndpoint;

    @Test
    public void stats() {

        // TODO : Implémenter la méthode stats
        final Mono<List<Metrics>> stats = actuatorStatsEndpoint.stats();

        StepVerifier.create(stats)
                .expectNext(Arrays.asList(
                        new Metrics("Buffers", "1324 kB"),
                        new Metrics("Cached", "4321 kB"),
                        new Metrics("MemFree", "4567 kB"),
                        new Metrics("MemTotal", "12345 kB"),
                        new Metrics("SwapFree", "1111 kB"),
                        new Metrics("SwapTotal", "1023 kB"))
                )
                .verifyComplete();
    }
}