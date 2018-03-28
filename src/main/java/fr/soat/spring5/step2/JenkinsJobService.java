package fr.soat.spring5.step2;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.reactive.function.client.ClientResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;

/**
 * ETAPE 2 : Développer votre dashboard jenkins.
 */
@Service
public class JenkinsJobService {

    /**
     * Dans Jenkins, les types de job pouvant contenir d'autres jobs.
     */
    private final static List<String> PARENTS_TYPE = Arrays.asList(
            "com.cloudbees.hudson.plugins.folder.Folder",
            "org.jenkinsci.plugins.workflow.multibranch.WorkflowMultiBranchProject"
    );

    /** Propriétés de jenkins. */
    private final JenkinsProperties properties;

    public JenkinsJobService(final JenkinsProperties properties) {
        this.properties = properties;
    }

    /**
     * <b>ETAPE 2.1 :</b> Récupère une réponse d'après une url de Jenkins.
     *
     * <b>ASTUCE :</b> Utiliser le client http {@link org.springframework.web.reactive.function.client.WebClient}
     *
     * @param baseUrl l'url de votre job
     * @return une instance de {@link ClientResponse}
     */
    protected static Mono<ClientResponse> jenkinsClient(final String baseUrl) {
        return Mono.error(new RuntimeException("Utiliser WebClient pour récupérer un répone json (sur /api/json)"));
    }

    /**
     * <b>ETAPE 2.2 :</b> Récupère la liste des jobs de jenkins à partir d'une url
     *
     * <b>ASTUCE 1 :</b> Transformer un {@link Mono} de {@link ClientResponse} en un {@link Flux} de {@link JenkinsJob}
     * <b>ASTUCE 2 :</b> Transformer au préalable le {@link ClientResponse} en un {@link JenkinsApi}
     *
     * @param baseUrl l'url de votre job ou de votre jenkins s'il s'agit des jobs racine.
     * @return un {@link Flux} de {@link JenkinsJob}
     */
    protected static Flux<JenkinsJob> jobs(final String baseUrl) {
        return Flux.error(new RuntimeException("A partir de la méthode jenkinsClient, transformer cela en un flux de JenkinsJob"));
    }

    /**
     * Retourne {@code true} s'il s'agit d'un job contenant d'autre job.
     *
     * @param job le job.
     * @return {@code true} dans le cas d'un job parent. {@code false} dans le cas contraire.
     */
    private static boolean isParent(JenkinsJob job) {
        return PARENTS_TYPE.contains(job.getClassType());
    }

    /**
     * <b>ETAPE 2.3 :</b> Récupère en profondeur la liste des jobs.
     *
     * <ul>
     * <li>S'il s'agit d'un job parent, récupérer les enfants de ce job.</li>
     * <li>Pour chaque jobs enfants, définisser lui son parent.</li>
     * <li>Pour finir, trier l'ensemble des jobs récupérer par leur nom.</li>
     * </ul>
     *
     * <b>ASTUCE :</b> Pensez à la récursivité
     *
     * @param baseUrl l'url d'un job ou l'url racine de jenkins
     * @return un {@link Flux} de {@link JenkinsJob}
     */
    protected static Flux<JenkinsJob> deepJobs(String baseUrl) {
        return Flux.error(new RuntimeException("A partir des méthodes jobs et isParent, récupérer l'ensemble des jobs" +
                " (qui ne sont pas des parents)"));
    }

    /**
     * <b>ETAPE 2.4 :</b> Toutes les 10 secondes, récupére la liste de tous les jobs.
     *
     * <b>ASTUCE :</b> Passer à la méthode {@link #deepJobs(String)} l'url de jenkins que vous trouverez dans {@link JenkinsProperties#getUrl()}.
     *
     * @return un {@link Flux} de {@link JenkinsJob}
     */
    @GetMapping(value = "/jobs/feed", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<JenkinsJob> jobStatus() {
        return Flux.error(new RuntimeException("A partir de la méthode deepJobs, récupérer toutes les 10 secondes " +
                "l'ensemble de vos jobs"));
    }

}