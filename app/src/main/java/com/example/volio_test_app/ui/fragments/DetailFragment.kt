package com.example.volio_test_app.ui.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.volio_test_app.R
import com.example.volio_test_app.databinding.FragmentDetailBinding
import com.example.volio_test_app.model.Content
import com.example.volio_test_app.ui.DetailViewModel
import com.example.volio_test_app.ui.MainActivity
import com.example.volio_test_app.ui.NewsViewModel
import kotlinx.android.synthetic.main.fragment_detail.*


class DetailFragment : Fragment() {
    lateinit var detailViewModel: DetailViewModel

    // View Binding
    private lateinit var binding: FragmentDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailBinding.inflate(layoutInflater)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        detailViewModel = (activity as MainActivity).detailViewModel!!
        detailViewModel.detailLiveData.observe(viewLifecycleOwner) {
            binding.title.text = it.title
            binding.publishedDate.text = it.published_date
            binding.description.text = it.description
            Glide.with(this).load(it.sections[0].content.preview_image.href).into(image_detail)
        }
        detailViewModel.fetchDetail()
    }
}