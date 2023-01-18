package mostafagad.projects.jitPackCompose.data.connection

import mostafagad.projects.jitPackCompose.data.entities.Repository
import retrofit2.http.GET
import retrofit2.http.Path

interface Apis {

    @GET("users/{user-name}/repos")
    suspend fun getMyRepo(@Path("user-name") userName:String):ArrayList<Repository>
}