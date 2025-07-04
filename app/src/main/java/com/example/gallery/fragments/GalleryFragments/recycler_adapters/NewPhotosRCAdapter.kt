package com.example.gallery.fragments.GalleryFragments.recycler_adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.AdapterView.OnItemClickListener
import androidx.core.view.marginEnd
import androidx.core.view.marginTop
import androidx.core.view.setPadding
import androidx.navigation.NavController
import androidx.recyclerview.widget.RecyclerView
import com.example.gallery.R
import com.example.gallery.databinding.PhotoItemBinding
import com.example.gallery.fragments.GalleryFragments.recycler_adapters.NewPhotosRCAdapter.NewPhotosViewHolder

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

    class NewPhotosViewHolder(private val binding: PhotoItemBinding, private val listener: MyOnItemClickListener) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: PhotoItem){
            binding.imagePhotoItem.setImageResource(item.image)
            binding.root.setOnClickListener {
                listener.onItemClick(item)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewPhotosViewHolder {
        val binding = PhotoItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NewPhotosViewHolder(binding, listener)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onBindViewHolder(holder: NewPhotosViewHolder, position: Int) {
        holder.bind(itemList[position])
    }

}
