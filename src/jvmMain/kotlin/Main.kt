
import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
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
import com.google.gson.Gson
import kotlinx.coroutines.runBlocking
import model.Filme
import model.Top250Data
import model.Top250DataDetail
import org.jetbrains.skia.Image
import java.net.URL

@ExperimentalFoundationApi
fun main() = application {
    val filmes = remember { retornaListaFilmesApi() }
    Window(onCloseRequest = ::exitApplication, title = "IMDB") {
        App(filmes)
    }
}

@ExperimentalFoundationApi
@Composable
@Preview
fun App(filmes: Top250Data) {
    MaterialTheme(colors = darkColors()) {
        Surface(modifier = Modifier.fillMaxSize()) {
            Box(
                modifier = Modifier.wrapContentSize()
                    .scrollable(state = rememberScrollState(), orientation = Orientation.Vertical)
                    .padding(5.dp)
            ) {
                LazyVerticalGrid(cells = GridCells.Adaptive(250.dp)) {
                    items(filmes.items) { filme ->
                        MovieItem(filme)
                    }
                }
            }
        }
    }
}

private fun retornaListaFilmesApi(): Top250Data {
    return runBlocking {
        val urlApi = "https://imdb-api.com/en/API/Top250Movies/k_zy4q2zi4"
        val data = URL(urlApi).readText()
        Gson().fromJson(data, Top250Data::class.java)
    }
}

@Composable
fun MovieItem(items: Top250DataDetail) {
    val imageStar = "https://cdn-icons-png.flaticon.com/512/686/686384.png"
    Column {
        Image(
            bitmap = items.image.loadImageBitmap(),
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
                items.imDbRating.toString(),
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(end = 130.dp)
            )
            Text(items.year.toString(), fontSize = 18.sp)
        }
        Text(
            text = items.title,
            fontSize = 22.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier.align(Alignment.CenterHorizontally).padding(5.dp).width(240.dp)
        )
    }
}

fun String.loadImageBitmap(): ImageBitmap {
    val byteArray = URL(this).openStream().use { it.readBytes() }
    return Image.makeFromEncoded(byteArray).toComposeImageBitmap()
}

private fun retornaListaFilmes(): List<Filme> {
    return listOf(
        Filme(
            "Á Espera de Um Milagre",
            "https://bankshop.com.br/wp-content/uploads/2021/10/2020-1344-a-espera-milagre-poster.jpg",
            8.6,
            1999
        ),
        Filme(
            "Batman: O Cavaleiro das Trevas",
            "https://m.media-amazon.com/images/M/MV5BMTMxNTMwODM0NF5BMl5BanBnXkFtZTcwODAyMTk2Mw@@._V1_.jpg",
            9.0,
            2008
        ),
        Filme(
            "O Poderoso Chefão",
            "https://4.bp.blogspot.com/-ZzTyCFCEqCI/V7MVqZSKcwI/AAAAAAAAZhs/6VEtIF1eE8Ud6MREyqWPQbaWWPJLP-TKQCLcB/s1600/the-godfather-poster.jpeg",
            9.2,
            1972
        )
    )
}