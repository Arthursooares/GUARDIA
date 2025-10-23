package com.example.guardia

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import com.example.guardia.ui.theme.GuardiaScreen   // testa direto o chat
// import com.example.guardia.ui.AppNav   // manter comentado por enquanto

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme(colorScheme = lightColorScheme()) {
                // 🔹 Testando diretamente a Guardiã
                GuardiaScreen()
            }
        }
    }
}
