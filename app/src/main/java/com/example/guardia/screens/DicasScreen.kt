package com.example.guardia.screens

import androidx.annotation.DrawableRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.guardia.R

// ---------- Paleta / cores usadas também na bottom bar ----------
private val AzureLight = Color(0xFFE8F5FF)
private val AzureMid   = Color(0xFFD3ECFF)
private val TitleDark  = Color(0xFF0E3B5E)
private val PrimaryTeal = Color(0xFF33B2B2)
private val PrimaryBlue = Color(0xFF0E6D90)
private val CardStroke = Color(0xFFE1ECF7)

// ---------- Modelo das dicas ----------
data class TipItem(
    val id: Int,
    val title: String,
    val content: String,
    @DrawableRes val imageRes: Int
)

@Composable
private fun GuardiaBottomBar(
    currentRoute: String,
    onItemClick: (String) -> Unit
) {
    val items = listOf(
        BottomNavItem("feed", "Feed", Icons.Filled.ChatBubble),
        BottomNavItem("itens", "Itens", Icons.Filled.Description),
        BottomNavItem("home", "Início", Icons.Filled.Home, isCenter = true),
        BottomNavItem("guardia", "Guardiã", Icons.Filled.Star),
        BottomNavItem("perfil", "Perfil", Icons.Filled.Person)
    )

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 10.dp),
        contentAlignment = Alignment.Center
    ) {
        // "Pílula" de fundo da bottom bar (glass / card moderno)
        Surface(
            shape = RoundedCornerShape(26.dp),
            color = Color.White.copy(alpha = 0.96f),
            shadowElevation = 14.dp,
            tonalElevation = 0.dp,
            modifier = Modifier
                .fillMaxWidth()
                .height(62.dp)
        ) {
            NavigationBar(
                containerColor = Color.Transparent,
                tonalElevation = 0.dp
            ) {
                items.forEach { item ->
                    val selected = currentRoute == item.route

                    NavigationBarItem(
                        selected = selected,
                        onClick = { onItemClick(item.route) },
                        icon = {
                            if (item.isCenter) {
                                // Botão central flutuante
                                // Botão central flutuante — AGORA MAIS BAIXO
                                Box(
                                    modifier = Modifier
                                        .offset(y = (5).dp)   // ⬅️ antes era -18.dp
                                        .size(64.dp)
                                        .shadow(
                                            elevation = 16.dp,
                                            shape = CircleShape,
                                            clip = false
                                        )
                                        .clip(CircleShape)
                                        .background(
                                            Brush.verticalGradient(
                                                listOf(PrimaryTeal, PrimaryBlue)
                                            )
                                        )
                                        .border(1.dp, Color.White.copy(alpha = 0.7f), CircleShape),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Icon(
                                        imageVector = item.icon,
                                        contentDescription = item.label,
                                        tint = Color.White,
                                        modifier = Modifier.size(28.dp)
                                    )
                                }

                            } else {
                                Icon(
                                    imageVector = item.icon,
                                    contentDescription = item.label,
                                    modifier = Modifier.size(22.dp)
                                )
                            }
                        },
                        label = {
                            Text(
                                text = item.label,
                                fontSize = 10.sp
                            )
                        },
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = PrimaryBlue,
                            selectedTextColor = PrimaryBlue,
                            indicatorColor = Color(0xFFE6F0FB),
                            unselectedIconColor = Color(0xFF9AA9B5),
                            unselectedTextColor = Color(0xFF9AA9B5)
                        )
                    )
                }
            }
        }
    }
}



// ---------- Tela de Dicas ----------
@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun GuardiaTipsScreen(
    onBackClick: () -> Unit = {}
) {
    // 🔹 AGORA SÃO 4 CARDS
    val tips = listOf(
        TipItem(
            id = 1,
            title = "Perigos dos\njogos online",
            content = "• Nunca aceite convites para conversar fora do jogo\n\n" +
                    "• Use apelidos, não seu nome real\n\n" +
                    "• Não compartilhe dados pessoais com outros jogadores\n\n" +
                    "• Bloqueie e reporte comportamentos abusivos",
            imageRes = R.drawable.guardia_videogame
        ),
        TipItem(
            id = 2,
            title = "Comunicação\nfamiliar",
            content = "• Converse abertamente com sua família sobre suas experiências online\n\n" +
                    "• Compartilhe o que você faz na internet\n\n" +
                    "• Peça ajuda quando se sentir desconfortável\n\n" +
                    "• Mantenha um diálogo saudável e honesto",
            imageRes = R.drawable.guardia_familia
        ),
        TipItem(
            id = 3,
            title = "Cuidados nas\nRedes Sociais",
            content = "• Nunca compartilhe informações pessoais como endereço, telefone ou escola\n\n" +
                    "• Configure suas redes sociais como privadas\n\n" +
                    "• Cuidado ao aceitar solicitações de amizade de desconhecidos\n\n" +
                    "• Pense bem antes de postar fotos ou informações",
            imageRes = R.drawable.guardia_celular
        ),
        TipItem(
            id = 4,
            title = "Glossário\nGrooming",
            content = "Grooming é o processo em que adultos mal-intencionados criam vínculos emocionais " +
                    "com crianças e adolescentes para exploração sexual.\n\nSinais de alerta:\n" +
                    "• Elogios excessivos\n" +
                    "• Pedidos de segredo\n" +
                    "• Presentes inesperados\n" +
                    "• Conversas com conteúdo sexual\n" +
                    "• Pedidos de fotos íntimas",
            imageRes = R.drawable.guardia_escudo // pode trocar por outra ilustr. depois
        )
    )

    val faqTip = TipItem(
        id = 5,
        title = "Perguntas Frequentes",
        content = "• Como fazer uma denúncia?\n" +
                "• O que fazer se eu for vítima?\n" +
                "• Como proteger minha privacidade online?\n" +
                "• Onde buscar ajuda profissional?\n" +
                "• Como conversar com meus pais sobre isso?\n\n" +
                "Entre em contato com a Guardiã para mais informações e suporte!",
        imageRes = R.drawable.guardia_celular // só pra não quebrar, não é exibida no diálogo
    )

    var showFaqDialog by remember { mutableStateOf(false) }
    val pagerState = rememberPagerState(initialPage = 0) { tips.size }

    // 🔹 4 cores, uma pra cada card (ordem bate com os 4 tips)
    val pageColors = listOf(
        Color(0xFF063C80), // jogos
        Color(0xFFFFD166), // comunicação
        Color(0xFF00B6C9), // redes
        Color(0xFF7C3AED)  // grooming
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
                val bgColor = pageColors[page]

                Card(
                    modifier = Modifier
                        .padding(horizontal = 20.dp)
                        .fillMaxWidth()
                        .fillMaxHeight(0.95f),
                    shape = RoundedCornerShape(32.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.Transparent),
                    elevation = CardDefaults.cardElevation(defaultElevation = 10.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(bgColor)
                            .padding(horizontal = 24.dp, vertical = 24.dp)
                    ) {
                        Column(
                            modifier = Modifier.fillMaxSize(),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            // título
                            Text(
                                text = tip.title,
                                fontSize = 26.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.White,
                                lineHeight = 30.sp
                            )

                            Spacer(Modifier.height(8.dp))

                            // 🔥 área da imagem pega TODO o resto, ANCORADA EMBAIXO
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .weight(1f),
                                contentAlignment = Alignment.BottomCenter
                            ) {
                                Image(
                                    painter = painterResource(id = tip.imageRes),
                                    contentDescription = null,
                                    modifier = Modifier
                                        .fillMaxWidth(1.3f)
                                        .graphicsLayer {
                                            transformOrigin = TransformOrigin(0.5f, 1f)
                                            scaleX = 2.55f
                                            scaleY = 2.55f
                                        }
                                        .offset(
                                            x = if (tip.id == 2) 20.dp else 0.dp,  // ajuste card 2
                                            y = 30.dp
                                        ),
                                    contentScale = ContentScale.Fit
                                )
                            }
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            // ===== Indicadores =====
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 4.dp),
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
                                color = if (selected) Color.White else Color(0x80FFFFFF),
                                shape = CircleShape
                            )
                    )
                }
            }

            // ===== Card FAQ (agora com imagem à esquerda) =====
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .clickable { showFaqDialog = true },
                shape = RoundedCornerShape(24.dp),
                colors = CardDefaults.cardColors(containerColor = Color(0xFF9EC5FF)), // azul do protótipo
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 14.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {


                    Image(
                        painter = painterResource(id = R.drawable.guardia_chat),
                        contentDescription = "Ilustração Guardiã FAQ",
                        modifier = Modifier
                            .size(90.dp)
                            .scale(2.7f)
                            .offset(x = (-6).dp, y = 4.dp),  // 🔥 DESCE 10dp
                        contentScale = ContentScale.Fit
                    )

                    Spacer(Modifier.width(6.dp))

                    Column(
                        modifier = Modifier.weight(1f)
                    ) {

                        Text(
                            text = "Dúvidas sobre a Guardiã?",
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF0E3B5E) // título escuro
                        )

                        Text(
                            text = "Respondemos nas",
                            fontSize = 12.sp,
                            color = Color(0xFF2F4A65)
                        )

                        Spacer(Modifier.height(6.dp))

                        // ⭐ BOTÃO IGUAL AO PROTÓTIPO
                        OutlinedButton(
                            onClick = { showFaqDialog = true },
                            shape = RoundedCornerShape(50),
                            border = BorderStroke(2.dp, Color.White),
                            colors = ButtonDefaults.outlinedButtonColors(
                                containerColor = Color.Transparent,
                                contentColor = Color.White
                            ),
                            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 6.dp)
                        ) {
                            Text(
                                text = "Perguntas frequentes",
                                fontSize = 12.sp,
                                fontWeight = FontWeight.SemiBold
                            )
                        }
                    }
                }
            }


            Spacer(modifier = Modifier.height(8.dp))

            // ===== Bottom Bar (igual Home) =====
            GuardiaBottomBar(
                currentRoute = "tips",
                onItemClick = { route ->
                    when (route) {
                        "home" -> onBackClick()
                        else -> { /* aqui você conecta com o NavController depois */ }
                    }
                }
            )
        }

        if (showFaqDialog) {
            TipDialog(tip = faqTip, onDismiss = { showFaqDialog = false })
        }
    }
}

// ---------- Diálogo ----------
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

@Preview(
    name = "Tela de Dicas - Carrossel",
    showBackground = true,
    showSystemUi = true
)
@Composable
fun GuardiaTipsScreenPreview() {
    GuardiaTipsScreen()
}
