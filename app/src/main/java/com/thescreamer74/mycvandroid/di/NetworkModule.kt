package com.thescreamer74.mycvandroid.di

import com.thescreamer74.mycvandroid.network.CvServerExperienceApi
import com.thescreamer74.mycvandroid.network.CvServerGraduationApi
import com.thescreamer74.mycvandroid.network.CvServerPersonalApi
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import java.util.*

val clientModule = module {
    val interceptor = Interceptor { chain ->
        val originalRequest = chain.request()

        chain.proceed(
            originalRequest.newBuilder().url(originalRequest.url).build()
        )
    }

    single {
        OkHttpClient
            .Builder()
            .addInterceptor(interceptor)
            .build()
    }
}

val networkModule = module {
    single {
        Retrofit.Builder()
            .baseUrl("https://hidden-oasis-61952.herokuapp.com") //192.168.1.112 //192.168.1.20 //192.168.43.203:8080
            .addConverterFactory(GsonConverterFactory.create())
            .client(this.get())
            .build()
    }
    factory { this.get<Retrofit>().create<CvServerGraduationApi>() }

    factory { this.get<Retrofit>().create<CvServerExperienceApi>() }

    factory { this.get<Retrofit>().create<CvServerPersonalApi>() }
}