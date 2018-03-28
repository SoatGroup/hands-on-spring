package fr.soat.spring5.step2;

import com.github.tomakehurst.wiremock.junit.WireMockRule;
import fr.soat.spring5.step1.B_MetricsServiceTest;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.reactive.function.client.ClientResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.getRequestedFor;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import static com.github.tomakehurst.wiremock.client.WireMock.verify;
import static fr.soat.spring5.internal.Testing.resourceContent;
import static org.springframework.http.HttpHeaders.CONTENT_TYPE;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

/**
 * @author Soat - 2/1/18.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(
        properties = {"jenkins.url = http://localhost:8089/"},
        classes = {B_MetricsServiceTest.MetricGeneratorConf.class})
public class JenkinsJobServiceTest {

    @Rule
    public WireMockRule wireMockRule = new WireMockRule(8089);

    @Test
    public void jenkinsClient() {
        stubFor(get(urlEqualTo("/api/json")).willReturn(aResponse().withStatus(200)
                .withHeader(CONTENT_TYPE, APPLICATION_JSON_UTF8_VALUE)
                .withBody(resourceContent("jenkins/root.json"))));

        // TODO : Implementer la méthode jenkinsClient
        Mono<ClientResponse> response = JenkinsJobService.jenkinsClient("http://localhost:8089");

        StepVerifier.create(response)
                .expectNextMatches(r -> r.statusCode().is2xxSuccessful())
                .verifyComplete();

        verify(getRequestedFor(urlEqualTo("/api/json")));
    }

    @Test
    public void jobs() {
        stubFor(get(urlEqualTo("/api/json")).willReturn(aResponse().withStatus(200)
                .withHeader(CONTENT_TYPE, APPLICATION_JSON_UTF8_VALUE)
                .withBody(resourceContent("jenkins/root.json"))));

        // TODO : Implementer la méthode jobs
        Flux<JenkinsJob> jobs = JenkinsJobService.jobs("http://localhost:8089");

        StepVerifier.create(jobs)
                .expectNextMatches(j -> j.getName().equals("folder"))
                .expectNextMatches(j -> j.getName().equals("workflow-multi-branch"))
                .expectNextMatches(j -> j.getName().equals("workflow-job"))
                .expectNextMatches(j -> j.getName().equals("free-style"))
                .verifyComplete();

        verify(getRequestedFor(urlEqualTo("/api/json")));
    }

    @Test
    public void deebJobs() {
        stubFor(get(urlEqualTo("/api/json")).willReturn(aResponse().withStatus(200)
                .withHeader(CONTENT_TYPE, APPLICATION_JSON_UTF8_VALUE)
                .withBody(resourceContent("jenkins/root.json"))));
        stubFor(get(urlEqualTo("/job/workflow-multi-branch/api/json")).willReturn(aResponse().withStatus(200)
                .withHeader(CONTENT_TYPE, APPLICATION_JSON_UTF8_VALUE)
                .withBody(resourceContent("jenkins/job-workflow-multi-branch.json"))));
        stubFor(get(urlEqualTo("/job/folder/api/json")).willReturn(aResponse().withStatus(200)
                .withHeader(CONTENT_TYPE, APPLICATION_JSON_UTF8_VALUE)
                .withBody(resourceContent("jenkins/job-folder.json"))));

        // TODO : Implementer la méthode deebJobs
        Flux<JenkinsJob> jobs = JenkinsJobService.deepJobs("http://localhost:8089");

        StepVerifier.create(jobs.map(JenkinsJob::getName))
                .recordWith(ArrayList::new)
                .expectNextCount(6)
                .expectRecordedMatches(i -> i.equals(Arrays.asList(
                        "free-style",
                        "job-folder-1",
                        "job-folder-2",
                        "job-multibranch-1",
                        "job-multibranch-2",
                        "workflow-job")))
                .verifyComplete();

        verify(getRequestedFor(urlEqualTo("/api/json")));
        verify(getRequestedFor(urlEqualTo("/job/folder/api/json")));
        verify(getRequestedFor(urlEqualTo("/job/workflow-multi-branch/api/json")));
    }

}