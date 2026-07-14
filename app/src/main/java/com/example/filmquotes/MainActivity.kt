package com.example.filmquotes

import android.os.Build.VERSION.SDK_INT
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.ImageLoader
import coil.compose.AsyncImage
import coil.decode.GifDecoder
import coil.decode.ImageDecoderDecoder
import coil.request.ImageRequest
import com.example.filmquotes.ui.theme.FilmQuotesTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FilmQuotesTheme {
                Surface (
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    FilmQuoteApp()
                }
            }
        }
    }
}
@Composable
fun GifFromUrl(url: String, modifier: Modifier = Modifier) {
    val context = LocalContext.current
    val imageLoader = ImageLoader.Builder(context)
        .components {
            if (SDK_INT >= 28) {
                add(ImageDecoderDecoder.Factory())
            } else {
                add(GifDecoder.Factory())
            }
        }
        .build()
    AsyncImage(
        model = ImageRequest.Builder(context)
            .data(url)
            .crossfade(true)
            .build(),
        imageLoader = imageLoader,
        contentDescription = "Animated GIF from URL",
        modifier = modifier.fillMaxWidth().height(250.dp),
        contentScale = ContentScale.Fit
    )
}

@Composable
fun FilmQuoteApp(modifier: Modifier = Modifier) {
    Surface(
        color = Color.Cyan,
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier.fillMaxSize().padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            GifFromUrl(
                url = "https://media1.giphy.com/media/v1.Y2lkPTc5MGI3NjExOG1teHJ4c3U5YmF0cmZvMWd2NzJnYm9zMmQ3dWZqMDNudzB6emRwOCZlcD12MV9pbnRlcm5hbF9naWZfYnlfaWQmY3Q9Zw/QEw27HkigUT5wtqMeX/giphy.gif",
            )
            QuoteText(
                quote = "Hasta la vista, baby!"
            )
            FilmText(
                film = "Terminator 2: Judgement Day"
            )
            Button(onClick = {
                println("Button was clicked!")
            }) {
                Text(
                    text = stringResource(R.string.button_label)
                )
            }
            Image(
                painter = painterResource(id = R.drawable.t2_logo),
                contentDescription = "Terminator logo",
                modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp),
                contentScale = ContentScale.Fit
            )
        }
    }
}
@Composable
fun QuoteText(quote: String, modifier: Modifier = Modifier) {
            Text(
                text = quote,
                fontSize = 64.sp,
                lineHeight = 88.sp,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                fontStyle = FontStyle.Italic,
                modifier = modifier.fillMaxWidth()
            )
        }



@Composable
fun FilmText(film: String, modifier: Modifier = Modifier) {
    Text(
        text = film,
        fontSize = 38.sp,
        lineHeight = 60.sp,
        color = Color.Gray,
        textAlign = TextAlign.End,
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)

    )
}



@Preview(showBackground = true)
@Composable
fun FilmQuotePreview() {
    FilmQuotesTheme {
        FilmQuoteApp()
    }
}