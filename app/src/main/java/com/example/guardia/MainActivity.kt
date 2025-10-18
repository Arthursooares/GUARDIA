package com.example.guardia

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import com.example.guardia.ui.theme.GuardiaScreen   // importa a tela do chat

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme(colorScheme = lightColorScheme()) {

                // 🔹 COMENTADO TEMPORARIAMENTE
                /*
                AppNav(
                    onDoLogin = { email, pass ->
                        // TODO: autenticar usuário
                    },
                    onDoRegister = { name, email, pass ->
                        // TODO: criar conta / enviar para API ou Firebase
                    }
                )
                */

                // 🔹 TESTANDO DIRETAMENTE A GUARIDÃ
                GuardiaScreen()
            }
        }
    }
}
