package kg.example.pixabay.model

import android.media.Image

data class PixabayModel(
    val total: Int,
    val totalHits: Int,
    val hits: List<ImageModel>
)

data class ImageModel(
    val largeImageURL: String
)
