package com.example.guardia.screens

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
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
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.foundation.Canvas
import androidx.compose.ui.draw.clip

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
            primary = Color(0xFF3B7DAC),
            background = Color(0xFFAFDFDF)
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
            Column {
                TopAppBar(
                    title = {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Icon(
                                imageVector = Icons.Default.ArrowBack,
                                contentDescription = "Voltar",
                                tint = Color(0xFF1E3A5F),
                                modifier = Modifier
                                    .size(28.dp)
                                    .clickable { /* Ação de voltar */ }
                            )
                            Spacer(modifier = Modifier.width(12.dp))
                            Text(
                                text = "Meus ",
                                fontSize = 30.sp,
                                fontWeight = FontWeight.Normal,
                                color = Color(0xFF1E3A5F)
                            )
                            Text(
                                text = "Relatórios",
                                fontSize = 30.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color(0xFF1E3A5F)
                            )
                            Spacer(modifier = Modifier.weight(1f))
                            ClipboardIcon()
                        }
                    },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = Color(0xFFAFDFDF)
                    ),
                    modifier = Modifier.height(72.dp)
                )
                // Linha divisória
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(1.dp)
                        .background(Color(0xFF90C4C4))
                )
            }
        },
        bottomBar = {
            BottomNavigationBar(
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
                .padding(horizontal = 20.dp, vertical = 16.dp),
            verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            items(relatorios) { relatorio ->
                RelatorioCard(relatorio)
            }
            item {
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }
}

@Composable
fun ClipboardIcon() {
    Box(
        modifier = Modifier
            .size(56.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(Color(0xFF3B7DAC)),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.padding(10.dp)
        ) {
            // Clip superior da prancheta
            Box(
                modifier = Modifier
                    .width(18.dp)
                    .height(5.dp)
                    .clip(RoundedCornerShape(topStart = 3.dp, topEnd = 3.dp))
                    .background(Color.White)
            )
            // Corpo da prancheta com linhas
            Box(
                modifier = Modifier
                    .width(30.dp)
                    .height(32.dp)
                    .clip(RoundedCornerShape(3.dp))
                    .background(Color.White)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(start = 6.dp, top = 6.dp, end = 6.dp, bottom = 4.dp),
                    verticalArrangement = Arrangement.spacedBy(3.dp)
                ) {
                    // 3 linhas de checklist
                    repeat(3) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(3.dp)
                        ) {
                            // Checkbox
                            Box(
                                modifier = Modifier
                                    .size(4.dp)
                                    .background(Color(0xFF3B7DAC))
                            )
                            // Linha
                            Box(
                                modifier = Modifier
                                    .width(14.dp)
                                    .height(2.dp)
                                    .background(Color(0xFF3B7DAC))
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun RelatorioCard(relatorio: Relatorio) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(85.dp)
            .clickable { /* Ação ao clicar */ },
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFFD1ECEC)
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp, vertical = 16.dp),
            contentAlignment = Alignment.CenterStart
        ) {
            Column(
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "${relatorio.titulo} - ${relatorio.data}",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Normal,
                    color = Color(0xFF1E3A5F),
                    letterSpacing = 0.sp
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = relatorio.tamanho,
                    fontSize = 14.sp,
                    color = Color(0xFF6B7D8B),
                    fontWeight = FontWeight.Normal,
                    letterSpacing = 0.sp
                )
            }
        }
    }
}

@Composable
fun BottomNavigationBar(
    selectedTab: Int,
    onTabSelected: (Int) -> Unit
) {
    Surface(
        color = Color(0xFFE8F5F5),
        modifier = Modifier.height(70.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Perfil
            BottomNavItem(
                icon = Icons.Default.Person,
                selected = selectedTab == 0,
                onClick = { onTabSelected(0) }
            )

            // Mensagens
            BottomNavItem(
                icon = Icons.Default.ChatBubbleOutline,
                selected = selectedTab == 1,
                onClick = { onTabSelected(1) }
            )

            // Home (com círculo azul)
            Box(
                modifier = Modifier
                    .size(56.dp)
                    .clip(CircleShape)
                    .background(Color(0xFF3B7DAC))
                    .clickable { onTabSelected(2) },
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.Home,
                    contentDescription = "Home",
                    tint = Color.White,
                    modifier = Modifier.size(30.dp)
                )
            }

            // Comunidade
            BottomNavItem(
                icon = Icons.Default.Person,
                selected = selectedTab == 3,
                onClick = { onTabSelected(3) }
            )

            // Configurações
            BottomNavItem(
                icon = Icons.Default.Settings,
                selected = selectedTab == 4,
                onClick = { onTabSelected(4) }
            )
        }
    }
}

@Composable
fun BottomNavItem(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    selected: Boolean,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .size(48.dp)
            .clickable(onClick = onClick),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = Color(0xFF3B7DAC),
            modifier = Modifier.size(28.dp)
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