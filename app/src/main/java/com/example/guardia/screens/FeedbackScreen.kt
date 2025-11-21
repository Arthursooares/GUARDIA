package com.example.guardia.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.rememberScrollState


@Composable
fun FeedbackScreen(
    onBackClick: () -> Unit = {},
    onBottomItemClick: (String) -> Unit = {}
) {
    var selectedScore by remember { mutableStateOf<Int?>(null) }
    var expanded by remember { mutableStateOf(false) }
    var selectedType by remember { mutableStateOf("Sugestão") }
    var text by remember { mutableStateOf("") }

    val feedbackOptions = listOf("Sugestão", "Elogio", "Problema", "Reclamação")

    // ===== FUNDO COM DEGRADÊ =====
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    listOf(
                        Color(0xFFAEE6FF), // azul bem claro topo
                        Color(0xFF8CCBEF), // meio
                        Color(0xFF78B8E3)  // um pouco mais escuro embaixo
                    )
                )
            )
    ) {

        Column(
            modifier = Modifier.fillMaxSize()
        ) {

            // ===== TOP BAR =====
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 20.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "voltar",
                    tint = Color(0xFF102A43),
                    modifier = Modifier
                        .size(26.dp)
                        .clickable { onBackClick() }
                )

                Spacer(Modifier.width(12.dp))

                Text(
                    "Feedbacks",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.ExtraBold,
                    color = Color(0xFF0055B8)
                )

                Spacer(Modifier.weight(1f))

                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(10.dp))
                        .background(Color(0xFF0055B8))
                        .padding(horizontal = 10.dp, vertical = 6.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.Star,
                        contentDescription = "estrela",
                        tint = Color(0xFFFFD700),
                        modifier = Modifier.size(22.dp)
                    )
                }
            }

            // ===== CARD CENTRAL =====
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .weight(1f),
                shape = RoundedCornerShape(24.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color.White.copy(alpha = 0.95f)
                ),
                elevation = CardDefaults.cardElevation(defaultElevation = 10.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(20.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    // ===== TÍTULO =====
                    Text(
                        "Somos todo ouvidos!",
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF102A43)
                    )

                    Spacer(Modifier.height(4.dp))

                    Text(
                        "O quanto você recomenda a Guardiã para amigos e colegas?",
                        fontSize = 14.sp,
                        color = Color(0xFF52606D),
                        modifier = Modifier.padding(horizontal = 8.dp),
                    )

                    Spacer(Modifier.height(16.dp))

                    // ===== LABEL NPS =====
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            "Sua nota",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = Color(0xFF102A43)
                        )

                        Text(
                            "0 = Não recomendaria | 10 = Recomendo muito",
                            fontSize = 11.sp,
                            color = Color(0xFF9FB3C8)
                        )
                    }

                    Spacer(Modifier.height(10.dp))

                    // ===== NPS 0–10 =====
                    // ===== NPS 0–10 =====
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .horizontalScroll(rememberScrollState())
                            .padding(horizontal = 4.dp),
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        (0..10).forEach { number ->
                            Box(
                                modifier = Modifier
                                    .size(30.dp)
                                    .clip(CircleShape)
                                    .background(
                                        if (selectedScore == number)
                                            Color(0xFF0055B8)
                                        else Color.White
                                    )
                                    .border(1.dp, Color(0xFF0055B8), CircleShape)
                                    .clickable { selectedScore = number },
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = number.toString(),
                                    fontSize = 13.sp,
                                    fontWeight = FontWeight.Medium,
                                    color = if (selectedScore == number)
                                        Color.White
                                    else
                                        Color(0xFF102A43)
                                )
                            }
                        }
                    }

                    Spacer(Modifier.height(20.dp))

                    // ===== DROPDOWN =====
                    Column(modifier = Modifier.fillMaxWidth()) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                "Tipo de feedback",
                                fontWeight = FontWeight.SemiBold,
                                fontSize = 14.sp,
                                color = Color(0xFF102A43)
                            )
                            Spacer(Modifier.width(4.dp))
                            Icon(
                                imageVector = Icons.Default.Info,
                                contentDescription = null,
                                tint = Color(0xFF9FB3C8),
                                modifier = Modifier.size(16.dp)
                            )
                        }

                        Spacer(Modifier.height(6.dp))

                        Box(
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            OutlinedButton(
                                onClick = { expanded = true },
                                modifier = Modifier.fillMaxWidth(),
                                shape = RoundedCornerShape(12.dp),
                                colors = ButtonDefaults.outlinedButtonColors(
                                    containerColor = Color(0xFFF7F9FC),
                                    contentColor = Color(0xFF102A43)
                                )
                            ) {
                                Text(
                                    selectedType,
                                    color = Color(0xFF102A43)
                                )
                            }

                            DropdownMenu(
                                expanded = expanded,
                                onDismissRequest = { expanded = false }
                            ) {
                                feedbackOptions.forEach {
                                    DropdownMenuItem(
                                        text = { Text(it) },
                                        onClick = {
                                            selectedType = it
                                            expanded = false
                                        }
                                    )
                                }
                            }
                        }
                    }

                    Spacer(Modifier.height(18.dp))

                    // ===== TEXTAREA =====
                    Column(modifier = Modifier.fillMaxWidth()) {
                        Text(
                            "Deixe suas observações (opcional)",
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 14.sp,
                            color = Color(0xFF102A43)
                        )

                        Spacer(Modifier.height(6.dp))

                        OutlinedTextField(
                            value = text,
                            onValueChange = { text = it },
                            placeholder = {
                                Text("Descreva o que está pensando...")
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(120.dp),
                            shape = RoundedCornerShape(12.dp),
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedBorderColor = Color(0xFF0055B8),
                                unfocusedBorderColor = Color(0xFFCBD2D9)
                            )
                        )
                    }

                    Spacer(Modifier.height(20.dp))

                    // ===== BOTÃO ENVIAR =====
                    Button(
                        onClick = { /* TODO: enviar depois */ },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(48.dp),
                        shape = RoundedCornerShape(14.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFF0055B8)
                        )
                    ) {
                        Text(
                            "Enviar",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                }
            }

            Spacer(Modifier.height(8.dp))

            // ===== BOTTOM NAVIGATION BAR (ajustada) =====
            GuardiaBottomBar(
                currentRoute = "tips",
                onItemClick = onBottomItemClick
            )

            Spacer(Modifier.height(8.dp))
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewFeedbackScreen() {
    MaterialTheme {
        FeedbackScreen()
    }
}
