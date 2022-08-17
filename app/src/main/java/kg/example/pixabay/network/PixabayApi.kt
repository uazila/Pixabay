package kg.example.pixabay.network

import kg.example.pixabay.model.PixabayModel
import retrofit2.http.GET
import retrofit2.http.Query

interface PixabayApi {

    @GET("api/")
    fun getImagesByWord(
        @Query("key") key: String = "25007027-7418deb977c638792f4bfb99f",
        @Query("q") keyWord: String,
        @Query("page") page: Int,
        @Query("per_page") perPage: Int = 9
    ): retrofit2.Call<PixabayModel>
}