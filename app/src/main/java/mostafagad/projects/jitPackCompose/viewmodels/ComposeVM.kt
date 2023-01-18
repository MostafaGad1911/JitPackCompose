package mostafagad.projects.jitPackCompose.viewmodels

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import mostafagad.projects.jitPackCompose.data.connection.GitHubRepository
import mostafagad.projects.jitPackCompose.data.entities.Repository
import javax.inject.Inject

@HiltViewModel
class ComposeVM @Inject internal constructor(private val repository: GitHubRepository) : ViewModel() {
    private val job = Job()
    val myReposState = SnapshotStateList<Repository>()

    suspend fun getMyRepos(userName: String) = viewModelScope.launch(job + Dispatchers.IO) {
        val repos = repository.getMyRepos(userName = userName)
        myReposState.addAll(repos)
    }


    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }
}