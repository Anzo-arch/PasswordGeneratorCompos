import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application

@Composable
@Preview
fun App() {
    var length by remember { mutableStateOf("12") }
    var includeSymbols by remember { mutableStateOf(true) }
    var includeNumbers by remember { mutableStateOf(true) }
    var darkTheme by remember { mutableStateOf(false) }
    var password by remember { mutableStateOf("") }

    val clipboardManager = LocalClipboardManager.current

    fun generatePassword(): String {
        val letters = ('a'..'z') + ('A'..'Z')
        val numbers = ('0'..'9')
        val symbols = listOf('!', '@', '#', '$', '%', '^', '&', '*', '(', ')', '-', '_', '+', '=')

        val charPool = mutableListOf<Char>().apply {
            addAll(letters)
            if (includeNumbers) addAll(numbers)
            if (includeSymbols) addAll(symbols)
        }

        if (charPool.isEmpty()) return ""

        val lengthInt = length.toIntOrNull() ?: 12

        val passwordChars = mutableListOf<Char>()
        val usedChars = mutableSetOf<Char>()

        while (passwordChars.size < lengthInt && usedChars.size < charPool.size) {
            val nextChar = charPool.random()
            if (usedChars.add(nextChar)) {
                passwordChars.add(nextChar)
            }
        }

        while (passwordChars.size < lengthInt) {
            passwordChars.add(charPool.random())
        }

        return passwordChars.joinToString("")
    }

    val themeColors = if (darkTheme) darkColors() else lightColors()

    MaterialTheme(colors = themeColors) {
        Column(modifier = Modifier.padding(16.dp).fillMaxWidth()) {
            Text("Gerador de Senhas", style = MaterialTheme.typography.h5)
            Spacer(modifier = Modifier.height(16.dp))

            Row(verticalAlignment = Alignment.CenterVertically) {
                Text("🌙 Modo escuro")
                Spacer(Modifier.width(8.dp))
                Switch(checked = darkTheme, onCheckedChange = { darkTheme = it })
            }

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = length,
                onValueChange = { if (it.all(Char::isDigit)) length = it },
                label = { Text("Tamanho da senha") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.fillMaxWidth()
            )

            Row {
                Checkbox(checked = includeSymbols, onCheckedChange = { includeSymbols = it })
                Text("Incluir símbolos")
            }

            Row {
                Checkbox(checked = includeNumbers, onCheckedChange = { includeNumbers = it })
                Text("Incluir números")
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(onClick = {
                password = generatePassword()
                clipboardManager.setText(androidx.compose.ui.text.AnnotatedString(password))
            }) {
                Text("Gerar e copiar senha")
            }

            Spacer(modifier = Modifier.height(16.dp))
            Text("Senha gerada:")
            Text(password, style = MaterialTheme.typography.h6)
        }
    }
}

fun main() = application {
    Window(onCloseRequest = ::exitApplication, title = "Gerador de Senhas") {
        App()
    }
}
