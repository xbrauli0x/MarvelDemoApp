package com.example.marveldemoapp.network

import android.content.Context
import com.example.marveldemoapp.BuildConfig
import com.google.gson.GsonBuilder
import okhttp3.Cache
import okhttp3.OkHttpClient
import org.koin.core.KoinComponent
import org.koin.core.inject
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object BaseClient : KoinComponent {

    private const val cacheSize = (5 * 1024 * 1024).toLong()
    private val myCache = Cache(provideContext().cacheDir, cacheSize)

    private val apiService: Api by lazy {
        retrofit().create(Api::class.java)
    }

    fun provideMarvelApi(): Api {
        return apiService
    }

    private fun retrofit(): Retrofit {
        val gson = GsonBuilder().setDateFormat("hh:mm:ss dd/MM/yyyy").create()
        return Retrofit.Builder()
            .baseUrl(BuildConfig.API_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(provideClient())
            .build()
    }


    private fun provideClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .cache(myCache)
            /*.addInterceptor { chain ->
                var request = chain.request()
                request = if (Utils.hasNetwork(provideContext())!!)
                    request.newBuilder().header("Cache-Control", "public, max-age=" + 5).createParams()
                else
                    request.newBuilder().header(
                        "Cache-Control",
                        "public, only-if-cached, max-stale=" + (60 * 60 * 24 * 7)
                    ).createParams()
                chain.proceed(request)
            }*/
            .build()
    }

    private fun provideContext(): Context {
        val ctx: Context by inject()
        return ctx
    }
}