package fpl.md07.beeslearn.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import fpl.md07.beeslearn.R
import fpl.md07.beeslearn.models.Movie
import fpl.md07.beeslearn.viewmodels.data.movieList

@Composable
fun MovieScreen() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(start = 10.dp, end = 10.dp, top = 50.dp, bottom = 20.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start
    ) {
        Image(
            painter = painterResource(R.drawable.back_left),
            contentDescription = "Back",
            modifier = Modifier
                .padding(16.dp)
                .clickable { /* Handle back navigation */ }
        )
        Box(
            modifier = Modifier
                .padding(16.dp, bottom = 10.dp)
                .size(width = 276.dp, height = 151.dp)
                .shadow(18.dp, RoundedCornerShape(6.dp))
                .background(Color(0xFFFFF176))
                .align(Alignment.CenterHorizontally)
        ) {
            Row(
                modifier = Modifier.fillMaxSize(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Column(
                    modifier = Modifier
                        .padding(10.dp)
                        .weight(1f)
                ) {
                    Text(
                        text = "Podcast",
                        style = MaterialTheme.typography.bodyLarge.copy(
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF591429), // Màu nâu chữ
                            fontSize = 20.sp
                        )
                    )
                    Text(
                        text = "excellent way for\nstudents to practice\nself-study",
                        style = MaterialTheme.typography.bodyMedium.copy(
                            color = Color(0xFF591429),
                            fontSize = 14.sp
                        )
                    )
                }

                Image(
                    painter = painterResource(R.drawable.beetv),
                    contentDescription = "Bee with headphones",
                    modifier = Modifier
                        .width(117.dp)
                        .height(72.dp),
                    contentScale = ContentScale.Crop
                )
            }
        }
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier.padding(8.dp),
            contentPadding = PaddingValues(8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(movieList.size) { index ->
                MovieItem(movie = movieList[index])
            }
        }
    }
}

@Composable
fun MovieItem(movie: Movie) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .background(Color.White, RoundedCornerShape(8.dp)),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            modifier = Modifier
                .width(200.dp)
                .height(250.dp)
                .clip(shape = RoundedCornerShape(8.dp))
                .clickable { },
            painter = painterResource(id = movie.imageResMovie),
            contentDescription = null,
            contentScale = ContentScale.Crop // Thay đổi sang Crop nếu bạn muốn cắt ảnh
        )
        Text(
            text = movie.titleMovie,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .padding(8.dp),
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreViewMovieScreen() {
    MovieScreen()
}