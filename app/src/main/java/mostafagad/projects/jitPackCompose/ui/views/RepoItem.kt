package mostafagad.projects.jitPackCompose.ui.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import mostafagad.projects.jitPackCompose.data.entities.Repository
import mostafagad.projects.compose.R

@Composable
fun RepoItem(repo: Repository) {
    Box(contentAlignment = Alignment.Center) {
        Column {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(90.dp)
                    .border(
                        1.dp,
                        Color.LightGray,
                        shape = RoundedCornerShape(8.dp),
                    )
            ) {
                Image(
                    painter = rememberAsyncImagePainter(R.drawable.ic_github),
                    modifier = Modifier
                        .padding(15.dp)
                        .height(90.dp)
                        .width(90.dp),
                    contentDescription = stringResource(
                        R.string.github
                    ),
                    contentScale = ContentScale.Inside
                )
                Text(
                    text = repo.name,
                    modifier = Modifier
                        .width(0.dp)
                        .weight(1f)
                        .padding(end = 20.dp , top = 4.dp),
                    maxLines = 1
                )
                Row(
                    modifier = Modifier.padding(4.dp),
                    horizontalArrangement = Arrangement.End,
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Text(
                        text = repo.forks_count.toString(),
                    )

                    Image(
                        painter = painterResource(R.drawable.ic_forked),
                        contentDescription = stringResource(
                            R.string.fork
                        ),
                        contentScale = ContentScale.Inside
                    )


                }
                Row(
                    modifier = Modifier.padding(4.dp),
                    horizontalArrangement = Arrangement.End,
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Text(
                        text = repo.watchers_count.toString(),
                    )

                    Image(
                        painter = painterResource(R.drawable.ic_star_yellow),
                        contentDescription = stringResource(
                            R.string.fork
                        ),
                        contentScale = ContentScale.Inside
                    )


                }

            }
        }
    }
}
