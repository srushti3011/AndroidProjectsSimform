package com.example.learningproject.NetworkCallFiles

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.net.HttpURLConnection
import java.net.MalformedURLException
import java.net.URL

class ApiCallService {
    suspend inline fun <reified U> callApiWithoutBody(
        reqMethod: String,
        headers: Map<String, String>?,
        urlString: String
    ): Result<U> = withContext(Dispatchers.IO) {
        try {
            val url = URL(urlString)
            val connection = url.openConnection() as HttpURLConnection
            connection.run {
                requestMethod = reqMethod
                headers?.forEach {
                    setRequestProperty(it.key, it.value)
                }
                useCaches
                if (responseCode in 200..299) {
                    var response = ""
                    inputStream.bufferedReader().use {
                        it.lines().forEach { line ->
                            response += line
                        }
                    }
                    delay(2000)
                    return@run Result.success(Json.decodeFromString<U>(response))
                } else {
                    // check other response codes and proceed
                    return@run Result.failure(Exception("Problem in call"))
                }
            }
        } catch (exception: MalformedURLException) {
            return@withContext Result.failure(exception)
        }
    }

    suspend inline fun <reified T, reified U> callWithBody(
        reqMethod: String,
        headers: Map<String, String>?,
        body: T? = null,
        urlString: String
    ): Result<U> = withContext(Dispatchers.IO) {
        try {
            val url = URL(urlString)
            val connection = url.openConnection() as HttpURLConnection
            connection.run {
                requestMethod = reqMethod
                headers?.forEach {
                    setRequestProperty(it.key, it.value)
                }
                if (body != null) {
                    doOutput = true
                    val bodyToSend = Json.encodeToString(body)
                    outputStream.write(bodyToSend.toByteArray(Charsets.UTF_8))
                }
                if (responseCode in 200..299) {
                    var response = ""
                    inputStream.bufferedReader().use {
                        it.lines().forEach { line ->
                            response += line
                        }
                    }
                    delay(2000)
                    return@run Result.success(Json.decodeFromString<U>(response))
                } else {
                    // check other response codes and proceed
                    return@run Result.failure(Exception("Problem in call"))
                }
            }
        } catch (exception: MalformedURLException) {
            return@withContext Result.failure(exception)
        }
    }
}
