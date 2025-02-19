package com.example.tamueats.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.tamueats.R
import com.example.tamueats.databinding.FragmentCategoriesBinding
import com.example.tamueats.databinding.FragmentFavouritesBinding


class CategoriesFragment : Fragment() {
    private lateinit var binding: FragmentCategoriesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentCategoriesBinding.inflate(inflater,container,false)
        return  binding.root
    }
}