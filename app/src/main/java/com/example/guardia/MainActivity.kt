package com.example.guardia

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import com.example.guardia.ui.AppNav   // importa o NavHost com Login e Register

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme(colorScheme = lightColorScheme()) {
                AppNav(
                    onDoLogin = { email, pass ->
                        // TODO: autenticar usuário
                    },
                    onDoRegister = { name, email, pass ->
                        // TODO: criar conta / enviar para API ou Firebase
                    }
                )
            }
        }
    }
}
