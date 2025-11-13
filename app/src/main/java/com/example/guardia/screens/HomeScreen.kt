package com.example.guardia.screens

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChatBubble
import androidx.compose.material.icons.filled.Description
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.guardia.R
import androidx.compose.ui.draw.scale


// ---------- Paleta ----------
private val AzureLight = Color(0xFFE8F5FF)
private val AzureMid   = Color(0xFFD3ECFF)
private val TitleDark  = Color(0xFF0E3B5E)
private val PrimaryTeal = Color(0xFF33B2B2)
private val PrimaryBlue = Color(0xFF0E6D90)
private val IconBg     = Color(0xFFE7F1FA) // reservado caso queira usar
private val CardStroke = Color(0xFFE1ECF7)

// ---------- Card com IMAGEM (com tamanho/padding/offset individual) ----------
@Composable
private fun ImageCard(
    title: String,
    @DrawableRes imageRes: Int,
    onClick: () -> Unit,
    imageSize: Dp = 70.dp,          // tamanho do espaço reservado
    imageScale: Float = 1.0f,       // 🔹 aumenta a imagem dentro do espaço
    imagePadding: Dp = 0.dp,
    imageOffsetY: Dp = 0.dp,
    imageOffsetX: Dp = 0.dp,
    cardHeight: Dp = 84.dp,
    textStartPadding: Dp = 110.dp
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(cardHeight)
            .shadow(8.dp, RoundedCornerShape(20.dp), clip = false)
            .background(Color.White, RoundedCornerShape(20.dp))
            .border(1.dp, CardStroke, RoundedCornerShape(20.dp))
            .clickable { onClick() }
    ) {
        // 🔹 imagem à esquerda, cresce dentro do espaço com scale()
        Box(
            modifier = Modifier
                .align(Alignment.CenterStart)
                .offset(x = imageOffsetX, y = imageOffsetY) // 🔹 agora age antes do padding
                .padding(start = 16.dp)
                .size(imageSize),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(imageRes),
                contentDescription = title,
                modifier = Modifier
                    .scale(imageScale)
                    .padding(imagePadding),
                contentScale = ContentScale.Fit
            )
        }

        // 🔹 texto à direita
        Text(
            text = title,
            fontSize = 18.sp,
            fontWeight = FontWeight.SemiBold,
            color = TitleDark,
            modifier = Modifier
                .align(Alignment.CenterStart)
                .padding(start = textStartPadding)
        )
    }
}


// Wrapper para o card de Dicas
@Composable
private fun TipsCard(
    @DrawableRes imageRes: Int,
    onClick: () -> Unit,
    imageSize: Dp = 70.dp,
    imageScale: Float = 1.0f,
    imagePadding: Dp = 0.dp,
    imageOffsetY: Dp = 0.dp,
    cardHeight: Dp = 84.dp,
    textStartPadding: Dp = 110.dp
) {
    ImageCard(
        title = "Dicas da Guardiã",
        imageRes = imageRes,
        onClick = onClick,
        imageSize = imageSize,
        imageScale = imageScale,
        imagePadding = imagePadding,
        imageOffsetY = imageOffsetY,
        cardHeight = cardHeight,
        textStartPadding = textStartPadding
    )
}



// ---------- Card com ÍCONE (mantido caso queira usar em outros lugares) ----------
@Composable
private fun ShortcutCard(
    title: String,
    icon: ImageVector,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(64.dp)
            .shadow(elevation = 6.dp, shape = RoundedCornerShape(18.dp), clip = false)
            .background(Color.White, shape = RoundedCornerShape(18.dp))
            .border(1.dp, CardStroke, RoundedCornerShape(18.dp))
            .clickable { onClick() }
            .padding(horizontal = 14.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(44.dp)
                .clip(RoundedCornerShape(14.dp))
                .background(IconBg),
            contentAlignment = Alignment.Center
        ) {
            Icon(icon, contentDescription = title, tint = TitleDark)
        }
        Spacer(Modifier.width(12.dp))
        Text(
            text = title,
            fontSize = 15.sp,
            fontWeight = FontWeight.SemiBold,
            color = TitleDark
        )
    }
}

data class HomeCardData(val title: String, val icon: ImageVector)

// ---------- TELA ----------
@Composable
fun HomeScreen(
    navController: NavHostController,
    userName: String = "Lívia",
    onMenuClick: () -> Unit = {},
    onChatClick: () -> Unit = {},
    onItemClick: (String) -> Unit = {}
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Brush.verticalGradient(listOf(AzureLight, AzureMid, AzureLight)))
    ) {
        Column(Modifier.fillMaxSize()) {

            // Top Bar
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp, start = 12.dp, end = 12.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                IconButton(onClick = onMenuClick) {
                    Icon(Icons.Filled.Menu, contentDescription = "Menu", tint = TitleDark)
                }
            }

            // Conteúdo (scroll)
            Column(
                modifier = Modifier
                    .weight(1f)
                    .verticalScroll(rememberScrollState())
                    .padding(horizontal = 16.dp, vertical = 8.dp)
            ) {
                Text(
                    text = buildAnnotatedString {
                        append("Bem vindo(a), ")
                        withStyle(SpanStyle(color = PrimaryBlue, fontWeight = FontWeight.Bold)) {
                            append(userName)
                        }
                        append("!")
                    },
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = TitleDark,
                    modifier = Modifier.padding(top = 6.dp, bottom = 12.dp)
                )

                // Card principal
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(180.dp)
                        .shadow(8.dp, RoundedCornerShape(28.dp), clip = false)
                        .background(
                            brush = Brush.horizontalGradient(listOf(PrimaryTeal, PrimaryBlue)),
                            shape = RoundedCornerShape(28.dp)
                        )
                        .padding(18.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxSize(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column(
                            modifier = Modifier
                                .weight(1f)
                                .padding(end = 8.dp)
                        ) {
                            Text(
                                "Converse com a Guardiã",
                                color = Color.White,
                                fontSize = 18.sp,
                                fontWeight = FontWeight.SemiBold
                            )
                            Spacer(Modifier.height(6.dp))
                            Text(
                                "Algum problema ou dúvida?\nVamos resolver isso juntas!",
                                color = Color.White.copy(alpha = 0.95f),
                                fontSize = 12.sp,
                                lineHeight = 16.sp
                            )
                            Spacer(Modifier.height(12.dp))
                            Button(
                                onClick = onChatClick,
                                colors = ButtonDefaults.buttonColors(containerColor = AzureLight),
                                shape = RoundedCornerShape(50),
                                contentPadding = PaddingValues(horizontal = 18.dp, vertical = 6.dp),
                                elevation = ButtonDefaults.buttonElevation(defaultElevation = 0.dp)
                            ) {
                                Text(
                                    "Comece agora",
                                    color = PrimaryBlue,
                                    fontSize = 12.sp,
                                    fontWeight = FontWeight.SemiBold
                                )
                            }
                        }

                        Box(
                            modifier = Modifier
                                .fillMaxHeight()
                                .width(110.dp),
                            contentAlignment = Alignment.BottomEnd
                        ) {
                            Image(
                                painter = painterResource(R.drawable.personagem),
                                contentDescription = "Personagem Guardiã",
                                modifier = Modifier
                                    .fillMaxHeight()
                                    .aspectRatio(0.6f),
                                contentScale = ContentScale.Fit
                            )
                        }
                    }
                }

                Spacer(Modifier.height(20.dp))

                // ----- Card "Dicas da Guardiã" -> navega para a tela de dicas -----
                TipsCard(
                    imageRes = R.drawable.ic_dicas,
                    onClick = {
                        navController.navigate("tips")   // 👈 AQUI faz a navegação
                    },
                    imageSize = 72.dp,
                    imageScale = 1.3f,
                    imageOffsetY = 0.dp
                )

                Spacer(Modifier.height(14.dp))

                ImageCard(
                    title = "Meus Relatórios",
                    imageRes = R.drawable.ic_relatorios,
                    onClick = { onItemClick("Meus Relatórios") },
                    imageSize = 70.dp,
                    imageScale = 1.0f,
                    imageOffsetY = 0.dp
                )
                Spacer(Modifier.height(14.dp))

                ImageCard(
                    title = "Upgrade Guardiã",
                    imageRes = R.drawable.estrela,
                    onClick = { onItemClick("Upgrade Guardiã") },
                    imageSize = 74.dp,
                    imageScale = 1.6f,
                    imageOffsetX = (-3).dp,
                    imageOffsetY = (-1).dp
                )
                Spacer(Modifier.height(14.dp))

                ImageCard(
                    title = "Feedbacks",
                    imageRes = R.drawable.ic_feedbacks,
                    onClick = { onItemClick("Feedbacks") },
                    imageSize = 70.dp,
                    imageScale = 1.8f,
                    imageOffsetY = 0.dp
                )

                Spacer(Modifier.height(16.dp))
            }

            // Bottom Bar
            NavigationBar(containerColor = Color.White, tonalElevation = 10.dp) {
                NavigationBarItem(
                    selected = false,
                    onClick = { /* esquerdo 1 */ },
                    icon = { Icon(Icons.Filled.ChatBubble, contentDescription = "Mensagens", tint = Color(0xFF9AA9B5)) },
                    label = { Text("Feed", fontSize = 10.sp) }
                )
                NavigationBarItem(
                    selected = false,
                    onClick = { /* esquerdo 2 */ },
                    icon = { Icon(Icons.Filled.Description, contentDescription = "Itens", tint = Color(0xFF9AA9B5)) },
                    label = { Text("Itens", fontSize = 10.sp) }
                )
                NavigationBarItem(
                    selected = true,
                    onClick = { /* Home */ },
                    icon = {
                        Box(
                            modifier = Modifier
                                .size(44.dp)
                                .clip(CircleShape)
                                .background(Brush.verticalGradient(listOf(AzureMid, AzureLight)))
                                .border(1.dp, CardStroke, CircleShape),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(Icons.Filled.Home, contentDescription = "Início", tint = TitleDark)
                        }
                    },
                    label = { Text("Início", fontSize = 10.sp) }
                )
                NavigationBarItem(
                    selected = false,
                    onClick = { onItemClick("guardia") },
                    icon = { Icon(Icons.Filled.Star, contentDescription = "Guardiã", tint = Color(0xFF9AA9B5)) },
                    label = { Text("Guardiã", fontSize = 10.sp) }
                )
                NavigationBarItem(
                    selected = false,
                    onClick = { /* Perfil */ },
                    icon = { Icon(Icons.Filled.Person, contentDescription = "Perfil", tint = Color(0xFF9AA9B5)) },
                    label = { Text("Perfil", fontSize = 10.sp) }
                )
            }
        }
    }
}


@Preview(showBackground = true, backgroundColor = 0xFFE8F5FF)
@Composable
fun HomeScreenPreview() {
    val navController = androidx.navigation.compose.rememberNavController()
    HomeScreen(navController = navController)
}
