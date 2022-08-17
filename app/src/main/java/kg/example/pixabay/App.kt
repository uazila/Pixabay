package kg.example.pixabay

import android.app.Application
import kg.example.pixabay.network.PixabayApi

class App : Application() {

    companion object {
        lateinit var api: PixabayApi
    }

    override fun onCreate() {
        super.onCreate()
        val retrofitService = RetrofitService()
        api = retrofitService.getApi()
    }

}