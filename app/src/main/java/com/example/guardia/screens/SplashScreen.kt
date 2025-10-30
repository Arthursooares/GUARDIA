package com.example.guardia.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun SplashScreen(onContinue: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {

// Fundo da tela
      /*  Image(
            painter = painterResource(id = R.drawable.bg_splash), // Carrega a imagem bg_splash.png do diretório res/drawable
            contentDescription = null, // Não precisa de descrição para acessibilidade aqui
            modifier = Modifier.fillMaxSize(), // Faz a imagem ocupar toda a tela
            contentScale = ContentScale.Crop // Ajusta a imagem para preencher sem distorcer (corta se necessário)
        )*/

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

           /* Image(
                painter = painterResource(id = R.drawable.logo_guardia), // Carrega a imagem logo_guardia.png
                contentDescription = "Logo Guardião", // Descrição para acessibilidade
                modifier = Modifier.size(180.dp) // Define tamanho fixo para a logo
            )*/


            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = "Guardião",
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )

            Spacer(modifier = Modifier.height(48.dp))

            Button(onClick = onContinue) {
                Text("Continuar")
            }
        }
    }
}
