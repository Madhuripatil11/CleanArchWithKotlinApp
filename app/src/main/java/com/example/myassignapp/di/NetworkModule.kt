package com.example.myassignapp.di

import com.example.myassignapp.common.PreferencesDataStoreHelper
import com.example.myassignapp.data.api.ApiService
import com.example.myassignapp.data.api.OAuthInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

/**
 * Created by Madhuri Patil on 3/14/2024.
 */
@InstallIn(SingletonComponent::class)
@Module
class NetworkModule {
    @Provides
    @Singleton
    fun provideAuthInterceptor(dataStoreManager: PreferencesDataStoreHelper): OAuthInterceptor {
        return OAuthInterceptor(dataStoreManager)
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(authInterceptor: OAuthInterceptor): OkHttpClient {
        val okHttpClient = OkHttpClient().newBuilder()
        okHttpClient.callTimeout(120, TimeUnit.SECONDS)
        okHttpClient.connectTimeout(120, TimeUnit.SECONDS)
        okHttpClient.readTimeout(120, TimeUnit.SECONDS)
        okHttpClient.writeTimeout(120, TimeUnit.SECONDS)
        okHttpClient.addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
        okHttpClient.addInterceptor(authInterceptor)
        okHttpClient.followSslRedirects(true)
        return okHttpClient.build()
    }

    @Provides
    @Singleton
    fun provideRestApiService(okHttpClient: OkHttpClient): ApiService {
        return Retrofit.Builder()
            .baseUrl("https://reqres.in/api/")
            .client(okHttpClient)
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }

    object RetrofitInstance {
        val api : ApiService by lazy {
            Retrofit.Builder()
                .baseUrl("https://reqres.in/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .addConverterFactory(ScalarsConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
                .create(ApiService::class.java)
        }
    }

}