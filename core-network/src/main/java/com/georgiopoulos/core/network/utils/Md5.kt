package com.georgiopoulos.core.network.utils

import timber.log.Timber
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException

internal fun String.md5(): String =
    try {
        val digest = MessageDigest.getInstance("MD5")
        val bytes = digest.digest(toByteArray())

        val hexString = StringBuilder(bytes.size * 2)
        for (byte in bytes) {
            hexString.append("%02x".format(byte))
        }

        hexString.toString()
    } catch (exception: NoSuchAlgorithmException) {
        Timber.e(exception.message)
        ""
    }