package mostafagad.projects.jitPackCompose.ui.activities

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberAsyncImagePainter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import mostafagad.projects.compose.R
import mostafagad.projects.jitPackCompose.data.entities.Repository
import mostafagad.projects.jitPackCompose.ui.theme.ComposeFirstTheme
import mostafagad.projects.jitPackCompose.viewmodels.ComposeVM

@AndroidEntryPoint
class GitHubRepos : ComponentActivity() {

    private val composeVM: ComposeVM by viewModels()

    private val userName: String? by lazy {
        intent.getStringExtra("user-name")
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeFirstTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(10.dp), color = Color.White
                ) {
                    ReposRV()
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
    }

    private fun loadMyRepos() {
        CoroutineScope(Dispatchers.IO).launch {
            composeVM.getMyRepos(userName = userName!!)
        }
    }




    @Composable
    private fun ReposRV() {
        val repos by composeVM.myReposValue.collectAsState()
        loadMyRepos()

        LazyColumn (verticalArrangement = Arrangement.spacedBy(5.dp)){
            items(items = repos, itemContent = {repo ->
                Log.i("TEST_ITEM", repo.toString())
                RepoItem(repo = repo)
            })
        }
    }


    @Composable
    private fun RepoItem(repo: Repository) {
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
                            .padding(end = 20.dp),
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
}