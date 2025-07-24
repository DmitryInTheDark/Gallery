package com.example.gallery.fragments.GalleryFragments.make_fragments

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.gallery.databinding.MakeFragmentBinding
import com.example.gallery.fragments.GalleryFragments.MyOnItemClickListener
import com.example.gallery.fragments.GalleryFragments.make_fragments.adapters.MakeRCAdapter
import com.example.gallery.fragments.GalleryFragments.make_fragments.adapters.PhotoItem
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MakeFragment : Fragment(), MyOnItemClickListener {

    private lateinit var binding: MakeFragmentBinding

    private val makeViewModel: MakeViewModel by viewModels()
    private lateinit var adapter: MakeRCAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = MakeFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val navController = findNavController()

        adapter = MakeRCAdapter(this)
        binding.makeRCView.adapter = adapter
        binding.makeRCView.layoutManager = GridLayoutManager(requireContext(), 4)
        binding.materialToolbar.title = "All Photos"

        makeViewModel.photoList.observe(viewLifecycleOwner){
            adapter.addPhotos(it)
        }

        val bitmap = makeViewModel.photoList.value?.get(0)?.image
        if (bitmap != null) binding.imageMake.setImageBitmap(bitmap)

        binding.floatingActionButton.setOnClickListener{
            if (ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED){
                ActivityCompat.requestPermissions(requireActivity(), arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE), 1)
            }else{
                val intent = Intent(Intent.ACTION_PICK)
            }


        }
    }

    override fun onItemClick(item: PhotoItem) {
        binding.imageMake.setImageBitmap(item.image)
    }
}