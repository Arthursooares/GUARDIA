package com.example.guardia.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.guardia.R

data class TipItem(
    val id: Int,
    val title: String,
    val icon: ImageVector,
    val iconColor: Color,
    val content: String
)

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun GuardiaTipsScreen(
    onBackClick: () -> Unit = {}
) {
    val tips = listOf(
        TipItem(
            id = 1,
            title = "Cuidados nas\nRedes Sociais",
            icon = Icons.Default.PhoneAndroid,
            iconColor = Color(0xFF3B82F6),
            content = "• Nunca compartilhe informações pessoais como endereço, telefone ou escola\n\n" +
                    "• Configure suas redes sociais como privadas\n\n" +
                    "• Cuidado ao aceitar solicitações de amizade de desconhecidos\n\n" +
                    "• Pense bem antes de postar fotos ou informações"
        ),
        TipItem(
            id = 2,
            title = "Perigos dos\nJogos Online",
            icon = Icons.Default.SportsEsports,
            iconColor = Color(0xFF3B82F6),
            content = "• Nunca aceite convites para conversar fora do jogo\n\n" +
                    "• Use apelidos, não seu nome real\n\n" +
                    "• Não compartilhe dados pessoais com outros jogadores\n\n" +
                    "• Bloqueie e reporte comportamentos abusivos"
        ),
        TipItem(
            id = 3,
            title = "Comunicação\nFamiliar",
            icon = Icons.Default.People,
            iconColor = Color(0xFFFFB020),
            content = "• Converse abertamente com sua família sobre suas experiências online\n\n" +
                    "• Compartilhe o que você faz na internet\n\n" +
                    "• Peça ajuda quando se sentir desconfortável\n\n" +
                    "• Mantenha um diálogo saudável e honesto"
        ),
        TipItem(
            id = 4,
            title = "Glossário\nGrooming",
            icon = Icons.Default.MenuBook,
            iconColor = Color(0xFF7C3AED),
            content = "Grooming é o processo onde adultos mal-intencionados criam vínculos emocionais " +
                    "com crianças e adolescentes para exploração sexual.\n\nSinais de alerta:\n" +
                    "• Elogios excessivos\n" +
                    "• Pedidos de segredo\n" +
                    "• Presentes inesperados\n" +
                    "• Conversas com conteúdo sexual\n" +
                    "• Pedidos de fotos íntimas"
        )
    )

    val faqTip = TipItem(
        id = 5,
        title = "Perguntas Frequentes",
        icon = Icons.Default.Help,
        iconColor = Color(0xFF7C3AED),
        content = "• Como fazer uma denúncia?\n" +
                "• O que fazer se eu for vítima?\n" +
                "• Como proteger minha privacidade online?\n" +
                "• Onde buscar ajuda profissional?\n" +
                "• Como conversar com meus pais sobre isso?\n\n" +
                "Entre em contato com a Guardiã para mais informações e suporte!"
    )

    var showFaqDialog by remember { mutableStateOf(false) }
    val pagerState = rememberPagerState(initialPage = 0) { tips.size }

    val pageColors = listOf(
        Pair(Color(0xFF9FE2EE), Color(0xFF2563A7)),
        Pair(Color(0xFF7DD4E5), Color(0xFFFFB020)),
        Pair(Color(0xFFFFD166), Color(0xFF2563A7)),
        Pair(Color(0xFF9FE2EE), Color(0xFF7C3AED))
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(Color(0xFF9FE2EE), Color(0xFF7DD4E5))
                )
            )
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            // ===== Header =====
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(Color(0xFF9FE2EE), Color(0xFF7DD4E5))
                        )
                    )
            ) {
                Column(modifier = Modifier.fillMaxWidth()) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp, vertical = 20.dp)
                    ) {
                        IconButton(
                            onClick = onBackClick,
                            modifier = Modifier.align(Alignment.CenterStart)
                        ) {
                            Icon(
                                imageVector = Icons.Default.ArrowBack,
                                contentDescription = "Voltar",
                                tint = Color(0xFF4B5563),
                                modifier = Modifier.size(28.dp)
                            )
                        }

                        Surface(
                            shape = CircleShape,
                            color = Color.White,
                            shadowElevation = 8.dp,
                            modifier = Modifier
                                .align(Alignment.CenterEnd)
                                .size(36.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.Shield,
                                contentDescription = "Guardiã",
                                tint = Color(0xFF2563A7),
                                modifier = Modifier.padding(7.dp)
                            )
                        }

                        Row(
                            modifier = Modifier.align(Alignment.Center),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "Dicas da ",
                                fontSize = 22.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color(0xFF4A7C8B)
                            )
                            Text(
                                text = "Guardiã",
                                fontSize = 22.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color(0xFF2563A7)
                            )
                        }
                    }

                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(1.5.dp)
                            .background(Color(0x4D4A7C8B))
                            .padding(horizontal = 20.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            // ===== Carrossel =====
            HorizontalPager(
                state = pagerState,
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            ) { page ->
                val tip = tips[page]
                val (leftColor, rightColor) = pageColors[page]

                Card(
                    modifier = Modifier
                        .padding(horizontal = 20.dp)
                        .fillMaxWidth()
                        .fillMaxHeight(0.95f),
                    shape = RoundedCornerShape(32.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
                ) {
                    Row(modifier = Modifier.fillMaxSize()) {
                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .fillMaxHeight()
                                .background(leftColor)
                        ) {
                            Column(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(24.dp),
                                verticalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text(
                                    text = tip.title,
                                    fontSize = 22.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = Color(0xFF111827),
                                    lineHeight = 28.sp
                                )

                                // ==== AQUI ENTRA A IMAGEM ====
                                Surface(
                                    shape = RoundedCornerShape(24.dp),
                                    color = Color(0x33FFFFFF),
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(160.dp)
                                ) {
                                    Box(
                                        modifier = Modifier.fillMaxSize(),
                                        contentAlignment = Alignment.Center
                                    ) {
                                        Image(
                                            painter = painterResource(id = R.drawable.guardia_celular),
                                            contentDescription = "Guardiã com celular",
                                            modifier = Modifier
                                                .fillMaxWidth(0.9f)
                                                .height(160.dp)
                                        )
                                    }
                                }
                            }
                        }

                        Box(
                            modifier = Modifier
                                .width(72.dp)
                                .fillMaxHeight()
                                .background(rightColor)
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            // ===== Indicadores =====
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                repeat(tips.size) { index ->
                    val selected = pagerState.currentPage == index
                    Box(
                        modifier = Modifier
                            .padding(horizontal = 3.dp)
                            .size(if (selected) 10.dp else 6.dp)
                            .shadow(if (selected) 2.dp else 0.dp, CircleShape)
                            .background(
                                color = if (selected) Color(0xFF2563A7) else Color(0xFFCBD5F5),
                                shape = CircleShape
                            )
                    )
                }
            }

            // ===== FAQ =====
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .clickable { showFaqDialog = true },
                shape = RoundedCornerShape(24.dp),
                colors = CardDefaults.cardColors(containerColor = Color(0xFFE5ECFF)),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp, vertical = 16.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = "Dúvidas sobre a Guardiã?",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF1F2933)
                        )
                        Text(
                            text = "Perguntas frequentes",
                            fontSize = 12.sp,
                            color = Color(0xFF4B5563)
                        )
                    }
                    Icon(
                        imageVector = Icons.Default.ArrowForward,
                        contentDescription = "Abrir FAQ",
                        tint = Color(0xFF2563A7)
                    )
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            // ===== Bottom Nav =====
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 32.dp, vertical = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(imageVector = Icons.Default.Home, contentDescription = "Início", tint = Color(0xFF9CA3AF))
                Icon(imageVector = Icons.Default.Lightbulb, contentDescription = "Dicas", tint = Color(0xFF2563A7))
                Icon(imageVector = Icons.Default.ChatBubble, contentDescription = "Chat", tint = Color(0xFF9CA3AF))
                Icon(imageVector = Icons.Default.Person, contentDescription = "Perfil", tint = Color(0xFF9CA3AF))
            }

            Spacer(modifier = Modifier.height(8.dp))
        }

        if (showFaqDialog) {
            TipDialog(tip = faqTip, onDismiss = { showFaqDialog = false })
        }
    }
}

@Composable
fun TipDialog(
    tip: TipItem,
    onDismiss: () -> Unit
) {
    Dialog(onDismissRequest = onDismiss) {
        Card(
            shape = RoundedCornerShape(24.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp)
        ) {
            Column(
                modifier = Modifier
                    .padding(28.dp)
                    .verticalScroll(rememberScrollState())
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.Top
                ) {
                    Text(
                        text = tip.title.replace("\n", " "),
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF2563A7),
                        modifier = Modifier.weight(1f),
                        lineHeight = 24.sp
                    )
                    IconButton(
                        onClick = onDismiss,
                        modifier = Modifier.offset(x = 8.dp, y = (-8).dp)
                    ) {
                        Text(
                            text = "×",
                            fontSize = 32.sp,
                            color = Color(0xFF9CA3AF),
                            fontWeight = FontWeight.Light
                        )
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = tip.content,
                    fontSize = 14.sp,
                    color = Color(0xFF4B5563),
                    lineHeight = 20.sp
                )

                Spacer(modifier = Modifier.height(24.dp))

                Button(
                    onClick = onDismiss,
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(50),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF2563A7)),
                    contentPadding = PaddingValues(vertical = 14.dp)
                ) {
                    Text(
                        text = "Fechar",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}

@Preview(name = "Tela de Dicas - Carrossel", showBackground = true, showSystemUi = true)
@Composable
fun GuardiaTipsScreenPreview() {
    GuardiaTipsScreen()
}
