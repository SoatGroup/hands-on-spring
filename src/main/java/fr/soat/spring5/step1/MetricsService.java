package fr.soat.spring5.step1;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.Objects;

@RestController
public class MetricsService {

    private static final String FILENAME = "proc.stats";
    private static final String REGEX = ":( )*";

    public String getFilename() {
        return FILENAME;
    }

    /**
     *
     * Le fichier proc.stats contient les statistiques relative à la machine.
     * Cette méthode doit lire le fichier et emettre chaque ligne du fichier
     * individuellement
     *
     * @return un {@link Flux} du fichier ligne par ligne
     */
    public Flux<String> readFile() {
        try {
            return Flux.fromIterable(Files.readAllLines(Paths.get(FILENAME), StandardCharsets.UTF_8));
        } catch (IOException e) {
            throw new RuntimeException("Error during reading file");
        }

    }

    /**
     * Cette méthode est une simple méthode de conversion : elle appelle la méthode readFile
     * et convertie chaque ligne en un objet Metric.
     *
     * Vous pouvez vous aider de la REGEX défini en début de fichier pour parser chaque ligne
     * @return un {@link Flux} des métriques
     */
    public Flux<Metrics> toMetrics() {
        return readFile().map(i -> new Metrics(i.split(REGEX)[0], i.split(REGEX)[1]));

    }

    /**
     * Cette méthode lie à intervalle régulier le fichier de statistique.
     *
     * Une fois la méthode implémentée, ouvrez dans votre navigateur web l'url :
     *
     * http://localhost:8080/metrics
     *
     * @return un {@link Flux} des métriques
     */
    @GetMapping(value = "/metrics", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Metrics> watchFile() {
        return Flux.interval(Duration.ZERO, Duration.ofSeconds(5)).flatMap(i -> toMetrics());
    }

    /**
     * Cette méthode a exactement le même comportement que la méthode précédente, mais
     * permet de ne garder qu'une métrique spécifique.
     *
     * @param filter nom de la métrique à filtrer
     * @return un {@link Flux} de la métrique filtrer
     */
    @GetMapping(value = "/metrics/{filter}", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Metrics> watchFile(@PathVariable("filter") String filter) {
        return watchFile().filter(i -> Objects.equals(filter, i.getName()));
    }




}
