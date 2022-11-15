import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application

@Composable
@Preview
fun App() {
    val modifierImage = Modifier.size(350.dp)
    val contentImage = ContentScale.Fit

    MaterialTheme {
        Column (modifier = Modifier.verticalScroll(rememberScrollState())) {
            Text(text = "Á Espera de um Milagre", fontSize = 22.sp)
            Image(painterResource("drawables/capa.jpeg"), "Capa", modifier =  modifierImage, contentScale =  contentImage, alignment = Alignment.CenterStart)
            Text("Nota: 8.6 - Ano: 1999", fontSize = 18.sp)
        }
    }
}

fun main() = application {
    Window(onCloseRequest = ::exitApplication) {
        App()
    }
}
