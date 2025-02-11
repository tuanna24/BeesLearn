package fpl.md07.beeslearn.screens.podcast

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import fpl.md07.beeslearn.R
import fpl.md07.beeslearn.components.BackComponent
import fpl.md07.beeslearn.models.Podcast
import fpl.md07.beeslearn.ui.theme.Nunito_Bold
import fpl.md07.beeslearn.viewmodels.PodcastViewModel

@Composable
fun PodcastListScreen(
    navController: NavController,
    podcastViewModel: PodcastViewModel = viewModel()
) {
    val podcasts by podcastViewModel.podcasts
    val loading by podcastViewModel.loading
    val error by podcastViewModel.error

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(start = 10.dp, end = 10.dp, top = 20.dp, bottom = 20.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start
    ) {
        BackComponent(navController)
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(start = 10.dp, end = 10.dp, top = 60.dp, bottom = 20.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start
    ) {
        Box(
            modifier = Modifier
                .padding(16.dp, bottom = 10.dp)
                .size(width = 276.dp, height = 151.dp)
                .shadow(24.dp, RoundedCornerShape(6.dp))
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
                        .padding(start = 10.dp)
                        .weight(1f)
                ) {
                    Text(
                        text = "Podcast",
                        style = MaterialTheme.typography.bodyLarge.copy(
                            fontWeight = FontWeight.Bold,
                            fontFamily = Nunito_Bold,
                            color = Color(0xFF591429), // Màu nâu chữ
                            fontSize = 20.sp
                        )
                    )
                    Text(
                        text = "Cách tuyệt vời để\nthực hành tự học\n",
                        style = MaterialTheme.typography.bodyMedium.copy(
                            color = Color(0xFF591429),
                            fontFamily = Nunito_Bold,
                            fontSize = 14.sp
                        )
                    )
                }

                Image(
                    painter = painterResource(R.drawable.headphone),
                    contentDescription = "Bee with headphones",
                    modifier = Modifier
                        .size(140.dp),
                    contentScale = ContentScale.Crop
                )
            }
        }
        // Podcast List
        when {
            loading -> {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
            }

            error != null -> {
                Text(
                    text = "Error: $error",
                    color = MaterialTheme.colorScheme.error,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
            }

            else -> {
                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(15.dp),
                    modifier = Modifier.fillMaxHeight()
                ) {
                    items(podcasts) { podcast ->
                        PodcastItem(podcast, navController)
                    }
                }
            }
        }
    }
}

@Composable
fun PodcastItem(podcast: Podcast, navController: NavController) {
//    val podcastId = podcast.id
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable {
                navController.navigate("podcastDetail/${podcast.title.hashCode()}")
            },
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Use Coil for loading images from URLs
        AsyncImage(
            model = podcast.image_url,
            contentDescription = null,
            modifier = Modifier
                .size(80.dp)
                .clip(RoundedCornerShape(8.dp)),
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.width(16.dp))

        Column(
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = podcast.title,
                color = colorResource(id = R.color.secondary_color),
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                fontFamily = Nunito_Bold,
                maxLines = 1
            )
            Text(
                text = podcast.description,
                color = colorResource(id = R.color.secondary2_color),
                fontSize = 14.sp,
                fontFamily = Nunito_Bold,
                maxLines = 2
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 0.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = podcast.duration,
                    fontWeight = FontWeight.W300,
                    fontFamily = Nunito_Bold,
                    fontSize = 12.sp
                )
                Text(
                    text = "${podcast.views} views",
                    fontWeight = FontWeight.W300,
                    fontSize = 12.sp,
                    fontFamily = Nunito_Bold,
                    modifier = Modifier
                        .padding(end = 50.dp)
                )
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewPodcastScreen() {
    var navController = rememberNavController()
    PodcastListScreen(navController)
}