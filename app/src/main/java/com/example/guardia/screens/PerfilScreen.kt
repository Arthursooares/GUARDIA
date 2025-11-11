package com.example.guardia.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.guardia.R
import com.example.guardia.ui.theme.GuardiaTheme

@Composable
fun PerfilScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFF6EA9CE), // Azul claro em cima
                        Color(0xFF1D4ED8)  // Azul escuro embaixo
                    )
                )
            )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 64.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // --- Seção da Imagem de Perfil ---
            Box(contentAlignment = Alignment.BottomEnd) {
                Image(
                    painter = painterResource(id = R.drawable.livia), // Trocar pela imagem do usuário
                    contentDescription = "Foto de Perfil",
                    modifier = Modifier
                        .size(150.dp)
                        .clip(CircleShape)
                        .border(4.dp, Color.White, CircleShape),
                    contentScale = ContentScale.Crop
                )


            }

            Spacer(modifier = Modifier.height(16.dp))

            // --- Nome e Email do Usuário ---
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = "Livia Oliveira",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "livia.oliveira@gmail.com",
                    fontSize = 16.sp,
                    color = Color.White.copy(alpha = 0.8f) // Cor branca com leve transparência
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            // --- Botão Editar Perfil ---
            Button(
                onClick = { /* TODO: Handle Edit Profile click */ },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 40.dp),
                shape = RoundedCornerShape(50),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFBFDBFE).copy(alpha = 0.5f) // Azul claro com transparência
                ),
                border = BorderStroke(1.dp, Color.White.copy(alpha = 0.7f)),
                contentPadding = PaddingValues(horizontal = 12.dp, vertical = 8.dp)
            ) {
                // AQUI: Trocamos Row por Box para ter controle total do alinhamento
                Box(modifier = Modifier.fillMaxWidth()) {
                    // Ícone alinhado à esquerda
                    Box(
                        modifier = Modifier
                            .size(45.dp) // Tamanho do círculo azul
                            .background(color = Color(0xFF3B82F6), shape = CircleShape)
                            .align(Alignment.CenterStart), // Alinha na esquerda e centraliza verticalmente
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.Person,
                            contentDescription = "Ícone de Perfil",
                            tint = Color.White,
                            modifier = Modifier.size(40.dp) // Tamanho do ícone (menor que o círculo)
                        )
                    }

                    // Texto alinhado no centro absoluto do botão
                    Text(
                        text = "Editar Perfil",
                        color = Color(0xFF1E40AF), // Azul escuro
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 16.sp,
                        modifier = Modifier.align(Alignment.Center) // Alinha no centro do Box pai
                    )
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun PerfilScreenPreview() {
    GuardiaTheme() {
        PerfilScreen()
    }
}
