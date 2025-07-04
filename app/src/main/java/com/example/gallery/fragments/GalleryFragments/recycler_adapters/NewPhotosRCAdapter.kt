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
class NewPhotosRCAdapter(private val navController: NavController): RecyclerView.Adapter<NewPhotosViewHolder>()  {

    var count = 0

    class NewPhotosViewHolder(private val binding: PhotoItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(count: Int, click: () -> Unit){
            if (count%2 != 0) {
                binding.imagePhotoItem.setImageResource(R.drawable.test_photo)
            }else{
                binding.imagePhotoItem.setImageResource(R.drawable.test_photo2)
            }
            binding.root.setOnClickListener {
                click()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewPhotosViewHolder {
        val binding = PhotoItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NewPhotosViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return 20
    }

    override fun onBindViewHolder(holder: NewPhotosViewHolder, position: Int) {
        count++
        holder.bind(count, { navController.navigate(R.id.action_homeFragment_to_detailFragment) })
    }

}
