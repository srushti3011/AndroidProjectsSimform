package com.example.learningproject

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.learningproject.NetworkCallFiles.ApiState
import com.example.learningproject.databinding.ActivityHttpurlconnectionHomeBinding

class HTTPURLConnectionHome : AppCompatActivity() {

    private lateinit var binding: ActivityHttpurlconnectionHomeBinding
    private val viewModel: HttpUrlConnectionHomeVM by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityHttpurlconnectionHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val headers = mapOf(
            "x-api-key" to "reqres-free-v1"
        )

        binding.apply {
            val endpoint = EndPoint.getSingleUser
            Log.i("TAG", endpoint.getMethod())

            makeHTTPGetCall.setOnClickListener {
                val httpTaskForGet = HTTPAsyncTask(
                    requestMethod = endpoint.getMethod(),
                    headers = headers
                ) {
                    binding.tvUpdateAfterExecution.text = "Api Call done"
                }
                val urlGot = endpoint.endpointURL(2)
                Log.i("TAG", "final url is ${urlGot}")
                httpTaskForGet.execute(urlGot)
            }

            makeHTTPGetCallWithQueryParams.setOnClickListener {
                val endpoint = EndPoint.getUserList
                Log.i("TAG", "Calling...")
                val httpTaskForGet = HTTPAsyncTask(
                    requestMethod = endpoint.getMethod(),
                    headers = headers
                )
                val urlGot = endpoint.endpointURL(queryParam = mapOf(
                    "page" to "1"
                ))
                Log.i("TAG", "url is ${urlGot}")
                httpTaskForGet.execute(urlGot)
            }

            btnMakeHTTPCallWithBody.setOnClickListener {
                val headers = mapOf(
                    "x-api-key" to "reqres-free-v1",
                    "Content-Type" to "application/json"
                )
                val httpTaskForPost = HTTPAsyncTask(
                    requestMethod = EndPoint.createUser.getMethod(),
                    headers = headers,
                    body = "{ \"name\": \"NameSent\", \"job\": \"JobSent\"}"
                )
                httpTaskForPost.execute(EndPoint.createUser.endpointURL())
            }

            btnGetCallUsingCoroutine.setOnClickListener {
                viewModel.makeCoroutineGetCall()
            }

            btnCreateUser.setOnClickListener {
                viewModel.createUser()
            }
        }

        viewModel.apiState.observe(this, {
            when (it) {
                ApiState.Idle -> {
                    binding.tvUpdateAfterExecution.text = "Idle"
                    Log.i("TAG", "is idle")
                }
                ApiState.Loading -> {
                    Log.i("TAG", "is loading")
                    binding.tvUpdateAfterExecution.text = "Loading"
                }
                ApiState.Success -> {
                    binding.tvUpdateAfterExecution.text = viewModel.apiResponse.value.toString()
                    Log.i("TAG", "is success")
                    viewModel.callComplete("makeCoroutineGetCall")
                }
                ApiState.Error -> {
                    binding.tvUpdateAfterExecution.text = viewModel.apiError.toString()
                    Log.i("TAG", "is error")
                    viewModel.callComplete("makeCoroutineGetCall")
                }
            }
        })

        viewModel.createUserApiState.observe(this, {
            when (it) {
                ApiState.Idle -> {}
                ApiState.Loading -> {}
                ApiState.Success -> {
                    Toast.makeText(
                        this,
                        "User created successfully",
                        Toast.LENGTH_LONG
                    ).show()
                    viewModel.callComplete("createUser")
                }
                ApiState.Error -> {
                    viewModel.callComplete("createUser")
                }
            }
        })
    }
}