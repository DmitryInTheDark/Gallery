package com.example.gallery.fragments.GalleryFragments.home_fragments.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.gallery.fragments.GalleryFragments.MyOnItemClickListener
import com.example.gallery.R
import com.example.gallery.databinding.PhotoItemNewAndPopularBinding
import com.example.gallery.fragments.GalleryFragments.make_fragments.adapters.PhotoItem
import com.example.gallery.fragments.GalleryFragments.make_fragments.adapters.PhotoItemType
import com.example.gallery.fragments.GalleryFragments.home_fragments.adapters.NewPhotosRCAdapter.NewPhotosViewHolder

//Я уже забыл, как клики на элементы делаются через интерфейс, поэтому когда логику буду прописывать,
//добавлю это, а пока что пусть контроллер будет в адаптере(да, со мной будет трудно работать)
class NewPhotosRCAdapter(private val listener: MyOnItemClickListener): RecyclerView.Adapter<NewPhotosViewHolder>()  {



    val itemList = listOf(
        PhotoItem(R.drawable.test_photo, "test_photo1", "очень длинное описание", PhotoItemType.New),
        PhotoItem(R.drawable.test_photo2, "test_photo2", "очень длинное описание2", PhotoItemType.Popular),
        PhotoItem(R.drawable.test_photo, "test_photo1", "очень длинное описание", PhotoItemType.New),
        PhotoItem(R.drawable.test_photo2, "test_photo2", "очень длинное описание2", PhotoItemType.Popular),
        PhotoItem(R.drawable.test_photo, "test_photo1", "очень длинное описание", PhotoItemType.New),
        PhotoItem(R.drawable.test_photo2, "test_photo2", "очень длинное описание2", PhotoItemType.Popular),
        PhotoItem(R.drawable.test_photo, "test_photo1", "очень длинное описание", PhotoItemType.New),
        PhotoItem(R.drawable.test_photo2, "test_photo2", "очень длинное описание2", PhotoItemType.Popular),
        PhotoItem(R.drawable.test_photo, "test_photo1", "очень длинное описание", PhotoItemType.New),
        PhotoItem(R.drawable.test_photo2, "test_photo2", "очень длинное описание2", PhotoItemType.Popular),
        PhotoItem(R.drawable.test_photo, "test_photo1", "очень длинное описание", PhotoItemType.New),
        PhotoItem(R.drawable.test_photo2, "test_photo2", "очень длинное описание2", PhotoItemType.Popular),
    )

    class NewPhotosViewHolder(private val binding: PhotoItemNewAndPopularBinding, private val listener: MyOnItemClickListener) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: PhotoItem){
            binding.imagePhotoItem.setImageResource(item.image)
            binding.root.setOnClickListener {
                listener.onItemClick(item)
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

}
