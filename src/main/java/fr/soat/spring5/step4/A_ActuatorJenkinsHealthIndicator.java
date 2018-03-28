package fr.soat.spring5.step4;

import fr.soat.spring5.step2.JenkinsProperties;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.ReactiveHealthIndicator;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import static org.springframework.http.HttpStatus.OK;

/**
 * <b>ETAPE 4 :</b> Définisser un nouveau health check qui vérifie que vous accéder bien à jenkins.
 *
 * <ul>
 * <li>Implémenter la classe {@link ReactiveHealthIndicator}</li>
 * <li>Rajouter l'annotation {@link Component} à votre classe.</li>
 * <li>Puis visiter l'url : http://localhost:8080/actuator/health</li>
 * <li>Et si vous coupiez le réseau ?</li>
 * <li>Activer la propriété {@code management.endpoint.health.show-details} dans fichier {@code application.yml}</li>
 * </ul>
 *
 * @author Soat - 2/11/18.
 */
public class A_ActuatorJenkinsHealthIndicator {

    private final JenkinsProperties jenkinsProperties;

    public A_ActuatorJenkinsHealthIndicator(final JenkinsProperties jenkinsProperties) {
        this.jenkinsProperties = jenkinsProperties;
    }

}
