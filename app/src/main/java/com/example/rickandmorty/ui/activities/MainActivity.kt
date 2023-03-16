package com.example.rickandmorty.ui.activities

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.rickandmorty.R
import com.example.rickandmorty.databinding.ActivityMainBinding
import com.example.rickandmorty.extensions.loadByCoil
import com.example.rickandmorty.ui.activities.arch.SharedViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    private val sharedViewModel: SharedViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        sharedViewModel.characterByIdLiveData.observe(this) { character ->

            character ?: return@observe

            binding.apply {
                nameTextView.text = character.name
                aliveTextView.text = character.status
                speciesTextView.text = character.species
                originTextView.text = character.origin.name
                character.image.let { imageUrl ->
                    this.headerImageView.loadByCoil(imageUrl)
                }

                val imageType = genderImageType(character.gender)
                genderImageView.setImageResource(imageType)

            }
        }
    }

    private fun genderImageType(gender: String?): Int {

        return if (gender.equals("male", true)) {
            R.drawable.ic_male_24
        } else {
            R.drawable.ic_female_24
        }
    }

}