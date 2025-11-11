package com.example.guardia.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog

data class TipItem(
    val id: Int,
    val title: String,
    val icon: ImageVector,
    val iconColor: Color,
    val content: String
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GuardiaTipsScreen(
    onBackClick: () -> Unit = {}
) {
    var selectedTip by remember { mutableStateOf<TipItem?>(null) }

    val tips = listOf(
        TipItem(
            id = 1,
            title = "Cuidados nas\nRedes Sociais",
            icon = Icons.Default.PhoneAndroid,
            iconColor = Color(0xFF3B82F6),
            content = "• Nunca compartilhe informações pessoais como endereço, telefone ou escola\n\n• Configure suas redes sociais como privadas\n\n• Cuidado ao aceitar solicitações de amizade de desconhecidos\n\n• Pense bem antes de postar fotos ou informações"
        ),
        TipItem(
            id = 2,
            title = "Perigos nos\nJogos Online",
            icon = Icons.Default.SportsEsports,
            iconColor = Color(0xFF3B82F6),
            content = "• Nunca aceite convites para conversar fora do jogo\n\n• Use apelidos, não seu nome real\n\n• Não compartilhe dados pessoais com outros jogadores\n\n• Bloqueie e reporte comportamentos abusivos"
        ),
        TipItem(
            id = 3,
            title = "Comunicação\nFamiliar e\nLiberdade Assistida",
            icon = Icons.Default.People,
            iconColor = Color(0xFF3B82F6),
            content = "• Converse abertamente com sua família sobre suas experiências online\n\n• Compartilhe o que você faz na internet\n\n• Peça ajuda quando se sentir desconfortável\n\n• Mantenha um diálogo saudável e honesto"
        ),
        TipItem(
            id = 4,
            title = "Glossário\nGrooming",
            icon = Icons.Default.MenuBook,
            iconColor = Color(0xFF7C3AED),
            content = "Grooming é o processo onde adultos mal-intencionados criam vínculos emocionais com crianças e adolescentes para exploração sexual.\n\nSinais de alerta:\n• Elogios excessivos\n• Pedidos de segredo\n• Presentes inesperados\n• Conversas com conteúdo sexual\n• Pedidos de fotos íntimas"
        ),
        TipItem(
            id = 5,
            title = "Perguntas\nFrequentes (FAQ)",
            icon = Icons.Default.Help,
            iconColor = Color(0xFF7C3AED),
            content = "• Como fazer uma denúncia?\n• O que fazer se eu for vítima?\n• Como proteger minha privacidade online?\n• Onde buscar ajuda profissional?\n• Como conversar com meus pais sobre isso?\n\nEntre em contato com a Guardiã para mais informações e suporte!"
        )
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFF9FE2EE),
                        Color(0xFF7DD4E5)
                    )
                )
            )
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(
                                Color(0xFF9FE2EE),
                                Color(0xFF7DD4E5)
                            )
                        )
                    )
            ) {
                Column(
                    modifier = Modifier.fillMaxWidth()
                ) {

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


            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 20.dp)
                    .padding(top = 20.dp, bottom = 32.dp),
                verticalArrangement = Arrangement.spacedBy(14.dp)
            ) {

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(14.dp)
                ) {
                    tips.take(2).forEach { tip ->
                        TipCard(
                            tip = tip,
                            modifier = Modifier.weight(1f),
                            onClick = { selectedTip = tip }
                        )
                    }
                }


                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(14.dp)
                ) {
                    tips.slice(2..3).forEach { tip ->
                        TipCard(
                            tip = tip,
                            modifier = Modifier.weight(1f),
                            onClick = { selectedTip = tip }
                        )
                    }
                }


                TipCard(
                    tip = tips[4],
                    modifier = Modifier.fillMaxWidth(),
                    onClick = { selectedTip = tips[4] },
                    height = 135.dp
                )
            }
        }


        selectedTip?.let { tip ->
            TipDialog(
                tip = tip,
                onDismiss = { selectedTip = null }
            )
        }
    }
}

@Composable
fun TipCard(
    tip: TipItem,
    modifier: Modifier = Modifier,
    height: androidx.compose.ui.unit.Dp = 155.dp,
    onClick: () -> Unit
) {
    Card(
        modifier = modifier
            .height(height)
            .shadow(6.dp, RoundedCornerShape(24.dp))
            .clickable(onClick = onClick),
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Surface(
                shape = RoundedCornerShape(16.dp),
                color = Color(0xFFE8D4F0),
                modifier = Modifier.size(60.dp)
            ) {
                Icon(
                    imageVector = tip.icon,
                    contentDescription = tip.title,
                    tint = tip.iconColor,
                    modifier = Modifier.padding(11.dp)
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = tip.title,
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF374151),
                textAlign = TextAlign.Center,
                lineHeight = 14.sp
            )
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
            colors = CardDefaults.cardColors(
                containerColor = Color.White
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp)
        ) {
            Column(
                modifier = Modifier.padding(28.dp)
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
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF2563A7)
                    ),
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

// PREVIEW
@Preview(
    name = "Tela de Dicas - Modo Claro",
    showBackground = true,
    showSystemUi = true
)
@Composable
fun GuardiaTipsScreenPreview() {
    GuardiaTipsScreen()
}

@Preview(
    name = "Card de Dica",
    showBackground = true
)
@Composable
fun TipCardPreview() {
    TipCard(
        tip = TipItem(
            id = 1,
            title = "Cuidados nas\nRedes Sociais",
            icon = Icons.Default.PhoneAndroid,
            iconColor = Color(0xFF3B82F6),
            content = "Conteúdo de exemplo"
        ),
        onClick = {}
    )
}

@Preview(
    name = "Dialog de Dica",
    showBackground = true
)
@Composable
fun TipDialogPreview() {
    TipDialog(
        tip = TipItem(
            id = 1,
            title = "Cuidados nas Redes Sociais",
            icon = Icons.Default.PhoneAndroid,
            iconColor = Color(0xFF3B82F6),
            content = "• Nunca compartilhe informações pessoais\n• Configure privacidade\n• Cuidado com desconhecidos"
        ),
        onDismiss = {}
    )
}
