package ng.mathemandy.remote.utils

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import com.google.common.io.Resources
import ng.mathemandy.remote.ApiService
import okhttp3.mockwebserver.MockWebServer
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.io.File
import java.net.URL

const val REQUEST_PATH: String = "/grade"

private val okHttpClient: OkHttpClient
    get() = OkHttpClient.Builder().build()

private val moshi: Moshi
    get() = Moshi.Builder()
        .add(KotlinJsonAdapterFactory()).build()

@Suppress("UnstableApiUsage")
internal fun getJson(path: String): String {
    val uri: URL = Resources.getResource(path)
    val file = File(uri.path)
    return String(file.readBytes())
}

internal fun makeTestApiService(mockWebServer: MockWebServer): ApiService = Retrofit.Builder()
    .baseUrl(mockWebServer.url("/"))
    .client(okHttpClient)
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .build()
    .create(ApiService::class.java)