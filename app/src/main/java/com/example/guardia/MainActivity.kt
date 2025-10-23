package com.example.guardia

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
<<<<<<< HEAD
import com.example.guardia.ui.theme.GuardiaScreen   // importa a tela do chat
=======
import com.example.guardia.ui.AppNav   // importa o NavHost com Login e Register
>>>>>>> 369c7e1d1b7980f55bac5ef8ca21f212f1e83c4b

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme(colorScheme = lightColorScheme()) {
<<<<<<< HEAD

                // 🔹 COMENTADO TEMPORARIAMENTE
                /*
=======
>>>>>>> 369c7e1d1b7980f55bac5ef8ca21f212f1e83c4b
                AppNav(
                    onDoLogin = { email, pass ->
                        // TODO: autenticar usuário
                    },
                    onDoRegister = { name, email, pass ->
                        // TODO: criar conta / enviar para API ou Firebase
                    }
                )
<<<<<<< HEAD
                */

                // 🔹 TESTANDO DIRETAMENTE A GUARIDÃ
                GuardiaScreen()
=======
>>>>>>> 369c7e1d1b7980f55bac5ef8ca21f212f1e83c4b
            }
        }
    }
}
