package ng.mathemandy.remote

import ng.mathemandy.remote.model.BaseRemoteModel
import retrofit2.http.GET

interface ApiService {
    @GET("grade")
    suspend fun fetchHomeData(): BaseRemoteModel
}
