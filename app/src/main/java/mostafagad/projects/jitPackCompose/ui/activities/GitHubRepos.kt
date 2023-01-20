package mostafagad.projects.jitPackCompose.ui.activities

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import mostafagad.projects.jitPackCompose.ui.theme.ComposeFirstTheme
import mostafagad.projects.jitPackCompose.ui.views.RepoItem
import mostafagad.projects.jitPackCompose.ui.views.ShimmerRepo
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

    private fun loadMyRepos() {
        CoroutineScope(Dispatchers.IO).launch {
            composeVM.getMyRepos(userName = userName!!)
        }
    }


    @Composable
    private fun ReposRV() {
        val repos by composeVM.myReposValue.collectAsState()
        loadMyRepos()
        var isLoading by remember {
            mutableStateOf(true)
        }
        LaunchedEffect(key1 = true) {
            delay(10000)
            isLoading = repos.isEmpty()
        }

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(5.dp)
        ) {
            items(items = repos, itemContent = { repo ->
                Log.i("TEST_ITEM", repo.toString())
                ShimmerRepo(isLoading = isLoading, contentAfterLoading = {
                    RepoItem(repo = repo)
                })
            })
        }
    }


}