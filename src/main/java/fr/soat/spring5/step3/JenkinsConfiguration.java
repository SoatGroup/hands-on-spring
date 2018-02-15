package fr.soat.spring5.step3;

import fr.soat.spring5.step2.JenkinsJob;
import fr.soat.spring5.step2.JenkinsJobService;
import io.micrometer.core.instrument.Metrics;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;

import java.net.URI;
import java.time.Duration;

/**
 * <b>ETAPE 3 :</b> Passer au {@link RouterFunction}.
 *
 * <ul>
 * <li>Définisser un @Bean {@link RouterFunction}</li>
 * <li>Utiliser la classe {@link RouterFunctions} pour définir 2 routes :
 * <ul>
 * <li>{@code /} qui redirige vers index.html</li>
 * <li>{@code /jobs/feed} qui liste tous les jobs enfants toutes les 10 secondes</li>
 * </ul>
 * </li>
 * </ul>
 *
 * <b>ASTUCE :</b> Pour activer cette classe, pensez à rajouter l'annotation {@link Configuration}.
 *
 * @author Soat - 2/10/18.
 */
@Configuration
public class JenkinsConfiguration {

    private final JenkinsJobService jenkinsService;

    public JenkinsConfiguration(final JenkinsJobService jenkinsService) {
        this.jenkinsService = jenkinsService;
    }

    /**
     * Pour définir une {@link RouterFunction}, utiliser la classe {@link RouterFunctions} comme un builder
     *
     * @return votre {@link RouterFunction}
     */
    @Bean
    public RouterFunction routes() {
        return RouterFunctions
                .route(
                        RequestPredicates.GET("/"),
                        request -> ServerResponse.temporaryRedirect(URI.create("/index.html")).build())
                .and(RouterFunctions.route(
                        RequestPredicates.GET("/jobs/feed"),
                        request -> ServerResponse.ok()
                                .contentType(MediaType.TEXT_EVENT_STREAM)
                                .body(BodyInserters.fromPublisher(Flux.interval(Duration.ZERO, Duration.ofSeconds(10))
                                        .doOnEach((i) -> Metrics.counter("jenkins.count").increment())
                                        .doOnRequest((i) -> Metrics.counter("jenkins.request").increment())
                                        .doOnCancel(() -> Metrics.counter("jenkins.cancel").increment())
                                        .doOnError((e) -> Metrics.counter("jenkins.error").increment())
                                        .flatMap(i -> jenkinsService.jobStatus())
                                        .share(), JenkinsJob.class)
                                ))
                );
    }

}
