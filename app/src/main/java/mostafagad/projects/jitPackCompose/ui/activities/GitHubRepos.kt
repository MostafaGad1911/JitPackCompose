package mostafagad.projects.jitPackCompose.ui.activities

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import mostafagad.projects.jitPackCompose.data.models.MenuItem
import mostafagad.projects.jitPackCompose.ui.theme.ComposeFirstTheme
import mostafagad.projects.jitPackCompose.ui.views.*
import mostafagad.projects.jitPackCompose.viewmodels.ComposeVM

@AndroidEntryPoint
class GitHubRepos : ComponentActivity() {

    private val composeVM: ComposeVM by viewModels()
    private val firebaseAuth: FirebaseAuth by lazy { Firebase.auth }

    private val userName: String? by lazy {
        intent.getStringExtra("user-name")
    }
    private val avatar: String? by lazy {
        intent.getStringExtra("user-avatar")
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


    @SuppressLint("UnusedMaterialScaffoldPaddingParameter")
    @Composable
    private fun ReposRV() {

        val scaffoldState = rememberScaffoldState()
        val scope = rememberCoroutineScope()
        Scaffold(
            scaffoldState = scaffoldState,
            topBar = {
                AppBar(
                    onNavigationIconClick = {
                        scope.launch {
                            scaffoldState.drawerState.open()
                        }
                    }
                )
            },
            drawerGesturesEnabled = true,
            drawerBackgroundColor = Color.White,
            drawerContent = {
                DrawerHeader(userName = userName.toString(), avatar = avatar.toString())
                DrawerBody(
                    items = listOf(
                        MenuItem(
                            id = "logout",
                            title = "Logout",
                            contentDescription = "Logout github",
                            icon = mostafagad.projects.compose.R.drawable.logout
                        )
                    ),
                    onItemClick = {
                        when (it.id) {
                            "logout" -> {
                                firebaseAuth.signOut()
                                startActivity(Intent(this@GitHubRepos, Login::class.java))
                            }
                        }
                    }
                )
            }
        ) {
            val repos by composeVM.myReposValue.collectAsState()
            loadMyRepos()
            var isLoading by remember {
                mutableStateOf(true)
            }
            LaunchedEffect(key1 = true) {
                delay(10000)
                isLoading = repos.isEmpty()
            }

            Column {
                Spacer(modifier = Modifier.height(15.dp))
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
    }


}