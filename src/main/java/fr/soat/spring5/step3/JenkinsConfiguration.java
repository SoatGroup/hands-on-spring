package fr.soat.spring5.step3;

import fr.soat.spring5.step2.JenkinsJobService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;

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
        throw new RuntimeException("Implémenter vos routes !");
    }

}
