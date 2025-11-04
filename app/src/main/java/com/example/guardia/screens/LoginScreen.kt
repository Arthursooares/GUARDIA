package com.example.guardia.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.guardia.R
import com.example.guardia.ui.theme.GuardiaTheme


@Composable
fun LoginScreen(onRegisterClick: () -> Unit) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val tituloTexto = "Login"
    val scrollState = rememberScrollState()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black) // fundo sólido para evitar bordas brancas
    ) {
        Image(
            painter = painterResource(id = R.drawable.bg_splash),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop // garante que a imagem cubra toda a tela
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState) // AQUI: Adicionamos a rolagem vertical
                .padding(
                    top = 32.dp,
                    start = 16.dp,
                    end = 16.dp,
                    bottom = 32.dp
                ), 

            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.shield),
                contentDescription = "Logo Guardião",
                modifier = Modifier
                    .size(240.dp),
                contentScale = ContentScale.Crop
            )

            Text(
                text = tituloTexto,
                fontSize = 34.sp,
                color = MaterialTheme.colorScheme.onPrimary,
                fontWeight = FontWeight.Bold, // Opcional: Deixa o texto em negrito
                modifier = Modifier.padding(
                    bottom = 32.dp
                )

            )

            // Container para o label e o campo de texto, alinhado à esquerda
            Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.Start) {
                Text(
                    text = "Email",
                    fontSize = 20.sp, // tamanho da fonte do label
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onPrimary, // Cor azul brilhante do label
                    modifier = Modifier.padding(start = 16.dp, bottom = 4.dp)
                )

                TextField(
                    value = email,
                    onValueChange = { email = it },
                    modifier = Modifier.fillMaxWidth(),
                    label = { Text("email@gmail.com") },
                    shape = RoundedCornerShape(16.dp), // raio dos cantos
                    colors = TextFieldDefaults.colors(
                        focusedTextColor = MaterialTheme.colorScheme.primary,
                        unfocusedTextColor = Color.Transparent,
                        cursorColor = Color.Black,
                        focusedContainerColor = MaterialTheme.colorScheme.onPrimary, // Fundo branco
                        unfocusedContainerColor = MaterialTheme.colorScheme.onPrimary, // Fundo branco
                        focusedIndicatorColor = Color.Transparent, // Sem linha de foco
                        unfocusedIndicatorColor = Color.Transparent // Sem linha de foco
                    ),
                    singleLine = true
                )
                Spacer(modifier = Modifier.padding(8.dp))


                Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.Start) {
                    Text(
                        text = "Senha",
                        fontSize = 20.sp, // fonte do label
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onPrimary, // Cor azul brilhante do label
                        modifier = Modifier.padding(start = 16.dp, bottom = 4.dp)
                    )

                    TextField(
                        value = password,
                        onValueChange = { password = it },
                        modifier = Modifier.fillMaxWidth(),
                        label = { Text("********") },
                        shape = RoundedCornerShape(16.dp), // raio dos cantos
                        colors = TextFieldDefaults.colors(
                            focusedTextColor = MaterialTheme.colorScheme.primary, // Cor do texto
                            unfocusedTextColor = Color.Transparent, // Cor do texto
                            cursorColor = Color.Black, // Cor do cursor
                            focusedContainerColor = MaterialTheme.colorScheme.onPrimary, // Fundo branco
                            unfocusedContainerColor = MaterialTheme.colorScheme.onPrimary, // Fundo branco
                            focusedIndicatorColor = Color.Transparent, // Sem linha de foco
                            unfocusedIndicatorColor = Color.Transparent // Sem linha de foco
                        ),
                        singleLine = true
                    )
                    Spacer(modifier = Modifier.padding(5.dp))
                    Button(
                        onClick = onRegisterClick,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 16.dp),
                        shape = RoundedCornerShape(28.dp), // Shape para os TextFields
                        contentPadding = PaddingValues(), // padding padrão para ter controle total
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.Transparent // Botão fica transparente
                        )
                    ) {
                        // O Box contém o gradiente e o texto
                        Box(

                            modifier = Modifier
                                .fillMaxWidth()
                                .background(
                                    brush = Brush.verticalGradient(
                                        colors = listOf(
                                            Color(0xFF1E3A8A),
                                            Color(0xFF080F2F)
                                        )
                                    )
                                )
                                .padding(vertical = 16.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "Entrar",
                                color = MaterialTheme.colorScheme.onPrimary,
                                fontSize = 22.sp
                            )
                        }
                    }
                }
                Spacer(modifier = Modifier.padding(16.dp))

                Text(
                    fontSize = 14.sp,
                    text = buildAnnotatedString {
                        withStyle(style = SpanStyle(color = MaterialTheme.colorScheme.onPrimary)) {
                            append("Não tem uma conta? ") // AQUI: Usamos um texto mais curto
                        }
                        withStyle(style = SpanStyle(color = MaterialTheme.colorScheme.tertiary)) {
                            append("Cadastre-se") // AQUI: Usamos um texto mais curto
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { onRegisterClick() },
                    textAlign = TextAlign.Center

                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    GuardiaTheme {
        LoginScreen(onRegisterClick = {})
    }
}
