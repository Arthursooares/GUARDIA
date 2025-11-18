package com.example.guardia.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun FeedbackScreen(onBackClick: () -> Unit = {}) {

    var selectedScore by remember { mutableStateOf<Int?>(null) }
    var expanded by remember { mutableStateOf(false) }
    var selectedType by remember { mutableStateOf("Sugestão") }
    var text by remember { mutableStateOf("") }

    val feedbackOptions = listOf("Sugestão", "Elogio", "Problema", "Reclamação")

    // ===== FUNDO COM DEGRADÊ, COMO NO PROTÓTIPO =====
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    listOf(
                        Color(0xFFB3E5FC), // azul claro no topo
                        Color(0xFFEAF6FF)  // quase branco embaixo
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
                    fontWeight = FontWeight.SemiBold,
                    color = Color(0xFF102A43)
                )

                Spacer(Modifier.weight(1f))

                Icon(
                    imageVector = Icons.Default.Star,
                    contentDescription = "estrela",
                    tint = Color(0xFFFFD700),
                    modifier = Modifier.size(26.dp)
                )
            }

            // ===== CARD CENTRAL (BRANCO) =====
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .weight(1f), // ocupa bem a tela
                shape = RoundedCornerShape(24.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color.White
                ),
                elevation = CardDefaults.cardElevation(
                    defaultElevation = 8.dp
                )
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
                            .padding(horizontal = 8.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        (0..10).forEach { number ->
                            Box(
                                modifier = Modifier
                                    .size(34.dp) // AUMENTADO PARA NÃO APERTAR O 10
                                    .clip(CircleShape)
                                    .background(
                                        if (selectedScore == number) Color(0xFF0D47A1)
                                        else Color.White
                                    )
                                    .border(1.dp, Color(0xFF0D47A1), CircleShape)
                                    .clickable { selectedScore = number },
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = number.toString(),
                                    fontSize = 14.sp, // tamanho bom pra caber 10
                                    fontWeight = FontWeight.Medium,
                                    color = if (selectedScore == number) Color.White else Color(0xFF102A43)
                                )
                            }
                        }
                    }


                    Spacer(Modifier.height(20.dp))

                    // ===== DROPDOWN =====
                    Column(modifier = Modifier.fillMaxWidth()) {
                        Text(
                            "Tipo de feedback",
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 14.sp,
                            color = Color(0xFF102A43)
                        )

                        Spacer(Modifier.height(6.dp))

                        Box(
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            OutlinedButton(
                                onClick = { expanded = true },
                                modifier = Modifier.fillMaxWidth(),
                                shape = RoundedCornerShape(12.dp),
                                colors = ButtonDefaults.outlinedButtonColors(
                                    containerColor = Color(0xFFF7F9FC)
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
                            placeholder = { Text("Descreva o que está pensando...") },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(120.dp),
                            shape = RoundedCornerShape(12.dp),
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedBorderColor = Color(0xFF0D47A1),
                                unfocusedBorderColor = Color(0xFFCBD2D9)
                            )
                        )
                    }

                    Spacer(Modifier.height(20.dp))

                    // ===== BOTÃO ENVIAR =====
                    Button(
                        onClick = { /* enviar depois */ },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(48.dp),
                        shape = RoundedCornerShape(14.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFF0D47A1)
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

            Spacer(Modifier.height(16.dp))
        }
    }
}

// PREVIEW
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewFeedbackScreen() {
    MaterialTheme {
        FeedbackScreen()
    }
}
