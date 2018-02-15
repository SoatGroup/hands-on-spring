package fr.soat.spring5

import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import java.nio.file.Files
import java.nio.file.Paths
import java.nio.file.StandardOpenOption
import java.time.Duration
import java.util.*
import javax.annotation.PostConstruct


open class MetricsGenerator {

    private val random = Random()

    @PostConstruct
    fun writeOnFiles() {

        /*
MemTotal:        7778104 kB
MemFree:         4806144 kB
Buffers:          211756 kB
Cached:          1071092 kB
SwapTotal:       4194296 kB
SwapFree:        4194296 kB
         */


        Flux.interval(Duration.ofSeconds(1))
                .map { createStats() }
                .flatMap { str -> Mono.fromCallable { -> write(str) }.onErrorResume { Mono.empty() } }
                .subscribe()


    }


    private fun write(str: String) {
        Files.write(Paths.get("proc.stats"), str.toByteArray(), StandardOpenOption.CREATE,StandardOpenOption.TRUNCATE_EXISTING)
    }

    open fun createStats(): String {
        val mem = random.nextInt(567894)
        val free = random.nextInt(567894)
        val buffer = random.nextInt(567894)
        val cached = random.nextInt(567894)
        val swap = random.nextInt(567894)
        val swapFree = random.nextInt(567894)
        val template =
"""MemTotal:        $mem kB
MemFree:            $free kB
Buffers:            $buffer kB
Cached:             $cached kB
SwapTotal:          $swap kB
SwapFree:           $swapFree kB"""
        return template
    }
}