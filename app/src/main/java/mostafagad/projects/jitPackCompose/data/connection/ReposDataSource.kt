package mostafagad.projects.jitPackCompose.data.connection

import javax.inject.Inject

class ReposDataSource @Inject constructor(private val apis: Apis) {

    suspend fun getRepos(userName:String) = apis.getMyRepo(userName = userName)
}