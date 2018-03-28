package fr.soat.spring5.step1;

import fr.soat.spring5.internal.Testing;
import org.junit.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Signal;
import reactor.test.StepVerifier;

import java.util.Base64;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.joining;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * ASTUCE : Le nom de la méthode de test vous donnera toujours une petite indication !
 */
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
        Flux<Integer> result = Flux.range(0, 7);

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

    @Test
    public void G_subsribe()  {

        Flux.just("Déchiffrez le  code secret contenu dans la chaine de caractère ci-dessous ....",
                "Pour cela, utilisez la méthode ????????? avec ????????? !",
                "ajqXrjix0nw8WWgHibgIfYXalvWHoObi6FmuFArs9iYvObQ6N3Xish90/G20sF9g/SyYIUgyxLwrVDhb",
                "39dsmjgOJGNtCAOekENTiKcQzQVudXEErxH0A1KkQtAWssqEqzzZd/EEB2x/+QGlgUZPrLbyyFgwED/q",
                "W3JFFZVNaaq7lRmD9NtlQJxz/xDiNmuOqvg7QV4zHCWD907pEU8KzsAhmO66dCNomgsAmXoqLOyfcYze",
                "6jgr/cp/qyqTfvsbE33b0TpbuQXlE/CIkV8pbxzQLx8VN+d+X9yAT53ZiE62hXFC2usFHkN2Ac0JNd2T")
                .doOnComplete(() -> System.out.println("Flux just complete !"))
                .log()
                .skip(2)
                .collect(Collectors.joining())
                .map(encrypted -> Base64.getDecoder().decode(encrypted))
                .map(Testing::decrypt)
                // TODO : sortez le resultat dans la console pour obtenir le code secret !
                ;

        assertThat(Testing.hash("secret-code"))
                .isEqualTo("175A31E27FB919943262AA7CD084031EE47D25E8A8DE541534CA76AFF0180F60");
    }

}
