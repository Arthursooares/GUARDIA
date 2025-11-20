package com.example.guardia.screens

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.guardia.R

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                CuidadosRedesSociaisScreen()
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewCuidadosRedesSociais() {
    MaterialTheme {
        CuidadosRedesSociaisScreen()
    }
}

@Composable
fun CuidadosRedesSociaisScreen() {
    val lightCyan = Color(0xFFB8E6E6)
    val darkCyan = Color(0xFF2B5F6F)
    val tealGreen = Color(0xFF3BACAA)
    val yellow = Color(0xFFFFD54F)

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(lightCyan)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(30.dp))

            // Título Principal
            Text(
                text = "Cuidados nas Redes Sociais",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = darkCyan,
                textAlign = TextAlign.Center,
                lineHeight = 30.sp
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Card de Riscos do Ambiente Virtual com personagem
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(tealGreen, RoundedCornerShape(16.dp))
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // Coluna de texto
                    Column(
                        modifier = Modifier.weight(1f),
                        horizontalAlignment = Alignment.Start
                    ) {
                        Text(
                            text = "Riscos do Ambiente Virtual e\nDesafios da Família",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White,
                            textAlign = TextAlign.Left,
                            lineHeight = 22.sp
                        )

                        Spacer(modifier = Modifier.height(12.dp))

                        Text(
                            text = "O ambiente online apresenta riscos que vão além do uso excessivo, sendo necessária classificação nas chamadas \"4 Cs\":",
                            fontSize = 12.sp,
                            color = Color.White,
                            textAlign = TextAlign.Left,
                            lineHeight = 17.sp
                        )
                    }

                    Spacer(modifier = Modifier.width(12.dp))

                    // Personagem (placeholder - substitua com sua imagem)
                    // Para usar sua própria imagem:
                    // 1. Coloque a imagem em res/drawable (ex: personagem.png)
                    // 2. Descomente a linha abaixo e comente o Box placeholder
                    /*
                    Image(
                        painter = painterResource(id = R.drawable.personagem),
                        contentDescription = "Personagem",
                        modifier = Modifier
                            .size(100.dp)
                            .clip(CircleShape),
                        contentScale = ContentScale.Crop
                    )
                    */

                    // Placeholder da personagem
                    // Personagem
                    Image(
                        painter = painterResource(id = R.drawable.character),
                        contentDescription = "character",
                        modifier = Modifier
                            .size(100.dp)
                            .clip(CircleShape),
                        contentScale = ContentScale.Crop
                    )
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            // Cards dos 4 Cs
            QuatroCsCard(
                titulo = "Conteúdo (Content):",
                descricao = "Possibilidade de exposição a informações ou imagens prejudiciais: sexo explícito, violência, ódio, racismo; violência, pornografia, linguagem inapropriada, automutilação, suicídio...",
                bgColor = Color.White,
                textColor = darkCyan
            )

            Spacer(modifier = Modifier.height(12.dp))

            QuatroCsCard(
                titulo = "Contato (Contact):",
                descricao = "Envolver a interação com outros (de forma amigável ou não: criminosos, assediadores) ou com partes que atuam de forma ilícita ou prejudiciais.",
                bgColor = Color.White,
                textColor = darkCyan
            )

            Spacer(modifier = Modifier.height(12.dp))

            QuatroCsCard(
                titulo = "Conduta (Conduct):",
                descricao = "Diz respeito às ações pessoalmente danosas, que a criança ou adolescente pode iniciar ou participar...",
                bgColor = Color.White,
                textColor = darkCyan
            )

            Spacer(modifier = Modifier.height(12.dp))

            QuatroCsCard(
                titulo = "Contrato (Contract):",
                descricao = "Relaciona-se aos termos de serviço das plataformas, que muitas vezes expõem crianças e adolescentes e dá atenção ao usuário, sem que este compreenda as contrapartidas impostas.",
                bgColor = Color.White,
                textColor = darkCyan
            )

            Spacer(modifier = Modifier.height(28.dp))

            // Título Principais Riscos
            Text(
                text = "Principais Riscos Psicológicos\ne de Segurança",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = darkCyan,
                textAlign = TextAlign.Center,
                lineHeight = 24.sp
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Lista de Riscos
            RiscoComIcone("Exposição a Conteúdo\nInadequado", yellow, tealGreen)
            Spacer(modifier = Modifier.height(10.dp))

            RiscoComIcone("Cyberbullying", yellow, tealGreen)
            Spacer(modifier = Modifier.height(10.dp))

            RiscoComIcone("Privacidade e Segurança", yellow, tealGreen)
            Spacer(modifier = Modifier.height(10.dp))

            RiscoComIcone("Vício em Tecnologia/Uso\nProblemático", yellow, tealGreen)

            Spacer(modifier = Modifier.height(28.dp))

            // Título Impactos
            Text(
                text = "Impactos na Saúde Física\ne Cognitiva",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = darkCyan,
                textAlign = TextAlign.Center,
                lineHeight = 24.sp
            )

            Spacer(modifier = Modifier.height(20.dp))

            // Impactos Detalhados
            Column(
                modifier = Modifier.fillMaxWidth()
            ) {
                ImpactoDetalhe(
                    titulo = "Atrasos no Desenvolvimento:",
                    descricao = "O uso excessivo de telas na primeira infância (até 6 anos) é um fator de risco para atrasos motores, de linguagem e no desenvolvimento cognitivo.",
                    darkCyan
                )

                Spacer(modifier = Modifier.height(16.dp))

                ImpactoDetalhe(
                    titulo = "Problemas Físicos:",
                    descricao = "O tempo de tela contribui para o sedentarismo e má postura, o que piora hábitos alimentares e causa problemas de visão, como miopia e fadiga visual.",
                    darkCyan
                )

                Spacer(modifier = Modifier.height(16.dp))

                ImpactoDetalhe(
                    titulo = "Prejuízo Cognitivo:",
                    descricao = "Muitos aplicativos promovem um estado de \"multitarefa\" que reduz a atenção entre vários estímulos, enfraquecendo a capacidade de concentração.",
                    darkCyan
                )

                Spacer(modifier = Modifier.height(16.dp))

                ImpactoDetalhe(
                    titulo = "Substituição do Brincar Livre:",
                    descricao = "O tempo gasto em dispositivos digitais substitui o brincar livre, atividade fundamental para o desenvolvimento social e cognitivo em crianças.",
                    darkCyan
                )
            }

            Spacer(modifier = Modifier.height(40.dp))
        }
    }
}

@Composable
fun QuatroCsCard(titulo: String, descricao: String, bgColor: Color, textColor: Color) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(bgColor, RoundedCornerShape(10.dp))
            .padding(14.dp)
    ) {
        Column {
            Text(
                text = titulo,
                fontSize = 13.sp,
                fontWeight = FontWeight.Bold,
                color = textColor,
                lineHeight = 19.sp
            )
            Spacer(modifier = Modifier.height(5.dp))
            Text(
                text = descricao,
                fontSize = 12.sp,
                color = textColor.copy(alpha = 0.85f),
                lineHeight = 17.sp
            )
        }
    }
}

@Composable
fun RiscoComIcone(texto: String, iconColor: Color, bgColor: Color) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(bgColor, RoundedCornerShape(10.dp))
            .padding(horizontal = 16.dp, vertical = 14.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Ícone de alerta
        Box(
            modifier = Modifier
                .size(36.dp)
                .background(iconColor, RoundedCornerShape(6.dp)),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "!",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = bgColor
            )
        }

        Spacer(modifier = Modifier.width(14.dp))

        Text(
            text = texto,
            fontSize = 14.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color.White,
            lineHeight = 19.sp
        )
    }
}

@Composable
fun ImpactoDetalhe(titulo: String, descricao: String, textColor: Color) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = titulo,
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            color = textColor,
            lineHeight = 20.sp
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = descricao,
            fontSize = 13.sp,
            color = textColor.copy(alpha = 0.85f),
            lineHeight = 18.sp
        )
    }
}