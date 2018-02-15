package fr.soat.spring5.step4;

import fr.soat.spring5.step1.Metrics;
import fr.soat.spring5.step1.MetricsService;
import org.reactivestreams.Publisher;
import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.List;

/**
 * <b>ETAPE 4.1 :</b> Définisser un export des métriques de l'étape 1
 *
 * <ul>
 * <li>Utiliser les annotations {@link Endpoint} et {@link Component} sur votre classe</li>
 * <li>Ajouter une methode avec l'annotation {@link ReadOperation}</li>
 * <li>Trouver le bon type de retour (réactif) pour exposer toutes les {@link Metrics} que vous avez écrit à l'étape 1</li>
 * <li>Visiter la page <a href="http://localhost:8080/actuator/">http://localhost:8080/actuator/</a></li>
 * <li>Activer votre métrique en configurant la propriété {@code management.endpoints.web.expose} dans le fichier
 * {@code application.yml}</li>
 * </ul>
 *
 * <b>ETAPE 4.2 :</b> Rajouter des métriques directement dans votre code (sur
 * {@link fr.soat.spring5.step2.JenkinsJobService} par exemple)
 * <ul>
 * <li>A chaque à l'aide de la classe {@link io.micrometer.core.instrument.Metrics} ajouter un compteur à chaque :
 * <li>cancel</li>
 * <li>complete</li>
 * <li>request</li>
 * <li>error</li>
 * </li>
 * <li>Visiter la page <a href="http://localhost:8080/actuator/">http://localhost:8080/actuator/</a></li>
 * </ul>
 *
 * <b>ASTUCE :</b> Pour connaitre tous les endpoints exposés, vous pouvez utilisé {@code ['*']} dans un premier temps
 * pour le propriété {@code management.endpoints.web.expose}
 *
 * @author Soat - 2/12/18.
 */
public class B_ActuatorStatsEndpoint {

    private final MetricsService metricsService;

    public B_ActuatorStatsEndpoint(final MetricsService metricsService) {
        this.metricsService = metricsService;
    }

    public Mono<List<Metrics>> stats() {
        return Mono.error(new RuntimeException("Retourner un mono des métriques (récupérer à l'aide de metricsService)"));
    }
}
