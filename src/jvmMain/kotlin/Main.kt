import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.toComposeImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import org.jetbrains.skia.Image
import java.net.URL

@Composable
@Preview
fun App() {
    val imageCapa = "https://bankshop.com.br/wp-content/uploads/2021/10/2020-1344-a-espera-milagre-poster.jpg"
    val imageStar = "https://cdn-icons-png.flaticon.com/512/686/686384.png"

    MaterialTheme(colors = darkColors()) {
        Surface(modifier = Modifier.fillMaxSize()) {
            Box(modifier = Modifier.wrapContentSize()) {
                Column(modifier = Modifier.align(Alignment.Center).matchParentSize()) {
                    Image(
                        bitmap = imageCapa.loadImageBitmap(),
                        null,
                        modifier = Modifier.size(350.dp).padding(5.dp),
                        contentScale = ContentScale.Fit
                    )
                    Row(modifier = Modifier.width(240.dp).align(Alignment.CenterHorizontally).padding(5.dp)) {
                        Row(modifier = Modifier.align(Alignment.CenterVertically)) {
                            Icon(
                                bitmap = imageStar.loadImageBitmap(),
                                null,
                                modifier = Modifier.height(20.dp).padding(end = 10.dp),
                                tint = Color.Unspecified
                            )
                        }
                        Text(
                            "8.6",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(end = 130.dp)
                        )
                        Text("1999", fontSize = 18.sp)
                    }
                    Text(
                        text = "Á Espera de um Milagre",
                        fontSize = 22.sp,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.align(Alignment.CenterHorizontally).padding(5.dp).width(240.dp)
                    )
                }
            }
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
    return Image.makeFromEncoded(byteArray).toComposeImageBitmap()
}