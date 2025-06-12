package com.example.learningproject

import android.os.AsyncTask
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import com.example.learningproject.NetworkCallFiles.HTTPMethods
import java.net.HttpURLConnection
import java.net.URL

class HTTPAsyncTask(
    val requestMethod: String,
    val headers: Map<String, String>?,
    var body: String? = null,
    val runOnCompletion: (() -> Unit)? = null
): AsyncTask<String, Unit, Unit>() {

    override fun onPreExecute() {
        super.onPreExecute()
        Log.i("TAG", "Will call api now ${Thread.currentThread()}")
    }

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun doInBackground(vararg params: String?) {
        Log.i("TAG", "Began api call now ${Thread.currentThread()}")
        val url = URL(params[0])
        val connection = url.openConnection() as HttpURLConnection
        connection.requestMethod = requestMethod
        headers?.forEach {
            connection.setRequestProperty(it.key, it.value)
        }
        if (this.body != null) {
            connection.doOutput = true
            connection.outputStream.write(body?.toByteArray())
            Log.i("TAG", body.toString())
        }
        val statusCode = connection.responseCode
        if (statusCode in 200..299) {
            connection.inputStream.bufferedReader().use {
                it.lines().forEach { line ->
                    Log.i("TAG", line)
                }
            }
        } else {
            Log.i("TAG", "error in call")
        }
    }

    override fun onPostExecute(result: Unit?) {
        super.onPostExecute(result)
        Log.i("TAG", "EXECUTION DONE Now on ${Thread.currentThread()}")
        runOnCompletion?.let { it() }
    }
}

enum class EndPoint {
    getSingleUser, getUserList, createUser;
    val baseURL: String
        get() = "https://reqres.in/api"

    val route: String
        get() = when(this) {
                getSingleUser -> "/users"
                getUserList -> "/users"
                createUser -> "/users"
            }

    fun endpointURL(pathParam: Int? = null, queryParam: Map<String, String>? = null): String {
        return when (this) {
            getSingleUser -> {
                if (pathParam != null) {
                    "${this.baseURL}${this.route}${this.pathParam(pathParam)}"
                } else {
                    ""
                }
            }
            getUserList -> {
                if (queryParam != null) {
                    "${this.baseURL}${this.route}${this.queryParams(queryParam)}"
                } else {
                    ""
                }
            }
            createUser -> "${this.baseURL}${this.route}"
        }
    }

    fun queryParams(params: Map<String, String>): String? {
        return when(this) {
            getUserList -> {
                var queryParamsToAppend = "?"
                params.forEach {
                    queryParamsToAppend += "${it.key}=${it.value}&"
                }
                queryParamsToAppend
            }
            getSingleUser, createUser -> null
        }
    }

    fun pathParam(param: Int): String? {
        return when (this) {
            getUserList -> null
            getSingleUser -> "/${param}"
            createUser -> null
        }
    }
    fun getMethod(): String {
        return when(this) {
            getUserList -> HTTPMethods.GET.toString()
            getSingleUser -> HTTPMethods.GET.toString()
            createUser -> HTTPMethods.POST.toString()
        }
    }
}

