package com.example.gallery.fragments.GalleryFragments.recycler_adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.gallery.R
import com.example.gallery.databinding.PhotoItemProfileBinding
import com.example.gallery.fragments.GalleryFragments.recycler_adapters.ProfileRCAdapter.ProfileViewHolder

class ProfileRCAdapter: RecyclerView.Adapter<ProfileViewHolder>() {

    var count = 0

    class ProfileViewHolder(private val binding: PhotoItemProfileBinding):RecyclerView.ViewHolder(binding.root) {
        fun bind(count: Int){
            if (count%2 != 0) {
                binding.imagePhotoItem.setImageResource(R.drawable.test_photo)
            }else{
                binding.imagePhotoItem.setImageResource(R.drawable.test_photo2)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProfileViewHolder {
        val binding = PhotoItemProfileBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProfileViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return 20
    }

    override fun onBindViewHolder(holder: ProfileViewHolder, position: Int) {
        count++
        holder.bind(count, )
    }
}