package com.example.guardia.screens

import android.content.Context
import android.content.Intent
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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.outlined.ChatBubbleOutline
import androidx.compose.material.icons.outlined.Group
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
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
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.FileProvider
import com.example.guardia.data.relatorios.RelatorioDatabase
import com.example.guardia.data.relatorios.RelatorioEntity
import com.example.guardia.data.relatorios.RelatorioRepository
import java.io.File
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import android.graphics.pdf.PdfDocument
import android.graphics.Paint
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.LaunchedEffect




// ===== THEME =====
@Composable
fun MeusRelatoriosTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = lightColorScheme(
            primary = Color(0xFF537FA8),
            background = Color(0xFFAFDFDF),
            surface = Color.White
        ),
        content = content
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MeusRelatoriosScreen(
    onBackClick: () -> Unit = {},
    onHomeClick: () -> Unit = {}
) {
    val context = LocalContext.current

    // DB e repositório
    val db: RelatorioDatabase = remember(context) {
        RelatorioDatabase.getInstance(context)
    }

    val repo: RelatorioRepository = remember(db) {
        RelatorioRepository(db.relatorioDao())
    }

    // estado com a lista de relatórios
    var relatorios by remember { mutableStateOf<List<RelatorioEntity>>(emptyList()) }

    // carrega do banco quando a tela abre (chama o repository, que já usa Dispatchers.IO)
    LaunchedEffect(Unit) {
        val lista = repo.listarTodos()
        relatorios = lista
    }

    var selectedTab by remember { mutableIntStateOf(2) }

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
                                    .clickable { onBackClick() }
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
                onTabSelected = {
                    selectedTab = it
                    if (it == 2) onHomeClick()
                }
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
            items(count = relatorios.size) { index ->
                val relatorio = relatorios[index]

                RelatorioCardDetailed(
                    relatorio = relatorio,
                    onClick = {
                        gerarECompartilharPdf(context, relatorio)
                    }
                )
            }

            item {
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}





// ===== ICON DO CLIPBOARD =====

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

            drawRoundRect(
                color = Color.White,
                topLeft = Offset(width * 0.3f, height * 0.05f),
                size = androidx.compose.ui.geometry.Size(width * 0.4f, height * 0.12f),
                cornerRadius = androidx.compose.ui.geometry.CornerRadius(4f, 4f)
            )

            drawRoundRect(
                color = Color.White,
                topLeft = Offset(width * 0.15f, height * 0.15f),
                size = androidx.compose.ui.geometry.Size(width * 0.7f, height * 0.75f),
                cornerRadius = androidx.compose.ui.geometry.CornerRadius(6f, 6f)
            )

            val lineStartX = width * 0.25f
            val checkboxSize = 8f

            for (i in 0..2) {
                val lineY = height * (0.3f + i * 0.18f)

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

                drawLine(
                    color = Color(0xFF537FA8),
                    start = Offset(lineStartX + checkboxSize + 8f, lineY),
                    end = Offset(lineStartX + (width * 0.4f), lineY),
                    strokeWidth = 3f,
                    cap = StrokeCap.Round
                )
            }
        }
    }
}

// ===== CARD DO RELATÓRIO =====

@Composable
fun RelatorioCardDetailed(
    relatorio: RelatorioEntity,
    onClick: () -> Unit
) {
    val dataFormatada = remember(relatorio.dataHora) {
        val sdf = SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault())
        sdf.format(Date(relatorio.dataHora))
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(90.dp)
            .clickable { onClick() },
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
                    text = "Relatório Guardiã - $dataFormatada",
                    fontSize = 19.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color(0xFF2B4A6F),
                    letterSpacing = 0.2.sp
                )
                Text(
                    text = "Risco: ${relatorio.risco} • Categoria: ${relatorio.categoria}",
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Normal,
                    color = Color(0xFF6B8299),
                    letterSpacing = 0.sp
                )
            }
        }
    }
}

// ===== BOTTOM BAR =====

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
            BottomNavIconItem(
                icon = Icons.Default.Person,
                selected = selectedTab == 0,
                onClick = { onTabSelected(0) }
            )
            BottomNavIconItem(
                icon = Icons.Default.ChatBubbleOutline,
                selected = selectedTab == 1,
                onClick = { onTabSelected(1) }
            )

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

            BottomNavIconItem(
                icon = Icons.Default.Person,
                selected = selectedTab == 3,
                onClick = { onTabSelected(3) }
            )
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

// ===== GERAÇÃO E COMPARTILHAMENTO DE PDF =====

private fun gerarECompartilharPdf(
    context: Context,
    relatorio: RelatorioEntity
) {
    val file = criarPdfDoRelatorio(context, relatorio)

    val uri = FileProvider.getUriForFile(
        context,
        "${context.packageName}.provider",
        file
    )

    val intent = Intent(Intent.ACTION_SEND).apply {
        type = "application/pdf"
        putExtra(Intent.EXTRA_STREAM, uri)
        addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
    }

    context.startActivity(
        Intent.createChooser(intent, "Compartilhar relatório em PDF")
    )
}

private fun criarPdfDoRelatorio(
    context: Context,
    relatorio: RelatorioEntity
): File {
    val pageWidth = 595         // A4 em pontos (72dpi aprox)
    val pageHeight = 842
    val marginStart = 40f
    val marginEnd = 40f
    val marginTop = 40f
    val marginBottom = 40f
    val lineHeight = 22f

    val pdfDocument = PdfDocument()

    var pageNumber = 1
    var pageInfo = PdfDocument.PageInfo.Builder(pageWidth, pageHeight, pageNumber).create()
    var page = pdfDocument.startPage(pageInfo)
    var canvas = page.canvas

    val paint = Paint().apply {
        isAntiAlias = true
        textSize = 14f
        color = android.graphics.Color.BLACK
    }

    var y = marginTop
    val maxWidth = pageWidth - marginStart - marginEnd

    fun newPage() {
        pdfDocument.finishPage(page)
        pageNumber++
        pageInfo = PdfDocument.PageInfo.Builder(pageWidth, pageHeight, pageNumber).create()
        page = pdfDocument.startPage(pageInfo)
        canvas = page.canvas
        y = marginTop
    }

    fun drawWrapped(text: String, bold: Boolean = false) {
        // linha em branco
        if (text.isEmpty()) {
            y += lineHeight
            if (y > pageHeight - marginBottom) {
                newPage()
            }
            return
        }

        paint.typeface = if (bold) {
            android.graphics.Typeface.create(
                android.graphics.Typeface.DEFAULT,
                android.graphics.Typeface.BOLD
            )
        } else {
            android.graphics.Typeface.DEFAULT
        }

        val words = text.split(" ")
        var line = ""

        for (word in words) {
            val candidate = if (line.isEmpty()) word else "$line $word"
            if (paint.measureText(candidate) > maxWidth) {
                // desenha a linha atual e vai para a próxima
                canvas.drawText(line, marginStart, y, paint)
                y += lineHeight

                if (y > pageHeight - marginBottom) {
                    newPage()
                }

                line = word
            } else {
                line = candidate
            }
        }

        if (line.isNotEmpty()) {
            canvas.drawText(line, marginStart, y, paint)
            y += lineHeight

            if (y > pageHeight - marginBottom) {
                newPage()
            }
        }
    }

    // ---- Conteúdo do relatório ----
    drawWrapped("Relatório Guardiã", bold = true)
    drawWrapped("")

    drawWrapped("Resumo: ${relatorio.resumo}")
    drawWrapped("Categoria: ${relatorio.categoria}")
    drawWrapped("Nível de risco: ${relatorio.risco}")
    drawWrapped("Orientação: ${relatorio.orientacao}")
    drawWrapped("Encaminhamento: ${relatorio.encaminhamento}")
    drawWrapped("")


    // Finaliza a última página
    pdfDocument.finishPage(page)

    val dir = File(context.getExternalFilesDir("reports"), "")
    if (!dir.exists()) dir.mkdirs()

    val fileName = "relatorio_${relatorio.id}.pdf"
    val file = File(dir, fileName)

    pdfDocument.writeTo(file.outputStream())
    pdfDocument.close()

    return file
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewRelatorios() {
    MeusRelatoriosTheme {
        MeusRelatoriosScreen()
    }
}


