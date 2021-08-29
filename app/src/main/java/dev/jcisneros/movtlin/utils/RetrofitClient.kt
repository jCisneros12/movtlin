package dev.jcisneros.movtlin.utils

import com.google.gson.GsonBuilder
import dev.jcisneros.movtlin.domain.WebService
import dev.jcisneros.movtlin.utils.Const.BASE_URL_MOVIES
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient{

    val webservice by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL_MOVIES)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build().create(WebService::class.java)
    }
}