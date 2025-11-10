package com.example.guardia.network

import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

// tem o mesmo formato que você já mandava
data class ChatRequest(
    val text: String,
    val userId: String? = null   // deixa opcional, caso um dia use
)

interface ChatApi {
    // isso vai bater em: BASE_URL + "chat"
    @POST("chat")
    suspend fun send(@Body body: ChatRequest): Response<ResponseBody>
}
