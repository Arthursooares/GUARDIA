package com.example.guardia.screens

import android.content.Context
import android.content.Intent
import android.graphics.Paint
import android.graphics.pdf.PdfDocument
import android.text.Layout
import android.text.StaticLayout
import android.text.TextPaint
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Mic
import androidx.compose.material.icons.filled.Send
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.FileProvider
import com.example.guardia.R
import com.example.guardia.data.relatorios.RelatorioDatabase
import com.example.guardia.data.relatorios.RelatorioEntity
import com.example.guardia.data.relatorios.RelatorioRepository
import com.example.guardia.network.ChatApi
import com.example.guardia.network.ChatRequest
import com.example.guardia.network.provideChatApi
import kotlinx.coroutines.launch
import org.json.JSONObject
import java.io.File
import java.io.FileOutputStream

// quem fala
enum class Role { USER, ASSISTANT }

data class MessageUi(
    val id: String,
    val role: Role,
    val text: String
)

// 👇 bolha de "digitando..."
@Composable
private fun TypingBubble() {
    val infiniteTransition = rememberInfiniteTransition(label = "starAnimation")

    val angle by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 2000, easing = LinearEasing)
        ),
        label = "rotation"
    )

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
                Icon(
                    imageVector = Icons.Default.Star,
                    contentDescription = "Estrela animada",
                    modifier = Modifier
                        .size(20.dp)
                        .rotate(angle)
                        .graphicsLayer { this.alpha = alpha },
                    tint = Color(0xFFFFD700)
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

// bolha da guardiã (esquerda)
@Composable
private fun AssistantMessage(msg: MessageUi) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 10.dp, end = 70.dp, top = 6.dp, bottom = 6.dp),
        verticalAlignment = Alignment.Top
    ) {
        // avatar maior
        Box(
            modifier = Modifier
                .size(64.dp)
                .clip(CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.guardia4),
                contentDescription = "Guardiã",
                modifier = Modifier
                    .fillMaxSize()
                    .clip(CircleShape),
                contentScale = ContentScale.Crop
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

// bolha do usuário (direita)
// bolha do usuário (direita)
@Composable
private fun UserMessage(msg: MessageUi) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                start = 70.dp,
                end = 10.dp,
                top = 6.dp,
                bottom = 6.dp
            ),
        horizontalArrangement = Arrangement.End,
        verticalAlignment = Alignment.Bottom
    ) {

        // 🔵 bolha com largura limitada REAL (não força o avatar)
        Surface(
            modifier = Modifier
                .weight(1f, false)           // não espreme nada
                .padding(end = 8.dp)
                .widthIn(max = 250.dp),       // limite ideal para bolha
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

        // 🟣 Avatar preservado SEM alterar tamanho ou aspecto
        Box(
            modifier = Modifier
                .size(64.dp)           // tamanho ORIGINAL
                .clip(CircleShape)
                .background(Color(0xFF21A189)),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.livia),
                contentDescription = "Usuária",
                modifier = Modifier
                    .fillMaxSize()
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

    var lastSeverity by remember { mutableStateOf<Int?>(null) }
    var lastReport by remember { mutableStateOf<String?>(null) }

    val context = LocalContext.current

// DB e repositório com tipos bem claros
    val db: RelatorioDatabase = remember(context) {
        RelatorioDatabase.getInstance(context)
    }
    val relatorioRepo: RelatorioRepository = remember(db) {
        RelatorioRepository(db.relatorioDao())
    }


    // mensagem de boas-vindas UMA VEZ
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
                    IconButton(
                        onClick = {
                            (context as? androidx.activity.ComponentActivity)
                                ?.onBackPressedDispatcher
                                ?.onBackPressed()
                        },
                        modifier = Modifier.align(Alignment.CenterStart)
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Voltar",
                            tint = Color(0xFF003E3A)
                        )
                    }

                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Image(
                            painter = painterResource(id = R.drawable.ic_guardia_escudo),
                            contentDescription = "Logo Guardiã",
                            modifier = Modifier.size(130.dp)
                        )
                    }
                }

                Spacer(Modifier.height(12.dp))

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
                    items(
                        items = messages,
                        key = { item: MessageUi -> item.id }
                    ) { msg: MessageUi ->
                        ChatBubble(msg)
                    }

                    if (isTyping) {
                        item {
                            TypingBubble()
                        }
                    }

                    if (lastReport != null && lastReport!!.isNotBlank()) {
                        item {
                            TextButton(
                                onClick = {
                                    try {
                                        val pdfFile = generatePdfReport(context, lastReport!!)
                                        val uri = FileProvider.getUriForFile(
                                            context,
                                            "${context.packageName}.provider",
                                            pdfFile
                                        )

                                        val send = Intent(Intent.ACTION_SEND).apply {
                                            type = "application/pdf"
                                            putExtra(Intent.EXTRA_STREAM, uri)
                                            addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
                                        }

                                        context.startActivity(
                                            Intent.createChooser(
                                                send,
                                                "Compartilhar relatório em PDF"
                                            )
                                        )
                                    } catch (e: Exception) {
                                        e.printStackTrace()
                                    }
                                },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 16.dp)
                            ) {
                                Text("📄 Compartilhar relatório em PDF", color = Color.White)
                            }
                        }
                    }
                }

                // barra de input
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 6.dp, vertical = 6.dp)
                        .clip(RoundedCornerShape(999.dp))
                        .background(Color(0xFFEFF2F4)),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(onClick = { /* futuro: anexar */ }) {
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

                            scope.launch {
                                try {
                                    val res = chatApi.send(ChatRequest(original))

                                    if (res.isSuccessful) {
                                        val raw = res.body()?.string()

                                        val (replyText, severity, reportText) = try {
                                            val json = JSONObject(raw ?: "{}")

                                            val reply = json.optString(
                                                "reply",
                                                "Não consegui entender a resposta da Guardiã."
                                            )

                                            // detectar se é só saudação simples
                                            val userText = original.lowercase()
                                            val words = userText.split("\\s+".toRegex())
                                                .filter { it.isNotBlank() }
                                            val greetings = listOf(
                                                "oi", "olá", "ola", "bom dia",
                                                "boa tarde", "boa noite", "e aí", "eaí",
                                                "tudo bem"
                                            )
                                            val isGreetingOnly =
                                                words.size <= 5 && greetings.any { g: String -> g in userText }

                                            val risk = json.optJSONObject("risk")
                                            val nivel = risk?.optString("nivel") ?: "Moderado"
                                            val orientacao = risk?.optString("orientacao") ?: ""
                                            val encaminhamento =
                                                risk?.optString("encaminhamento") ?: ""

                                            val contextoJson = json.optJSONObject("contexto")
                                            val resumo =
                                                contextoJson?.optString("resumoSituacao") ?: ""
                                            val categoria = contextoJson?.optString("categoria") ?: ""

                                            val severityInt: Int
                                            val report: String

                                            if (isGreetingOnly) {
                                                // só saudação → sem relatório
                                                severityInt = 0
                                                report = ""
                                            } else {
                                                severityInt = when (nivel.lowercase()) {
                                                    "alerta" -> 2
                                                    "urgente" -> 3
                                                    else -> 1
                                                }

                                                report = buildString {
                                                    appendLine("Resumo da situação: $resumo")
                                                    appendLine("Categoria: $categoria")
                                                    appendLine("Nível de risco: $nivel")
                                                    if (orientacao.isNotBlank()) appendLine("Orientação: $orientacao")
                                                    if (encaminhamento.isNotBlank()) appendLine("Encaminhamento: $encaminhamento")
                                                }.trim()

                                                // salva no banco se teve relatório
                                                if (report.isNotBlank()) {
                                                    relatorioRepo.salvar(
                                                        RelatorioEntity(
                                                            resumo = if (resumo.isNotBlank()) resumo else original,
                                                            categoria = categoria.ifBlank { "nao_informada" },
                                                            risco = nivel,
                                                            orientacao = orientacao,
                                                            encaminhamento = encaminhamento,
                                                            textoCompleto = report
                                                        )
                                                    )
                                                }
                                            }

                                            Triple(
                                                reply.replace("\n", " ").trim(),
                                                severityInt,
                                                report
                                            )
                                        } catch (e: Exception) {
                                            Triple(
                                                raw?.replace("\n", " ")?.trim()
                                                    ?: "Não consegui processar a resposta.",
                                                0,
                                                ""
                                            )
                                        }

                                        lastSeverity = severity
                                        lastReport = reportText.takeIf { it.isNotBlank() }

                                        messages += MessageUi(
                                            id = System.currentTimeMillis().toString() + "_a",
                                            role = Role.ASSISTANT,
                                            text = replyText
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
                                    isTyping = false
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

// Geração de PDF com o texto do relatório
fun generatePdfReport(context: Context, reportText: String): File {
    val pageWidth = 595
    val pageHeight = 842

    val pdfDocument = PdfDocument()
    val pageInfo = PdfDocument.PageInfo.Builder(pageWidth, pageHeight, 1).create()
    val page = pdfDocument.startPage(pageInfo)
    val canvas = page.canvas

    val paint = Paint()
    paint.isAntiAlias = true

    val textPaint = TextPaint(paint).apply {
        textSize = 12f
    }

    val leftMargin = 40f
    val topMargin = 60f
    val usableWidth = pageWidth - (leftMargin * 2).toInt()

    val staticLayout = StaticLayout.Builder
        .obtain(reportText, 0, reportText.length, textPaint, usableWidth)
        .setAlignment(Layout.Alignment.ALIGN_NORMAL)
        .setLineSpacing(0f, 1f)
        .setIncludePad(false)
        .build()

    canvas.save()
    canvas.translate(leftMargin, topMargin)
    staticLayout.draw(canvas)
    canvas.restore()

    pdfDocument.finishPage(page)

    val dir = File(context.getExternalFilesDir(null), "relatorios_guardia")
    if (!dir.exists()) dir.mkdirs()

    val file = File(dir, "relatorio_guardia_${System.currentTimeMillis()}.pdf")
    FileOutputStream(file).use { out ->
        pdfDocument.writeTo(out)
    }

    pdfDocument.close()
    return file
}

@Preview(showBackground = true)
@Composable
fun GuardiaScreenPreview() {
    GuardiaScreen()
}
