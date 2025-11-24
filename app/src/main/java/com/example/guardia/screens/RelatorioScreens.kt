package com.example.guardia.screens

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ChatBubbleOutline
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MeusRelatoriosTheme {
                MeusRelatoriosScreen()
            }
        }
    }
}

@Composable
fun MeusRelatoriosTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = lightColorScheme(
            primary = Color(0xFF537FA8),
            background = Color(0xFFAFDFDF),
            surface = Color(0xFFFFFFFF)
        ),
        content = content
    )
}

data class Relatorio(
    val titulo: String,
    val data: String,
    val tamanho: String
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MeusRelatoriosScreen() {
    var selectedTab by remember { mutableIntStateOf(2) }

    val relatorios = remember {
        listOf(
            Relatorio("Relatório Guardiã", "24/10/2025", "2,24 MB | PDF"),
            Relatorio("Relatório Guardiã", "10/10/2025", "3,05 MB | PDF"),
            Relatorio("Relatório Guardiã", "13/09/2025", "2,21 MB | PDF"),
            Relatorio("Relatório Guardiã", "25/08/2025", "2,88 MB | PDF"),
            Relatorio("Relatório Guardiã", "01/07/2025", "3,26 MB | PDF"),
            Relatorio("Relatório Guardiã", "01/07/2025", "3,26 MB | PDF"),
            Relatorio("Relatório Guardiã", "28/06/2025", "3,16 MB | PDF")
        )
    }

    Scaffold(
        topBar = {
            Column(
                modifier = Modifier.background(Color(0xFFAFDFDF))
            ) {
                TopAppBar(
                    title = {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Icon(
                                imageVector = Icons.Default.ArrowBack,
                                contentDescription = "Voltar",
                                tint = Color(0xFF2B4A6F),
                                modifier = Modifier
                                    .size(32.dp)
                                    .clickable { /* Voltar */ }
                            )
                            Spacer(modifier = Modifier.width(16.dp))
                            Text(
                                text = "Meus ",
                                fontSize = 32.sp,
                                fontWeight = FontWeight.Normal,
                                color = Color(0xFF2B4A6F),
                                letterSpacing = 0.sp
                            )
                            Text(
                                text = "Relatórios",
                                fontSize = 32.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color(0xFF2B4A6F),
                                letterSpacing = 0.sp
                            )
                            Spacer(modifier = Modifier.weight(1f))
                            ClipboardIconDetailed()
                        }
                    },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = Color(0xFFAFDFDF)
                    ),
                    modifier = Modifier.height(80.dp)
                )
                Divider(
                    color = Color(0xFF8FCBCB),
                    thickness = 1.dp
                )
            }
        },
        bottomBar = {
            BottomNavigationBarCustom(
                selectedTab = selectedTab,
                onTabSelected = { selectedTab = it }
            )
        },
        containerColor = Color(0xFFAFDFDF)
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 24.dp, vertical = 20.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(relatorios) { relatorio ->
                RelatorioCardDetailed(relatorio)
            }
            item {
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}

@Composable
fun ClipboardIconDetailed() {
    Box(
        modifier = Modifier
            .size(64.dp)
            .shadow(4.dp, RoundedCornerShape(10.dp))
            .clip(RoundedCornerShape(10.dp))
            .background(Color(0xFF537FA8)),
        contentAlignment = Alignment.Center
    ) {
        Canvas(modifier = Modifier.size(40.dp)) {
            val width = size.width
            val height = size.height

            // Clip superior (prendedor)
            drawRoundRect(
                color = Color.White,
                topLeft = Offset(width * 0.3f, height * 0.05f),
                size = androidx.compose.ui.geometry.Size(width * 0.4f, height * 0.12f),
                cornerRadius = androidx.compose.ui.geometry.CornerRadius(4f, 4f)
            )

            // Corpo da prancheta
            drawRoundRect(
                color = Color.White,
                topLeft = Offset(width * 0.15f, height * 0.15f),
                size = androidx.compose.ui.geometry.Size(width * 0.7f, height * 0.75f),
                cornerRadius = androidx.compose.ui.geometry.CornerRadius(6f, 6f)
            )

            // Linhas de checklist (3 linhas)
            val lineStartX = width * 0.25f
            val lineEndX = width * 0.75f
            val checkboxSize = 8f

            for (i in 0..2) {
                val lineY = height * (0.3f + i * 0.18f)

                // Checkbox (check mark)
                drawLine(
                    color = Color(0xFF537FA8),
                    start = Offset(lineStartX, lineY),
                    end = Offset(lineStartX + checkboxSize * 0.4f, lineY + checkboxSize * 0.4f),
                    strokeWidth = 3f,
                    cap = StrokeCap.Round
                )
                drawLine(
                    color = Color(0xFF537FA8),
                    start = Offset(lineStartX + checkboxSize * 0.4f, lineY + checkboxSize * 0.4f),
                    end = Offset(lineStartX + checkboxSize, lineY - checkboxSize * 0.2f),
                    strokeWidth = 3f,
                    cap = StrokeCap.Round
                )

                // Linha de texto
                drawLine(
                    color = Color(0xFF537FA8),
                    start = Offset(lineStartX + checkboxSize + 8f, lineY),
                    end = Offset(lineEndX, lineY),
                    strokeWidth = 3f,
                    cap = StrokeCap.Round
                )
            }
        }
    }
}

@Composable
fun RelatorioCardDetailed(relatorio: Relatorio) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(90.dp)
            .clickable { /* Abrir relatório */ },
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFFD1ECEC)
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 2.dp
        )
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 28.dp, vertical = 18.dp),
            contentAlignment = Alignment.CenterStart
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                Text(
                    text = "${relatorio.titulo} - ${relatorio.data}",
                    fontSize = 19.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color(0xFF2B4A6F),
                    letterSpacing = 0.2.sp
                )
                Text(
                    text = relatorio.tamanho,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Normal,
                    color = Color(0xFF6B8299),
                    letterSpacing = 0.sp
                )
            }
        }
    }
}

@Composable
fun BottomNavigationBarCustom(
    selectedTab: Int,
    onTabSelected: (Int) -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(75.dp)
    ) {
        // Barra de navegação com elevação
        Box(
            modifier = Modifier
                .fillMaxSize()
                .shadow(12.dp, RoundedCornerShape(topStart = 0.dp, topEnd = 0.dp))
                .background(Color(0xFFE8F5F5))
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.Center)
                .padding(horizontal = 20.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Perfil
            BottomNavIconItem(
                icon = Icons.Default.Person,
                selected = selectedTab == 0,
                onClick = { onTabSelected(0) }
            )

            // Mensagens
            BottomNavIconItem(
                icon = Icons.Default.ChatBubbleOutline,
                selected = selectedTab == 1,
                onClick = { onTabSelected(1) }
            )

            // Home - Botão destacado
            Box(
                modifier = Modifier
                    .size(62.dp)
                    .shadow(8.dp, CircleShape)
                    .clip(CircleShape)
                    .background(Color(0xFF537FA8))
                    .clickable { onTabSelected(2) },
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.Home,
                    contentDescription = "Home",
                    tint = Color.White,
                    modifier = Modifier.size(32.dp)
                )
            }

            // Comunidade
            BottomNavIconItem(
                icon = Icons.Default.Person,
                selected = selectedTab == 3,
                onClick = { onTabSelected(3) }
            )

            // Configurações
            BottomNavIconItem(
                icon = Icons.Default.Settings,
                selected = selectedTab == 4,
                onClick = { onTabSelected(4) }
            )
        }
    }
}

@Composable
fun BottomNavIconItem(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    selected: Boolean,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .size(52.dp)
            .clickable(onClick = onClick),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = Color(0xFF537FA8),
            modifier = Modifier.size(30.dp)
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun MeusRelatoriosPreview() {
    MeusRelatoriosTheme {
        MeusRelatoriosScreen()
    }
}