package com.example.guardia.screens

import android.app.Activity // 👈 ADIÇÃO para voltar
import android.content.Intent
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Mic
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext // ADIÇÃO para pegar a Activity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch
import org.json.JSONObject
import com.example.guardia.network.ChatApi
import com.example.guardia.network.ChatRequest
import com.example.guardia.network.provideChatApi
import com.example.guardia.R
import androidx.compose.material.icons.automirrored.filled.ArrowBack // ADIÇÃO seta auto espelhada
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.LinearEasing
import androidx.compose.ui.draw.rotate
import androidx.compose.animation.core.RepeatMode
import androidx.compose.material.icons.filled.Star
import androidx.compose.ui.graphics.graphicsLayer



// quem fala
enum class Role { USER, ASSISTANT }

data class MessageUi(
    val id: String,
    val role: Role,
    val text: String
)

// 👇 ADIÇÃO: bolha de "digitando..."
@Composable
private fun TypingBubble() {
    // animação infinita de rotação e brilho
    val infiniteTransition = rememberInfiniteTransition(label = "starAnimation")

    // rotação contínua
    val angle by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 2000, easing = LinearEasing)
        ),
        label = "rotation"
    )

    // brilho pulsante (mudança de alpha)
    val alpha by infiniteTransition.animateFloat(
        initialValue = 0.9f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 1200, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "pulse"
    )

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 10.dp, end = 70.dp, top = 4.dp, bottom = 4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Surface(
            color = Color(0xFF21A189),
            contentColor = Color.White,
            shape = RoundedCornerShape(16.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp)
            ) {
                // 🌟 Estrela dourada com rotação e brilho
                Icon(
                    imageVector = Icons.Default.Star,
                    contentDescription = "Estrela animada",
                    modifier = Modifier
                        .size(20.dp)
                        .rotate(angle)
                        .graphicsLayer { this.alpha = alpha },
                    tint = Color(0xFFFFD700) // 💛 Amarelo dourado
                )

                Spacer(Modifier.width(6.dp))

                Text(
                    text = "Digitando…",
                    fontSize = 13.sp
                )
            }
        }
    }
}



// bolha da guardiã (esquerda, com avatar circular maior e espaço reservado)
@Composable
private fun AssistantMessage(msg: MessageUi) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            // reserva espaço do lado direito da tela
            .padding(start = 10.dp, end = 70.dp, top = 6.dp, bottom = 6.dp),
        verticalAlignment = Alignment.Top
    ) {
        // avatar maior
        Box(
            modifier = Modifier
                .size(64.dp)
                .clip(CircleShape), // corta a imagem em círculo
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.guardia4),
                contentDescription = "Guardiã",
                modifier = Modifier
                    .fillMaxSize() // preenche o círculo
                    .clip(CircleShape), // garante que o corte se aplique à imagem
                contentScale = ContentScale.Crop // corta sem distorcer
            )
        }

        Spacer(Modifier.width(8.dp))

        Surface(
            color = Color(0xFF21A189),
            contentColor = Color.White,
            shape = RoundedCornerShape(16.dp)
        ) {
            Text(
                text = msg.text,
                modifier = Modifier.padding(horizontal = 14.dp, vertical = 10.dp),
                fontSize = 14.sp,
                lineHeight = 18.sp
            )
        }
    }
}

// bolha do usuário (direita, com avatar circular maior e espaço reservado)
@Composable
private fun UserMessage(msg: MessageUi) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            // reserva espaço do lado esquerdo agora
            .padding(start = 70.dp, end = 10.dp, top = 6.dp, bottom = 6.dp),
        horizontalArrangement = Arrangement.End,
        verticalAlignment = Alignment.Bottom
    ) {
        Surface(
            color = Color(0xFF21A189),
            contentColor = Color.White,
            shape = RoundedCornerShape(16.dp)
        ) {
            Text(
                text = msg.text,
                modifier = Modifier.padding(horizontal = 14.dp, vertical = 10.dp),
                fontSize = 14.sp,
                lineHeight = 18.sp
            )
        }

        Spacer(Modifier.width(8.dp))

        Box(
            modifier = Modifier
                .size(64.dp) // 🔹 tamanho total do círculo
                .clip(CircleShape)
                .background(Color(0xFF21A189)), // cor da borda de fundo
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.livia),
                contentDescription = "Guardiã",
                modifier = Modifier
                    .fillMaxSize(1.0F) // 🔹 tamanho da imagem dentro do círculo
                    .clip(CircleShape),
                contentScale = ContentScale.Crop
            )
        }
    }
}

@Composable
private fun ChatBubble(msg: MessageUi) {
    if (msg.role == Role.ASSISTANT) {
        AssistantMessage(msg)
    } else {
        UserMessage(msg)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GuardiaScreen() {
    val scope = rememberCoroutineScope()
    val chatApi: ChatApi = remember { provideChatApi() }

    val messages = remember { mutableStateListOf<MessageUi>() }
    var userMessage by remember { mutableStateOf("") }
    var isTyping by remember { mutableStateOf(false) }

    // denúncia (mantive, mas não mostramos no protótipo por enquanto)
    var lastSeverity by remember { mutableStateOf<Int?>(null) }
    var lastReport by remember { mutableStateOf<String?>(null) }

    val context = LocalContext.current // 👈 ADIÇÃO

    LaunchedEffect(Unit) {
        if (messages.isEmpty()) {
            messages += MessageUi(
                id = "welcome",
                role = Role.ASSISTANT,
                text = "Olá, Lívia! Me diga, o que temos para hoje?"
            )
        }
    }

    val listState = rememberLazyListState()
    LaunchedEffect(messages.size, isTyping) {
        if (messages.isNotEmpty()) {
            listState.animateScrollToItem(messages.size - 1)
        }
    }

    Scaffold(
        topBar = {
            // topo no estilo do protótipo
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        Brush.verticalGradient(
                            listOf(
                                Color(0xFFB2EBF2),
                                Color(0xFFE0F7FA)
                            )
                        )
                    )
                    .padding(top = 16.dp, bottom = 12.dp),
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 4.dp),
                    contentAlignment = Alignment.Center
                ) {
                    // 👇 ADIÇÃO: seta de voltar
                    IconButton(
                        onClick = {
                            (context as? androidx.activity.ComponentActivity)?.onBackPressedDispatcher?.onBackPressed()

                        },
                        modifier = Modifier.align(Alignment.CenterStart)
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Voltar",
                            tint = Color(0xFF003E3A)
                        )
                    }

                    // logo
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Image(
                            painter = painterResource(id = R.drawable.ic_guardia_logo),
                            contentDescription = "Logo Guardiã",
                            modifier = Modifier.size(130.dp)
                        )
                    }
                }

                Spacer(Modifier.height(12.dp))
                // linha branca que o protótipo tem
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(1.dp)
                        .background(Color.White.copy(alpha = 0.7f))
                )
            }
        },
        containerColor = Color.Transparent
    ) { padding ->

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        listOf(
                            Color(0xFFB2EBF2),
                            Color(0xFFE0F7FA),
                            Color(0xFF8EC7E3)
                        )
                    )
                )
                .padding(padding)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 6.dp, vertical = 4.dp)
            ) {
                // lista de mensagens
                LazyColumn(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth(),
                    state = listState,
                    contentPadding = PaddingValues(top = 6.dp, bottom = 6.dp)
                ) {
                    items(messages, key = { it.id }) { msg ->
                        ChatBubble(msg)
                    }

                    // 👇 ADIÇÃO: mostrar "digitando..." quando isTyping = true
                    if (isTyping) {
                        item {
                            TypingBubble()
                        }
                    }

                    if ((lastSeverity ?: 0) >= 2 && !lastReport.isNullOrBlank()) {
                        item {
                            TextButton(
                                onClick = {
                                    val send = Intent(Intent.ACTION_SEND).apply {
                                        type = "text/plain"
                                        putExtra(Intent.EXTRA_TEXT, lastReport)
                                    }
                                    // chooser
                                },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 16.dp)
                            ) {
                                Text("📄 Compartilhar denúncia", color = Color.White)
                            }
                        }
                    }
                }

                // barra de input igual ao protótipo
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 6.dp, vertical = 6.dp)
                        .clip(RoundedCornerShape(999.dp))
                        .background(Color(0xFFEFF2F4)),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(onClick = {
                        // futuro: anexar, abrir opções, etc.
                    }) {
                        Icon(
                            imageVector = Icons.Default.Add,
                            contentDescription = "Mais",
                            tint = Color(0xFF003E3A)
                        )
                    }

                    TextField(
                        value = userMessage,
                        onValueChange = { userMessage = it },
                        placeholder = { Text("Pergunte à Guardiã") },
                        modifier = Modifier.weight(1f),
                        singleLine = true,
                        colors = TextFieldDefaults.colors(
                            unfocusedContainerColor = Color.Transparent,
                            focusedContainerColor = Color.Transparent,
                            disabledContainerColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent,
                            focusedIndicatorColor = Color.Transparent
                        )
                    )

                    val canSend = userMessage.isNotBlank()
                    IconButton(
                        onClick = {
                            if (!canSend) return@IconButton

                            val original = userMessage.trim()

                            messages += MessageUi(
                                id = System.currentTimeMillis().toString() + "_u",
                                role = Role.USER,
                                text = original
                            )
                            userMessage = ""
                            isTyping = true
                            lastReport = null
                            lastSeverity = null

                            // chamada da API
                            scope.launch {
                                try {
                                    val res = chatApi.send(ChatRequest(original))
                                    if (res.isSuccessful) {
                                        val raw = res.body()?.string()
                                        val text = try {
                                            val json = JSONObject(raw ?: "")
                                            json.optString("message", raw ?: "")
                                        } catch (e: Exception) {
                                            raw
                                        }
                                            ?.replace("\\n", "\n")
                                            ?.trim()

                                        messages += MessageUi(
                                            id = System.currentTimeMillis().toString() + "_a",
                                            role = Role.ASSISTANT,
                                            text = text.takeUnless { it.isNullOrBlank() }
                                                ?: "Servidor respondeu vazio (200)."
                                        )
                                    } else {
                                        val err = res.errorBody()?.string()
                                        messages += MessageUi(
                                            id = System.currentTimeMillis().toString() + "_e",
                                            role = Role.ASSISTANT,
                                            text = "Erro HTTP ${res.code()}: ${err ?: "sem corpo"}"
                                        )
                                    }
                                } catch (e: Exception) {
                                    messages += MessageUi(
                                        id = System.currentTimeMillis().toString() + "_e",
                                        role = Role.ASSISTANT,
                                        text = "Falha de rede no app: ${e.message ?: "desconhecida"}"
                                    )
                                } finally {
                                    isTyping = false // 👈 garante que some o "digitando..."
                                }
                            }
                        }
                    ) {
                        if (canSend) {
                            Icon(
                                imageVector = Icons.Default.Send,
                                contentDescription = "Enviar",
                                tint = Color(0xFF003E3A)
                            )
                        } else {
                            Icon(
                                imageVector = Icons.Default.Mic,
                                contentDescription = "Falar",
                                tint = Color(0xFF003E3A)
                            )
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GuardiaScreenPreview() {
    GuardiaScreen()
}
