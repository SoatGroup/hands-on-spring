package fr.soat.spring5.internal

import fr.soat.spring5.MetricsGenerator

class StaticMetricsGenerator : MetricsGenerator() {

    override fun createStats(): String {
        return """MemTotal:        12345 kB
MemFree:            4567 kB
Buffers:            1324 kB
Cached:             4321 kB
SwapTotal:          1023 kB
SwapFree:           1111 kB"""
    }
}
