package com.example.guardia.screens


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.guardia.R
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.material3.MaterialTheme



@Composable
fun ComunicacaoFamiliarScreen() {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .background(Color(0xFFFFF3C2)) // Cor de fundo do layout
            .padding(16.dp)
    ) {

        // ===== Título e personagem =====
        Text(
            text = "Comunicação Familiar",
            fontSize = 26.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF333333),
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(12.dp))

        Image(
            painter = painterResource(id = R.drawable.guardia_familia), // substitua pela sua imagem!
            contentDescription = null,
            modifier = Modifier
                .size(120.dp)
                .align(Alignment.CenterHorizontally)
        )

        Spacer(modifier = Modifier.height(16.dp))

        // ===== Bloco de texto amarelo =====
        Card(
            colors = CardDefaults.cardColors(
                containerColor = Color(0xFFFFE8A3)
            ),
            shape = RoundedCornerShape(16.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "A comunicação é essencial para criar um ambiente familiar seguro, acolhedor e saudável. Quando conversamos abertamente, ajudamos as crianças a desenvolver autoestima e confiança.",
                fontSize = 16.sp,
                modifier = Modifier.padding(16.dp),
                color = Color(0xFF4B3F27)
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        // ===== Seção =====
        Text(
            text = "Desafios Vividos na Família",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Start
        )

        Divider(
            modifier = Modifier
                .padding(vertical = 8.dp)
                .fillMaxWidth(),
            color = Color(0xFFFFC04D),
            thickness = 2.dp
        )

        Text(
            text = "Nem sempre é fácil manter um diálogo saudável dentro de casa, especialmente quando surgem conflitos, emoções fortes ou falta de tempo.",
            color = Color(0xFF5B4A2C),
            fontSize = 15.sp
        )

        Spacer(modifier = Modifier.height(20.dp))

        // ===== Cards de dicas =====
        ComunicacaoCard(
            titulo = "Escuta Ativa",
            texto = "Quando alguém estiver falando, procure ouvir com atenção, sem interromper. Isto mostra respeito e incentiva a confiança."
        )

        Spacer(modifier = Modifier.height(14.dp))

        ComunicacaoCard(
            titulo = "Expresse Seus Sentimentos",
            texto = "Ensine as crianças a falar sobre o que sentem. Use frases como 'Eu me sinto assim quando...'."
        )

        Spacer(modifier = Modifier.height(14.dp))

        ComunicacaoCard(
            titulo = "Converse Sem Julgamentos",
            texto = "Evite críticas duras. Busque compreender antes de corrigir ou orientar."
        )

        Spacer(modifier = Modifier.height(14.dp))

        ComunicacaoCard(
            titulo = "Momentos de Diálogo",
            texto = "Crie momentos para conversar em família, como durante refeições ou antes de dormir."
        )

        Spacer(modifier = Modifier.height(26.dp))

        // ===== Rodapé =====
        Text(
            text = "Construir uma comunicação saudável é um processo contínuo. Comece com pequenos gestos diários.",
            fontSize = 14.sp,
            color = Color(0xFF6E5A34),
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 32.dp),
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun ComunicacaoCard(titulo: String, texto: String) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFFFFE8A3)
        ),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 3.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(modifier = Modifier.padding(16.dp)) {

            Text(
                text = titulo,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF4B3F27)
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = texto,
                fontSize = 15.sp,
                color = Color(0xFF4B3F27)
            )
        }
    }
}
@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
fun ComunicacaoFamiliarScreenPreview() {
    MaterialTheme {
        ComunicacaoFamiliarScreen()
    }
}

