package kg.example.pixabay

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import kg.example.pixabay.databinding.ActivityMainBinding
import kg.example.pixabay.model.ImageModel
import kg.example.pixabay.model.PixabayModel
import retrofit2.Call
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var page: Int = 1
    private var imageAdapter = ImageAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initClicer()
    }

    private fun initClicer() {
        binding.requestBtn.setOnClickListener {
            doRequest(page++)
        }
        binding.recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (!recyclerView.canScrollVertically(1)){
                    try {
                        doRequest(page++)
                    } catch (e: Exception){
                        e.printStackTrace()
                    }
                }
            }

        })
    }

    private fun doRequest(page: Int) {
        App.api.getImagesByWord(keyWord = binding.keyWordEd.text.toString(), page = page)
            .enqueue(object : retrofit2.Callback<PixabayModel> {
                override fun onResponse(
                    call: Call<PixabayModel>,
                    response: Response<PixabayModel>
                ) {
                    response.body()?.hits?.let {
                        imageAdapter = ImageAdapter()
                        imageAdapter.addList(it as ArrayList<ImageModel>)
                    }
                    binding.recyclerView.adapter = imageAdapter
                    Log.e("Фото", "onResponse:${response.body()?.hits!![0].largeImageURL}")
                }

                override fun onFailure(call: Call<PixabayModel>, t: Throwable) {
                    Log.e("Фото", "onFailure:${t.message}")
                }
            })
    }


}