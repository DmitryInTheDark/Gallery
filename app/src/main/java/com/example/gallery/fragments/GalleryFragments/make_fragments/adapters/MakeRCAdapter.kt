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

    class MakeViewHolder(
        private val binding: PhotoItemMakeBinding,
        private val listener: MyOnItemClickListener
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: PhotoItem) {
                binding.imagePhotoItem.setImageResource(item.image)
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

}