package com.example.gallery.fragments.GalleryFragments.make_fragments.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.gallery.R
import com.example.gallery.databinding.PhotoItemMakeBinding
import com.example.gallery.fragments.GalleryFragments.make_fragments.adapters.MakeRCAdapter.MakeViewHolder
import com.example.gallery.fragments.GalleryFragments.MyOnItemClickListener


//Тут я вспомнил, как делать через интерфейс
class MakeRCAdapter(private val listener: MyOnItemClickListener) :
    RecyclerView.Adapter<MakeViewHolder>() {

    var count = 0

    val itemList = mutableListOf<PhotoItem>()

    class MakeViewHolder(
        private val binding: PhotoItemMakeBinding,
        private val listener: MyOnItemClickListener
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: PhotoItem) {
                binding.imagePhotoItem.setImageBitmap(item.image)
                binding.root.setOnClickListener {
                    listener.onItemClick(item)
                }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MakeViewHolder {
        val binding = PhotoItemMakeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MakeViewHolder(binding, listener)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onBindViewHolder(holder: MakeViewHolder, position: Int) {
        holder.bind(itemList[position])
    }

    fun addPhotos(photos: List<PhotoItem>){
        itemList.addAll(photos)
        notifyDataSetChanged()
    }

}