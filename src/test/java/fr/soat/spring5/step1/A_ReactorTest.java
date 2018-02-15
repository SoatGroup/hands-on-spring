package fr.soat.spring5.step1;

import org.junit.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

public class A_ReactorTest {

    @Test
    public void A_just() {
        // TODO: créer Flux avec les évenements 1, 2, 3
        Flux<Integer> result = Flux.empty();

        StepVerifier.create(result)
                .expectNext(1, 2, 3)
                .verifyComplete();
    }

    @Test
    public void B_filter() {
        // TODO: prendre uniquement les nombres pair
        Flux<Integer> result = Flux.range(0, 6);

        StepVerifier.create(result)
                .expectNext(0, 2, 4, 6)
                .verifyComplete();
    }


    @Test
    public void C_map() {
        // TODO: créer Flux avec les évenements 1, 2, 3
        // puis les convertir en chaine de caractère
        Flux<String> result = Flux.empty();

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

        Flux<Integer> result = Flux.empty();

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

        Flux<String> result = Flux.empty();

        StepVerifier.create(result)
                .expectNext("John Doe", "Sarah Conor", "Bob L'Eponge", "Patrick L'étoile")
                .verifyComplete();
    }

    @Test
    public void F_flatMap() {

        // TODO: créer un flux qui génère de nouvelles valeurs via l'opérateur flatMap
        Flux<Integer> source = Flux.just(1, 2, 3, 4);

        Flux<String> result = source.flatMap(i -> Flux.just(i.toString()));

        StepVerifier.create(result)
                .expectNext("a", "b", "b", "c", "c", "c", "d", "d", "d", "d")
                .verifyComplete();

    }

}
