package com.example.learningproject.RetrofitWithInjection

import android.util.Log
import com.example.learningproject.MainActivity
import com.example.learningproject.NetworkCallFiles.CustomInterceptor
import com.example.learningproject.WebServicesExerciseActivity
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.components.SingletonComponent
import jakarta.inject.Named
import javax.inject.Singleton
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {

    @Provides
    @Singleton
    @Named("okhttpClient")
    fun provideOkhttpClient(): OkHttpClient {
        Log.i("TAG", "providing okhttpClient")
        return OkHttpClient.Builder()
            .addInterceptor(CustomInterceptor())
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofitInstance(
        @Named("okhttpClient") okHttpClient: OkHttpClient
    ): Retrofit {
        Log.i("TAG", "providing RetrofitInstance")
        return Retrofit.Builder()
            .baseUrl("https://reqres.in/api/")
            .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
            .addCallAdapterFactory(ApiCallAdapterFactory())
            .client(okHttpClient)
            .build()
    }

    @Provides
    fun provideUserService(retrofit: Retrofit): UserService {
        Log.i("TAG", "creating user service")
        return retrofit.create(UserService::class.java)
    }
}