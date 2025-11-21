package com.example.guardia.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.guardia.R

@Composable
fun ComunicacaoFamiliarScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFFFF3C2))
            .verticalScroll(rememberScrollState())
    ) {

        // ===== TOPO =====
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFFFFC94D))
                .padding(vertical = 16.dp)
        ) {
            Text(
                text = "Comunicação familiar",
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF23408F),
                modifier = Modifier.align(Alignment.Center),
                textAlign = TextAlign.Center
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        // ===== BLOCO SUPERIOR - TEXTO + CÍRCULOS + IMAGEM =====
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFFFFD96A))
                .padding(vertical = 20.dp)
        ) {

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {

                // ==== ÁREA DO TEXTO COM CÍRCULOS ATRÁS ====
                Box(
                    modifier = Modifier
                        .widthIn(max = 230.dp)
                        .padding(end = 12.dp),
                    contentAlignment = Alignment.Center
                ) {

                    // CÍRCULO ESCURO ATRÁS
                    Box(
                        modifier = Modifier
                            .size(170.dp)
                            .align(Alignment.Center)
                            .offset(x = 40.dp)
                            .background(Color(0xFFFFC24D), shape = CircleShape)
                    )

                    // CÍRCULO CLARO NA FRENTE
                    Box(
                        modifier = Modifier
                            .size(170.dp)
                            .align(Alignment.Center)
                            .offset(x = -20.dp)
                            .background(Color(0xFFFFF4C1), shape = CircleShape)
                    )

                    // ===== TEXTO =====
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "A importância de se comunicar",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF23408F),
                            textAlign = TextAlign.Center,
                            lineHeight = 22.sp
                        )
                        Text(
                            text = "com crianças e adolescentes",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF23408F),
                            textAlign = TextAlign.Center,
                            lineHeight = 22.sp
                        )
                        Text(
                            text = "e a liberdade assistida",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF23408F),
                            textAlign = TextAlign.Center,
                            lineHeight = 22.sp
                        )
                    }
                }

                // ===== IMAGEM =====
                Image(
                    painter = painterResource(id = R.drawable.guardia_comunicacao),
                    contentDescription = "Família Guardiã",
                    modifier = Modifier
                        .height(150.dp)
                        .aspectRatio(0.85f),
                    contentScale = ContentScale.Crop
                )
            }
        }

        // ===== TEXTO EXPLICATIVO =====
        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = "Muitos pais reconhecem a ameaça, mas uma grande parcela admite a falta de orientação e conhecimento sobre como proteger efetivamente seus filhos.",
            fontSize = 15.sp,
            color = Color(0xFF333333),
            textAlign = TextAlign.Justify,
            lineHeight = 22.sp,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
        )

        // ===== CARD COM BULLETS =====
        Spacer(modifier = Modifier.height(16.dp))

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 8.dp)
                .shadow(
                    elevation = 6.dp,
                    shape = RoundedCornerShape(20.dp),
                    clip = false
                ),
            shape = RoundedCornerShape(20.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color(0xFFFFF9E6)
            )
        ) {
            Column(
                modifier = Modifier.padding(vertical = 18.dp, horizontal = 18.dp)
            ) {
                BulletItem("Predadores Online (Grooming): É a prática de aliciamento.")
                BulletItem("Exposição a Conteúdo Nocivo/Inapropriado.")
                BulletItem("Cyberbullying.")
                BulletItem("Compartilhamento Excessivo de Dados.")
                BulletItem("Encontros Presenciais: O ápice do risco de grooming.")
                BulletItem("Desafios Online Perigosos: Conteúdos virais perigosos.")
            }
        }

        // ===== NOVA SEÇÃO: DESAFIOS E VISÃO DA PROTEÇÃO =====
        Spacer(modifier = Modifier.height(24.dp))

        DesafiosVisaoProtecaoSection()

        Spacer(modifier = Modifier.height(24.dp))
    }
}

// ========== SEÇÃO NOVA ==========
@Composable
private fun DesafiosVisaoProtecaoSection() {

    // Faixa de título geral
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFFFFC94D))
            .padding(vertical = 10.dp)
    ) {
        Text(
            text = "Desafios e Visão da Proteção",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF23408F),
            modifier = Modifier.align(Alignment.Center),
            textAlign = TextAlign.Center
        )
    }

    Spacer(modifier = Modifier.height(14.dp))

    // ========================
    // CARD 1 - INTERNET NÃO É A VILÃ
    // ========================
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 6.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFFFFEAAA) // COR CORRETA DO PRINT
        )
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                text = "A Internet não é a Vilã:",
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF23408F),
                textAlign = TextAlign.Center
            )

            Text(
                text = "É crucial que os pais vejam a tecnologia como uma ferramenta que, usada com equilíbrio, pode trazer muitos benefícios para o aprendizado, comunicação e criatividade das crianças.",
                fontSize = 14.sp,
                color = Color(0xFF333333),
                textAlign = TextAlign.Justify,
                lineHeight = 20.sp,
                modifier = Modifier.padding(top = 4.dp)
            )
        }
    }

    // ========================
    // CARD 2 - A IMPORTÂNCIA DO DIÁLOGO
    // ========================
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 6.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFFFFEAAA) // COR CORRETA DO PRINT
        )
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                text = "A Importância do Diálogo:",
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF23408F),
                textAlign = TextAlign.Center
            )

            Text(
                text = "Mais do que controlar, é importante conversar. O diálogo aberto cria confiança para que a criança se sinta segura em pedir ajuda quando algo a incomoda no ambiente digital.",
                fontSize = 14.sp,
                color = Color(0xFF333333),
                textAlign = TextAlign.Justify,
                lineHeight = 20.sp,
                modifier = Modifier.padding(top = 4.dp)
            )
        }
    }

    // ========================
    // CARD 3 - NÃO CONFIAR NA SEGURANÇA ABSOLUTA
    // ========================
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 6.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFFFFEAAA) // COR CORRETA DO PRINT
        )
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                text = "Não Confiar na Segurança Absoluta:",
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF23408F),
                textAlign = TextAlign.Center
            )

            Text(
                text = "Mesmo com filtros, senhas e configurações, nenhum sistema é 100% seguro. A supervisão ativa e a orientação constante continuam sendo fundamentais para manter as crianças protegidas.",
                fontSize = 14.sp,
                color = Color(0xFF333333),
                textAlign = TextAlign.Justify,
                lineHeight = 20.sp,
                modifier = Modifier.padding(top = 4.dp)
            )
        }
    }
}



// ========== BULLET ITEM ==========
@Composable
private fun BulletItem(text: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 8.dp),
        verticalAlignment = Alignment.Top
    ) {
        Text(
            text = "•",
            fontSize = 16.sp,
            color = Color(0xFF23408F),
            modifier = Modifier.padding(end = 6.dp)
        )
        Text(
            text = text,
            fontSize = 15.sp,
            color = Color(0xFF333333),
            lineHeight = 20.sp
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ComunicacaoFamiliarScreenPreview() {
    MaterialTheme {
        ComunicacaoFamiliarScreen()
    }
}
