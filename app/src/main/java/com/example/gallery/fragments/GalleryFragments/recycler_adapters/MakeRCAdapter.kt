package com.example.gallery.fragments.GalleryFragments.recycler_adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.gallery.R
import com.example.gallery.databinding.PhotoItemBinding
import com.example.gallery.fragments.GalleryFragments.recycler_adapters.MakeRCAdapter.MakeViewHolder
import com.example.gallery.fragments.GalleryFragments.make_fragments.MakePhotoItem
import kotlinx.coroutines.currentCoroutineContext


//Тут я вспомнил, как делать через интерфейс
class MakeRCAdapter(private val listener: MyOnItemClickListener) :
    RecyclerView.Adapter<MakeViewHolder>() {

    var count = 0

    val itemList = listOf(
        MakePhotoItem(R.drawable.test_photo), MakePhotoItem(R.drawable.test_photo2),
        MakePhotoItem(R.drawable.test_photo), MakePhotoItem(R.drawable.test_photo2),
        MakePhotoItem(R.drawable.test_photo), MakePhotoItem(R.drawable.test_photo2),
        MakePhotoItem(R.drawable.test_photo), MakePhotoItem(R.drawable.test_photo2),
        MakePhotoItem(R.drawable.test_photo), MakePhotoItem(R.drawable.test_photo2),
        MakePhotoItem(R.drawable.test_photo), MakePhotoItem(R.drawable.test_photo2),
        MakePhotoItem(R.drawable.test_photo), MakePhotoItem(R.drawable.test_photo2),
        MakePhotoItem(R.drawable.test_photo), MakePhotoItem(R.drawable.test_photo2),
        MakePhotoItem(R.drawable.test_photo), MakePhotoItem(R.drawable.test_photo2),
        MakePhotoItem(R.drawable.test_photo), MakePhotoItem(R.drawable.test_photo2),
    )

    class MakeViewHolder(
        private val binding: PhotoItemBinding,
        private val listener: MyOnItemClickListener
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: MakePhotoItem) {
                binding.imagePhotoItem.setImageResource(item.imageID)
                binding.root.setOnClickListener { root ->
                    listener.onItemClick(item)
                }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MakeViewHolder {
        val binding = PhotoItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MakeViewHolder(binding, listener)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onBindViewHolder(holder: MakeViewHolder, position: Int) {
        holder.bind(itemList[position])
    }

}