package mostafagad.projects.jitPackCompose.data.connection

import javax.inject.Inject

class GitHubRepository @Inject constructor(private val reposDataSource: ReposDataSource) {
    suspend fun getMyRepos(userName:String) = reposDataSource.getRepos(userName = userName)
}