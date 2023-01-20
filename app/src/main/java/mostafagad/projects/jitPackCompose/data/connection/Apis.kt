package mostafagad.projects.jitPackCompose.data.connection

import mostafagad.projects.jitPackCompose.data.entities.Repository
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface Apis {

    @GET("users/{user-name}/repos")
    suspend fun getMyRepo(
        @Path("user-name") userName: String,
        @Query("per_page") page: Int? = 100,
        @Query("type") type:String = "private" ,
        @Query("sort") sort:String = "updated"
    ): ArrayList<Repository>
}