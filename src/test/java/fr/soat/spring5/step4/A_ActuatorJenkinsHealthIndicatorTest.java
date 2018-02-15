package fr.soat.spring5.step4;

import com.github.tomakehurst.wiremock.junit.WireMockRule;
import fr.soat.spring5.step1.B_MetricsServiceTest;
import fr.soat.spring5.step2.JenkinsJob;
import fr.soat.spring5.step2.JenkinsJobService;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.ArrayList;
import java.util.Arrays;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.getRequestedFor;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import static com.github.tomakehurst.wiremock.client.WireMock.verify;
import static fr.soat.spring5.internal.Testing.resourceContent;
import static org.junit.Assert.*;
import static org.springframework.http.HttpHeaders.CONTENT_TYPE;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.HttpStatus.SERVICE_UNAVAILABLE;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

/**
 * @author Soat - 2/1/18.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(
        properties = {"jenkins.url = http://localhost:8089/"},
        classes = {B_MetricsServiceTest.MetricGeneratorConf.class})
public class A_ActuatorJenkinsHealthIndicatorTest {

    @Rule
    public WireMockRule wireMockRule = new WireMockRule(8089);

    @Autowired
    private A_ActuatorJenkinsHealthIndicator actuatorJenkinsHealthIndicator;

    @Test
    public void health_when_down() {
        stubFor(get(urlEqualTo("/")).willReturn(aResponse().withStatus(SERVICE_UNAVAILABLE.value())));

        // TODO : Implementer la méthode health
        Mono<Health> health = actuatorJenkinsHealthIndicator.health();

        StepVerifier.create(health)
                .expectNext(Health.down().withDetail("Status", SERVICE_UNAVAILABLE.getReasonPhrase()).build())
                .verifyComplete();

        verify(getRequestedFor(urlEqualTo("/")));
    }

    @Test
    public void health_when_up() {
        stubFor(get(urlEqualTo("/")).willReturn(aResponse().withStatus(OK.value())));

        // TODO : Implementer la méthode health
        Mono<Health> health = actuatorJenkinsHealthIndicator.health();

        StepVerifier.create(health)
                .expectNext(Health.up().build())
                .verifyComplete();

        verify(getRequestedFor(urlEqualTo("/")));
    }
}