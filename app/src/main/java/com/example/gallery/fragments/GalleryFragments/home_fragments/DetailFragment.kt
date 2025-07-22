package com.example.gallery.fragments.GalleryFragments.home_fragments

import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.Toolbar.OnMenuItemClickListener
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.domain.models.MyResult
import com.example.domain.use_case.GetPhotoByIDUseCase
import com.example.gallery.databinding.DetailsFragmentBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@AndroidEntryPoint
class DetailFragment : Fragment() {

    private lateinit var binding: DetailsFragmentBinding
    private var isEdit = false

    @Inject
    lateinit var getPhotoByIDUseCase: GetPhotoByIDUseCase

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DetailsFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val navController = findNavController()

        binding.materialToolbar5.setupWithNavController(navController)
        binding.materialToolbar5.title = null

        val args = arguments

        val photoID = args?.getString("id")


        if (photoID == null){
            Toast.makeText(requireContext(), "Файл не найден", Toast.LENGTH_SHORT).show()
            navController.navigateUp()
        }else{
            CoroutineScope(Dispatchers.IO).launch {
                val result = getPhotoByIDUseCase.execute(photoID)
                when(result){
                    is MyResult.Success -> {
                        val bitmap = BitmapFactory.decodeStream(result.data.inputStream)
                        withContext(Dispatchers.Main){
                            binding.imageView6.setImageBitmap(bitmap)
                            binding.textImageName.text = result.data.name
                            binding.textUsername.text = result.data.author
                            binding.editTextImageName.setText(result.data.name)
                            binding.editTextImageDescription.setText(result.data.description)
                            binding.textImageDescription.text = result.data.description
                            binding.textImageCreatedData.text = result.data.dateCreate
                            binding.radioButtonNew.isChecked = result.data.isNew
                            binding.radioButtonPopular.isChecked = result.data.isPopular
                        }
                    }
                    is MyResult.Error -> {
                        withContext(Dispatchers.Main){
                            Toast.makeText(requireContext(), "Ошибка получения данных", Toast.LENGTH_SHORT).show()
                            navController.navigateUp()
                        }
                    }
                }
            }
        }






         binding.buttonConfirmDetailsChanges.setOnClickListener{
             binding.materialToolbar5.menu.add("Edit")
             binding.scrollDetailLayout.visibility = View.VISIBLE
             binding.editLayout.visibility = View.GONE
             binding.materialToolbar5.title = null
             binding.imageCardView.setPadding(0, 0, 0, 0)
         }

        binding.materialToolbar5.setOnMenuItemClickListener(object : OnMenuItemClickListener{
            override fun onMenuItemClick(item: MenuItem?): Boolean {
                    binding.scrollDetailLayout.visibility = View.GONE
                    binding.editLayout.visibility = View.VISIBLE
                    binding.materialToolbar5.title = "Edit Photo"
                    binding.materialToolbar5.menu.clear()

                return true
            }
        })
    }
}