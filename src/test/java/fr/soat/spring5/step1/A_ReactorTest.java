package fr.soat.spring5.step1;

import org.junit.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;
import reactor.util.function.Tuple2;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class A_ReactorTest {

    @Test
    public void A_just() {
        // TODO: créer Flux avec les évenements 1, 2, 3
        Flux<Integer> result = Flux.just(1, 2, 3);

        StepVerifier.create(result)
                .expectNext(1, 2, 3)
                .verifyComplete();
    }

    @Test
    public void B_filter() {
        // TODO: prendre uniquement les nombres pair
        Flux<Integer> result = Flux.range(0, 7).filter(i -> i % 2 == 0);

        StepVerifier.create(result)
                .expectNext(0, 2, 4, 6)
                .verifyComplete();
    }


    @Test
    public void C_map() {
        // TODO: créer Flux avec les évenements 1, 2, 3
        // puis les convertir en chaine de caractère
        Flux<String> result = Flux.just(1, 2, 3).map(String::valueOf);

        StepVerifier.create(result)
                .expectNext("1", "2", "3")
                .verifyComplete();
    }

    @Test
    public void D_merge() {
        // TODO: faire un merge de 2 flux différents en un seul flux
        // puis les convertir en chaine de caractère

        Flux<Integer> a = Flux.just(1, 2, 3);
        Flux<Integer> b = Flux.just(4, 5, 6);

        Flux<Integer> result = Flux.concat(a, b);

        StepVerifier.create(result)
                .expectNext(1, 2, 3, 4, 5, 6)
                .verifyComplete();


    }

    @Test
    public void E_zip() {
        // TODO: composer de 2 flux différents en un seul flux
        // puis les convertir en chaine de caractère

        Flux<String> firstname = Flux.just("John", "Sarah", "Bob", "Patrick");
        Flux<String> name = Flux.just("Doe", "Conor", "L'Eponge", "L'étoile");

        Flux<String> result = Flux.<String, String>zip(firstname, name).map(i -> i.getT1() + " " + i.getT2());

        StepVerifier.create(result)
                .expectNext("John Doe", "Sarah Conor", "Bob L'Eponge", "Patrick L'étoile")
                .verifyComplete();
    }

    @Test
    public void F_flatMap() {

        // TODO: créer un flux qui génère de nouvelles valeurs via l'opérateur flatMap
        Flux<Integer> source = Flux.just(1, 2, 3, 4);
        List<String> letters = Arrays.asList("a", "b", "c", "d");

        Flux<String> result = source.flatMap(i -> Flux.fromIterable(Collections.nCopies(i, letters.get(i -1))));
        // OR
        // Flux<String> result = source.flatMap(i -> Mono.just(letters.get(i - 1)).repeat(i));

        StepVerifier.create(result)
                .expectNext("a", "b", "b", "c", "c", "c", "d", "d", "d", "d")
                .verifyComplete();

    }

}
