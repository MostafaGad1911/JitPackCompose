package mostafagad.projects.jitPackCompose.data.usecases

import mostafagad.projects.jitPackCompose.data.connection.Apis
import mostafagad.projects.jitPackCompose.data.entities.Repository
import javax.inject.Inject

class RepoUseCases @Inject constructor(private val  apis: Apis) {

    suspend fun getMyRepos(userName:String):ArrayList<Repository> = apis.getMyRepo(userName = userName)
}