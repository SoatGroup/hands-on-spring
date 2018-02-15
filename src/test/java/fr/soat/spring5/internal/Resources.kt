@file:JvmName("Testing")

package fr.soat.spring5.internal

/**
 * @author Soat - 2/11/18.
 */
fun resourceContent(resourcePath: String): String {
    return ClassLoader.getSystemResourceAsStream(resourcePath).use { inputStream ->
        inputStream.bufferedReader().use { it.readText() }
    }
}

