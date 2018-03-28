@file:JvmName("Testing")

package fr.soat.spring5.internal

import java.math.BigInteger
import java.nio.ByteBuffer
import java.nio.charset.StandardCharsets
import java.nio.charset.StandardCharsets.UTF_8
import java.security.MessageDigest
import java.util.*
import java.util.stream.IntStream
import javax.crypto.Cipher
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.SecretKeySpec

/**
 * @author Soat - 2/11/18.
 */
fun resourceContent(resourcePath: String): String {
    return ClassLoader.getSystemResourceAsStream(resourcePath).use { inputStream ->
        inputStream.bufferedReader().use { it.readText() }
    }
}

private const val CODE = "ReactorIsSo(Gre)@t"

private fun sized(end: Int, bytes: ByteArray): ByteArray {
    val wrap = ByteBuffer.allocate(end)
    IntStream.range(0, end).limit(bytes.size.toLong()).forEach { i -> wrap.put(bytes[i]) }
    return wrap.array()
}

private fun cipherOp(opmode: Int, value: ByteArray): ByteArray {
    val cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING")
    val keySpec = SecretKeySpec(sized(16, CODE.toByteArray(UTF_8)), "AES")
    val iv = IvParameterSpec(sized(16, CODE.toByteArray(UTF_8)))
    cipher.init(opmode, keySpec, iv)
    return cipher.doFinal(value)
}

fun encrypt(value: String): String {
    return Base64.getEncoder().encodeToString(cipherOp(Cipher.ENCRYPT_MODE, value.toByteArray(UTF_8)))
}

fun decrypt(encrypted: String): String {
    return String(cipherOp(Cipher.DECRYPT_MODE, Base64.getDecoder().decode(encrypted)))
}

fun decrypt(encrypted: ByteArray): String {
    return String(cipherOp(Cipher.DECRYPT_MODE, encrypted))
}

fun hash(input: String): String {
    val digest = MessageDigest.getInstance("SHA-256")
    val update = digest.digest(input.toByteArray(UTF_8))
    return BigInteger(update).negate().toString(16).toUpperCase()
}
