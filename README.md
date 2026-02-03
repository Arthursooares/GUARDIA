````md
# 🛡️ Guardiã

Aplicativo **Android** desenvolvido com **Jetpack Compose**, focado em **acolhimento, orientação e registro de situações de risco**.  
O projeto foi criado como prática de **Android moderno**, **arquitetura de aplicações**, **integração com APIs** e **persistência local**, utilizando uma assistente conversacional chamada **Guardiã**.

A aplicação simula um fluxo completo de uso real, com navegação estruturada por telas, interação via chat e geração de relatórios armazenados localmente.

---

## ✨ Principais funcionalidades

- **Onboarding e autenticação simulada**, com telas de splash, login e cadastro.
- **Tela Home** com acesso rápido às principais funcionalidades do aplicativo.
- **Chat da Guardiã**, com comunicação via **Retrofit/OkHttp** para um endpoint remoto e indicador de digitação.
- **Relatórios locais**, persistidos com **Room**, com opção de exportação e compartilhamento em **PDF**.
- **Perfil, configurações, segurança e feedback**, com fluxos de navegação dedicados.
- **Dicas e cuidados**, apresentadas em telas temáticas de orientação ao usuário.

---

## 🧱 Tecnologias e bibliotecas

- **Android + Kotlin**
- **Jetpack Compose** (Material 3)
- **Navigation Compose**
- **Retrofit + OkHttp**
- **Room**
- **Kotlin Coroutines**
- **Accompanist Pager**

---

## 🗂️ Estrutura do projeto

```text
app/src/main/java/com/example/guardia
├── data/relatorios      # Room (Entity, DAO, Database, Repository)
├── network              # Retrofit, providers e API do chat
├── screens              # Telas em Compose + navegação
├── ui/theme             # Tema, cores e tipografia
└── MainActivity.kt      # Ponto de entrada da aplicação
````

A estrutura do projeto segue princípios de **separação de responsabilidades**, facilitando manutenção, testes e evolução do código.

---

## 🔌 Integração de chat

A tela **Guardiã** envia mensagens para um backend via **HTTP** utilizando **Retrofit**.
O endpoint de chat é configurado em `RetrofitProvider.kt` e utiliza o caminho `chat1`.

> Para apontar para outro backend (ex: **n8n**), atualize a constante `N8N_BASE_URL`.

Essa abordagem permite substituir ou evoluir o serviço de chat sem impacto direto na interface.

---

## 🧪 Persistência de relatórios (Room)

Os relatórios gerados durante as interações são armazenados localmente no banco **`relatorios_db`**, utilizando **Room**.

Na tela **Meus Relatórios**, o usuário pode:

* Visualizar registros anteriores
* Consultar detalhes
* Exportar ou compartilhar relatórios em **PDF**

---

## 👤 Autoria

Projeto desenvolvido por **Arthur Soares**, com foco em aprendizado prático de **Android moderno**, **arquitetura de aplicações**, **integração de sistemas** e **persistência local de dados**.

---

## ✅ Requisitos

* **Android Studio Hedgehog** (ou superior)
* **JDK 17**
* **Gradle 8+** (via wrapper)
* **Dispositivo ou emulador Android** com **SDK 24+**

---

## ▶️ Como executar

1. Clone o repositório.
2. Abra o projeto no **Android Studio**.
3. Aguarde a sincronização do Gradle.
4. Selecione um dispositivo ou emulador.
5. Execute com **Run ▶** ou via terminal:

```bash
./gradlew :app:installDebug
```

---

## 🧪 Testes

```bash
./gradlew test
```

---

## 📦 Build APK (debug)

```bash
./gradlew :app:assembleDebug
```

---

## 🔐 Observações de segurança

* A URL do backend de chat está definida diretamente para ambiente de desenvolvimento. Para produção, recomenda-se o uso de **BuildConfig** ou variáveis de ambiente.
* O Room utiliza `fallbackToDestructiveMigration()`, o que pode apagar dados locais em mudanças de schema durante o desenvolvimento.

---

## 📄 Licença

Este projeto é distribuído sob a **Licença MIT**.  
Você pode utilizar, modificar e distribuir este software livremente, desde que mantenha o aviso de copyright.

Consulte o arquivo [`LICENSE`](./LICENSE) para mais detalhes.

