package com.example.guardia.ui.theme

import android.content.Intent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Mic
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.guardia.BuildConfig
import com.example.guardia.network.ChatRequest
import com.example.guardia.network.provideChatApi
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException
import java.net.SocketTimeoutException

enum class Role { USER, ASSISTANT }

data class MessageUi(
    val id: String,
    val role: Role,
    val text: String
)

@Composable
private fun Bubble(msg: MessageUi) {
    val isUser = msg.role == Role.USER
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp, vertical = 6.dp),
        horizontalArrangement = if (isUser) Arrangement.End else Arrangement.Start
    ) {
        Surface(
            color = if (isUser) Color(0xFF26A69A) else Color(0xFFE0F2F1),
            contentColor = if (isUser) Color.White else Color(0xFF004D40),
            shape = RoundedCornerShape(
                topStart = 18.dp, topEnd = 18.dp,
                bottomStart = if (isUser) 18.dp else 4.dp,
                bottomEnd = if (isUser) 4.dp else 18.dp
            ),
            shadowElevation = 1.dp
        ) {
            Text(
                text = msg.text,
                modifier = Modifier.padding(horizontal = 14.dp, vertical = 10.dp),
                fontSize = 16.sp
            )
        }
    }
}

@Composable
private fun TypingBubble() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp, vertical = 6.dp),
        horizontalArrangement = Arrangement.Start
    ) {
        Surface(
            color = Color(0xFFE0F2F1),
            contentColor = Color(0xFF004D40),
            shape = RoundedCornerShape(18.dp),
            shadowElevation = 0.dp
        ) {
            Text(
                "Digitando…",
                modifier = Modifier.padding(horizontal = 14.dp, vertical = 10.dp),
                fontSize = 14.sp
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GuardiaScreen() {
    val scope = rememberCoroutineScope()
    val context = LocalContext.current
    val keyboard = LocalSoftwareKeyboardController.current

    // API
    val chatApi = remember {
        provideChatApi(
            baseUrl = BuildConfig.N8N_BASE_URL, // precisa terminar com /
            debug = BuildConfig.DEBUG
        )
    }

    val messages = remember { mutableStateListOf<MessageUi>() }
    var userMessage by rememberSaveable { mutableStateOf("") }
    var isTyping by remember { mutableStateOf(false) }

    // dados opcionais que podem vir do backend
    var lastSeverity by remember { mutableStateOf<Int?>(null) }
    var lastReport by remember { mutableStateOf<String?>(null) }

    LaunchedEffect(Unit) {
        if (messages.isEmpty()) {
            messages += MessageUi(
                id = "welcome",
                role = Role.ASSISTANT,
                text = "Olá! Sou a Guardiã. Como posso ajudar você hoje?"
            )
        }
    }

    val listState = rememberLazyListState()
    LaunchedEffect(messages.size, isTyping) {
        if (messages.isNotEmpty()) listState.animateScrollToItem(messages.size - 1)
    }

    fun canSend(): Boolean = userMessage.isNotBlank() && !isTyping

    suspend fun sendMessage() {
        if (!canSend()) return
        val original = userMessage.trim()

        // adiciona msg do usuário
        messages += MessageUi(
            id = System.currentTimeMillis().toString() + "_u",
            role = Role.USER,
            text = original
        )
        userMessage = ""
        isTyping = true
        keyboard?.hide()

        // reset dos dados de denúncia a cada pergunta normal
        lastSeverity = null
        lastReport = null

        try {
            // envia texto cru; n8n decide o que fazer
            val res = chatApi.send(ChatRequest(text = original))
            messages += MessageUi(
                id = System.currentTimeMillis().toString() + "_a",
                role = Role.ASSISTANT,
                text = res.content
            )
            lastSeverity = res.severity
            lastReport = res.reportText
        } catch (e: SocketTimeoutException) {
            messages += MessageUi(
                id = System.currentTimeMillis().toString() + "_e",
                role = Role.ASSISTANT,
                text = "Tempo de resposta esgotado. Tente novamente."
            )
        } catch (e: HttpException) {
            messages += MessageUi(
                id = System.currentTimeMillis().toString() + "_e",
                role = Role.ASSISTANT,
                text = "Erro do servidor (${e.code()})."
            )
        } catch (e: IOException) {
            messages += MessageUi(
                id = System.currentTimeMillis().toString() + "_e",
                role = Role.ASSISTANT,
                text = "Sem conexão com a internet."
            )
        } catch (e: Exception) {
            messages += MessageUi(
                id = System.currentTimeMillis().toString() + "_e",
                role = Role.ASSISTANT,
                text = "Falha inesperada: ${e.message ?: "desconhecida"}"
            )
        } finally {
            isTyping = false
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Column {
                        Text("Guardiã", fontWeight = FontWeight.Bold)
                        Text("online", style = MaterialTheme.typography.labelSmall, color = Color(0xFF2E7D32))
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.White)
            )
        },
        containerColor = Color(0xFF26A69A)
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .background(Color(0xFF26A69A))
        ) {
            // histórico
            Surface(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth(),
                color = Color.Transparent
            ) {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    state = listState,
                    contentPadding = PaddingValues(top = 12.dp, bottom = 12.dp)
                ) {
                    items(messages, key = { it.id }) { msg -> Bubble(msg) }
                    if (isTyping) item { TypingBubble() }

                    if ((lastSeverity ?: 0) >= 2 && !lastReport.isNullOrBlank()) {
                        item {
                            TextButton(
                                onClick = {
                                    val send = Intent(Intent.ACTION_SEND).apply {
                                        type = "text/plain"
                                        putExtra(Intent.EXTRA_TEXT, lastReport)
                                    }
                                    context.startActivity(
                                        Intent.createChooser(send, "Compartilhar denúncia")
                                    )
                                },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 16.dp)
                            ) {
                                Text("📄 Compartilhar denúncia", color = Color.White, fontSize = 16.sp)
                            }
                        }
                    }
                }
            }

            // input
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp)
                    .background(Color.White, shape = RoundedCornerShape(24.dp))
                    .padding(start = 12.dp, end = 6.dp, top = 6.dp, bottom = 6.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                OutlinedTextField(
                    value = userMessage,
                    onValueChange = { userMessage = it },
                    modifier = Modifier.weight(1f),
                    placeholder = { Text("Pergunte à Guardiã") },
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(imeAction = ImeAction.Send),
                    keyboardActions = KeyboardActions(
                        onSend = { scope.launch { sendMessage() } }
                    ),
                    colors = OutlinedTextFieldDefaults.colors(
                        unfocusedContainerColor = Color.Transparent,
                        focusedContainerColor = Color.Transparent,
                        unfocusedBorderColor = Color.Transparent,
                        focusedBorderColor = Color.Transparent
                    )
                )

                val canTap = canSend()
                IconButton(
                    enabled = canTap,
                    onClick = { scope.launch { sendMessage() } }
                ) {
                    if (canTap) {
                        Icon(Icons.Filled.Send, contentDescription = "Enviar", tint = Color(0xFF26A69A))
                    } else {
                        Icon(Icons.Filled.Mic, contentDescription = "Microfone", tint = Color(0xFF26A69A))
                    }
                }
            }
        }
    }
}
