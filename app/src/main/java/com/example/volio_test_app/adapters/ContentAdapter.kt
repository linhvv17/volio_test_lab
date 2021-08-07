package com.example.volio_test_app.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.volio_test_app.R
import com.example.volio_test_app.model.Content
import kotlinx.android.synthetic.main.item_newfeed.view.*

class ContentAdapter : RecyclerView.Adapter<ContentAdapter.ContentViewHolder>() {

    inner class ContentViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)

    private val differCallback = object : DiffUtil.ItemCallback<Content>() {
        override fun areItemsTheSame(oldItem: Content, newItem: Content): Boolean {
            return oldItem.title == newItem.title
        }

        override fun areContentsTheSame(oldItem: Content, newItem: Content): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContentViewHolder {
        return ContentViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_newfeed,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    private var onItemClickListener: ((Content) -> Unit)? = null

    override fun onBindViewHolder(holder: ContentViewHolder, position: Int) {
        val content = differ.currentList[position]
        holder.itemView.apply {
            if (content.images!=null){
                Glide.with(this).load(content.images[0].href).into(image_1)
                Glide.with(this).load(content.images[1].href).into(image_2)
            }
            tvTitle.text = content.title
            tvPublished_date.text = content.published_date
            setOnClickListener {
                onItemClickListener?.let { it(content) }
            }
        }
    }

    fun setOnItemClickListener(listener: (Content) -> Unit) {
        onItemClickListener = listener
    }
}













