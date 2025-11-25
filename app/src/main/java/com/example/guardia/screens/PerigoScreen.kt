package com.example.guardia.screens

import androidx.compose.foundation.Image
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
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.guardia.R

private val title_color = Color(0xFF1F367C)
private val primary_bg_color = Color(0xFF293859)

@Composable
fun PerigoScreen(onNavigateToGuardia: () -> Unit = {}) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(primary_bg_color)
            .verticalScroll(rememberScrollState())
    ) {
        PerigoTopAppBar()

        Spacer(modifier = Modifier.height(24.dp))

        Box(modifier = Modifier.fillMaxWidth()) {
            Column {
                SectionHeader("Riscos do Ambiente dos Jogos Online:", "A diversão também merece atenção!")
                Spacer(modifier = Modifier.height(24.dp))
                Column(
                    modifier = Modifier.padding(start = 24.dp, end = 120.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    InfoText(
                        "Os jogos online, por sua natureza interativa e muitas vezes anônima, criam um ecossistema com vulnerabilidades específicas que exigem atenção redobrada dos pais e cuidadores.",
                        highlights = emptyList(),
                        color = Color.White
                    )
                }
            }
            Box(
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .offset(x = 40.dp, y = 90.dp)
                    .size(130.dp)
                    .background(color = Color(0xFF4CA593), shape = CircleShape)
            )
            Box(
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .offset(x = (-25).dp, y = 65.dp)
                    .size(75.dp)
                    .background(color = title_color, shape = CircleShape)
            )
            Image(
                painter = painterResource(id = R.drawable.guardia_videogame),
                contentDescription = null,
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .size(230.dp)
                    .offset(x = 70.dp, y = 10.dp),
                contentScale = ContentScale.Crop
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        PerigoSectionCard("Perigos de Interação e Conteúdo (Contato e Conduta)")
        Spacer(modifier = Modifier.height(16.dp))
        InfoText(
            "Os jogos online apresentam riscos significativos para os jovens, que vão além do cyberbullying. Os perigos se destacam por ocorrerem em ambientes fechados e imersivos, dos quais a vítima depende para se divertir, tornando a fuga mais difícil.",
            highlights = emptyList(),
            modifier = Modifier.padding(horizontal = 24.dp),
            color = Color.White
        )

        Spacer(modifier = Modifier.height(24.dp))

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFF355A86))
                .padding(vertical = 24.dp, horizontal = 24.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            Text(
                "Principais riscos:",
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp
            )
            RiskInfoItem(
                "Assédio e Comportamento Tóxico:",
                "Inclui sabotagem de jogo (\"griefing\"), rumores e linguagem agressiva. O anonimato e a competição frequentemente levam à naturalização de discursos de ódio, como racismo e homofobia."
            )
            RiskInfoItem(
                "Exposição a Conteúdo Inadequado:",
                "As plataformas são usadas para disseminar ideologias extremistas em comunidades privadas e há exposição, acidental ou não, a pornografia e violência extrema em chats e servidores."
            )
            RiskInfoItem(
                "Ação de Predadores (Grooming):",
                "Predadores abordam jovens de forma gradual, construindo confiança durante o jogo para depois isolá-los e manipulá-los a compartilhar informações íntimas ou pessoais, explorando vulnerabilidades como a solidão."
            )
            RiskInfoItem(
                "Jogos de Azar Disfarçados:",
                "Mecanismos como \"loot boxes\" e \"Gacha\" funcionam como apostas, incentivando gastos repetidos pela chance de itens raros. Essa exposição precoce pode naturalizar o vício em jogos de azar na vida adulta."
            )
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFF293859))
                .padding(vertical = 24.dp, horizontal = 24.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            RiskInfoItem(
                "Vazamento de Dados:",
                "Empresas coletam muitos dados pessoais, que podem ser vazados em falhas de segurança, levando a roubo de identidade e outros crimes."
            )
            RiskInfoItem(
                "Gastos Inesperados:",
                "O modelo \"freemium\" (grátis para jogar) incentiva compras dentro do aplicativo para progresso, o que pode resultar em cobranças acidentais e elevadas no cartão de crédito, especialmente por crianças."
            )
            RiskInfoItem(
                "Invasão de Dispositivos:",
                "Ao baixar mods, \"cheats\" ou jogos piratas, o usuário pode instalar malware sem saber. Esse software malicioso pode roubar dados ou até mesmo assumir o controle da webcam e do microfone."
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        PerigoSectionCard("Benefícios (Com Moderação e Supervisão)")

        Column(modifier = Modifier.padding(horizontal = 24.dp, vertical = 16.dp)) {
            BenefitItem(
                "Desenvolvimento Cognitivo",
                "Jogos de estratégia (RTS) ou quebra-cabeças complexos aprimoram o raciocínio lógico, a memória de trabalho e a capacidade de tomar decisões rápidas sob pressão."
            )
            HorizontalDivider(color = Color.White.copy(alpha = 0.3f))
            BenefitItem(
                "Socialização e Cooperação",
                "Jogos cooperativos exigem comunicação efetiva, trabalho em equipe, e resolução de conflitos em tempo real, habilidades sociais cruciais que são treinadas no ambiente seguro do jogo."
            )
            HorizontalDivider(color = Color.White.copy(alpha = 0.3f))
            BenefitItem(
                "Criatividade e Resolução de Problemas",
                "Jogos \"sandbox\" (como Minecraft ou Roblox) incentivam a imaginação, a criação de estruturas complexas e o planejamento de longo prazo."
            )
            HorizontalDivider(color = Color.White.copy(alpha = 0.3f))
            BenefitItem(
                "Coordenação Motora e Reflexos",
                "Jogos de ação ou esportivos aprimoram a coordenação óculo-manual e o tempo de reação a estímulos visuais e sonoros."
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        Column(
            modifier = Modifier.padding(horizontal = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(
                    text = "Ainda com dúvidas?",
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
                Icon(
                    imageVector = Icons.Default.Star,
                    contentDescription = null,
                    tint = Color(0xFFFFD54F)
                )
            }
            Button(
                onClick = onNavigateToGuardia,
                colors = ButtonDefaults.buttonColors(containerColor = Color.White),
                shape = RoundedCornerShape(50)
            ) {
                Text(
                    text = "Converse com a Guardiã",
                    color = title_color,
                    fontWeight = FontWeight.Bold
                )
            }
        }
        Spacer(modifier = Modifier.height(24.dp))
    }
}

// --- Componentes Reutilizáveis ---

@Composable
private fun PerigoTopAppBar() {
    Column(modifier = Modifier.padding(top = 30.dp)) {
        HorizontalDivider(color = Color.White.copy(alpha = 0.5f), thickness = 1.dp)
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White.copy(alpha = 0.2f))
                .padding(vertical = 12.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Perigo dos jogos online",
                color = title_color,
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                style = TextStyle(
                    shadow = Shadow(
                        color = Color.White,
                        offset = Offset(0f, 0f),
                        blurRadius = 10f
                    )
                )
            )
        }
        HorizontalDivider(color = Color.White.copy(alpha = 0.5f), thickness = 1.dp)
    }
}

@Composable
private fun SectionHeader(title: String, subtitle: String) {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        color = Color(0xFF355A86)
    ) {
        Column(
            modifier = Modifier.padding(vertical = 12.dp, horizontal = 24.dp)
        ) {
            Text(
                text = title,
                color = Color.White,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = subtitle,
                color = Color.White.copy(alpha = 0.8f),
                fontSize = 16.sp
            )
        }
    }
}

@Composable
fun RiskInfoItem(title: String, description: String) {
    Column {
        Text(
            title,
            color = Color.White,
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            description,
            color = Color.White.copy(alpha = 0.9f),
            fontSize = 14.sp,
            lineHeight = 20.sp
        )
    }
}

@Composable
fun BenefitItem(title: String, description: String) {
    Row(
        modifier = Modifier
            .padding(vertical = 12.dp)
            .height(IntrinsicSize.Min)
    ) {
        Text(
            title,
            modifier = Modifier.weight(1f),
            color = Color.White,
            fontWeight = FontWeight.Bold,
            fontSize = 15.sp
        )
        VerticalDivider(
            modifier = Modifier
                .fillMaxHeight()
                .width(1.dp),
            color = Color.White.copy(alpha = 0.5f)
        )
        Text(
            description,
            modifier = Modifier
                .weight(1.5f)
                .padding(start = 12.dp),
            color = Color.White,
            fontSize = 14.sp,
            lineHeight = 18.sp
        )
    }
}

@Composable
private fun PerigoSectionCard(title: String) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp)
            .background(
                color = Color.White.copy(alpha = 0.2f),
                shape = RoundedCornerShape(50.dp)
            )
            .padding(vertical = 12.dp, horizontal = 16.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = title,
            color = title_color,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            style = TextStyle(
                shadow = Shadow(
                    color = Color.White,
                    offset = Offset.Zero,
                    blurRadius = 10f
                )
            )
        )
    }
}

@Composable
fun InfoText(
    text: String,
    highlights: List<String>,
    color: Color = Color.White,
    modifier: Modifier = Modifier
) {
    Text(
        buildAnnotatedString {
            var currentIndex = 0

            while (currentIndex < text.length) {
                val highlightFound = highlights.firstOrNull { highlight ->
                    text.startsWith(highlight, currentIndex)
                }

                if (highlightFound != null) {
                    withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                        append(highlightFound)
                    }
                    currentIndex += highlightFound.length
                } else {
                    append(text[currentIndex])
                    currentIndex++
                }
            }
        },
        color = color,
        fontSize = 14.sp,
        lineHeight = 20.sp,
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun PerigoScreenPreview() {
    PerigoScreen()
}