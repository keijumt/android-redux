package keijumt.redux.data.api

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object GithubApiFactory {
    fun create(): GithubApi = Retrofit.Builder()
        .baseUrl("https://api.github.com/")
        .client(OkHttpClient.Builder().addInterceptor(AuthInterceptor()).build())
        .addConverterFactory(
            MoshiConverterFactory.create(
                Moshi.Builder()
                    .add(KotlinJsonAdapterFactory())
                    .build()
            )
        )
        .build()
        .create(GithubApi::class.java)
}