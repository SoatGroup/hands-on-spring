package fr.soat.spring5.step5

import fr.soat.spring5.step1.MetricsService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.getBean
import org.springframework.context.ApplicationContext
import org.springframework.context.annotation.Configuration
import javax.annotation.PostConstruct

@Configuration
open class KotlinConfiguration {

    @Autowired
    lateinit var context: ApplicationContext

    /**
     * Cette méthode se lance après le démarrage du contexte Spring
     *
     * Dans cette méthode, affichez le nom du fichier utilisé dans le MetricService;
     *
     * Vous pouvez vous inspirer de ce qui est fait dans la class ReactiveDemoApplication.
     *
     * Comparez le code Java avec la version Kotlin. Quelle est la différence entre les deux ?
     */
    @PostConstruct
    open fun displayFileName() {

    }
}