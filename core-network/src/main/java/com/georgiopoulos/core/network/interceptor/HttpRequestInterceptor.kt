package com.georgiopoulos.core.network.interceptor

import com.georgiopoulos.core.network.NetworkConstants.API_KEY
import com.georgiopoulos.core.network.NetworkConstants.HASH
import com.georgiopoulos.core.network.NetworkConstants.PRIVATE_KEY
import com.georgiopoulos.core.network.NetworkConstants.PUBLIC_KEY
import com.georgiopoulos.core.network.NetworkConstants.TIMESTAMP
import com.georgiopoulos.core.network.utils.md5
import okhttp3.Interceptor
import okhttp3.Response
import timber.log.Timber

/**
 * Interceptor for adding Marvel API authentication parameters to HTTP requests.
 *
 * This interceptor is designed for server-side applications interacting with the Marvel API.
 * It appends the required authentication parameters (ts, apikey, and hash) to the URL of
 * each outgoing HTTP request based on Marvel API authentication guidelines.
 *
 * Authentication for Server-Side Applications:
 * Server-side applications must pass two parameters in addition to the apikey parameter:
 *   - ts: a timestamp (or other long string which can change on a request-by-request basis)
 *   - hash: an MD5 digest of the ts parameter, your private key, and your public key
 *          (e.g., md5(ts + privateKey + publicKey))
 *
 * For example, a user with a public key of "1234" and a private key of "abcd" could construct
 * a valid call as follows: http://gateway.marvel.com/v1/public/comics?ts=1&apikey=1234&hash=ffd275c5130566a2916217b101f26150
 * (the hash value is the md5 digest of 1abcd1234)
 *
 * @see <a href="https://developer.marvel.com/documentation/authorization">Marvel API Authentication</a>
 */
internal class HttpRequestInterceptor : Interceptor {

    /**
     * Intercepts the HTTP request and adds the required authentication parameters (ts, apikey, and hash)
     * to the request URL before proceeding with the request.
     *
     * @param chain the interceptor chain
     * @return the response after adding authentication parameters to the request
     */
    override fun intercept(chain: Interceptor.Chain): Response {
        val newRequest = chain.request()
        val newRequestHttpUrl = newRequest.url

        val url = newRequestHttpUrl.newBuilder()
            .addQueryParameter(API_KEY, PUBLIC_KEY)
            .addQueryParameter(TIMESTAMP, calculateTimestamp().toString())
            .addQueryParameter(HASH, generateHash())
            .build()

        val newRequestWithParams = newRequest.newBuilder().url(url).build()
        return chain.proceed(newRequestWithParams)
    }

    /**
     * Calculates the timestamp for the authentication parameters.
     *
     * @return the timestamp in seconds, or 0L in case of an error
     */
    private fun calculateTimestamp(): Long {
        return try {
            System.currentTimeMillis() / 1000L
        } catch (exception: Exception) {
            Timber.e(exception, "Error calculating timestamp")
            0L
        }
    }

    /**
     * Generates the MD5 hash for the authentication parameters (ts, privateKey, publicKey).
     *
     * @return the MD5 hash as a hexadecimal string
     */
    private fun generateHash(): String {
        val timestamp = calculateTimestamp()
        return StringBuilder().apply {
            append(timestamp)
            append(PRIVATE_KEY)
            append(PUBLIC_KEY)
        }.toString().md5()
    }
}
