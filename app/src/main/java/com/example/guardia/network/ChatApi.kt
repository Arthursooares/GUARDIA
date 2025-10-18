package com.example.guardia.network

import com.google.gson.annotations.SerializedName
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.POST
import java.util.concurrent.TimeUnit


data class ChatRequest(
    val text: String,
    val userId: String? = null
)

data class ChatResponse(
    @SerializedName("messageId") val messageId: String,
    @SerializedName("role") val role: String,
    @SerializedName("content") val content: String,
    @SerializedName("receivedAt") val receivedAt: String,
    @SerializedName("severity") val severity: Int? = null,
    @SerializedName("resumo") val resumo: String? = null,
    @SerializedName("report_text") val reportText: String? = null,
)


interface ChatApi {
    @POST("chat")
    suspend fun send(@Body body: ChatRequest): ChatResponse
}

// ======= PROVIDER =======
private fun okHttpClient(debug: Boolean): OkHttpClient {
    val b = OkHttpClient.Builder()
        .connectTimeout(15, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS)
        .writeTimeout(30, TimeUnit.SECONDS)
    if (debug) {
        val log = HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }
        b.addInterceptor(log)
    }
    return b.build()
}

fun provideChatApi(baseUrl: String, debug: Boolean): ChatApi =
    Retrofit.Builder()
        .baseUrl(baseUrl)
        .client(okHttpClient(debug))
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(ChatApi::class.java)
