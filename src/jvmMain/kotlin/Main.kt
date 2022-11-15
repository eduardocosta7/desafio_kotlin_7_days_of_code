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
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.toComposeImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import org.jetbrains.skia.Image.Companion.makeFromEncoded
import java.net.URL

@Composable
@Preview
fun App() {
    val image = "https://bankshop.com.br/wp-content/uploads/2021/10/2020-1344-a-espera-milagre-poster.jpg"

    MaterialTheme {
        Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
            Text(text = "Á Espera de um Milagre", fontSize = 22.sp)
            Image(
                bitmap = image.loadImageBitmap(),
                "Capa",
                modifier = Modifier.size(350.dp),
                contentScale = ContentScale.Fit,
                alignment = Alignment.CenterStart
            )
            Text("Nota: 8.6 - Ano: 1999", fontSize = 18.sp)
        }
    }
}

fun main() = application {
    Window(onCloseRequest = ::exitApplication, title = "IMDB") {
        App()
    }
}

fun String.loadImageBitmap(): ImageBitmap {
    val byteArray = URL(this).openStream().use { it.readBytes() }
    return makeFromEncoded(byteArray).toComposeImageBitmap()
}