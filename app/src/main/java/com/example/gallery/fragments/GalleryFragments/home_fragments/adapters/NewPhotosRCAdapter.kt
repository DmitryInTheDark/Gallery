package com.example.gallery.fragments.GalleryFragments.home_fragments.adapters

import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.models.PhotoModel
import com.example.gallery.databinding.PhotoItemNewAndPopularBinding
import com.example.gallery.fragments.GalleryFragments.MyOnItemClickListener
import com.example.gallery.fragments.GalleryFragments.home_fragments.adapters.NewPhotosRCAdapter.NewPhotosViewHolder

class NewPhotosRCAdapter(private val listener: HomeOnClickListener): RecyclerView.Adapter<NewPhotosViewHolder>()  {



    val itemList = mutableListOf<PhotoModelWithBitmap>()

    class NewPhotosViewHolder(private val binding: PhotoItemNewAndPopularBinding, private val listener: HomeOnClickListener) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: PhotoModelWithBitmap){
            binding.imagePhotoItem.setImageBitmap(item.image)
            binding.root.setOnClickListener {
                listener.onClick(item)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewPhotosViewHolder {
        val binding = PhotoItemNewAndPopularBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NewPhotosViewHolder(binding, listener)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onBindViewHolder(holder: NewPhotosViewHolder, position: Int) {
        holder.bind(itemList[position])
    }


    fun updatePhotoList(newList: List<PhotoModel>){
        newList.forEach {
            val bitmap = BitmapFactory.decodeStream(it.image)
            if(bitmap != null){
                val newPhotoModel = PhotoModelWithBitmap(
                    it.id.toString(),
                    it.path,
                    bitmap,
                    it.isNew,
                    it.isPopular
                )
                itemList.add(newPhotoModel)
            }
        }
        notifyDataSetChanged()
    }
}
