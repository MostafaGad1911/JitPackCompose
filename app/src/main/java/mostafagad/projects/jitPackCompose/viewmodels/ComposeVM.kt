package mostafagad.projects.jitPackCompose.viewmodels

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
    private val myReposState = MutableStateFlow(emptyList<Repository>())
    val myReposValue: StateFlow<List<Repository>> get() = myReposState

    suspend fun getMyRepos(userName: String) = viewModelScope.launch(job + Dispatchers.IO) {
        myReposState.emit(repository.getMyRepos(userName = userName))
    }


    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }
}