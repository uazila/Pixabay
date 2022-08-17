package kg.example.pixabay

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import kg.example.pixabay.databinding.ItemImageBinding
import kg.example.pixabay.model.ImageModel

class ImageAdapter :
    RecyclerView.Adapter<ImageAdapter.ImageViewHolder>() {
    private val list = ArrayList<ImageModel>()

    class ImageViewHolder(private val binding: ItemImageBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(model: ImageModel) {
            binding.pixabayImage.load(model.largeImageURL)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val binding = ItemImageBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ImageViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        holder.onBind((list[position]))
    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun addList(model: ArrayList<ImageModel>) {
        list.addAll(model)
        notifyDataSetChanged()
    }

    fun clear() {
        list.clear()
        notifyDataSetChanged()
    }
}