package com.example.guardia.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.guardia.ui.theme.GuardiaTheme

// --- Cores Específicas do Glossário ---
private val BackgroundBlue = Color(0xFFD6E4F5)
private val HeaderBlue = Color(0xFFC0D5EF)
private val TextDarkBlue = Color(0xFF1A237E)
private val BlobRed = Color(0xFFB71C1C)
private val BannerYellow = Color(0xFFFDD835)
private val CardWhite = Color.White

@Composable
fun GroomingScreen() {
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundBlue)
            .verticalScroll(scrollState)
    ) {
        // 1. Topo: Título Principal
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(HeaderBlue)
                .padding(vertical = 16.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Glossário Grooming",
                color = TextDarkBlue,
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold
            )
        }

        // 2. Card de Definição
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 24.dp),
            contentAlignment = Alignment.Center
        ) {
            GroomingBlobBackground() // Fundo decorativo

            Card(
                modifier = Modifier.width(300.dp),
                shape = RoundedCornerShape(24.dp),
                colors = CardDefaults.cardColors(containerColor = CardWhite),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "O que é\nGrooming?",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = TextDarkBlue,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )
                    Text(
                        text = "Grooming é o processo que um predador (aliciador) usa para ganhar a confiança de uma criança ou adolescente na internet. É uma manipulação lenta e calculada.",
                        fontSize = 14.sp,
                        color = Color.Black,
                        textAlign = TextAlign.Center
                    )
                }
            }
        }

        // 3. Faixa Amarela
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(BannerYellow)
                .padding(16.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Códigos mais comuns usados por aliciadores para se comunicar e camuflar na internet:",
                color = TextDarkBlue,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                fontSize = 15.sp
            )
        }

        // 4. Lista de Itens
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            GroomingItem("Trade / Troca", "Usado para negociar e trocar material de abuso sexual infantil.")
            GroomingItem("Stars (Estrelas)", "Crianças \"populares\" por suas fotos famosas trocadas em grupos.")
            GroomingItem("🌽 (milho)", "Tradução de corn -> porn. Emoji para pornografia infantil.")
            GroomingItem("🌀 (espiral azul)", "Indica o interesse sexual por meninos.")
            GroomingItem("🍜 (macarrão)", "Noodles soa como nudes. Usado para pedir imagens íntimas.")
            GroomingItem("💖 e 🧀", "Coração e queijo: busca por imagens de meninas.")
            GroomingItem("🍭 (pirulito)", "Remete à obra Lolita (abuso sexual).")
            GroomingItem("🍬 e 🍕", "Bala e Pizza: códigos de aprovação ou interesse em crianças.")
            GroomingItem("👉👈 + OK", "Representa relação sexual.")
            GroomingItem("🍆 / 🍌", "Representação da genitália masculina.")
            GroomingItem("😏 Sorriso", "Tom sedutor ou insinuante.")
            GroomingItem("“Sua Princesa”", "Afeto excessivo para criar intimidade falsa.")
            GroomingItem("DM / Kik / Snap", "Pedido para migrar para chats privados/criptografados.")
            GroomingItem("“Patrocínio”", "Referência a Sugar Daddy/Mommy.")
            GroomingItem("ASL", "Age, Sex, Location: Pedido de dados pessoais.")
            GroomingItem("M / F", "Male / Female: Pergunta de gênero.")
            GroomingItem("MIRL", "Meet in real life: Tentar encontro presencial.")
            GroomingItem("IYKYK", "Código para segredos do grupo.")
            GroomingItem("KOTC", "Beijo na bochecha: Normalizar contato físico.")
            GroomingItem("LSKOL", "Beijo de língua: Termo explícito.")
            GroomingItem("PS (Parents)", "Alerta sobre a presença dos pais.")
            GroomingItem("👀 Olhos", "Espiar/Observar a vítima.")
            GroomingItem("😋 Rosto", "Gostou do conteúdo/aparência.")
            GroomingItem("Upada / Down", "Subindo ou baixando conteúdo ilegal.")
            GroomingItem("Trade DMs", "Troca de material ilícito.")
            GroomingItem("G.A.S.", "Gamer as Sender ou código para abuso.")
        }

        // 5. Card Atenção
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = BannerYellow),
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Atenção:",
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    color = Color.Black,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                Text(
                    text = "Os aliciadores usam linguagem secreta. Eles evoluem, mas a Guardiã também, trabalhando para desvendar truques e manter a segurança.",
                    fontSize = 14.sp,
                    color = Color.Black,
                    textAlign = TextAlign.Center
                )
            }
        }

        // 6. Rodapé
        GroomingFooter()

        Spacer(modifier = Modifier.height(16.dp))
    }
}

// --- Componentes Auxiliares (Renomeados para evitar conflito) ---

@Composable
private fun GroomingItem(term: String, definition: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        verticalAlignment = Alignment.Top
    ) {
        Text(
            text = "• ",
            color = Color.Black,
            fontSize = 14.sp,
            modifier = Modifier.padding(top = 2.dp)
        )
        Text(
            text = buildAnnotatedString {
                withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                    append("$term: ")
                }
                append(definition)
            },
            color = Color.Black,
            fontSize = 13.sp,
            lineHeight = 18.sp
        )
    }
}

@Composable
private fun GroomingBlobBackground() {
    Box(modifier = Modifier.size(1.dp)) {
        Box(modifier = Modifier.size(60.dp).offset((-120).dp, (-20).dp).background(BlobRed, CircleShape))
        Box(modifier = Modifier.size(80.dp).offset(120.dp, (-10).dp).background(BlobRed, CircleShape))
        Box(modifier = Modifier.size(50.dp).offset(140.dp, 30.dp).background(BlobRed, CircleShape))
        Box(modifier = Modifier.size(50.dp).offset((-130).dp, 40.dp).background(BlobRed, CircleShape))
    }
}

@Composable
private fun GroomingFooter() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp, horizontal = 24.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        // Substitua pelo seu Image resource
        Icon(
            imageVector = Icons.Default.Star,
            contentDescription = "Avatar Guardiã",
            modifier = Modifier
                .size(60.dp)
                .clip(CircleShape)
                .background(Color.DarkGray)
                .padding(8.dp),
            tint = Color.White
        )

        Spacer(modifier = Modifier.width(12.dp))

        Column {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = "Ainda com\ndúvidas?",
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp,
                    color = TextDarkBlue,
                    lineHeight = 16.sp
                )
                Icon(
                    imageVector = Icons.Default.Star,
                    contentDescription = null,
                    tint = BannerYellow,
                    modifier = Modifier.size(16.dp)
                )
            }
            Spacer(modifier = Modifier.height(4.dp))
            Button(
                onClick = { },
                colors = ButtonDefaults.buttonColors(containerColor = Color.White),
                shape = RoundedCornerShape(50),
                contentPadding = PaddingValues(horizontal = 12.dp, vertical = 4.dp),
                modifier = Modifier.height(30.dp)
            ) {
                Text("Converse com a Guardiã", color = TextDarkBlue, fontSize = 10.sp, fontWeight = FontWeight.Bold)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewGroomingScreen() {
    GuardiaTheme {
        GroomingScreen()
    }
}