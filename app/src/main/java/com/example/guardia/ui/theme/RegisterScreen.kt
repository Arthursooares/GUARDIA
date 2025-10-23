<<<<<<< HEAD
@file:Suppress("unused")

package com.example.guardia.ui


=======

@file:Suppress("unused")
package com.example.guardia.ui



>>>>>>> 369c7e1d1b7980f55bac5ef8ca21f212f1e83c4b
import android.util.Patterns
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Email
import androidx.compose.material.icons.rounded.Lock
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material.icons.rounded.Security
import androidx.compose.material.icons.rounded.Star
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.rememberCoroutineScope
import kotlinx.coroutines.launch
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
<<<<<<< HEAD
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
=======
>>>>>>> 369c7e1d1b7980f55bac5ef8ca21f212f1e83c4b

/**
 * Tela de Cadastro inspirada no layout do Guardiã (imagem enviada).
 * - Jetpack Compose + Material 3
 * - Gradiente teal/green e "onda" no topo
 * - Campos: Nome, Email, Senha, Confirmar senha
 * - Validação simples e botão habilita só quando tudo ok
 * - CTA para ir ao Login
 */

@Composable
fun RegisterScreen(
    modifier: Modifier = Modifier,
    onRegister: (name: String, email: String, password: String) -> Unit = { _, _, _ -> },
    onLoginClick: () -> Unit = {}
) {
    val focus = LocalFocusManager.current
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    var name by rememberSaveable { mutableStateOf("") }
    var email by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    var confirm by rememberSaveable { mutableStateOf("") }
    var showPassword by rememberSaveable { mutableStateOf(false) }
    var showPassword2 by rememberSaveable { mutableStateOf(false) }
    var acceptedTerms by rememberSaveable { mutableStateOf(true) }

    val emailValid = remember(email) { Patterns.EMAIL_ADDRESS.matcher(email).matches() }
    val passValid = remember(password) { password.length >= 8 }
    val passMatch = remember(password, confirm) { password.isNotEmpty() && password == confirm }
    val nameValid = remember(name) { name.trim().length >= 2 }

    val formValid = nameValid && emailValid && passValid && passMatch && acceptedTerms

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) },
        containerColor = Color.Transparent,
        topBar = { /* sem appbar para seguir o visual do layout */ }
    ) { padding ->
        Box(
            modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        listOf(Color(0xFF0C7468), Color(0xFF1FA36E))
                    )
                )
                .padding(padding)
        ) {
            // Fundo superior com "onda"
            TopWave()

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(Modifier.height(32.dp))
                LogoBadge()
                Spacer(Modifier.height(8.dp))
                Text(
                    text = "Guardiã",
                    style = MaterialTheme.typography.headlineMedium.copy(
                        color = Color(0xFF102147),
                        fontWeight = FontWeight.SemiBold,
                        letterSpacing = 0.sp
                    ),
                )
                Text(
                    text = "Cadastro",
                    style = MaterialTheme.typography.titleLarge.copy(color = Color(0xFF2C4A8A)),
                )

                Spacer(Modifier.height(24.dp))

                // Card com campos
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(containerColor = Color.White.copy(alpha = 0.92f)),
                    shape = RoundedCornerShape(24.dp),
                    elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
                ) {
                    Column(Modifier.padding(20.dp)) {
                        AppTextField(
                            value = name,
                            onValueChange = { name = it },
                            label = "Nome",
                            leading = { Icon(Icons.Rounded.Person, contentDescription = null) },
                            imeAction = ImeAction.Next,
                            onImeAction = { focus.moveFocus(FocusDirection.Down) },
                        )
                        Spacer(Modifier.height(12.dp))
                        AppTextField(
                            value = email,
                            onValueChange = { email = it },
                            label = "Email",
                            leading = { Icon(Icons.Rounded.Email, contentDescription = null) },
                            imeAction = ImeAction.Next,
                            onImeAction = { focus.moveFocus(FocusDirection.Down) },
                            isError = email.isNotBlank() && !emailValid,
                            supportingText = if (email.isNotBlank() && !emailValid) "Email inválido" else null
                        )
                        Spacer(Modifier.height(12.dp))
                        AppTextField(
                            value = password,
                            onValueChange = { password = it },
                            label = "Senha (mín. 8)",
                            leading = { Icon(Icons.Rounded.Lock, contentDescription = null) },
                            trailing = {
                                TextButton(onClick = { showPassword = !showPassword }) {
                                    Text(if (showPassword) "Ocultar" else "Mostrar")
                                }
                            },
                            visualTransformation = if (showPassword) VisualTransformation.None else PasswordVisualTransformation(),
                            imeAction = ImeAction.Next,
                            onImeAction = { focus.moveFocus(FocusDirection.Down) },
                            isError = password.isNotEmpty() && !passValid,
                            supportingText = if (password.isNotEmpty() && !passValid) "Use ao menos 8 caracteres" else null
                        )
                        Spacer(Modifier.height(12.dp))
                        AppTextField(
                            value = confirm,
                            onValueChange = { confirm = it },
                            label = "Confirmar senha",
                            leading = { Icon(Icons.Rounded.Lock, contentDescription = null) },
                            trailing = {
                                TextButton(onClick = { showPassword2 = !showPassword2 }) {
                                    Text(if (showPassword2) "Ocultar" else "Mostrar")
                                }
                            },
                            visualTransformation = if (showPassword2) VisualTransformation.None else PasswordVisualTransformation(),
                            imeAction = ImeAction.Done,
                            onImeAction = { focus.clearFocus() },
                            isError = confirm.isNotEmpty() && !passMatch,
                            supportingText = if (confirm.isNotEmpty() && !passMatch) "As senhas não coincidem" else null
                        )

                        Spacer(Modifier.height(6.dp))
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Checkbox(checked = acceptedTerms, onCheckedChange = { acceptedTerms = it })
                            Text(
                                "Aceito os termos de uso e privacidade",
                                modifier = Modifier.padding(start = 8.dp)
                            )
                        }

                        Spacer(Modifier.height(8.dp))
                        GradientButton(
                            text = "Criar conta",
                            enabled = formValid,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            if (formValid) {
                                onRegister(name.trim(), email.trim(), password)
                            } else {
                                val msg = when {
                                    !nameValid -> "Informe seu nome"
                                    !emailValid -> "Verifique o email"
                                    !passValid -> "A senha precisa ter 8+ caracteres"
                                    !passMatch -> "As senhas não coincidem"
                                    !acceptedTerms -> "É preciso aceitar os termos"
                                    else -> "Complete os campos"
                                }
                                scope.launch { snackbarHostState.showSnackbar(msg) }
                            }
                        }

                        Spacer(Modifier.height(8.dp))
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.Center
                        ) {
                            Text("Já tem conta? ")
                            Text(
                                "Entrar",
                                color = Color(0xFF123FA0),
                                fontWeight = FontWeight.SemiBold,
                                modifier = Modifier.clickable { onLoginClick() }
                            )
                        }
                    }
                }

                Spacer(Modifier.weight(1f))
            }
        }
    }
}

@Composable
private fun TopWave(modifier: Modifier = Modifier) {
    Box(modifier.fillMaxWidth().height(220.dp)) {
        Canvas(Modifier.fillMaxSize()) {
            val w = size.width
            val h = size.height
            val path = Path().apply {
                moveTo(0f, h * 0.75f)
                cubicTo(
                    w * 0.25f, h * 0.60f,
                    w * 0.75f, h * 0.90f,
                    w, h * 0.70f
                )
                lineTo(w, 0f)
                lineTo(0f, 0f)
                close()
            }
            drawPath(path = path, brush = SolidColor(Color(0xFFF7F9FF)))
        }
    }
}

@Composable
private fun LogoBadge() {
    Box(
        modifier = Modifier
            .size(96.dp)
            .clip(CircleShape)
            .background(Color(0xFFF7F9FF)),
        contentAlignment = Alignment.Center
    ) {
        Row(verticalAlignment = Alignment.Bottom) {
            Icon(
                imageVector = Icons.Rounded.Security,
                contentDescription = null,
                tint = Color(0xFF0C7468),
                modifier = Modifier.size(42.dp)
            )
            Icon(
                imageVector = Icons.Rounded.Star,
                contentDescription = null,
                tint = Color(0xFFFFB703),
                modifier = Modifier
                    .offset(x = (-6).dp, y = (-10).dp)
                    .size(18.dp)
            )
        }
    }
}

@Composable
private fun AppTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    leading: @Composable (() -> Unit)? = null,
    trailing: @Composable (() -> Unit)? = null,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    isError: Boolean = false,
    supportingText: String? = null,
    imeAction: ImeAction = ImeAction.Next,
    onImeAction: () -> Unit = {}
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = Modifier
            .fillMaxWidth()
            .semantics { contentDescription = label },
        singleLine = true,
        label = { Text(label) },
        leadingIcon = leading,
        trailingIcon = trailing,
        visualTransformation = visualTransformation,
        isError = isError,
        supportingText = { if (supportingText != null) Text(supportingText) },
        shape = RoundedCornerShape(24.dp),
        keyboardOptions = KeyboardOptions(imeAction = imeAction),
        keyboardActions = KeyboardActions(
            onNext = { onImeAction() },
            onDone = { onImeAction() }
        )
    )
}

@Composable
private fun GradientButton(
    text: String,
    enabled: Boolean,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    val enabledBrush = Brush.horizontalGradient(listOf(Color(0xFF0E2E7A), Color(0xFF071B43)))
    val disabledBrush = SolidColor(Color(0xFF8797C5).copy(alpha = 0.4f))

    Button(
        onClick = onClick,
        enabled = enabled,
        modifier = modifier.height(54.dp),
        shape = RoundedCornerShape(26.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Transparent,
            disabledContainerColor = Color.Transparent,
            contentColor = Color.White,
            disabledContentColor = Color.White
        ),
        contentPadding = PaddingValues()
    ) {
        Box(
            Modifier
                .fillMaxSize()
                .background(brush = if (enabled) enabledBrush else disabledBrush, shape = RoundedCornerShape(26.dp))
                .padding(horizontal = 24.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(text = text, style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.SemiBold)
        }
    }
}

// -------- PREVIEWS ---------
@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
private fun RegisterScreenPreview() {
    MaterialTheme(colorScheme = lightColorScheme()) {
        RegisterScreen()
    }
}

@Preview(showBackground = true, backgroundColor = 0xFF000000)
@Composable
private fun RegisterScreenDarkPreview() {
    MaterialTheme(colorScheme = darkColorScheme()) {
        RegisterScreen()
    }
}


// ====== LOGIN SCREEN ======
@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
    onLogin: (email: String, password: String) -> Unit = { _, _ -> },
    onRegisterClick: () -> Unit = {},
    onForgotPassword: (email: String) -> Unit = {}
) {
    val focus = LocalFocusManager.current
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    var email by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    var showPassword by rememberSaveable { mutableStateOf(false) }
    var rememberMe by rememberSaveable { mutableStateOf(true) }

    val emailValid = remember(email) { email.isNotBlank() && Patterns.EMAIL_ADDRESS.matcher(email).matches() }
    val passValid = remember(password) { password.length >= 6 }
    val formValid = emailValid && passValid

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) },
        containerColor = Color.Transparent
    ) { padding ->
        Box(
            modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(listOf(Color(0xFF0C7468), Color(0xFF1FA36E)))
                )
                .padding(padding)
        ) {
            TopWave()
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(Modifier.height(32.dp))
                LogoBadge()
                Spacer(Modifier.height(8.dp))
                Text(
                    text = "Guardiã",
                    style = MaterialTheme.typography.headlineMedium.copy(
                        color = Color(0xFF102147), fontWeight = FontWeight.SemiBold
                    )
                )
                Text(
                    text = "Login",
                    style = MaterialTheme.typography.titleLarge.copy(color = Color(0xFF2C4A8A))
                )

                Spacer(Modifier.height(24.dp))
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(containerColor = Color.White.copy(alpha = 0.92f)),
                    shape = RoundedCornerShape(24.dp),
                    elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
                ) {
                    Column(Modifier.padding(20.dp)) {
                        AppTextField(
                            value = email,
                            onValueChange = { email = it },
                            label = "Email",
                            leading = { Icon(Icons.Rounded.Email, contentDescription = null) },
                            imeAction = ImeAction.Next,
                            onImeAction = { focus.moveFocus(FocusDirection.Down) },
                            isError = email.isNotBlank() && !emailValid,
                            supportingText = if (email.isNotBlank() && !emailValid) "Email inválido" else null
                        )
                        Spacer(Modifier.height(12.dp))
                        AppTextField(
                            value = password,
                            onValueChange = { password = it },
                            label = "Senha",
                            leading = { Icon(Icons.Rounded.Lock, contentDescription = null) },
                            trailing = {
                                TextButton(onClick = { showPassword = !showPassword }) {
                                    Text(if (showPassword) "Ocultar" else "Mostrar")
                                }
                            },
                            visualTransformation = if (showPassword) VisualTransformation.None else PasswordVisualTransformation(),
                            imeAction = ImeAction.Done,
                            onImeAction = { focus.clearFocus() },
                            isError = password.isNotEmpty() && !passValid,
                            supportingText = if (password.isNotEmpty() && !passValid) "Mínimo 6 caracteres" else null
                        )

                        Spacer(Modifier.height(6.dp))
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Checkbox(checked = rememberMe, onCheckedChange = { rememberMe = it })
                                Text("Manter conectado")
                            }
                            Text(
                                "Esqueci a senha",
                                color = Color(0xFF123FA0),
                                fontWeight = FontWeight.SemiBold,
                                modifier = Modifier.clickable {
                                    if (emailValid) onForgotPassword(email) else scope.launch {
                                        snackbarHostState.showSnackbar("Informe um email válido")
                                    }
                                }
                            )
                        }

                        Spacer(Modifier.height(8.dp))
                        GradientButton(
                            text = "Entrar",
                            enabled = formValid,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            if (formValid) {
                                onLogin(email.trim(), password)
                            } else {
                                val msg = when {
                                    !emailValid -> "Verifique o email"
                                    !passValid -> "Senha muito curta"
                                    else -> "Complete os campos"
                                }
                                scope.launch { snackbarHostState.showSnackbar(msg) }
                            }
                        }

                        Spacer(Modifier.height(8.dp))
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.Center
                        ) {
                            Text("Não tem conta? ")
                            Text(
                                "Cadastrar",
                                color = Color(0xFF123FA0),
                                fontWeight = FontWeight.SemiBold,
                                modifier = Modifier.clickable { onRegisterClick() }
                            )
                        }
                    }
                }

                Spacer(Modifier.weight(1f))
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
private fun LoginScreenPreview() {
    MaterialTheme(colorScheme = lightColorScheme()) {
        LoginScreen()
    }
}


<<<<<<< HEAD
=======


>>>>>>> 369c7e1d1b7980f55bac5ef8ca21f212f1e83c4b
sealed class AppRoute(val route: String) {
    data object Login : AppRoute("login")
    data object Register : AppRoute("register")
}

@Composable
fun AppNav(
    startDestination: String = AppRoute.Login.route,
    onDoLogin: (email: String, pass: String) -> Unit = { _, _ -> },
    onDoRegister: (name: String, email: String, pass: String) -> Unit = { _, _, _ -> }
) {
    val nav = rememberNavController()

    NavHost(navController = nav, startDestination = startDestination) {
        composable(AppRoute.Login.route) {
            LoginScreen(
                onLogin = { email, pass -> onDoLogin(email, pass) },
                onRegisterClick = { nav.navigate(AppRoute.Register.route) },
                onForgotPassword = { /* TODO: recuperar senha */ }
            )
        }
        composable(AppRoute.Register.route) {
            RegisterScreen(
                onRegister = { name, email, pass -> onDoRegister(name, email, pass) },
                onLoginClick = {
                    // volta pro login; se quiser evitar duplicar, use popBackStack
                    nav.popBackStack()
                }
            )
        }
    }
}

<<<<<<< HEAD

// --- Validators ---
object Validators {
    fun name(name: String): String? = when {
        name.isBlank() -> "Informe seu nome"
        name.trim().length < 2 -> "Nome muito curto"
        else -> null
    }

    fun email(email: String): String? = when {
        email.isBlank() -> "Informe um email"
        !Patterns.EMAIL_ADDRESS.matcher(email).matches() -> "Email inválido"
        else -> null
    }

    fun password(pass: String): String? {
        if (pass.length < 8) return "Mínimo 8 caracteres"
        val hasLetter = pass.any { it.isLetter() }
        val hasDigit = pass.any { it.isDigit() }
        return if (!hasLetter || !hasDigit) "Use letras e números" else null
    }

    fun confirm(pass: String, confirm: String): String? =
        if (confirm != pass) "As senhas não coincidem" else null
}

// --- Repository de autenticação (abstração) ---
interface AuthRepository {
    suspend fun register(name: String, email: String, password: String): Result<Unit>
}

// Implementação de exemplo (stub). Troque por Firebase / API real.
class AuthRepositoryStub : AuthRepository {
    override suspend fun register(name: String, email: String, password: String): Result<Unit> {
        // Simula um atraso de rede e sucesso
        return Result.success(Unit)
    }
}

// --- UI State ---
data class RegisterFormState(
    val name: String = "",
    val email: String = "",
    val password: String = "",
    val confirm: String = "",
    val acceptedTerms: Boolean = true,
    val nameError: String? = null,
    val emailError: String? = null,
    val passError: String? = null,
    val confirmError: String? = null,
    val isSubmitting: Boolean = false,
    val submitError: String? = null,
    val submitSuccess: Boolean = false
) {
    val formValid: Boolean =
        nameError == null && emailError == null && passError == null && confirmError == null &&
                name.isNotBlank() && email.isNotBlank() && password.isNotBlank() && confirm.isNotBlank() && acceptedTerms
}

// --- ViewModel ---
class RegisterViewModel(private val repo: AuthRepository) : ViewModel() {
    private val _ui = MutableStateFlow(RegisterFormState())
    val ui: StateFlow<RegisterFormState> = _ui.asStateFlow()

    fun onNameChange(v: String) = _ui.update { it.copy(name = v, nameError = Validators.name(v)) }
    fun onEmailChange(v: String) = _ui.update { it.copy(email = v, emailError = Validators.email(v)) }
    fun onPassChange(v: String) = _ui.update { it.copy(password = v, passError = Validators.password(v), confirmError = Validators.confirm(v, it.confirm)) }
    fun onConfirmChange(v: String) = _ui.update { it.copy(confirm = v, confirmError = Validators.confirm(it.password, v)) }
    fun onTermsToggle(v: Boolean) = _ui.update { it.copy(acceptedTerms = v) }

    fun submit(onSuccess: () -> Unit) {
        val cur = _ui.value
        val nameErr = Validators.name(cur.name)
        val emailErr = Validators.email(cur.email)
        val passErr = Validators.password(cur.password)
        val confirmErr = Validators.confirm(cur.password, cur.confirm)
        _ui.update { it.copy(nameError = nameErr, emailError = emailErr, passError = passErr, confirmError = confirmErr) }
        val valid = listOf(nameErr, emailErr, passErr, confirmErr).all { it == null } && cur.acceptedTerms
        if (!valid) return

        viewModelScope.launch {
            _ui.update { it.copy(isSubmitting = true, submitError = null) }
            val result = repo.register(cur.name.trim(), cur.email.trim(), cur.password)
            result.fold(
                onSuccess = {
                    _ui.update { it.copy(isSubmitting = false, submitSuccess = true) }
                    onSuccess()
                },
                onFailure = { e ->
                    _ui.update { it.copy(isSubmitting = false, submitError = e.message ?: "Falha ao cadastrar") }
                }
            )
        }
    }
}


// --- Versão da RegisterScreen usando o ViewModel ---
@Composable
fun RegisterScreenWithVM(
    viewModel: RegisterViewModel = RegisterViewModel(AuthRepositoryStub()),
    onRegistered: () -> Unit = {},
    onLoginClick: () -> Unit = {}
) {
    val ui = viewModel.ui.collectAsState().value
    val focus = LocalFocusManager.current
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) },
        containerColor = Color.Transparent
    ) { padding ->
        Box(
            Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(listOf(Color(0xFF0C7468), Color(0xFF1FA36E)))
                )
                .padding(padding)
        ) {
            TopWave()
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(Modifier.height(32.dp))
                LogoBadge()
                Spacer(Modifier.height(8.dp))
                Text("Guardiã", style = MaterialTheme.typography.headlineMedium.copy(color = Color(0xFF102147), fontWeight = FontWeight.SemiBold))
                Text("Cadastro", style = MaterialTheme.typography.titleLarge.copy(color = Color(0xFF2C4A8A)))
                Spacer(Modifier.height(24.dp))

                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(containerColor = Color.White.copy(alpha = 0.92f)),
                    shape = RoundedCornerShape(24.dp),
                    elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
                ) {
                    Column(Modifier.padding(20.dp)) {
                        AppTextField(
                            value = ui.name,
                            onValueChange = viewModel::onNameChange,
                            label = "Nome",
                            leading = { Icon(Icons.Rounded.Person, null) },
                            imeAction = ImeAction.Next,
                            onImeAction = { focus.moveFocus(FocusDirection.Down) },
                            isError = ui.nameError != null,
                            supportingText = ui.nameError
                        )
                        Spacer(Modifier.height(12.dp))
                        AppTextField(
                            value = ui.email,
                            onValueChange = viewModel::onEmailChange,
                            label = "Email",
                            leading = { Icon(Icons.Rounded.Email, null) },
                            imeAction = ImeAction.Next,
                            onImeAction = { focus.moveFocus(FocusDirection.Down) },
                            isError = ui.emailError != null,
                            supportingText = ui.emailError
                        )
                        Spacer(Modifier.height(12.dp))
                        var showPass by rememberSaveable { mutableStateOf(false) }
                        AppTextField(
                            value = ui.password,
                            onValueChange = viewModel::onPassChange,
                            label = "Senha (mín. 8)",
                            leading = { Icon(Icons.Rounded.Lock, null) },
                            trailing = { TextButton(onClick = { showPass = !showPass }) { Text(if (showPass) "Ocultar" else "Mostrar") } },
                            visualTransformation = if (showPass) VisualTransformation.None else PasswordVisualTransformation(),
                            imeAction = ImeAction.Next,
                            onImeAction = { focus.moveFocus(FocusDirection.Down) },
                            isError = ui.passError != null,
                            supportingText = ui.passError
                        )
                        Spacer(Modifier.height(12.dp))
                        var showPass2 by rememberSaveable { mutableStateOf(false) }
                        AppTextField(
                            value = ui.confirm,
                            onValueChange = viewModel::onConfirmChange,
                            label = "Confirmar senha",
                            leading = { Icon(Icons.Rounded.Lock, null) },
                            trailing = { TextButton(onClick = { showPass2 = !showPass2 }) { Text(if (showPass2) "Ocultar" else "Mostrar") } },
                            visualTransformation = if (showPass2) VisualTransformation.None else PasswordVisualTransformation(),
                            imeAction = ImeAction.Done,
                            onImeAction = { focus.clearFocus() },
                            isError = ui.confirmError != null,
                            supportingText = ui.confirmError
                        )

                        Spacer(Modifier.height(6.dp))
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Checkbox(checked = ui.acceptedTerms, onCheckedChange = viewModel::onTermsToggle, enabled = !ui.isSubmitting)
                            Text("Aceito os termos de uso e privacidade", modifier = Modifier.padding(start = 8.dp))
                        }

                        Spacer(Modifier.height(8.dp))
                        GradientButton(
                            text = if (ui.isSubmitting) "Criando..." else "Criar conta",
                            enabled = ui.formValid && !ui.isSubmitting,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            viewModel.submit(onSuccess = onRegistered)
                        }

                        if (ui.submitError != null) {
                            Spacer(Modifier.height(8.dp))
                            AssistChip(onClick = { /* noop */ }, label = { Text(ui.submitError) })
                        }

                        Spacer(Modifier.height(8.dp))
                        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                            Text("Já tem conta? ")
                            Text("Entrar", color = Color(0xFF123FA0), fontWeight = FontWeight.SemiBold, modifier = Modifier.clickable { onLoginClick() })
                        }
                    }
                }

                Spacer(Modifier.weight(1f))
            }
        }
    }
}
=======
>>>>>>> 369c7e1d1b7980f55bac5ef8ca21f212f1e83c4b
